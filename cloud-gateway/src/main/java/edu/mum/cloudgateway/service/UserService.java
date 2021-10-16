package edu.mum.cloudgateway.service;

import edu.mum.cloudgateway.model.User;
import edu.mum.cloudgateway.security.model.Role;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private Map<String, User> data;

    @PostConstruct
    public void init(){
        data = new HashMap<>();

        data.put("user", new User("user", "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", true, Arrays.asList( Role.ROLE_USER)));

        data.put("admin", new User("admin", "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", true, Arrays.asList(Role.ROLE_ADMIN)));
    }

    public Mono<User> findByUsername(String username) {
        if (data.containsKey(username)) {
            return Mono.just(data.get(username));
        } else {
            return Mono.empty();
        }
    }

}
