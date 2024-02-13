package com.example._Interceptors.controllers;

import com.example._Interceptors.entities._Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/months")
public class MonthController {

    @GetMapping(path = "")
    public _Month getSingle(HttpServletRequest request) {
        _Month month = (_Month) request.getAttribute("MonthInterceptor-month");
        return month;
    }
}
