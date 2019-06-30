class Node {
    private int value;
    private Node next;

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    Node getNext() {
        return next;
    }

    void setNext(Node next) {
        this.next = next;
    }
}

class program {
    public static void main(String[] args){
        Node first = new Node();
        first.setValue(3);

        Node middle = new Node();
        middle.setValue(5);

        first.setNext(middle);

        Node last = new Node();
        last.setValue(7);

        middle.setNext(last);

        PrintList(first);
    }

    private static void PrintList(Node node){
        while (node != null){
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }
}
