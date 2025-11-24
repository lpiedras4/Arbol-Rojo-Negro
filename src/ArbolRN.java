public class ArbolRN <T extends Comparable<T>> {
    private Node root;

    public ArbolRN(Node root) {
        this.root = root;
    }

    public void crearArbol() {

    }

    public void insertarNodo(T data) { //basic binary search tree insertion :3
        Node<T> nuevo = new Node<>(data);
        if (root == null) { //si el arbol esta vacio, la raiz es negra
            nuevo.color = "NEGRO";
            root = nuevo;
            return;
        }

        Node<T> actual = root;
        Node<T> padre = null;

        while (actual != null) {
            padre = actual;
            if (data.compareTo(padre.data) < 0) {
                actual = actual.left;
            } else {
                actual = actual.right;
            }
        }
        nuevo.parent = padre.parent;

        if (data.compareTo(padre.data) < 0) {
            padre.left = nuevo;
        } else {
            padre.right = nuevo;
        }
        fixInsert(nuevo);
    }

    public void fixInsert(Node<T> nodo) { //ensuring red-black rules are followed yall
        while (nodo.parent != null && nodo.parent.color.equals("ROJO")) {
            Node<T> granny = nodo.parent.parent;
            //parent to the left
            if (nodo.parent == granny) {
                Node<T> uncle = granny.right;
                //si el tio es rojo...
                if (uncle != null && uncle.color.equals("ROJO")) {
                    nodo.parent.color = "NEGRO";
                    uncle.color = "NEGRO";
                    granny.color = "ROJO";
                    nodo = granny;
                } else { //si el nodo esta a la derecha, se rota a la izquierda
                    if (nodo == nodo.parent.right) {
                        nodo = nodo.parent;
                        rotarIzq(nodo);
                    }
                    //si el nodo esta a la izquierda, se rota a la derecha
                    nodo.parent.color = "NEGRO";
                    granny.color = "ROJO";
                    rotarDer(granny);
                }
            }
            //si el padre esta a la derecha
            else {
                Node<T> uncle = granny.left;
                if (uncle != null && uncle.color.equals("ROJO")) {
                    nodo.parent.color = "NEGRO";
                    uncle.color = "NEGRO";
                    granny.color = "ROJO";
                    nodo = granny;
                } else {
                    if (nodo == nodo.parent.left) {
                        nodo = nodo.parent;
                        rotarDer(nodo);
                    }
                    nodo.parent.color = "NEGRO";
                    granny.color = "ROJO";
                    rotarIzq(granny);
                }
            }
        }
        root.color = "NEGRO";
    }

    public void eliminarNodo(T data) {

    }

    public Node<T> buscarNodo(T data) {
        Node<T> nodo = root;
        while (nodo != null) {
            if (data.equals(nodo.data)) {
                return nodo;
            } else if (data.compareTo(nodo.data) < 0) {
                nodo = nodo.left;
            } else {
                nodo = nodo.right;
            }
        } return null;
    }
    public  void rotarIzq(Node<T> root){
        Node<T> nuevo = root.right;
        root.right = nuevo.left; //adecuar atributo parent
        if (nuevo.left != null){ nuevo.left.parent = root;}
        else if(root == root.parent.left){root.parent.left = nuevo;}
        else{root.parent.right = nuevo;}
        nuevo.left = root;
        root.parent = nuevo;
    }
    public  void rotarDer(Node<T> root){
        Node<T> nuevo = root.left;
        root.left = nuevo.right;
        if (nuevo.right != null){ nuevo.right.parent = root;}
        else if (root == root.parent.right){root.parent.right = nuevo;}
        else{root.parent.left = nuevo;}
        nuevo.right = root;
        root.parent = nuevo;
    }
    public Lista <T> preOrden (Node<T> nodo, Lista<T> list){
        if (nodo != null){
            list.insert(nodo.data);
            preOrden(nodo.left, list);
            preOrden(nodo.right, list);
        } return list;
    }
    public Lista <T> entreOrden (Node<T> nodo, Lista<T> list){
        if (nodo != null){
            entreOrden(nodo.left, list);
            list.insert(nodo.data);
            entreOrden(nodo.right, list);
        } return list;
    }
    public Lista <T> postOrden (Node<T> nodo, Lista<T> list){
        if (root != null){
            postOrden(nodo.left, list);
            postOrden(nodo.right, list);
            list.insert(nodo.data);
        } return list;
    }
}