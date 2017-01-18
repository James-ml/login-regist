package cn.jims.servlet;

import cn.itcast.vcode.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Jims on 2017/1/17.
 */
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String name = req.getParameter("name");
        VerifyCode vc = new VerifyCode();//创建验证码类
        BufferedImage image = vc.getImage();//得到验证码图片
        //把图片保存到session中,并放到request域中
        req.getSession().setAttribute("session_code",vc.getText());//获取验证码文本
        System.out.println(vc.getText());
        VerifyCode.output(image,resp.getOutputStream());//输出图片到页面上
    }
}
