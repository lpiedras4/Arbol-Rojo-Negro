public class Node <T extends Comparable<T>>{
    T value;
    Node<T> left, right, parent;
    boolean color; // true = Rojo, false = Negro

    // Para uso visual (se rellenan con computeNodePositions)
    double x, y; //x y y se establecen para establecer las coordenadas que se usaran para dibujar el arbol.

    Node(T value) {
        this.value = value;
        this.left = this.right = this.parent = null;
        this.color = true; // nuevo nodo por defecto ROJO
    }

    boolean esRojo() { return color; }//color == true rojo
    boolean esNegro() { return !color; } //color != true negro
}
