public class Node <T>{
    T data;
    Node next;
    Node left;
    Node right;
    String color;
    public Node (T data, String color){
        this.data = data;
        this.color = color;
        next = null;
        left = null;
        right = null;
    }

}
