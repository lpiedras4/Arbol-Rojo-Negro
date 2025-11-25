class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;
    Nodo padre;
    boolean color; // true = ROJO, false = NEGRO

    public Nodo(int valor) {
        this.valor = valor;
        this.color = true; // Por defecto rojo
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }
}

public class ArbolRojoNegro {
    protected Nodo raiz;

    public Nodo insertarBase(int valor) {
        Nodo nuevo = new Nodo(valor);
        Nodo padre = null;
        Nodo actual = raiz;

        while(actual != null) {
            padre = actual;
            if(valor < actual.valor) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
        }

        nuevo.padre = padre;
        if (padre == null) {
            raiz = nuevo;
        } else if (valor < padre.valor) {
            padre.izquierdo = nuevo;
        } else {
            padre.derecho = nuevo;
        }
        return nuevo;
    }

    public boolean validarRaizNegra() {
        if (raiz == null) return true;
        return !raiz.color; // Debe ser false (NEGRO)
    }

    public boolean validarNoDobleRoja(Nodo nodo) {
        if(nodo == null) return true;

        if(nodo.color == true) { // Si el nodo es ROJO
            if((nodo.izquierdo != null && nodo.izquierdo.color == true) ||
                    (nodo.derecho != null && nodo.derecho.color == true)) {
                return false; // Doble rojo encontrado
            }
        }
        return validarNoDobleRoja(nodo.izquierdo) && validarNoDobleRoja(nodo.derecho);
    }

    public void inorder() {
        inorderRecursivo(raiz);
        System.out.println();
    }

    private void inorderRecursivo(Nodo nodo) {
        if(nodo != null) {
            inorderRecursivo(nodo.izquierdo);
            System.out.print(nodo.valor + (nodo.color ? "R " : "N "));
            inorderRecursivo(nodo.derecho);
        }
    }

    public Nodo buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private Nodo buscarRecursivo(Nodo nodo, int valor) {
        if (nodo == null || nodo.valor == valor) {
            return nodo;
        }
        if (valor < nodo.valor) {
            return buscarRecursivo(nodo.izquierdo, valor);
        } else {
            return buscarRecursivo(nodo.derecho, valor);
        }
    }
}

