package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.LoginDto;


@FeignClient(name = "auth-service", url = "${AUTH_SERVICE_URL:http://localhost:9300}")
public interface AuthFeignClient {

    @PostMapping("/api/auth/signin")
    ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto);
}