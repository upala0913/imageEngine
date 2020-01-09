package com.upala.wong.config;

import com.upala.wong.entity.Manager;
import com.upala.wong.utils.SystemUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/********************************
 *  @program imageEngine
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-07 11:03
 *  @description
 ********************************/

@Log4j2
public class ImageInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	boolean res = true;
        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
        if (manager != null) {
            log.info("用户信息：{}", manager);
            res = true;
        }
		boolean window = SystemUtils.isWindow();
		boolean linux = SystemUtils.isLinux();
        if (window) {
        	log.info("[{}]系统", "window");
			response.sendRedirect("http://127.0.0.1:8090/upala/index.html");
			res = false;
		}
		if (linux) {
			log.info("[{}]系统", "linux");
        	response.sendRedirect("http://www.wongupala.top:8085");
        	res = false;
		}
		return res;
    }

}
