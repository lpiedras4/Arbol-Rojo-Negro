class Nodo{
    int valor;
    Nodo izquierdo;
    Nodo derecho;
    Nodo padre;
    boolean color;
    public Nodo (int valor){
        this.valor = valor;
        this.color = true;
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }
}
public class ArbolRojoNegro {
    protected Nodo raiz;
    public Nodo insertarrBase(int valor) {
        Nodo nuevo = new Nodo(valor);
        Nodo padre = null;
        Nodo actual = raiz;
        while(actual != null){
            padre = actual;
            if(valor < actual.valor){
                actual = actual.izquierdo;
            }
            else {
                actual = actual.derecho;
            }
        }
        nuevo.padre = padre;
        if (padre == null){
            raiz = nuevo;
        } else if (valor < padre.valor) {
            padre.izquierdo = nuevo;
        }
        else {
            padre.derecho = nuevo;
        }
        return nuevo;
    }

    public boolean validarRaizNegra(){
        if (raiz == null) return true;
        return !raiz.color;
    }
    public boolean validarNoDobleRoja(Nodo nodo){
        if(nodo == null) return true;
        if(nodo.color == true){
            if((nodo.izquierdo != null && nodo.izquierdo.color == true )||(nodo.derecho != null && nodo.derecho.color == true)){
                return false;
            }
        }
        return validarNoDobleRoja(nodo.izquierdo) && validarNoDobleRoja(nodo.derecho);

    }
    public void inorder(){
        inorderRecursivo(raiz);
        System.out.println();
    }

    private void inorderRecursivo(Nodo nodo){
        if(nodo == null){
            inorderRecursivo(nodo.izquierdo);
            System.out.println(nodo.valor + (nodo.color ? "R" : "N"));
            inorderRecursivo(nodo.derecho);

        }

    }

    public static void main(String[] args) {

    }
}


