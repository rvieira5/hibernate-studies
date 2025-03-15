package com.andcelsode.controller;

import com.andcelsode.model.Actor;
import com.andcelsode.service.ActorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActorController.class)
public class ActorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActorService actorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetActorById_Success() throws Exception {
        Actor mockActor = new Actor();
        mockActor.setId((short) 1);
        mockActor.setFirstName("John");
        mockActor.setSecondName("Doe");
        mockActor.setLastName("Lorem");
        mockActor.setLastUpdate(LocalDateTime.now());

        when(actorService.getActorById((short) 1)).thenReturn(mockActor);

        mockMvc.perform(get("/actors/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.secondName").value("Doe"))
                .andExpect(jsonPath("$.lastName").value("Lorem"));
    }

}
