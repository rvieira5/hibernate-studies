package com.andcelsode.controller;

import com.andcelsode.model.Actor;
import com.andcelsode.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {
    @Autowired
    private ActorService actorService;

    @GetMapping
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable("id") Short id) {
        return actorService.getActorById(id);
    }

    @PostMapping
    public Actor saveActor(@RequestBody Actor actor) {
        return actorService.saveActor(actor);
    }

    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable("id") Short id, @RequestBody Actor actorDetails) {
        return actorService.updateActor(id, actorDetails);
    }

    //Delete is still not working because it depends on another table ("film_actor")
    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable("id") Short id) {
        actorService.deleteActor(id);
    }


}
