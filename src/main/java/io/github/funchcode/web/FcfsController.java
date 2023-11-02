package io.github.funchcode.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FcfsController {

    @GetMapping(value = "/first")
    @ResponseStatus(HttpStatus.OK)
    public String firstGetApi() {
        return "okay...";
    }

}
