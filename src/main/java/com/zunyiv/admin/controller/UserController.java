package com.zunyiv.admin.controller;

import com.zunyiv.common.RequestUtils;
import com.zunyiv.common.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/12/3.
 */
@Controller
@RequestMapping("/admin")
public class UserController {
    private static Log log = LogFactory.getLog(UserController.class);

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        return RequestUtils.failReturn("error");
    }
}
