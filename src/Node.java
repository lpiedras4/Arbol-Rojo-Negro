public class Node <T>{
    T data;
    Node padre;
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
    public T getData(){
        return data;
    }
    public Node getPadre(){
        return padre;
    }
    public Node getLeftChild(){
        return left;
    }
    public Node getRightChild(){
        return right;
    }
}
