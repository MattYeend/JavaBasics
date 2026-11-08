package matthewyeend.hashset;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @param <E> the element type
 * @author matthewyeend
 */
public class HashSet<E> extends AbstractSet<E> {

    private final Node<E>[] buckets;
    private int size;

    /**
     * Constructs a hash table.
     *
     * @param bucketsLength the length of the buckets array, which must be positive
     * @throws IllegalArgumentException if bucketsLength is not positive
     */
    @SuppressWarnings("unchecked")
    public HashSet(int bucketsLength) {
        if (bucketsLength <= 0) {
            throw new IllegalArgumentException("bucketsLength must be positive");
        }
        buckets = (Node<E>[]) new Node[bucketsLength];
    }

    /**
     * Maps an object to a bucket index. floorMod always returns a value in
     * the range 0 to length - 1, including for negative hash codes.
     */
    private int indexFor(Object x) {
        return Math.abs(Objects.requireNonNull(x).hashCode() % buckets.length);
    }

    /**
     * Tests for set membership.
     *
     * @param x an object
     * @return true if x is an element of this set
     */
    @Override
    public boolean contains(Object x) {
        Node<E> current = buckets[indexFor(x)];
        while (current != null) {
            if (current.data.equals(x)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Adds an element to this set.
     *
     * @param x an object
     * @return true if x is a new object, false if x was already in the set
     */
    @Override
    public boolean add(E x) {
        int h = indexFor(x);
        Node<E> current = buckets[h];
        Node<E> last = null;
        while (current != null) {
            if (current.data.equals(x)) {
                return false; // Already in the set
            }
            last = current;
            current = current.next;
        }
        Node<E> newNode = new Node<>(x, null);
        if (last == null) {
            buckets[h] = newNode;
        } else {
            last.next = newNode;
        }
        size++;
        return true;
    }

    /**
     * Removes an object from this set.
     *
     * @param x an object
     * @return true if x was removed, false if x was not an element of this set
     */
    @Override
    public boolean remove(Object x) {
        int h = indexFor(x);
        Node<E> current = buckets[h];
        Node<E> previous = null;
        while (current != null) {
            if (current.data.equals(x)) {
                if (previous == null) {
                    buckets[h] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    /**
     * Returns an iterator that traverses the elements of this set.
     *
     * @return a hash set iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new HashSetIterator();
    }

    /**
     * Gets the number of elements in the set.
     *
     * @return the number of elements
     */
    @Override
    public int size() {
        return size;
    }

    private static final class Node<T> {
        private final T data;
        private Node<T> next;

        private Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    private final class HashSetIterator implements Iterator<E> {
        private int bucket = -1;
        private Node<E> current;
        private Node<E> lastReturned;

        @Override
        public boolean hasNext() {
            return (current != null && current.next != null)
                    || nextNonEmptyBucket(bucket + 1) < buckets.length;
        }

        @Override
        public E next() {
            if (current != null && current.next != null) {
                current = current.next;
            } else {
                int b = nextNonEmptyBucket(bucket + 1);
                if (b >= buckets.length) {
                    throw new NoSuchElementException();
                }
                bucket = b;
                current = buckets[b];
            }
            lastReturned = current;
            return current.data;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException("next() has not been called, "
                        + "or remove() was already called");
            }
            // Delegating keeps size correct. The removed node keeps its next
            // pointer, so the iterator can carry on from 'current'.
            HashSet.this.remove(lastReturned.data);
            lastReturned = null;
        }

        private int nextNonEmptyBucket(int from) {
            int b = from;
            while (b < buckets.length && buckets[b] == null) {
                b++;
            }
            return b;
        }
    }
}
