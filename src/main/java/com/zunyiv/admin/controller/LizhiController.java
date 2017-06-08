package com.zunyiv.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.zunyiv.admin.model.LizhiRecord;
import com.zunyiv.admin.model.WeiboRecord;
import com.zunyiv.admin.service.LizhiRecordService;
import com.zunyiv.common.DateUtils;
import com.zunyiv.common.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */
@Controller
@RequestMapping("/admin/lizhi")
public class LizhiController {

    @Autowired
    private LizhiRecordService lizhiRecordService;

    @RequestMapping("/query")
    @ResponseBody
    public String query(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            List<LizhiRecord> list = new ArrayList<>();
//            String sDate = request.getParameter("startDate");
//            String eDate = request.getParameter("endDate");
//            String keyWord = request.getParameter("value");
            int isPush = Integer.valueOf(request.getParameter("isPush"));

            list = this.lizhiRecordService.query(isPush);
            model.addAttribute("isPush", isPush);
            model.addAttribute("data", list);
            return RequestUtils.successReturn(JSONArray.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
            return RequestUtils.failReturn("fail");
        }

    }

    @RequestMapping("/push")
    @ResponseBody
    public String push(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            int id = Integer.valueOf(request.getParameter("id"));

            if (this.lizhiRecordService.push(id)) {
                return RequestUtils.successReturn("success");
            } else {
                return RequestUtils.successReturn("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RequestUtils.failReturn("fail");
        }

    }
}
