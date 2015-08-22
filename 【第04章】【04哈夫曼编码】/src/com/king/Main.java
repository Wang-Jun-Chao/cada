package com.king;

import java.util.Objects;

/**
 * Author: 王俊超
 * Date: 2015-01-01
 * Time: 10:50
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {

//        Node<Character, Integer> root = new Node<>('a', 1);
//        Node<Character, Integer> left = new Node<>('b', 2);
//        Node<Character, Integer> right = new Node<>('c', 3);
//
//        root.setLeftChild(left);
//        root.setRightChild(right);
//
//        root.postOrder(new Handler<Object, Node<Character, Integer>>() {
//            @Override
//            public Object hand(Node<Character, Integer> node) {
//                System.out.println(node.getData());
//                return null;
//            }
//        });

        Character[] keys = new Character[]{'a', 'b', 'c', 'd', 'e', 'f'};
        Integer[] values = new Integer[]{45, 13, 12, 16, 9, 5};

        HuffmanTree<Character, Integer> huffmanTree = new HuffmanTree<Character, Integer>(keys, values);
        Node<Character, Integer> root = huffmanTree.getRoot();

        root.preOrder(new Handler<Object, Node<Character, Integer>>() {
            @Override
            public Character hand(Node node) {
                System.out.print(node + "->");
                return null;
            }
        });

        System.out.println();
        root.inOrder(new Handler<Object, Node<Character, Integer>>() {
            @Override
            public Character hand(Node node) {
                System.out.print(node + "->");
                return null;
            }
        });
    }
}
