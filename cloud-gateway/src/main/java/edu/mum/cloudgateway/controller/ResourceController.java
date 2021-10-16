package edu.mum.cloudgateway.controller;


import edu.mum.cloudgateway.model.Message;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class ResourceController {

    @GetMapping("/resource/user")
    @PreAuthorize("hasRole('USER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public Mono<ResponseEntity<?>> user() {
       return Mono.just(ResponseEntity.ok(new Message("Content for user")));
    }

    @GetMapping("/resource/admin")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public Mono<ResponseEntity<?>> admin() {
        return Mono.just(ResponseEntity.ok(new Message("Content for admin")));
    }

    @GetMapping("/resource/user-or-admin")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public Mono<ResponseEntity<?>> userOrAdmin() {
        return Mono.just(ResponseEntity.ok(new Message ("Content for user or admin")));
    }
}