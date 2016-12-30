package com.zunyiv.admin.controller;

import com.zunyiv.admin.model.User;
import com.zunyiv.admin.service.UserService;
import com.zunyiv.common.MD5Util;
import com.zunyiv.common.RequestUtils;
import com.zunyiv.common.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;

    @RequestMapping("/userAdd")
    @ResponseBody
    public String addUser(HttpServletRequest request, HttpServletResponse response){
        try {
            String phone = request.getParameter("phone");
            int role = Integer.valueOf(request.getParameter("role"));

            if (null != userService.query(phone)) {
                return RequestUtils.successReturn("exist");
            }
            userService.addUser(new User(phone, role, MD5Util.md5(phone)));
            return RequestUtils.successReturn("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestUtils.failReturn("fail");
    }

}
