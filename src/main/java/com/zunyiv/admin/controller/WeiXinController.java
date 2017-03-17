package com.zunyiv.admin.controller;

import com.zunyiv.admin.model.wx.UserEntity;
import com.zunyiv.admin.model.wx.WeixinEntity;
import com.zunyiv.common.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/11/28.
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinController {
    private static Logger log = LoggerFactory.getLogger(WeiXinController.class);

    @RequestMapping("/wxredirect")
    public String redirect(HttpServletRequest request, HttpServletResponse response, Model model) {
        log.info("redirect");
        String code = request.getParameter("code");
        //获取访问者 信息
        WeixinEntity weixinEntity = WeiXinUtil.getWeiXinEntityByCode(code);
        String vOpenId = weixinEntity.getOpenId();
        String userId = "", channelCode = "", useDftProcedure = "";
//        UserInfo userInfo = userInfoDAO.getUserInfoByLogOnAccount(vOpenId);+

        UserEntity userEntity = new UserEntity(vOpenId);

//        if (null != userInfo) {
//            userId = userInfo.getUserId();
//            userInfoDAO.updateUserLogOnDate(userId);
//        } else {
//            //添加用户记录
//            userId = createUserInfo(userEntity);
//        }

        log.info("[wx redirect] openId=" + vOpenId + ", userId:" + userId);


        // 设置登陆session
//        SessionUtils.setSession(request, SessionUtils.USER_USERID, userId);
//        SessionUtils.setSession(request, SessionUtils.USER_LOGONACCOUNT, vOpenId);

        //跳转到具体业务类
        String url = "www.baidu.com";
//        String url =  SessionUtils.getSessionStr(request, SessionUtils.WX_REDIRECT_URL);

//        if (StringUtils.isEmpty(url)){
//            url = Constants.ROOT_PATH_NEW + "/dntm_java/transfer";
//        }

        log.info("[wx redirect] url=" + url);
        return "redirect:" + url;
    }

    // 创建用户
//    private String createUserInfo(UserEntity userInfo) {
//        return userInfoDAO.addUser(userInfo);
//    }

    /********************** 监听微信号发送信息 start****************************/
    /**
     * weixin 用户公众号 监听事件
     * @param request
     * @param response
     */
    @RequestMapping("/listen")
    @ResponseBody
    public void  loginRedirect(HttpServletRequest request, HttpServletResponse response) {
        //GET 是验证消息 直接返回
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            verify(request, response);  //验证
            return;
        }
        PrintWriter out = null;
        String logStr = "";
        String retStr = "success"; //写回数据
        String eventType = "third_wx_other";
        boolean putredisActive = true;
        String fromUserName = "";
        //下面处理具体消息事件
        try {
            out = response.getWriter();
            Document revDocument = XMLParse.wxRequest(request);
            String msgType = XMLParse.getString(revDocument,"MsgType");
            String toUserName = XMLParse.getString(revDocument,"ToUserName");
            fromUserName = XMLParse.getString(revDocument,"FromUserName"); //openId
//			logStr = "用户[" +fromUserName + "]，事件[" + Constants.WX_OTHER + "], 参数[openId:" + fromUserName + "]";
            StringBuffer returnSb = new StringBuffer();  //写回的数据
            if("text".equals(msgType)) {
                eventType = "third_wx_text";
                String content = XMLParse.getString(revDocument,"Content");
                String repleyStr = "";
                if (!StringUtils.isEmpty(content)) {
                    if (content.contains("#中奖查询")) {
                        repleyStr =  repleyStr + "<a href=\"http://dntm.happy6.com.cn:8090/bbs/forum.php?mod=viewthread&tid=14&extra=page%3D1\">【点击这里】查看“圈圈页匹配三个角色”活动中奖名单！</a>";
                        retStr = XMLParse.wrapText(fromUserName, toUserName, repleyStr);
                    }
                    //这里走客服消息回复
                    else {
                        repleyStr = dealRepley(content, fromUserName);
                        if (!StringUtils.isEmpty(repleyStr)) {
                            retStr = "success";
//                            returnKfMsg(repleyStr, fromUserName);  //以客服消息回复
                        }
                    }
                }
                if (StringUtils.isEmpty(repleyStr)) {
                    retStr = "success";
                }
                log.info("事件[third_wx_" + msgType + "], 参数[openId:" + fromUserName + "], content=" + content);
            }  else if("image".equals(msgType)) {  //图片
                retStr = "success";
                log.info("事件[third_wx_" + msgType + "], 参数[openId:" + fromUserName + "], content=" + XMLParse.getString(revDocument,"PicUrl"));
                eventType = "third_wx_image";
            } else if("voice".equals(msgType) || "video".equals(msgType) || "shortvideo".equals(msgType)) {  //语音 视频 小视频
                retStr = "success";
                log.info("事件[third_wx_" + msgType + "], 参数[openId:" + fromUserName + "], content=" + XMLParse.getString(revDocument,"MediaId"));
                eventType = "third_wx_" + msgType;
            } else if("event".equals(msgType)) {
                String event = XMLParse.getString(revDocument, "Event");
                eventType = "third_wx_event_" + event;
                if ("subscribe".equals(XMLParse.getString(revDocument, "Event"))) {
                    String eventKey = XMLParse.getString(revDocument, "EventKey");
                    String channelCode = "default";
                    if (!StringUtils.isEmpty(eventKey) && eventKey.startsWith("qrscene_")) {
                        channelCode = eventKey.substring("qrscene_".length());
                    }
//					returnSb.append("Life is too short to be someone else！\n\n");
//					returnSb.append("人生苦短，我TMD要自己去探索！\n\n");

//					returnSb.append("<a href=\"http://viewer.maka.im/k/2SNTK6IG\">* 点击爱情流量包，满满爱情故事，照单全收！</a>\n\n");
//					returnSb.append("<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?&appid=wx365bb713a5f70a70&redirect_uri=http://dntml.happy6.com.cn/member-center/member/indexAuth.do&response_type=code&scope=snsapi_base&state=123#wechat_redirect\">*点击个人中心，变身VIP，享受专属功能！</a>\n\n");


                    returnSb.append("Life is too short to be someone else！\n\n");

                    returnSb.append("最受欢迎：\n");
                    returnSb.append("<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7da410e7b5d045fe&redirect_uri=http://dntm.happy6.com.cn/wxif/transfer.aspx?action=993FF8D16-DD2C-42DD-9997-7B0007575A6C&response_type=code&scope=snsapi_base&state=1#wechat_redirect\">爱情故事测评</a>\n");
                    returnSb.append("-独特的双人匹配测评\n");
                    returnSb.append("-找你的爱情角色和故事\n");
                    returnSb.append("-获得帮助你和恋人、朋友更亲密的tips\n\n");

                    returnSb.append("最经典：\n");
                    returnSb.append("<a href=\"http://dntm.happy6.com.cn/ZYCP_Java/dntm_java/transfer\">我的幸福职业测评</a>\n");
                    returnSb.append("-服务世界500强的专业咨询公司出品\n");
                    returnSb.append("-趣味的可视化报告\n");
                    returnSb.append("-释放我的力量和潜力\n");
                    returnSb.append("-找到我的专属幸福职业！\n\n");

                    returnSb.append("需要补充能量干货？\n");
                    returnSb.append("<a href=\"http://mp.weixin.qq.com/mp/homepage?__biz=MzA4MjAxMTE2NQ==&hid=3&sn=b2907413f33218cfd831935ed9aff90f#wechat_redirect\">点我</a>\n");
//						returnSb.append("<a href=\"http://dntm.happy6.com.cn/bbs/plugin.php?id=hejin_box:index&model=list&fid=13\">点我</a>\n");
//						returnSb.append("<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?&appid=wx7da410e7b5d045fe&redirect_uri=http://dntm.happy6.com.cn/estimate-activity/register.auth&response_type=code&scope=snsapi_base&state=123#wechat_redirect\"> 参加See You Friday!线上活动，让爱你的人找到你</a>\n\n")

                    retStr = XMLParse.wrapText(fromUserName, toUserName, returnSb.toString());

                    //放置redis消息
//                    redisUtils.putSubscribeMsg(fromUserName, channelCode);
                    putredisActive = false;
                    logStr = "用户[third_wx_" +fromUserName + "]，事件[" + Constants.WX_SBUSCRIBE + "], 参数[openId:" + fromUserName + "]";
                } else if ("unsubscribe".equals(XMLParse.getString(revDocument, "Event"))) {
                    //放置redis消息
//                    redisUtils.putUnSubscribeMsg(fromUserName);
                    putredisActive = false;
                    retStr = "success";
                    logStr = "用户[" +fromUserName + "]，事件[" + Constants.WX_UN_SBUSCRIBE + "], 参数[openId:" + fromUserName + "]";
                } else if ("CLICK".equals(XMLParse.getString(revDocument, "Event"))) {
                    String eventKey = XMLParse.getString(revDocument, "EventKey");
                    eventType = eventType + "_key=" + eventKey;
                    //用户自定义 点击事件
                    retStr = "success";
                } else if ("VIEW".equals(XMLParse.getString(revDocument, "Event"))) {
                    String eventKey = XMLParse.getString(revDocument, "EventKey");
                    eventType = eventType + "_keyurl=" + eventKey;
                    retStr = "success";
                }
            } else {
                eventType = "third_wx_" + msgType; //location + link
                retStr = "success";
            }
            //写回数据
        } catch (Exception e) {
            log.info("error:" + e.getMessage());
            e.printStackTrace();
        } finally {

            if (null != out) {
                try {
                    out.write( new String(retStr.getBytes("utf-8"),"ISO8859_1"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                out.close();
            }
            logStr = "用户[" +fromUserName + "]，事件[" + eventType + "], 参数[openId:" + fromUserName + "]";
            log.info("======>" + logStr);
        }
    }

    /**
     * 配置初始化验证
     */
    public void verify(HttpServletRequest request, HttpServletResponse response) {
        //获取请求的参数map
        try {
            log.info("Weixin Request Map:" + request.getParameterMap().keySet());
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");

            /**
             * 加密/校验流程如下：
             1. 将token、timestamp、nonce三个参数进行字典序排序
             2. 将三个参数字符串拼接成一个字符串进行sha1加密
             3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
             * */
            String[] arrayDic={timestamp,nonce, Constants.WX_TOKEN};
            Arrays.sort(arrayDic);
            String newSig= "";
            for(int i=0; i<arrayDic.length; i++){
                newSig += arrayDic[i];
            }
            log.info("signature=" + signature + ";timestamp=" + timestamp + ";nonce=" + nonce + ";echostr=" + echostr);
            String mysgin = EncryptUtils.SHA1(newSig);
            if(mysgin.trim().equals(signature.trim())){
                response.getWriter().write(echostr);
            }else{
                response.getWriter().write("check error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
    }

    /**
     * 处理关键词
     * @param askWord
     * @return
     */
    public String dealRepley(String askWord, String fromUserName) {
        String[] keyWord = new String[]{"删掉","重置","重做", "重作", "重测", "重新", "抹掉", "删除", "清",
                "清除", "注销", "取关", "隐私", "退出", "解除匹配"};
        StringBuffer sb = new StringBuffer();
        askWord = askWord.trim();

        for (String word : keyWord) {
            if (askWord.contains(word)) {
                return "请到公众号【我的】→【个人中心】成为VIP，享受重测、隐藏专属功能，还有更多福利哦 \n\n如有任何建议建议及反馈，请回复：#建议 [您的意见]";
            }
        }

        if (askWord.contains("查ID") || askWord.contains("查id") ||askWord.contains("查Id") ||askWord.contains("查iD") ||
                askWord.equals("ID") || askWord.equals("id") || askWord.equals("Id") || askWord.equals("iD")) {
//            logger.info("[ ID ] " + fromUserName);
            sb.append("亲～  您的ID为："+ fromUserName +"");
            return sb.toString();
        }

        // 收集意见
        if (askWord.contains("#职业测评") || askWord.contains("#爱情测评") || askWord.contains("#建议")) {
            //记录意见日志
//            ideaLog.info("[意见收集] [" + fromUserName +"] " + askWord);
            sb.append("亲～ 感谢您的意见与问题，我们将会整理你的意见，并且持续改善您的用户体验。\n\n如有其他的反馈欢迎您添加” donottell-me”微信号。");
            return sb.toString();
        }

        if (askWord.contains("优惠码") || askWord.contains("优惠券") || askWord.contains("优惠卷")) {
            sb.append("你好，优惠码是推荐您来的那个人的电话号码，或者您也可以推荐朋友，这样您的手机号就是优惠码，无论是推荐人还是被推荐人，都会获得职业解密券。");
            return sb.toString();
        }


//		sb.append("您好，欢迎使用回复自动系统。\n职业测评的问题，请回复：DNTM职业\n爱情测评的问题，请回复：DNTM爱情\n其他的建议及反馈，请回复：#建议 [您的意见]");
        sb.append("您好，欢迎使用回复自动系统。有任何建议建议及反馈，请回复：#建议 [您的意见]");
        return sb.toString();

    }

    /**
     * 以客服消息形式回复
     * @param content  内容
     * @param userOpenId 用户openId
     */
//    public void returnKfMsg(String content, String userOpenId) {
////		String token = "JHtVkyl8fBwMLTpg5GrPiD28nifFLyKqWup9Z9dYpDKwNM1gba4BJRLMfIMD5pJVYJ4rXuBwPlqkTWq0FI8nHZKCqdrObY4R5cHonTERmpwZEGdAJAPUW";
//        String token = redisUtils.getAccessToken();
//        String returnStr = "{\"touser\": \"" + userOpenId + "\",\"msgtype\": \"text\",\"text\": {\"content\": \"" + content + "\"}}";
//        MessageAPI.messageCustomSend(token, returnStr);
//    }

    /********************** 监听微信号发送信息 end ****************************/
}
