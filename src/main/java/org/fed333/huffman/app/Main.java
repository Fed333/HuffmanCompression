package org.fed333.huffman.app;

import org.fed333.huffman.app.model.Node;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;



class Huffman
{
    // traverse the Huffman Tree and store Huffman Codes
    // in a map.
    public static void encode(Node root, String str,
                              Map<Character, String> huffmanCode)
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

    // traverse the Huffman Tree and decode the encoded string
    public static int decode(Node root, int index, StringBuilder sb)
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

    // Builds Huffman Tree and huffmanCode and decode given input text
    public static void buildHuffmanTree(String text)
    {
        // count frequency of appearance of each character
        // and store it in a map
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0 ; i < text.length(); i++) {
            if (!freq.containsKey(text.charAt(i))) {
                freq.put(text.charAt(i), 0);
            }
            freq.put(text.charAt(i), freq.get(text.charAt(i)) + 1);
        }

        // Create a priority queue to store live nodes of Huffman tree
        // Notice that highest priority item has lowest frequency
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getFreq));

        // Create a leaf node for each character and add it
        // to the priority queue.
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // do till there is more than one node in the queue
        while (pq.size() != 1)
        {
            // Remove the two nodes of highest priority
            // (lowest frequency) from the queue
            Node left = pq.poll();
            Node right = pq.poll();

            // Create a new internal node with these two nodes as children
            // and with frequency equal to the sum of the two nodes
            // frequencies. Add the new node to the priority queue.
            int sum = left.getFreq() + right.getFreq();
            pq.add(new Node('\0', sum, left, right));
        }

        // root stores pointer to root of Huffman Tree
        Node root = pq.peek();

        // traverse the Huffman tree and store the Huffman codes in a map
        Map<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);

        // print the Huffman codes
        System.out.println("Huffman Codes are :\n");
        for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        System.out.println("\nOriginal string was :\n" + text);

        // print encoded string
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < text.length(); i++) {
            sb.append(huffmanCode.get(text.charAt(i)));
        }

        System.out.println("\nEncoded string is :\n" + sb);

        // traverse the Huffman Tree again and this time
        // decode the encoded string
        int index = -1;
        System.out.println("\nDecoded string is: \n");
        while (index < sb.length() - 2) {
            index = decode(root, index, sb);
        }
    }

    public static void main(String[] args)
    {
        String text = "Huffman coding is a data compression algorithm.";

        buildHuffmanTree(text);
    }
}