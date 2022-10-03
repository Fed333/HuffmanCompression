package org.fed333.huffman.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HuffmanCompressionServiceTest {

    @Autowired
    private HuffmanCompressionService service;

    @Test
    public void test_compress() {
        String sourceText = "Hello world!";
        String expectedCompressed = "0110001101011111000101110111100001101";

        String actualCompressed = service.compress(sourceText);

        assertThat(actualCompressed).isEqualTo(expectedCompressed);

    }
}