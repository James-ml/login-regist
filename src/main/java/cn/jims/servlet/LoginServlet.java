package cn.jims.servlet;

import cn.itcast.commons.CommonUtils;
import cn.jims.entity.User;
import cn.jims.service.UserService;
import exception.UserException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jims on 2017/1/18.
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //依赖UserService
        UserService userService = new UserService();
        //封装表单数据
        User form = CommonUtils.toBean(req.getParameterMap(),User.class);
        try {
            if ((form.getUsername()).equals("")) {
                throw new UserException("登录信息不能为空！");
            }
            String vCode1 = (String)req.getSession().getAttribute("session_code");
            if (!form.getVerifyCode().equalsIgnoreCase(vCode1)){
                throw new UserException("验证码错误！");
            }
                //调用业务方法
                User user = userService.login(form);
                //把user存入session中（session中保存数据可以用重定向,也可以用转发）
                //首页判断该session中的数据是否存在，从而显示登录信息
                req.getSession().setAttribute("sessionUser", user);

                //req.getContextPath()代表项目名
                resp.sendRedirect("/jsp/index.jsp");
                //req.getRequestDispatcher("/jsp/index.jsp").forward(req,resp);


        }catch (UserException e){
            //输出异常信息
            req.setAttribute("msg",e.getMessage());
            req.setAttribute("user",form);//回显表单信息
            //转发到登录页面
            req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
        }
    }
}
