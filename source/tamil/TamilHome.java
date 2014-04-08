package tamil;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.util.*;
public class TamilHome extends HttpServlet
{
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, java.io.IOException
  {

    resp.setContentType("text/html;charset=UTF-8");
    req.setCharacterEncoding("utf-8");
    String input = req.getParameter("search_inp");
    StringBuffer sb = new StringBuffer();
    try
    {
      AlageetuVaipadu.getVaipadu(input,sb);
      resp.getOutputStream().write(sb.toString().getBytes());
    }
    catch(Exception ex)
    {
      String s = "[\"Failure\" , \""+ex.getMessage()+"\"]";
      resp.getOutputStream().write(s.getBytes());
    }
  }
}
