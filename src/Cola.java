
public class Cola <T>{
    Node frente = null;
    Node fondo = frente;
    Node adicionar(T data) {
        Node node = new Node(data,"rojo");
        if(frente==null){
            frente = node;
            fondo = node;
        }else{
            fondo.next= node;
            fondo=node;

        }
        return frente;
    }
    void imprimir() {
        Node current = frente;
        System.out.println("Imprimiendo cola");
        while(current!=null){
            System.out.println(current.data + "\n" + "-");
            current = current.next;
        }
    }
    T extraer() {
        Node<T> remove = frente;
        if(frente == null){
            System.out.println("La cola está vacía");
            return null;
        }
        frente = frente.next;
        return remove.data;
    }
    Object frente() {
        if (frente==null){
            System.out.println("La cola esta vacía");
            return null;
        }else{
            return frente.data;
        }
    }
    Object fondo() {
        if (frente==null){
            System.out.println("La cola esta vacía");
            return null;
        }else{

            while(fondo.next!=null){
                fondo = fondo.next;
            }
            return fondo.data;
        }

    }
    boolean vacia() {
        if(frente==null){
            return true;
        }else{
            return false;
        }
    }

}
