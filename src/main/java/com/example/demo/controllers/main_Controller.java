package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class main_Controller {

    @RequestMapping("/main")
    public String main(){
        return "main";
    }
    @GetMapping("/main")
    public String main(Model model){
        Htmlrequest_getsides triangle_sides = new Htmlrequest_getsides();  //side_a,side_b,side_c
        model.addAttribute("triangle_sides", triangle_sides);
        return "main";
    }

    @PostMapping("/main")
    public String main(@ModelAttribute Htmlrequest_getsides triangle_sides, Model model) {
        
        Coluction_function ans = new Coluction_function();
    
        double side_a = triangle_sides.getSide_a();
        double side_b = triangle_sides.getSide_b();
        double side_c = triangle_sides.getSide_c();
        
        model.addAttribute("triangle_sides", triangle_sides);
        model.addAttribute("area", ans.main(side_a,side_b,side_c));
    
        return "main";
    }
    
}
