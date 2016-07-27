package com.springmvc.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dello on 2016/7/27.
 */
@Controller
public class WebController extends BaseController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
