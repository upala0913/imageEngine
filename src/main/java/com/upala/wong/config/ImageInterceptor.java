package com.upala.wong.config;

import com.upala.wong.entity.Manager;
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
        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
        if (manager != null) {
            log.info("用户信息：{}", manager);
            return true;
        }
        response.sendRedirect("http://127.0.0.1:8085");
        return false;
    }

}
