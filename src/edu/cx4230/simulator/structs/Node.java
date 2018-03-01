package edu.cx4230.simulator.structs;

/**
 * Node class used for implementing your SinglyLinkedList.
 *
 * DO NOT ALTER THIS FILE!!
 *
 * @author CS 1332 TAs
 */
public class Node<T> {

    private T data;
    private Node<T> next;

    /**
     * Create a new Node with the given data object and next node.
     *
     * @param data data to store in the node
     * @param next next node
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Create a new Node with the given data object and no next node.
     *
     * @param data data to store in this node
     */
    public Node(T data) {
        this(data, null);
    }

    /**
     * Get the data stored in the node.
     *
     * @return data in this node.
     */
    public T getData() {
        return data;
    }

    /**
     * Get the next node.
     *
     * @return next node.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Set the next node.
     *
     * @param next new next node.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node containing: " + data;
    }

}
