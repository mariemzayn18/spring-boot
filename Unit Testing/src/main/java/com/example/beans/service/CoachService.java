package com.example.beans.service;

import com.example.beans.exceptions.NotFoundException;
import com.example.beans.repositories.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.beans.entity.Coach;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;
    public List<Coach> allCoaches(){
        return coachRepository.findAll();
    }

    public void addCoach(Coach coach){
        coachRepository.save(coach);
    }
    public void updateCoach(Coach coach){
        coachRepository.save(coach);
    }

    public void removeCoach(Long id){
        Optional<Coach> coach = coachRepository.findById(id);
        if(coach.isPresent()){
            coachRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Coach with ID " + id + " does not exist.");
    }

}
