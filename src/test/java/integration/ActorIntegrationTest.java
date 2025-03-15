package integration;

import com.andcelsode.App;
import com.andcelsode.model.Actor;
import com.andcelsode.repository.ActorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = App.class)
@ActiveProfiles("test")
public class ActorIntegrationTest {

    @Autowired
    private ActorRepository actorRepository;

    @Test
    public void testSaveAndFindActor() {
        Actor actor = new Actor();
        actor.setFirstName("Bing");
        actor.setSecondName("Book");
        actor.setLastName("Lala");
        actor.setLastUpdate(LocalDateTime.now());

        Actor savedActor = actorRepository.save(actor);
        Optional<Actor> foundActor = actorRepository.findById(savedActor.getId());

        assertTrue(foundActor.isPresent());
        assertEquals("Bing", foundActor.get().getFirstName());
        assertEquals("Book", foundActor.get().getSecondName());
        assertEquals("Lala", foundActor.get().getLastName());
    }
}
