package com.leraningreactor.learningreactor.fluxandmonoplayground

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

import javax.xml.soap.Text

class FluxAndMonoTest {

    @Test
    void fluxTest() {
        Flux<String> flux = Flux.just("Spring", "Mono", "Flux")
                .concatWith(Flux.error(new Exception("Erro aqui")))
                .log()
        flux.subscribe(System.out::println, (e) -> System.err.print("Exception is " + e), () -> System.print("Complete"))
    }

    @Test
    void fluxTestWithoutError() {
        Flux<String> fluxString = Flux.just("Carro", "Casa", "moto").log()

        StepVerifier.create(fluxString)
                .expectNext("Carro")
                .expectNext("Casa")
                .expectNext("moto")
                .verifyComplete()
    }


    @Test
    void fluxTestWithError() {
        Flux<String> fluxString = Flux.just("Carro", "Casa", "moto")
                .concatWith(Flux.error(new Exception("Erro aqui"))).log()
        StepVerifier.create(fluxString)
                .expectNext("Carro")
                .expectNext("Casa")
                .expectNext("moto")
                .expectError(Exception.class)
                .verify()
    }

    @Test
    void fluxTestCounterWithoutError() {
        Flux<String> fluxString = Flux.just("Carro", "Casa", "moto").log()
        StepVerifier.create(fluxString)
                .expectNextCount(3)
                .verifyComplete()
    }


    @Test
    void monoTest(){
        Mono<String> monoString = Mono.just("Spring")
        StepVerifier.create(monoString.log())
        .expectNext("Spring")
        .verifyComplete()
    }

    @Test
    void monoTestWithError(){
        StepVerifier.create(Mono.error(new Exception("Erro")))
                .expectError(Exception.class)
                .verify()
    }

}
