package com.example.beans.controller;

import com.example.externalbean.entity.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CoachController {

    @Autowired
    @Qualifier("baseballCoach")
    private Coach baseballCoach;

    @Autowired
    @Qualifier("footballCoach")
    private Coach footballCoach;

    @Autowired
    @Qualifier("hockeyCoach")
    private Coach hockeyCoach;

    @Autowired
    private List<Coach> allCoaches;

    @GetMapping("/baseballCoach")
    public Coach getBaseballCoach(){
        return baseballCoach;
    }

    @GetMapping("/footballCoach")
    public Coach getFootballCoach(){
        return footballCoach;
    }

    @GetMapping("/hockeyCoach")
    public Coach getHockeyCoach(){
        return hockeyCoach;
    }

    @GetMapping("/coaches")
    public List<Coach> getAllCoaches(){
        return allCoaches;
    }

}
