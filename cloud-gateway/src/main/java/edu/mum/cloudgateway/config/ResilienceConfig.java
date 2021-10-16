package edu.mum.cloudgateway.config;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

@Configuration
public class ResilienceConfig {
    @Bean
    public CircuitBreakerConfigCustomizer orderServiceCircuitBreaker() {
        return CircuitBreakerConfigCustomizer
                .of("orderServiceFallBackMethod", builder -> {
                    builder.slidingWindowSize(5);
                    builder.failureRateThreshold(3);
                    builder.waitDurationInOpenState(Duration.ofSeconds(10));
                    builder.slowCallDurationThreshold(Duration.ofSeconds(5));
                    builder.slowCallRateThreshold(100);
                    builder.permittedNumberOfCallsInHalfOpenState(2);
                    builder.slidingWindowType(COUNT_BASED);
                });
    }

    @Bean
    public CircuitBreakerConfigCustomizer paymentServiceCircuitBreaker() {
        return CircuitBreakerConfigCustomizer
                .of("paymentServiceFallBackMethod", builder -> {
                    builder.slidingWindowSize(5);
                    builder.failureRateThreshold(3);
                    builder.waitDurationInOpenState(Duration.ofSeconds(10));
                    builder.slowCallDurationThreshold(Duration.ofSeconds(5));
                    builder.slowCallRateThreshold(100);
                    builder.permittedNumberOfCallsInHalfOpenState(2);
                    builder.slidingWindowType(COUNT_BASED);
                });
    }


}