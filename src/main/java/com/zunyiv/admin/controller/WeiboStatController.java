package com.zunyiv.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.zunyiv.admin.model.WeiboRecord;
import com.zunyiv.admin.service.WeiboRecordService;
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
 * 前端页面展示相关
 * Created by luoshuhong on 2016/12/27.
 */
@Controller
@RequestMapping("/admin/weibostat")
public class WeiboStatController {
    @Autowired
    private WeiboRecordService weiboRecordService;

    @RequestMapping("/query")
    @ResponseBody
    public String query(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            List<WeiboRecord> list = new ArrayList<>();
            String sDate = request.getParameter("startDate");
            String eDate = request.getParameter("endDate");
            String value = request.getParameter("value");
            if (!StringUtils.isEmpty(value)) {
                list = weiboRecordService.query(value);
            } else {
                //默认查询一周的数据
                if (StringUtils.isEmpty(sDate) || StringUtils.isEmpty(eDate)) {
                    eDate = DateUtils.getNextDay(new Date(), "1", DateUtils.PATTERN_YYYYMMDD);
                    sDate = DateUtils.getNextDay(new Date(), "-7", DateUtils.PATTERN_YYYYMMDD);
                }
                list = weiboRecordService.query(sDate, eDate);
            }
            model.addAttribute("data", list);
            return RequestUtils.successReturn(JSONArray.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
            return RequestUtils.failReturn("fail");
        }
    }

    @RequestMapping("/stat")
    @ResponseBody
    public String stat(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            List<WeiboRecord> list = new ArrayList<>();
            String sDate = request.getParameter("startDate");
            String eDate = request.getParameter("endDate");

            //默认查询一周的数据
            if (StringUtils.isEmpty(sDate) || StringUtils.isEmpty(eDate)) {
                eDate = DateUtils.getNextDay(new Date(), "1", DateUtils.PATTERN_YYYYMMDD);
                sDate = DateUtils.getNextDay(new Date(), "-7", DateUtils.PATTERN_YYYYMMDD);
            }
            list = weiboRecordService.stat(sDate, eDate);
            return RequestUtils.successReturn(JSONArray.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
            return RequestUtils.failReturn("fail");
        }
    }


}
