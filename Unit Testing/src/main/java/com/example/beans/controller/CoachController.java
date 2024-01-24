package com.example.beans.controller;

import com.example.beans.entity.Coach;
import com.example.beans.service.CoachService;
import com.example.beans.exceptions.InvalidRequestException;
import com.example.beans.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CoachController {
    @Autowired
    public CoachService coachService;

    @GetMapping("/coaches")
    public List<Coach> getAllCoaches(){
        return coachService.allCoaches();
    }

    @PostMapping("/coaches")
    public void addCoach(@RequestBody Coach coach){
        coachService.addCoach(coach);
    }

    @PutMapping("/coaches")
    public void updateCoach(@RequestBody Coach coach) throws Exception {
        coachService.updateCoach(coach);
    }

    @DeleteMapping("/coaches/{id}")
    public void deleteCoach(@PathVariable Long id) throws Exception {
        coachService.removeCoach(id);
    }
}
