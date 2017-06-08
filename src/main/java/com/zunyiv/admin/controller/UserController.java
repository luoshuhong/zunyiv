package com.zunyiv.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.zunyiv.admin.model.User;
import com.zunyiv.admin.service.UserService;
import com.zunyiv.admin.service.WeiboRecordService;
import com.zunyiv.common.MD5Util;
import com.zunyiv.common.RequestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/3.
 */
@Controller
@RequestMapping("/admin")
public class UserController {
    private static Log log = LogFactory.getLog(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private WeiboRecordService weiboRecordService;

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


    @RequestMapping("/userQuery")
    @ResponseBody
    public String query(HttpServletRequest request, HttpServletResponse response){
        try {
            List<User> list = this.userService.query();
            return RequestUtils.successReturn(JSONArray.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestUtils.failReturn("fail");
    }


    // 微信访问补全小尾巴 初始化
    @RequestMapping("/user/weixin/fillWeiBoTailInit")
    public String fillWeiTail(HttpServletRequest request, HttpServletResponse response, Model model){
        try {
            // 获取所有微博小尾巴
            Set<String> weiboTail = this.weiboRecordService.queryWeiBoTail();

            // 获取已经认领了的微博小尾巴
            Set<String> userTail = this.userService.queryAllWeiBoTail();

            for (String usedTail : userTail) {
                if (weiboTail.contains(usedTail)) {
                    weiboTail.remove(usedTail);
                }
            }

            model.addAttribute("weiboTail", weiboTail);
            return "userFillWeiboTail";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userFillWeiboTail";
    }

    // 微信访问补全小尾巴 初始化
    @RequestMapping("/user/weixin/fillWeiBoTailSave")
    public String fillSave(HttpServletRequest request, HttpServletResponse response, Model model){
        try {
            request.setCharacterEncoding("UTF-8");//传值编码
            response.setContentType("text/html;charset=UTF-8");//设置传输编码搜索
            String phone = request.getParameter("phone");
            String tail = request.getParameter("tail");
            String realName = request.getParameter("realName");
            String birthday = request.getParameter("birthday");
            Integer professional = Integer.valueOf(request.getParameter("professional"));

            User user = new User(phone, 1, MD5Util.md5(phone));
            user.setWeiboTail(tail);
            user.setRealName(realName);
            user.setProfessional(professional);
            user.setBirthday(birthday);
            System.out.println("phone:" + phone + ",tail:" + tail + "，realName:" + realName);

            if (null != userService.query(phone)) {
                this.userService.updateUser(user);
                model.addAttribute("resultMsg", "更新成功 ^_^！");
            } else {
                this.userService.addUser(user);
                model.addAttribute("resultMsg", "添加成功 ^_^！");
            }

            model.addAttribute("phone", phone);
            model.addAttribute("realName", realName);
            return "result";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("resultMsg", "系统异常，请重试！");
            return "userFillWeiboTail";
        }
    }

}
