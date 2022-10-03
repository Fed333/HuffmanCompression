package org.fed333.huffman.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.fed333.huffman.app.service.HuffmanCompressionService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compress")
public class HuffmanFileCompressionController {

    private final HuffmanCompressionService compressionService;

    @SneakyThrows
    @GetMapping
    public ResponseEntity<Map<String,String>> compressFile(@RequestParam("fileToEncode") MultipartFile file) {
        String textToEncode = new BufferedReader(new InputStreamReader(file.getInputStream())).lines().collect(Collectors.joining());
        String compressed = compressionService.compress(textToEncode);
        return ResponseEntity.ok(Stream.of(new Object[][]{
                {"source text", textToEncode},
                {"after huffman compression", compressed}
        }).collect(Collectors.toMap(o->(String)o[0],o->(String)o[1])));
    }
}
