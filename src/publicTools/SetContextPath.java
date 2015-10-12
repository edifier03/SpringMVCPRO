package publicTools;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetContextPath extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        service(req, resp);
    }
     
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        service(req, resp);
    }
     
    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        String contentPath = request.getContextPath();
        String realPath = request.getRealPath("/");
 
        //TODO:  调用你的类设置 如                                               
        //Sysinfo.setContentPath(contentPath);
        //Sysinfo.setRealPath(realPath);
 
        super.service(request, response);
    }
     
}