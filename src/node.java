class node {
    private int value;
    private node next;

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    node getNext() {
        return next;
    }

    void setNext(node next) {
        this.next = next;
    }
}

class program {
    public static void main(String[] args){
        node first = new node();
        first.setValue(3);

        node middle = new node();
        middle.setValue(5);

        first.setNext(middle);

        node last = new node();
        last.setValue(7);

        middle.setNext(last);

        PrintList(first);
    }

    private static void PrintList(node node){
        while (node != null){
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }
}
