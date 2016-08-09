package com.broto.backstage.controller;

import com.alibaba.druid.support.json.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by yitao on 2016/7/26.
 */
@Controller
public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);


    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    public static void response(HttpServletResponse res, String text, String charset) {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.setContentType("application/json");
        res.setCharacterEncoding(charset);

        try {
            res.getOutputStream().write(text.getBytes(charset));
        } catch (Exception var4) {
            log.error("Error", var4);
        }

    }

    public static void response(HttpServletResponse res, String text) {
        response(res, text, "UTF-8");
    }

    public static void responseJson(HttpServletResponse res, Object obj) {
        response(res, JSONUtils.toJSONString(obj));
    }

    public static void printRequestHeaders(HttpServletRequest req) {
        if(req != null) {
            Enumeration names = req.getHeaderNames();
            StringBuilder sb = new StringBuilder("\n=========Request Headers=========");

            while(names.hasMoreElements()) {
                String name = (String)names.nextElement();
                sb.append("\n\t").append(name + ": " + req.getHeader(name));
            }

            sb.append("\n=========Request Headers=========");
            log.info(sb.toString());
        }

    }

    public static void printRequestAttribute(HttpServletRequest req) {
        if(req != null) {
            Enumeration names = req.getAttributeNames();
            StringBuilder sb = new StringBuilder("\n=========Request Attribute=========");

            while(names.hasMoreElements()) {
                String name = (String)names.nextElement();
                sb.append("\n\t").append(name + ": " + req.getAttribute(name));
            }

            sb.append("\n=========Request Attribute=========");
            log.info(sb.toString());
        }

    }

    public static void printRequestCookie(HttpServletRequest req) {
        if(req != null) {
            Cookie[] cookies = req.getCookies();
            if(cookies != null) {
                StringBuilder sb = new StringBuilder("\nCookie: ");
                Cookie[] arr$ = cookies;
                int len$ = cookies.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Cookie cookie = arr$[i$];
                    sb.append("\n\t").append(JSONUtils.toJSONString(cookie));
                }

                log.info(sb.toString());
            }

        }
    }

    public static void printRequestBrief(HttpServletRequest req) {
        if(log.isInfoEnabled() && req != null) {
            StringBuilder sb = new StringBuilder("\nInvoke: " + req.getRequestURL());
            Enumeration names = req.getHeaderNames();
            sb.append("\nParams:");
            names = req.getParameterNames();

            while(names.hasMoreElements()) {
                String cookies = (String)names.nextElement();
                sb.append("\n\t" + cookies + ": [" + req.getParameter(cookies) + "]");
            }

            Cookie[] var8 = req.getCookies();
            if(var8 != null) {
                sb.append("\nCookies: ");
                Cookie[] arr$ = var8;
                int len$ = var8.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Cookie cookie = arr$[i$];
                    sb.append("\n\t").append(JSONUtils.toJSONString(cookie));
                }
            }

            sb.append("\nReq Ip:\t").append(req.getRemoteAddr());
            sb.append("\nFwd Ip:\t").append(getReqIp(req));
            log.info(sb.toString());
        }

    }

    public static String getReqIp(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        return StringUtils.isNotBlank(ip)?ip:req.getRemoteAddr();
    }

    public static String getUserAgent(HttpServletRequest req) {
        return req.getHeader("user-agent");
    }

    public static String getReferer(HttpServletRequest req) {
        return req.getHeader("referer");
    }

    public static void clearSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
    }

    public static String getCookieDomain(HttpServletRequest req) {
        String hostname = req.getServerName();
        String cookieDomain = null;
        /*if(!hostname.equals("localhost") && !URLUtils.isIpAddress(hostname)) {
            cookieDomain = "." + URLUtils.getDomainName(hostname);
        }*/

        return cookieDomain;
    }
}
