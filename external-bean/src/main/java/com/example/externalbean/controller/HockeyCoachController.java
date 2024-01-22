package com.example.externalbean.controller;
import com.example.externalbean.entity.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HockeyCoachController {

    @Autowired
    @Qualifier("hockeyCoach")
    private Coach hockeyCoach;

    @GetMapping("/hockeyCoach")
    public Coach getHockeyCoach(){
        return hockeyCoach;
    }


}
