package com.wk.controller;

import com.wk.services.validateCode.ValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;

/**
 * Created by wk on 2017/6/20.
 */
@Controller
public class ValidateCodeController {
    /**
     * 响应验证码页面
     * @return null
     */
    @RequestMapping(value="/validateCode")
    public String validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("get request");
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession();

        //生成验证码
        ValidateCode vCode = new ValidateCode(120,40,5,100);
        //将图片流转化为图片
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        vCode.write(byteArrayOutputStream);
        byte[] imageBitArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        String imageB64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageBitArray);
        //验证码存入session
        session.setAttribute("code", vCode.getCode());
        //验证图片返回前端
        response.getWriter().write(imageB64);
        return null;
    }
}
