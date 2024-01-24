package com.example.externalbean.service;

import com.example.externalbean.entity.Coach;
import com.example.externalbean.repositories.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HockeyCoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Bean(name ="hockeyCoach")
    @ConditionalOnMissingBean
    public Coach addHockeyCoach(){
        Coach coach= new Coach();
        coach.setName("Coach");
        coach.setType("Hockey");
        coachRepository.save(coach);
        return coach;
    }

}
