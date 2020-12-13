package com.github.anhem.springboothazelcastperformance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerRedirect {

    @RequestMapping("/")
    public String redirectToSwagger() {
        return "redirect:swagger-ui.html";
    }
}
