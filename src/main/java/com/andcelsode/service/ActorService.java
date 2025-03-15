package com.andcelsode.service;

import com.andcelsode.exception.ValidationException;
import com.andcelsode.model.Actor;
import com.andcelsode.repository.ActorRepository;
import com.andcelsode.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Actor getActorById(Short id) {
        return actorRepository.findById(id).orElseThrow(() ->
                new ValidationException("Actor not found with ID:" + id)
        );
    }

    public Actor saveActor(Actor actor) {
        ValidationUtils.validateActor(actor);
        actor.setLastUpdate(LocalDateTime.now());
        return actorRepository.save(actor);
    }

    public Actor updateActor(Short id, Actor actorDetails) {
        Actor existingActor = actorRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Actor not found with ID: " + id));

        existingActor.setFirstName(actorDetails.getFirstName());
        existingActor.setSecondName(actorDetails.getSecondName());
        existingActor.setLastName(actorDetails.getLastName());
        existingActor.setLastUpdate(LocalDateTime.now());

        return actorRepository.save(existingActor);
    }

    public void deleteActor(Short id) {
        Actor existingActor = actorRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Actor not found with ID: " + id));

        actorRepository.delete(existingActor);
    }
}
