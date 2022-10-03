package org.fed333.huffman.app.service;

import org.fed333.huffman.app.model.Node;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HuffmanEncoder {

    public void encode(Node root, String str, Map<Character, String> huffmanCode)
    {
        if (root == null)
            return;

        // found a leaf node
        if (root.getLeft() == null && root.getRight() == null) {
            huffmanCode.put(root.getCh(), str);
        }


        encode(root.getLeft(), str + "0", huffmanCode);
        encode(root.getRight(), str + "1", huffmanCode);
    }

}
