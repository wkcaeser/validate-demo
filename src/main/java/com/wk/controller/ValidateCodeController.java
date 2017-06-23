package com.wk.controller;

import com.google.gson.JsonObject;
import com.wk.services.validateCode.ValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
        session.setAttribute("code", vCode.getCode().toLowerCase());
        //验证图片返回前端
        response.getWriter().write(imageB64);
        return null;
    }

    /**
     * 检查验证码是否正确
     * @return {status:SUCCESS/ERROR}
     */
    @RequestMapping(value="/checkValidateCode", method = RequestMethod.GET)
    public String checkValidateCode(HttpServletRequest request, HttpServletResponse response){
        //获取session中存储的当前validateCode
        String code = (String)request.getSession().getAttribute("code");
        String validateCodeText = request.getParameter("validateCodeText");
        //检查验证码是否正确
        String answer = "ERROR";
        if(validateCodeText != null && code != null){
            if(validateCodeText.toLowerCase().equals(code)) {
                answer = "SUCCESS";
            }
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", answer);
        try {
//            System.out.println(code);
//            System.out.println(validateCodeText);
//            System.out.println(jsonObject);
            response.getWriter().write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
