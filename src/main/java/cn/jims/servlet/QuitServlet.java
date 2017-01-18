package cn.jims.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jims on 2017/1/18.
 */
public class QuitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使session失效
        req.getSession().invalidate();
        resp.sendRedirect("/jsp/index.jsp");
    }
}
