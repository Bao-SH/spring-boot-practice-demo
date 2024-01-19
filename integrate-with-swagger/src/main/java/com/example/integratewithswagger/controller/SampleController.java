package com.example.integratewithswagger.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/{id}")
    public String findById(@PathVariable long id) {
        return "succeed by " + id;
    }

    @GetMapping("/hello")
    @Operation(method = "get method", summary = "get greetings", description = "get descriptions")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK",
        content = @Content(mediaType = "application/json", examples =
        @ExampleObject(name = "test name", value = "default value"),
            schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    public String greet() {
        return "hello";
    }

    @GetMapping("/secured")
    public String secured() {
        return "this is a secured endpoint";
    }
}

