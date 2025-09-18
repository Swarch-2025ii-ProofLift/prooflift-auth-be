package com.prooflift.login.Demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor

public class DemoController {

    @PostMapping(value ="demo") // método post para login de endpoint seguro
    public String demo() {
        return "Demo secure endpoint";
    }

}
// todas las rutas están protegidas por defecto con spring security