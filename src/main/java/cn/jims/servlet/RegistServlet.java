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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jims on 2017/1/17.
 */
public class RegistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");//请求编码
        resp.setContentType("text/html;charset=utf-8");//响应编码

        UserService userService = new UserService();

        //封装注册表单数据
        User form = CommonUtils.toBean(req.getParameterMap(), User.class);
        System.out.println(form);

        /*
        表单校验
        1、创建一个map，用来装载所有的变淡错误信息
        在校验过程中，如果失败，向map添加错误信息，其中key为表单字段名称
        2、校验之后，查看map长度是否大于0，如果大于0，说明有错误信息
        保存map到request中，保存form到request中，转发到regist中
        3、如果map为空，说明没有错误信息，向下执行
         */
        Map<String, String> errors = new HashMap<>();

        String username = form.getUsername();//获取表单的用户名
        if (username == null || username.trim().isEmpty()) {
            errors.put("username", "用户名不能为空！");
        } else if (username.length() < 3 || username.length() > 15) {
            errors.put("username", "用户名长度必须在3-15之间");
        }

        String password = form.getPassword();
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", "密码名不能为空！");
        } else if (password.length() < 3 || password.length() > 15) {
            errors.put("password", "密码长度必须在3-15之间");
        }

        String rePassword = form.getRePassword();
        if (rePassword == null || rePassword.trim().isEmpty()) {
            errors.put("rePassword", "确认密码不能为空！");
        } else if (!rePassword.equals(password)) {
            errors.put("rePassword", "两次输入密码必须相同");
        }

        String Code = (String) req.getSession().getAttribute("session_code");//图片文本的验证码
        //req.getSession().removeAttribute("rCode");
        if (!Code.equalsIgnoreCase(form.getVerifyCode())) {
            errors.put("verifyCode", "验证码错误！");
        }

        /*
        判断map是否为空，不为空，说明存在错误
         */
        if (errors != null && errors.size() > 0) {
            //保存errors到request域中,用于显示错误信息
            req.setAttribute("errors", errors);
            //保存form信息到request域中，为了回显
            req.setAttribute("user", form);
            //转发到regist.jsp
            req.getRequestDispatcher("/jsp/regist.jsp").forward(req, resp);
            //在方法中使用，该处是结束方法的执行（还有一个是返回方法指定类型的值）
            return;
        }


        try {
            userService.regist(form);
            resp.getWriter().print("<h1>注册成功！</h1><a href='" +
                    req.getContextPath() +
                    "/jsp/login.jsp" + "'>点击这里去登录</a>");
        } catch (UserException e) {
            //获取异常信息
            req.setAttribute("msg", e.getMessage());
            //保存表单数据，到request域
            req.setAttribute("user", form);//用来表单中回显（回显用户名）
            //转发到regist.jsp
            req.getRequestDispatcher("/jsp/regist.jsp").forward(req, resp);
        }


//        User user = new User();
//        //BeanUtils.populate(user, req.getParameterMap());
//
//        String registCode = req.getParameter("rCode");
//        String rePassword = req.getParameter("rePassword");
//
//        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
//            req.setAttribute("msg", "用户名不能为空");
//            req.setAttribute("user", user);
//            req.getRequestDispatcher("/regist.jsp").forward(req, resp);
//            return;
//        }
//        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
//            req.setAttribute("msg", "密码不能为空!");
//            req.setAttribute("user", user);
//            req.getRequestDispatcher("/regist.jsp").forward(req, resp);
//            return;
//        }
//        if (registCode == null || registCode.trim().isEmpty()) {
//            req.setAttribute("msg", "验证码不能为空！");
//            req.setAttribute("user", user);
//            req.getRequestDispatcher("/regist.jsp").forward(req, resp);
//            return;
//        }

        /*
        验证码校验
         */
        /*String Code = (String)req.getSession().getAttribute("session_code");
        //req.getSession().removeAttribute("rCode");
        if (!Code.equalsIgnoreCase(form.getVerifyCode())){
            req.setAttribute("mag","验证码错误！");
            //保存表单数据，用于回显
            req.setAttribute("user",form);
            req.getRequestDispatcher("/jsp/regist.jsp").forward(req,resp);
            return;
        }*/
    }
}
