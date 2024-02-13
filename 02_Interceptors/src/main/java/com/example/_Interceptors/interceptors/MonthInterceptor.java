package com.example._Interceptors.interceptors;

import com.example._Interceptors.entities._Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    public static List<_Month> listMonths = new ArrayList<>(Arrays.asList(
            new _Month(1, "January", "Gennaio", "nonso"),
            new _Month(2, "February", "Febbraio", "nonso"),
            new _Month(3, "March", "Marzo", "nonso"),
            new _Month(4, "April", "Aprile", "nonso")
    ));


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String idMonthString = request.getHeader("monthNumber");
        if (idMonthString == null || idMonthString.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Month number is required");
            return true;
        }

        int idMonth = Integer.parseInt(idMonthString);
        Optional<_Month> month = listMonths.stream().filter(singleMonth -> {
            return singleMonth.getMonthNumber() == idMonth;
        }).findFirst();

        if (month.isPresent()) {
            request.setAttribute("MonthInterceptor-month", month.get());
        } else {
            request.setAttribute("MonthInterceptor-month", new _Month(idMonth, "nope", "nope", "nope"));
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
