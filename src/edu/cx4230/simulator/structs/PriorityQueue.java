package edu.cx4230.simulator.structs;

import java.util.LinkedList;
import java.util.List;

public class PriorityQueue<T extends Comparable<? super T>> {

    private List<T> backingList;
    private int size;

    public PriorityQueue() {
        this.backingList = new LinkedList<>();
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
