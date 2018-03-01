package edu.cx4230.simulator.structs;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Your implementation of a SinglyLinkedList
 *
 * @author Zachary Halaby
 * @version 1.0
 */

public class SinglyLinkedList<T> implements List<T> {

    // Do not add new instance variables.
    private Node<T> head;
    private int size;

    @Override
    public void add(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Must have data entered.");
        } else if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index must be less "
                    + "than the size of the list and/or not negative");
        }  else if (index == 0) {
            this.head = new Node<>(data, head);
        } else {
            Node<T> searchNode = this.head;
            for (int i = 1; i < index; i++) {
                searchNode = searchNode.getNext();
            }
            Node<T> inserter = new Node<T>(data, searchNode.getNext());
            searchNode.setNext(inserter);
        }
        size++;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index must be less "
                    + "than the size of the list and/or not negative");
        } else if (index == 0) {
            return head.getData();
        } else {
            Node<T> searchNode = new Node<T>(null, null);
            searchNode = head;
            for (int i = 0; i < index; i++) {
                searchNode = searchNode.getNext();
            }
            return searchNode.getData();
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index must be greater"
                    + "than the size of the list and/or not negative");
        } else if (index == 0) {
            Node<T> newNode = new Node<T>(null, null);
            newNode = head;
            head = head.getNext();
            size--;
            return newNode.getData();
        } else {
            Node<T> searchNode = new Node<T>(null, null);
            searchNode = head;
            for (int i = 1; i < index; i++) {
                searchNode = searchNode.getNext();
            }
            Node<T> tempNode = new Node<T>(null, null);
            tempNode = searchNode.getNext();
            searchNode.setNext(tempNode.getNext());
            size--;
            return tempNode.getData();
        }
    }

//    @Override
//    public void add(T data) {
//        if (data == null) {
//            throw new IllegalArgumentException("Must enter data in parameter");
//        } else {
//            this.add(data);
//        }
//        //size++;
//    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Must enter data in parameter");
        } else if (size == 0) {
            Node<T> newNode = new Node<T>(data);
            this.head = newNode;
        } else {
            Node<T> searchNode =
                    new Node<T>(data);
            searchNode = head;
            while (searchNode.getNext() != null) {
                searchNode = searchNode.getNext();
            }
            Node<T> toInsert = new Node<T>(data, null);
            searchNode.setNext(toInsert);
        }
        size++;
    }

    @Override
    public T remove() {
        if (this.isEmpty()) {
            return null;
        } else {
            Node<T> temp = new Node<T>(null, null);
            temp = this.head;
            this.head = this.head.getNext();
            size--;
            return temp.getData();
        }
    }

    @Override
    public T removeFromBack() {
        if (this.isEmpty()) {
            return null;
        } else {
            Node<T> front = new Node<T>(null, null);
            Node<T> back = new Node<T>(null, null);
            front = this.head;
            while (front.getNext() != null) {
                back = front;
                front = front.getNext();
            }
            back.setNext(null);
            this.size--;
            return front.getData();
        }
    }

    @Override
    public int removeFirstOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Must enter data in parameter");
        } else if (size == 0 || this.head == null) {
            throw new NoSuchElementException(("There are no items in the list"
                    + " and therefore no data matches"));
        } else if (head.getData().equals(data)) {
            this.remove();
            return 0;
        } else {
            Node<T> searchNode = new Node<T>(null, null);
            searchNode = head;
            int position = 1;
            while ((searchNode.getNext() != null)
                    && (!searchNode.getNext().getData().equals(data))) {
                searchNode = searchNode.getNext();
                position++;
            }
            if (searchNode == null || searchNode.getNext() == null) {
                throw new NoSuchElementException("No element with the given"
                        + " data exists in this Linked List");
            } else {
                this.remove(position);
                return position;
            }
        }
    }

    @Override
    public Object[] toArray() {
        Object[] objectArray = new Object[size];
        Node<T> getter = new Node<T>(null, null);
        getter = this.head;
        for (int i = 0; i < size; i++) {
            objectArray[i] = getter.getData();
            getter = getter.getNext();
        }
        return objectArray;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public Node<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public Iterator<T> iterator() {
        final List<T> currentList = this;
        return new Iterator<T>() {
            Node<T> currentNode = head;
            int count = 0;

            @Override
            public boolean hasNext() {
                //System.out.println("count is: " + currentNode.getNext() + " and size is: " + size);
                return (!currentList.isEmpty()) && (count < size);
            }

            @Override
            public T next() {
                count++;
                if (currentNode == null) {
                    throw new NoSuchElementException();
                } else if (count > size) {
                    throw new NoSuchElementException();
                } else {
                    T thisNodesData = currentNode.getData();
                    currentNode = currentNode.getNext();
                    return thisNodesData;
                }
            }
        };
    }

//    @Override
//    public void forEach(Consumer<? super T> action) {
//
//    }

}
