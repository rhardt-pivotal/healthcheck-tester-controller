package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.domain.AppStatus;
import de.jonashackt.springbootvuejs.domain.User;
import de.jonashackt.springbootvuejs.repository.AppStatusRepository;
import de.jonashackt.springbootvuejs.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppStatusRepository appStatusRepository;

    @RequestMapping(path = "/hello")
    public @ResponseBody String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody long addNewUser (@RequestParam String firstName, @RequestParam String lastName) {
        User user = new User(firstName, lastName);
        userRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }

    @GetMapping(path="/user/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") long id) {
        LOG.info("Reading user with id " + id + " from database.");
        return userRepository.findById(id).get();
    }

    @RequestMapping("/buildTestData")
    public ResponseEntity<List<AppStatus>> testData() {
        IntStream.range(0,10).forEach(
                i -> appStatusRepository.save(new AppStatus(-1, UUID.randomUUID().toString(), "test-app-"+i, true))
        );
        return ResponseEntity
                .ok(StreamSupport
                        .stream(appStatusRepository
                                .findAll().spliterator(), false)
                        .collect(Collectors.toList()));
    }

}
