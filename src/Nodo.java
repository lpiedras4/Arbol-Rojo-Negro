public class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;
    Nodo padre;
    boolean color; // true = ROJO, false = NEGRO

    public Nodo(int valor) {
        this.valor = valor;
        this.color = true;
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }
}
