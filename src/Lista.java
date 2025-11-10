public class Lista <T> {
    private Node head;
    public Lista(){
        head = null;
    }
    public  T get(int index){
        if(head == null){
            System.out.println("Error: Elemento no encontrado");
            return null;
        }
        Node curr = head;
        for(int j=0;j<index;j++){
            curr = curr.next;
        }
        return (T) curr.data;
    }
    public int size(){
        int size = 0;
        Node curr = head;
        while(curr != null){
            size++;
            curr = curr.next;
        }
        return size;
    }
    public Node <T> insert (T data){
        Node newNode = new Node(data,"rojo");
        newNode.next = null;
        if(head == null){
            head = newNode;
        }else{
            Node last = head;
            while(last.next!=null){
                last = last.next;
            }

            last.next = newNode;
        }
        return head;
    }

    public void printList (){
        if(vacia()){
            System.out.println("Lista Vacia");
        }
        Node current = head;
        while(current != null){
            System.out.println(current.data);
            current = current.next;
        }
    }

    public Node <T> remove (int key){

        if(head==null){
            System.out.println("List is empty");
        }else{
            Node currentNode = head, prev = null;
            if(key==0){
                head = currentNode.next;
                System.out.println("Found key " + key + " and deleted");
                return head;
            }
            for(int i=0;i<key;i++){
                prev = currentNode;
                currentNode = currentNode.next;
            }

            if(currentNode!=null){
                prev.next = currentNode.next;
                System.out.println("Found key " + key + " and deleted");
            }

            if (currentNode == null){
                System.out.println(key + " not found");
            }
        }

        return head;
    }
    public boolean vacia(){
        return head == null;
    }
}
