class Nodo {
    int dato;
    Nodo der;
    Nodo izq;
    Nodo parentesco;
    boolean color;

    Nodo(int dato){
        this.dato = dato;
        this.der = null;
        this.izq = null;
    }
}

