package com.leraningreactor.learningreactor.controller

import org.springframework.http.MediaType
import org.springframework.http.MediaTypeEditor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

import java.time.Duration

@RestController
class FluxAndMonoController {

    @GetMapping(value = "/flux", produces = "application/stream+json")
    Flux<Integer> returnFlux(){
        return Flux.just(1,3,4).delayElements(Duration.ofSeconds(3)).log()
    }
}
