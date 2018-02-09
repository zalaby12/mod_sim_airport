package edu.cx4230.simulator.structs;

import edu.cx4230.simulator.util.Print;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Set<T> implements Iterable<T> {

    private List<T> backingList;
    private int size;

    public Set() {
        this.backingList = new ArrayList<>();
    }

    public void add(T t) {
        if (!backingList.contains(t)) {
            size++;
            backingList.add(t);
        } else {
            Print.line("Found a collision with " + t.toString());
        }
    }

    public T removeRandom() {
       return backingList.remove(new Random().nextInt(backingList.size()));
    }

    public T remove(T t) {
        if (backingList.indexOf(t) != -1) {
            size--;
            return backingList.remove(backingList.indexOf(t));
        }
        return null;
    }

    public int size() { return this.size; }

    public Iterator<T> iterator() {
        return backingList.iterator();
    }

}
