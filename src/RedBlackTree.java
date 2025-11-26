import java.util.ArrayList;
import java.util.List;

/**
 * Arbol Rojo-Negro genérico listo para integrarse con un controlador JavaFX.
 * - Soporta T extends Comparable<T>
 * - Usa un nodo NIL sentinel
 * - Inserción y eliminación completas
 * - Recorridos (pre/in/post) que devuelven listas
 * - Metodo getNodeViews() que devuelve nodos con coordenadas (x,y) y color
 *   útil para dibujar en un Pane/Canvas.
 *
 * Nota: el cálculo de posiciones es sencillo: x se asigna en orden (in-order)
 * y depende de un contador; y = profundidad * levelGap.
 */
public class RedBlackTree<T extends Comparable<T>> {

    /** Nodo interno genérico */
    public static class Node<T extends Comparable<T>> {
        T value;
        Node<T> left, right, parent;
        boolean color; // true = RED, false = BLACK

        // Para uso visual (se rellenan con computeNodePositions)
        double x, y;

        Node(T value) {
            this.value = value;
            this.left = this.right = this.parent = null;
            this.color = true; // nuevo nodo por defecto ROJO
        }

        boolean isRed() { return color; }//color == true es ROJO
        boolean isBlack() { return !color; } //color != true es NEGRO
    }

    // Clase que el controlador puede usar para dibujar
    public static class NodeView<T> {
        public T value;
        public boolean red;
        public double x, y;
        public int parentIndex; // -1 = es raíz
    }

    private final Node<T> NIL;
    private Node<T> root;

    public RedBlackTree() {
        NIL = new Node<>(null);
        NIL.color = false; // NIL es negro
        NIL.left = NIL.right = NIL.parent = NIL;
        root = NIL;
    }

    /* ------------------------- OPERACIONES PUBLICAS ------------------------- */

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
    public boolean eliminar(T value) {
        Node<T> z = buscarNodo(value);
        if (z == null || z == NIL) return false;
        deleteNode(z);
        return true;
    }

    // Busca y devuelve el valor (o null)
    public T buscar(T value) {
        Node<T> n = buscarNodo(value);
        return (n == null || n == NIL) ? null : n.value;
    }

    /* ------------------------- RECORRIDOS ------------------------- */
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



    /* ------------------------- IMPLEMENTACION PRIVADA ------------------------- */

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
        Node<T> cur = root;
        while (cur != NIL) {
            int cmp = value.compareTo(cur.value);
            if (cmp == 0) return cur;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return null;
    }

    /* ------------------------- ROTACIONES ------------------------- */
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

    /* ------------------------- ARREGLAR INSERCION ------------------------- */
    private void insertFixup(Node<T> z) {
        while (z.parent != NIL && z.parent.isRed()) {
            if (z.parent == z.parent.parent.left) {
                Node<T> y = z.parent.parent.right;
                if (y != NIL && y.isRed()) {
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
                if (y != NIL && y.isRed()) {
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

    /* ------------------------- ELIMINACION ------------------------- */
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
        while (x != root && x.isBlack()) {
            if (x == x.parent.left) {
                Node<T> w = x.parent.right;
                if (w.isRed()) {
                    w.color = false;
                    x.parent.color = true;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.isBlack() && w.right.isBlack()) {
                    w.color = true;
                    x = x.parent;
                } else {
                    if (w.right.isBlack()) {
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
                if (w.isRed()) {
                    w.color = false;
                    x.parent.color = true;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.isBlack() && w.left.isBlack()) {
                    w.color = true;
                    x = x.parent;
                } else {
                    if (w.left.isBlack()) {
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

}
