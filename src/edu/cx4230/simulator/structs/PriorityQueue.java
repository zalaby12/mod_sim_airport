package edu.cx4230.simulator.structs;

import java.util.Iterator;

/*
 * Generic priority queue featuring iteration and sorting. Uses a simple list as
 * the backing structure.
 */
public class PriorityQueue<T extends Comparable<? super T>> implements Iterable<T>{

    private List<T> backingList;
    private int size;

    public PriorityQueue() {
        this.backingList = new SinglyLinkedList<>();
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void push(T t) {
        size++;
        this.backingList.add(this.findInsertPoint(this.backingList, t), t);
    }

    public T pop() {
        if (size != 0) {
            size--;
            return backingList.remove(0);
        }
        return null;
    }

    public T peek() {
        if (size != 0) {
            return backingList.get(0);
        }
        return null;
    }

    public T remove(int index) {
        if (index >= this.size) {
            return null;
        } else {
            return this.backingList.remove(index);
        }
    }

    public Iterator<T> iterator() {
        return backingList.iterator();
    }

    private int findInsertPoint(List<T> list, T t) {
        int index = 0;
        for (T item : list) {
            // if t should go in front of the rest of the items
            if (t.compareTo(item) > 0) {
                return index;
            } else {
                index++;
            }
        }
        return index;
    }

}
