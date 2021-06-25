package com.example.testreactivo;

import com.example.testreactivo.service.UppercaseConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

@SpringBootTest
public class UppercaseConverterTest {


    final TestPublisher<String> testPublisher = TestPublisher.create();
    @Test
    void getUpperCase() {
        UppercaseConverter uppercaseConverter = new UppercaseConverter(testPublisher.flux());
        StepVerifier.create(uppercaseConverter.getUpperCase())
                .then(() -> testPublisher.emit("datos", "GeNeRaDoS", "Sofka"))
                .expectNext("DATOS", "GENERADOS", "SOFKA")
                .verifyComplete();
    }
}
