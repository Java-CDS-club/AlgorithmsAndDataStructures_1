package LinkedList;

import java.util.Collection;
import java.util.Iterator;

/**
 * A Node in the LinkedList
 *
 * @param <T> is the generic type of the value the node will contain
 */
class LinkedListNode<T> {
    /**
     * The next node in the Linked List (null if last node)
     */
    LinkedListNode<T> next;
    /**
     * The node value
     */
    private T value;

    /**
     * Constructs a new node with the specified value.
     *
     * @param value is the value contained within the node
     */
    LinkedListNode(T value) {
        this.value = value;
    }

    T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

    LinkedListNode<T> getNext() {
        return next;
    }

    void setNext(LinkedListNode<T> next) {
        this.next = next;
    }
}

/**
 * A linked list collection capable of basic operations such as
 * Add, Remove, Find and Enumerate
 *
 * @param <T> is the generic type the Linked List can contain
 */
class LinkedList<T> implements Collection {
    /**
     * The first node in the list or null if empty
     */
    private LinkedListNode<T> Head;
    /**
     * The last node in the list or null if empty
     */
    private LinkedListNode<T> Tail;
    private int count = 0;

    public LinkedListNode<T> getHead() {
        return Head;
    }

    private void setHead(LinkedListNode<T> head) {
        Head = head;
    }

    public LinkedListNode<T> getTail() {
        return Tail;
    }

    private void setTail(LinkedListNode<T> tail) {
        Tail = tail;
    }

    /**
     * Add the specified value to the start of the Linked List
     *
     * @param value The value to add to the start of the list
     */
    public void AddFirst(T value) {
        AddFirst(new LinkedListNode<T>(value));
    }

    /**
     * Adds the specified node to the start of the link list
     *
     * @param node The node to add to the start of the list
     */
    void AddFirst(LinkedListNode<T> node) {

        // Save off the head node so we don't lose it
        LinkedListNode<T> temp = Head;

        // Point head to the new node
        Head = node;

        // Insert the rest of the list behind the head
        Head.next = temp;

        count++;

        if (count == 1) {
            // if the list was empty then Head and Tail should
            // both point to the new node.
            Tail = Head;
        }
    }

    /**
     * Add the value to the end of the list
     *
     * @param value The value to add
     */
    public void AddLast(T value) {
        AddLast(new LinkedListNode<T>(value));
    }

    /**
     * Adds the specified node to the end of the list
     *
     * @param node The node to add to the end of the list
     */
    public void AddLast(LinkedListNode<T> node) {
        if (count == 0) {
            Head = node;
        } else {
            Tail.next = node;
        }
        Tail = node;
        count++;
    }

    /**
     * Removes the last node from the list
     */
    public void RemoveLast() {
        if (count != 0) {
            if (count == 1) {
                Head = null;
                Tail = null;
            } else {
                LinkedListNode<T> current = Head;
                while (current.next != Tail) {
                    current = current.next;
                }

                current.next = null;
                Tail = current;
            }

            count--;
        }
    }

    /**
     * Removes the first node from the list.
     */
    public void RemoveFirst() {
        if (count != 0) {
            Head = Head.next;
            count--;

            if (count == 0) {
                Tail = null;
            }
        }
    }

    /**
     * The number of items currently in the list
     *
     * @return the number of items currently in the list
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Check if the list is empty
     *
     * @return True when the list is empty
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if the list contains the specified item,
     * false otherwise.
     *
     * @param o The item to search for
     * @return True if the item is found, false otherwise
     */
    @Override
    public boolean contains(Object o) {
        LinkedListNode<T> current = Head;
        while (current != null) {
            if (current.getValue().equals(o)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public Iterator iterator() {

        return new Iterator() {
            LinkedListNode<T> lastReturned;
            LinkedListNode<T> next;
            int nextIndex;

            @Override
            public boolean hasNext() {
                return nextIndex < size();
            }

            @Override
            public Object next() {
                lastReturned = next;
                next = next.getNext();
                nextIndex++;
                return lastReturned.getValue();
            }
        };
    }

    /**
     * Copies the node values into an array.
     *
     * @return the formed array
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        LinkedListNode<T> current = Head;
        for (int i = 0; i < size(); i++) {
            array[i] = current.getValue();
            current = current.getNext();
        }
        return array;
    }

    /**
     * Adds the specified value to the front of the list
     *
     * @param o The value to add
     * @return True after adding the object
     */
    @Override
    public boolean add(Object o) {
        AddFirst((T) o);
        return true;
    }

    /**
     * Removes the first occurrence of the item from the list (searching
     * from Head to Tail).
     *
     * @param o The item to remove
     * @return True if the item was found and removed, false otherwise
     */
    @Override
    public boolean remove(Object o) {
        LinkedListNode<T> previous = null;
        LinkedListNode<T> current = Head;

        // 1: Empty list - do nothing
        // 2: Single node: (previous is null)
        // 3: Many nodes
        //    a: node to remove is the first node
        //    b: node to remove is the middle or last
        while (current != null) {
            if (current.getValue().equals(o)) {
                // It's a node in the middle or end
                if (previous != null) {
                    // Case 3b
                    previous.setNext(current.getNext());

                    // It was the end - so update Tail
                    if (current.getNext() == null) {
                        Tail = previous;
                    }
                    count--;
                } else {
                    // Case 2 or 3a
                    RemoveFirst();
                }
                return true;
            }

            previous = current;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object o : c) {
            add(o);
        }
        return true;
    }

    @Override
    public void clear() {
        setHead(null);
        setTail(null);
        count = 0;
    }

    @Override
    public boolean retainAll(Collection c) {
        return addAll(c);
    }

    @Override
    public boolean removeAll(Collection c) {
        for (Object o : c) {
            remove(o);
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        LinkedListNode<T> current = Head;
        for (int i = 0; i < a.length || current == null; i++) {
            a[i] = current.getValue();
            current = current.getNext();
        }
        return a;
    }
}