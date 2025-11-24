public class ArbolRN <T> {
private Node root;
public ArbolRN (Node root){
    this.root = root;
}
public void crearArbol (){

}
    public void insertarNodo(T data){
    Node <T> curr = new Node<>(data,"rojo");
    Node <T> nodoEncontrado = busqRecurs(root,data);
    if (nodoEncontrado==null){
    nodoEncontrado = nodoEncontrado.getPadre();
    }
    }
    public  void eliminarNodo(T data){

    }
    public Node<T> buscarNodo(T data){
    Node <T> nodoEncontrado = busqRecurs(root,data);
    balancear(nodoEncontrado);
    return nodoEncontrado;
    }
    private Node <T> busqRecurs(Node nodo, T valor){
    if(valor == nodo.getData()){
        return nodo;
    }else if((int)valor< (int)nodo.getData()){
    if (nodo.getLeftChild()==null){
        return nodo;
    }
    return busqRecurs(nodo.getLeftChild(),valor);
    }else{
        if(nodo.getRightChild()==null){
            return nodo;
        }
        return busqRecurs(nodo.getRightChild(),valor);
    }

    }
    public  void rotarIzq(T data){

    }
    public  void rotarDer(T data){

    }
    public Lista<T> preOrden (){
    return null;
    }
    public Lista<T> entreOrden (){
        return null;
    }
    public Lista<T> posOrden (){
        return null;
    }
    public Lista<T> aLoAncho (){
        return null;
    }
}
