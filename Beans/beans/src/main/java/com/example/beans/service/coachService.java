package com.example.beans.service;

import com.example.beans.repositories.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.externalbean.entity.Coach;
import com.example.externalbean.service.HockeyCoachService;

import java.util.List;

@Configuration
public class coachService {

    @Autowired
    private CoachRepository coachRepository;

    @Bean(name ="baseballCoach")
    public Coach addBaseballCoach(){
        Coach coach= new Coach();
        coach.setName("Coach");
        coach.setType("Baseball");
        coachRepository.save(coach);
        return coach;
    }

    @Bean(name ="footballCoach")
    public Coach addFootballCoach(){
        Coach coach= new Coach();
        coach.setName("Coach");
        coach.setType("Football");
        coachRepository.save(coach);
        return coach;
    }

    @Bean(name ="hockeyCoach")
    public Coach addHockeyCoach(){
        HockeyCoachService hockeyCoachService = new HockeyCoachService(){
            @Override
            public com.example.externalbean.entity.Coach addHockeyCoach(){
                Coach coach= new Coach();
                coach.setName("Coach");
                coach.setType("Hockey Overrided!");
                coachRepository.save(coach);
                return coach;
            }
    }   ;
        return hockeyCoachService.addHockeyCoach();
    }

    @Bean
    public List<Coach> allCoaches(){
        return coachRepository.findAll();
    }
}
