/*
 */
package com.kongmy.ds.p5;

/**
 *
 * @author Kong My
 */
public abstract class MyAbstractList<E> implements MyList<E> {

    public MyAbstractList() {
        // Empty default constuctor
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public abstract int size();

    public abstract boolean add(E object);

    public abstract boolean add(int index, E object);

    public abstract E get(int index);

    public abstract void set(int index, E object);

    public abstract int indexOf(E object);

    public boolean remove(E object) {
        int index = indexOf(object);
        if (index >= 0) {
            remove(index);
        }
        return index >= 0;
    }

    public abstract Object remove(int index);

    public boolean contains(E object) {
        return indexOf(object) >= 0;
    }

    protected void ensureValidIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean addAll(MyList<E> otherList) {
        boolean changed = false;

        for (E object : otherList) {
            if (indexOf(object) < 0) {
                add(object);
                changed = true;
            }
        }

        return changed;
    }

    @Override
    public boolean removeAll(MyList<E> otherList) {
        boolean changed = false;

        for (E object : otherList) {
            changed |= remove(object);
        }

        return changed;
    }

    @Override
    public boolean retainAll(MyList<E> otherList) {
        boolean changed = false;

        ThisListLoop:
        for (int i = 0; i < size(); i++) {

            OtherListLoop:
            for (E object : otherList) {
                if (get(i).equals(object)) {
                    continue ThisListLoop;
                }
            }

            remove(i);
            i--;
            changed = true;
        }

        return changed;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for (E object : this) {
            builder.append(object.toString()).append(", ");
        }

        // Remove tailing comma if not empty
        if (size() > 0) {
            builder.delete(builder.length() - 2, builder.length());
        }

        builder.append("]");
        return builder.toString();
    }
}
