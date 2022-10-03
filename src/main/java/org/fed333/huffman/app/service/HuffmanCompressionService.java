package org.fed333.huffman.app.service;

import lombok.RequiredArgsConstructor;
import org.fed333.huffman.app.model.Node;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HuffmanCompressionService {

    private final HuffmanEncoder huffmanEncoder;

    private final HuffmanDecoder huffmanDecoder;

    private final HuffmanTreeBuilder huffmanTreeBuilder;

    public String compress(String text) {

        Node root = huffmanTreeBuilder.buildHuffmanTree(text);

        Map<Character, String> huffmanCode = new HashMap<>();
        huffmanEncoder.encode(root, "", huffmanCode);

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < text.length(); i++) {
            sb.append(huffmanCode.get(text.charAt(i)));
        }

        return sb.toString();
    }

    public String decompress(String text) {
        throw new UnsupportedOperationException();
    }

}
