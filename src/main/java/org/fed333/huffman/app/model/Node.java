package org.fed333.huffman.app.model;

import lombok.Data;

// A Tree node
@Data
public class Node {
    char ch;
    int freq;
    Node left = null, right = null;

    public Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    public Node(char ch, int freq, Node left, Node right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
};
