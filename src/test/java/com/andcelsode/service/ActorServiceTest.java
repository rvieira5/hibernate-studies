package com.andcelsode.service;

import com.andcelsode.exception.ValidationException;
import com.andcelsode.model.Actor;
import com.andcelsode.repository.ActorRepository;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActorServiceTest {
    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorService actorService;

    private Actor mockActor;

    @BeforeEach
    public void setUp() {
        mockActor = new Actor();
        mockActor.setId((short) 1);
        mockActor.setFirstName("John");
        mockActor.setSecondName("Doe");
        mockActor.setLastName("Lorem");
        mockActor.setLastUpdate(LocalDateTime.now());
    }

    private void mockFindActorById(Short id, Actor actor) {
        if (actor == null) {
            when(actorRepository.findById((id))).thenReturn(Optional.empty());
            return;
        }
        when(actorRepository.findById((id))).thenReturn(Optional.of(actor));
    }

    @Test
    public void testGetActorById_Success() {
        mockFindActorById((short) 1, mockActor);

        Actor result = actorService.getActorById((short) 1);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getSecondName());
        assertEquals("Lorem", result.getLastName());
        verify(actorRepository, times(1)).findById((short) 1);
    }

    @Test
    public void testGetActorById_NotFound() {
        mockFindActorById((short) 1, null);

        assertThrows(ValidationException.class, () -> {
            actorService.getActorById((short) 1);
        });

        verify(actorRepository, times(1)).findById((short) 1);
    }

    @Test
    public void testSaveActor_Success() {
        when(actorRepository.save(mockActor)).thenReturn(mockActor);

        Actor result = actorService.saveActor(mockActor);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getSecondName());
        assertEquals("Lorem", result.getLastName());
        verify(actorRepository, times(1)).save(mockActor);
    }

    @Test
    public void testSaveActor_InvalidData() {
        Actor invalidActor = new Actor();
        invalidActor.setFirstName(null);
        invalidActor.setSecondName("Uzumaki");
        invalidActor.setLastName(null);

        assertThrows(ValidationException.class, () -> {
            actorService.saveActor(invalidActor);
        });

        verify(actorRepository, never()).save(any());
    }

    @Test
    public void testSaveActor_RepositoryFailure() {
        when(actorRepository.save(mockActor)).thenThrow(new DataAccessException("Database error") {
        });

        assertThrows(ServiceException.class, () -> {
            actorService.saveActor(mockActor);
        });

        verify(actorRepository, times(1)).save(mockActor);
    }
}
