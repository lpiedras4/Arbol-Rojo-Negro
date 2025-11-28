import java.util.ArrayList;
import java.util.List;
public class RedBlackTree<T extends Comparable<T>> {
    private final Node<T> NIL;
    private Node<T> root;

    public RedBlackTree() {
        NIL = new Node<>(null);
        NIL.color = false; // NIL es negro
        NIL.left = NIL.right = NIL.parent = NIL;
        root = NIL;
    }
    public Node<T> getRoot() { return root == NIL ? null : root; }

    public boolean isEmpty() { return root == NIL; }

    // Inserta un valor
    public void insertar(T value) {
        Node<T> node = new Node<>(value);
        node.left = node.right = node.parent = NIL;
        node.color = true; // rojo

        Node<T> y = NIL;
        Node<T> x = root;

        while (x != NIL) {
            y = x;
            if (value.compareTo(x.value) < 0) x = x.left;
            else x = x.right;
        }

        node.parent = y;
        if (y == NIL) {
            root = node;
        } else if (value.compareTo(y.value) < 0) {
            y.left = node;
        } else {
            y.right = node;
        }

        insertFixup(node);
    }

    // Elimina el primer nodo que coincida con value, devuelve true si se eliminó
    Node<T> eliminar(T value) {
        Node<T> nodoEliminar = buscarNodo(value);
        if (nodoEliminar == null || nodoEliminar == NIL) return null;

        deleteNode(nodoEliminar);
        return nodoEliminar;   // z es nodo eliminado
    }
    public boolean eliminarM(T valor) {
        Node<T> nodoEliminar = eliminar(valor);
        return nodoEliminar != null;   // true
    }
//
    public List<T> inorder() {
        List<T> out = new ArrayList<>();
        inorderRec(root, out);
        return out;
    }

    public List<T> preorder() {
        List<T> out = new ArrayList<>();
        preorderRec(root, out);
        return out;
    }

    public List<T> postorder() {
        List<T> out = new ArrayList<>();
        postorderRec(root, out);
        return out;
    }

    /* ------------------------- UTILIDADES PARA LA UI ------------------------- */

    /**
     * Devuelve una lista de NodeView con posiciones x,y (en píxeles) y color.
     * parentIndex indica el índice en esta lista del padre o null si es raíz.
     * Las posiciones se calculan con un recorrido inorder para X y profundidad para Y.
     *
     * @param levelGap separación vertical entre niveles (ej: 80)
     * @param nodeGap separación horizontal base por unidad de x (ej: 60)
     */
    public java.util.List<NodeView<T>> getNodeViews(double levelGap, double nodeGap) {
        java.util.List<NodeView<T>> lista = new ArrayList<>();
        generarCoordenadas(root, 0, 440, 40, levelGap, nodeGap, -1, lista);
        return lista;
    }

    private void generarCoordenadas(Node<T> nodo, int nivel, double x, double y,
                                    double levelGap, double nodeGap,
                                    int padreIndex, java.util.List<NodeView<T>> lista) {

        if (nodo == NIL) return;

        NodeView<T> nv = new NodeView<>();
        nv.value = nodo.value;
        nv.red = nodo.color;
        nv.x = x;
        nv.y = y;
        nv.parentIndex = padreIndex;

        lista.add(nv);
        int indexActual = lista.size() - 1;

        // izquierda
        generarCoordenadas(
                nodo.left,
                nivel + 1,
                x - nodeGap * (4.0 / (nivel + 1)),
                y + levelGap,
                levelGap, nodeGap,
                indexActual,
                lista
        );

        // derecha
        generarCoordenadas(
                nodo.right,
                nivel + 1,
                x + nodeGap * (4.0 / (nivel + 1)),
                y + levelGap,
                levelGap, nodeGap,
                indexActual,
                lista
        );
    }
    private void inorderRec(Node<T> node, List<T> out) {
        if (node == NIL) return;
        inorderRec(node.left, out);
        out.add(node.value);
        inorderRec(node.right, out);
    }

    private void preorderRec(Node<T> node, List<T> out) {
        if (node == NIL) return;
        out.add(node.value);
        preorderRec(node.left, out);
        preorderRec(node.right, out);
    }

    private void postorderRec(Node<T> node, List<T> out) {
        if (node == NIL) return;
        postorderRec(node.left, out);
        postorderRec(node.right, out);
        out.add(node.value);
    }

    private Node<T> buscarNodo(T value) {
        Node<T> actual = root;
        while (actual != NIL) {
            int nodoComp = value.compareTo(actual.value);
            if (nodoComp == 0) return actual;
            actual = (nodoComp < 0) ? actual.left : actual.right;
        }
        return null;
    }

    public boolean buscar(T valor) {
        Node<T> nodoBus = buscarNodo(valor);
        return nodoBus != null && nodoBus != NIL;
    }
    private void leftRotate(Node<T> x) {
        Node<T> y = x.right;
        x.right = y.left;
        if (y.left != NIL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == NIL) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node<T> x) {
        Node<T> y = x.left;
        x.left = y.right;
        if (y.right != NIL) y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == NIL) root = y;
        else if (x == x.parent.right) x.parent.right = y;
        else x.parent.left = y;
        y.right = x;
        x.parent = y;
    }
    private void insertFixup(Node<T> z) {
        while (z.parent != NIL && z.parent.esRojo()) {
            if (z.parent == z.parent.parent.left) {
                Node<T> y = z.parent.parent.right;
                if (y != NIL && y.esRojo()) {
                    z.parent.color = false;
                    y.color = false;
                    z.parent.parent.color = true;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = false;
                    z.parent.parent.color = true;
                    rightRotate(z.parent.parent);
                }
            } else {
                Node<T> y = z.parent.parent.left;
                if (y != NIL && y.esRojo()) {
                    z.parent.color = false;
                    y.color = false;
                    z.parent.parent.color = true;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = false;
                    z.parent.parent.color = true;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = false;
    }
    private void deleteNode(Node<T> z) {
        Node<T> y = z;
        Node<T> x;
        boolean yOriginalColor = y.color;

        if (z.left == NIL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == NIL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                if (x != NIL) x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (!yOriginalColor) {
            deleteFixup(x);
        }
    }

    private void transplant(Node<T> u, Node<T> v) {
        if (u.parent == NIL) root = v;
        else if (u == u.parent.left) u.parent.left = v;
        else u.parent.right = v;
        v.parent = u.parent;
    }

    private Node<T> minimum(Node<T> node) {
        while (node.left != NIL) node = node.left;
        return node;
    }

    private void deleteFixup(Node<T> x) {
        while (x != root && x.esNegro()) {
            if (x == x.parent.left) {
                Node<T> w = x.parent.right;
                if (w.esRojo()) {
                    w.color = false;
                    x.parent.color = true;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.esNegro() && w.right.esNegro()) {
                    w.color = true;
                    x = x.parent;
                } else {
                    if (w.right.esNegro()) {
                        w.left.color = false;
                        w.color = true;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = false;
                    w.right.color = false;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                Node<T> w = x.parent.left;
                if (w.esRojo()) {
                    w.color = false;
                    x.parent.color = true;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.esNegro() && w.left.esNegro()) {
                    w.color = true;
                    x = x.parent;
                } else {
                    if (w.left.esNegro()) {
                        w.right.color = false;
                        w.color = true;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = false;
                    w.left.color = false;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = false;
    }

    /* ------------------------- POSICIONES PARA UI ------------------------- */
    // helper para asignar x por orden y y por profundidad
    private static class PosCounter {
        int count = 0;
        final double nodeGap;
        final double levelGap;
        PosCounter(double nodeGap, double levelGap) { this.nodeGap = (int) nodeGap; this.levelGap = levelGap; }
    }

    private void computeNodePositions(Node<T> node, int depth, PosCounter counter) {
        if (node == NIL) return;
        computeNodePositions(node.left, depth + 1, counter);
        node.x = counter.count * counter.nodeGap;
        node.y = depth * counter.levelGap;
        counter.count++;
        computeNodePositions(node.right, depth + 1, counter);
    }

    private void collectNodesInOrder(Node<T> node, List<Node<T>> out) {
        if (node == NIL) return;
        collectNodesInOrder(node.left, out);
        out.add(node);
        collectNodesInOrder(node.right, out);
    }

    //para eliminar
    private void trasplantar(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    private Node minValue(Node n) {
        while (n.left != null) n = n.left;
        return n;
    }

    private Node buscarNodo(Node actual, T valor) {
        while (actual != null) {
            int cmp = valor.compareTo((T) actual.value);
            if (cmp == 0) return actual;
            actual = (cmp < 0) ? actual.left : actual.right;
        }
        return null;
    }

    private void eliminarNodo(Node<T> z) {
        Node<T> y = z;
        Node<T> x;
        boolean yOriginalColor = y.color;

        if (z.left == NIL) { //sin hijo izquierdo -> reemplazamos por el derecho
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == NIL) { //sin hijo derecho -> reemplazamos por el izquierdo
            x = z.left;
            transplant(z, z.left);
        } else { //sin hijos -> sustituimos por sucesor
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) { //mientras sea hijo de z, no importa que sea NULL o NIL
                x.parent = y;
            } else { //y es reemplazado por hijo derecho
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y); //z reemplazado por y
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color; //mantenemos el color de z
        }

        if (!yOriginalColor) { //si el nodo sucesor (o el eliminado) era negro, aseguramos que cumpla con las reglas
            deleteFixup(x);
        }
    }

    private void transplante(Node<T> u, Node<T> v) { //reemplaza un subarbol por otro subarbol
        if (u.parent == NIL) root = v;
        else if (u == u.parent.left) u.parent.left = v;
        else u.parent.right = v;
        v.parent = u.parent;
    }

    private Node<T> minimo(Node<T> node) {
        while (node.left != NIL) node = node.left;
        return node;
    }

    private void eliminarFixup(Node<T> x) { //asegura cumplimeinto de propiedades
        while (x != root && x.esNegro()) {
            if (x == x.parent.left) {
                Node<T> w = x.parent.right;
                if (w.esRojo()) {
                    w.color = false;
                    x.parent.color = true;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.esNegro() && w.right.esNegro()) {
                    w.color = true;
                    x = x.parent;
                } else {
                    if (w.right.esNegro()) {
                        w.left.color = false;
                        w.color = true;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = false;
                    w.right.color = false;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                Node<T> w = x.parent.left;
                if (w.esRojo()) {
                    w.color = false;
                    x.parent.color = true;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.esNegro() && w.left.esNegro()) {
                    w.color = true;
                    x = x.parent;
                } else {
                    if (w.left.esNegro()) {
                        w.right.color = false;
                        w.color = true;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = false;
                    w.left.color = false;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = false; //x debe ser negro
    }


}
