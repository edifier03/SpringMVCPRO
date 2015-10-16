package publicTools;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.test.bean.TestBean;
@Resource
public class AccessRight extends HandlerInterceptorAdapter{
	private  SqlSessionFactory sqlSessionFactory;
	
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	/**  
     * ��ҵ��������������֮ǰ������  
     * �������false  
     *     �ӵ�ǰ������������ִ��������������afterCompletion(),���˳��������� 
     * �������true  
     *    ִ����һ��������,ֱ�����е���������ִ�����  
     *    ��ִ�б����ص�Controller  
     *    Ȼ�������������,  
     *    �����һ������������ִ�����е�postHandle()  
     *    �����ٴ����һ������������ִ�����е�afterCompletion()  
     */    
	//����Ȩ��У��
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        if ("GET".equalsIgnoreCase(request.getMethod())) {  
//	            RequestUtil.saveRequest();  
	        }  
	       
	        String requestUri = request.getRequestURI();  
	        String contextPath = request.getContextPath();  
	        String url = requestUri.substring(contextPath.length());  

	        String username =  (String)request.getSession().getAttribute("user"); 
	        
	        List<TestBean> beanlist = sqlSessionFactory.openSession().selectList("com.mvc.test.selectList", "1");
	        System.out.println(beanlist.size());
	        
	        if(username == null){  
	        	request.getSession().setAttribute("user", "test");
	            request.getRequestDispatcher("/WEB-INF/view/noLogin/welcome.jsp").forward(request, response);  
	            return false;  
	        }else  
	            return true;     
	}
	/** 
     * ��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ�еĶ���    
     * ����modelAndView�м������ݣ����統ǰʱ�� 
     */  
	@Override    
    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {    
	        if(modelAndView != null){  //���뵱ǰʱ��    
	            modelAndView.addObject("var", "����postHandle");    
	        }    
	}
	 /**  
     * ��DispatcherServlet��ȫ����������󱻵���,������������Դ��   
     *   
     * �����������׳��쳣ʱ,��ӵ�ǰ����������ִ�����е���������afterCompletion()  
     */    
    @Override    
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)    
            throws Exception {    
    	
    }
}
