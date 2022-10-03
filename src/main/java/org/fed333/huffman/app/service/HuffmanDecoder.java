package org.fed333.huffman.app.service;

import org.fed333.huffman.app.model.Node;
import org.springframework.stereotype.Service;

@Service
public class HuffmanDecoder {

    // traverse the Huffman Tree and decode the encoded string
    public int decode(Node root, int index, StringBuilder sb)
    {
        if (root == null)
            return index;

        // found a leaf node
        if (root.getLeft() == null && root.getRight() == null)
        {
            System.out.print(root.getCh());
            return index;
        }

        index++;

        if (sb.charAt(index) == '0')
            index = decode(root.getLeft(), index, sb);
        else
            index = decode(root.getRight(), index, sb);

        return index;
    }

}
