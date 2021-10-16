package edu.mum.cloudgateway.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @CircuitBreaker(name = "orderFallBack", fallbackMethod = "orderServiceFallBackMethod")
    @GetMapping("/orderServiceFallBackMethod")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public String orderServiceFallBackMethod(Exception e){
        return "order Service took longer time than expected.Please try again after sometime";
    }

    @CircuitBreaker(name = "paymentServiceFallBackMethod", fallbackMethod = "paymentServiceFallBackMethod")
    @GetMapping("/paymentServiceFallBackMethod")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public String paymentServiceFallBackMethod(Exception e){
        return "Payment Service took longer time than expected.Please try again after sometime";
    }

}
