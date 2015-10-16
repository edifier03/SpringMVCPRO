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
     * 在业务处理器处理请求之前被调用  
     * 如果返回false  
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     * 如果返回true  
     *    执行下一个拦截器,直到所有的拦截器都执行完毕  
     *    再执行被拦截的Controller  
     *    然后进入拦截器链,  
     *    从最后一个拦截器往回执行所有的postHandle()  
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()  
     */    
	//进行权限校验
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
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */  
	@Override    
    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {    
	        if(modelAndView != null){  //加入当前时间    
	            modelAndView.addObject("var", "测试postHandle");    
	        }    
	}
	 /**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
     *   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */    
    @Override    
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)    
            throws Exception {    
    	
    }
}
