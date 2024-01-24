package com.example.beans.controller;

import com.example.beans.exceptions.NotFoundException;
import com.example.beans.entity.Coach;
import com.example.beans.repositories.CoachRepository;
import com.example.beans.service.CoachService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CoachControllerTest {
    MockMvc mockMvc;

    @MockBean
    CoachRepository coachRepository;

    @Autowired
    CoachService coachService;

    @Autowired
    WebApplicationContext webApplicationContext;

    Coach coach1;
    Coach coach2;
    Coach coach3;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        coach1= new Coach((long)1,"Coach1","Hockey");
        coach2= new Coach((long)2,"Coach2","Baseball");
        coach3= new Coach((long)3,"Coach3","Football");

        List<Coach> coaches = List.of(coach1, coach2, coach3);
        Mockito.when(coachRepository.findAll()).thenReturn(coaches);
        Mockito.when(coachRepository.findById((long)1)).thenReturn(Optional.of(coach1));
        Mockito.when(coachRepository.findById((long)2)).thenReturn(Optional.of(coach2));
        Mockito.when(coachRepository.findById((long)3)).thenReturn(Optional.of(coach3));
        Mockito.when(coachRepository.findById((long)4)).thenReturn(Optional.empty());
    }

    @Test
    void getAllCoaches() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/coaches"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("Coach1"));
    }

    @Test
    void deletingExistingCoach() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/coaches/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deletingCoach_NotFound() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/coaches/4"))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andExpect(result ->
                        assertEquals("Coach with ID 4 does not exist.", result.getResolvedException().getMessage()));
    }

    @Test
    void deletingCoach_NotFound_usingRule() throws Exception{
        Mockito.when(coachRepository.findById((long)4))
                .thenThrow(new NotFoundException("Coach with ID 4 does not exist."));

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            coachService.removeCoach((long)4);
        });

        assertEquals("Coach with ID 4 does not exist.", exception.getMessage());
    }

    @Test
    void deletingExistingCoach_usingRule_bySendingRequest() throws Exception{
        Mockito.when(coachRepository.findById((long) 4))
                .thenThrow(new NotFoundException("Coach with ID 4 does not exist."));

        assertThrows(NotFoundException.class, () -> {
            mockMvc.perform(MockMvcRequestBuilders.delete("/coaches/4"));
        });

    }

}