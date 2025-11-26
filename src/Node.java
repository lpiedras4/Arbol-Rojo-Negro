public class Node <T>{
        T data;
        Node<T> next, left, right, parent;
        String color;
        public Node (T data){
            this.data = data;
            this.color = "ROJO"; //siempre inicializados como rojos
        }


}
