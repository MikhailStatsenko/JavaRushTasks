package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    int size;
    Entry<String> root;

    public CustomTree() {
        root = new Entry<>("0");
    }

    @Override
    public boolean add(String s) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            if (entry == null)
                continue;

            if (!entry.availableToAddLeftChildren) {
                queue.add(entry.leftChild);
            } else {
                entry.leftChild = new Entry<>(s);
                entry.availableToAddLeftChildren = false;
                size++;
                return true;
            }
            if (!entry.availableToAddRightChildren) {
                queue.add(entry.rightChild);
            } else {
                entry.rightChild = new Entry<>(s);
                entry.availableToAddRightChildren = false;
                size++;
                return true;
            }
        }
        updateChildrenAvailability(root);
        return add(s);
    }

    public String getParent(String s) {
        Entry<String> result = getParentEntry(root, s);
        return (result == null) ? null : result.elementName;
    }

    private Entry<String> getParentEntry(Entry<String> root, String s) {
        if (root.leftChild != null && root.leftChild.elementName.equals(s) ||
            root.rightChild != null && root.rightChild.elementName.equals(s))
            return root;

        if (root.leftChild != null) {
            Entry<String> leftSubtree = getParentEntry(root.leftChild, s);
            if (leftSubtree != null) return leftSubtree;
        }
        if (root.rightChild != null) {
            return getParentEntry(root.rightChild, s);
        }
        return null;
    }

    private void updateChildrenAvailability(Entry<String> root) {
        if (root == null)
            return;
        if (!root.isAvailableToAddChildren() && root.leftChild == null && root.rightChild == null) {
            root.availableToAddLeftChildren = true;
            root.availableToAddRightChildren = true;
            return;
        }
        updateChildrenAvailability(root.leftChild);
        updateChildrenAvailability(root.rightChild);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String))
            throw new UnsupportedOperationException();

        String s = (String) o;

        Entry<String> entry = getParentEntry(root, s);

        if (entry == null)
            return false;

        if (entry.leftChild != null && entry.leftChild.elementName.equals(s)) {
            entry.leftChild = null;
        } else {
            entry.rightChild = null;
        }
        size = -1;
        recountEntities(root);
        return true;
    }

    private void recountEntities(Entry<String> root) {
        if (root == null)
            return;

        size++;

        if (root.leftChild != null)
            recountEntities(root.leftChild);
        if (root.rightChild != null)
            recountEntities(root.rightChild);
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }
    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}

