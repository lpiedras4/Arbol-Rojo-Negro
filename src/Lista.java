public class Lista <T extends Comparable<T>>{
    private Node head = null;

    public T get(int index) {
        if (this.head == null) {
            System.out.println("Error: Elemento no encontrado");
            return null;
        } else {
            Node curr = this.head;

            for(int j = 0; j < index; ++j) {
                curr = curr.next;
            }

            return (T)curr.data;
        }
    }

    public int size() {
        int size = 0;

        for(Node curr = this.head; curr != null; curr = curr.next) {
            ++size;
        }

        return size;
    }

    public Node<T> insert(T data) {
        Node newNode = new Node(data);
        newNode.next = null;
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node last;
            for(last = this.head; last.next != null; last = last.next) {
            }

            last.next = newNode;
        }

        return this.head;
    }

    public void printList() {
        if (this.vacia()) {
            System.out.println("Lista Vacia");
        }

        for(Node current = this.head; current != null; current = current.next) {
            System.out.println(current.data);
        }

    }

    public Node<T> remove(int key) {
        if (this.head == null) {
            System.out.println("List is empty");
        } else {
            Node currentNode = this.head;
            Node prev = null;
            if (key == 0) {
                this.head = currentNode.next;
                System.out.println("Found key " + key + " and deleted");
                return this.head;
            }

            for(int i = 0; i < key; ++i) {
                prev = currentNode;
                currentNode = currentNode.next;
            }

            if (currentNode != null) {
                prev.next = currentNode.next;
                System.out.println("Found key " + key + " and deleted");
            }

            if (currentNode == null) {
                System.out.println(key + " not found");
            }
        }

        return this.head;
    }

    public boolean vacia() {
        return this.head == null;
    }
}
