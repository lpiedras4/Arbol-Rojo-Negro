public class Main {
    public static void main(String[] args) {
        ArbolRojoNegro arbol = new ArbolRojoNegro();

        System.out.println(" PRUEBAS ARBOL ROJO-NEGRO ");

        // Prueba 1: Insertar valores
        System.out.println("\n1. Insertando valores: 10, 5, 15, 3, 7");
        arbol.insertarBase(10);
        arbol.insertarBase(5);
        arbol.insertarBase(15);
        arbol.insertarBase(3);
        arbol.insertarBase(7);

        // Prueba 2: Recorrido inorder
        System.out.println("\n2. Recorrido Inorder:");
        arbol.inorder();

        // Prueba 3: Validar raiz negra
        System.out.println("\n3. Validando raIz negra:");
        boolean raizNegra = arbol.validarRaizNegra();
        System.out.println("Raiz es negra: " + raizNegra);

        // Prueba 4: Validar no doble roja
        System.out.println("\n4. Validando no doble roja:");
        boolean noDobleRoja = arbol.validarNoDobleRoja(arbol.raiz);
        System.out.println("No hay doble roja: " + noDobleRoja);

        // Prueba 5: Insertar más valores y probar búsqueda
        System.out.println("\n5. Insertando más valores: 20, 1");
        arbol.insertarBase(20);
        arbol.insertarBase(1);

        System.out.println("Recorrido Inorder despues de más inserciones:");
        arbol.inorder();

        // Prueba 6: Busqueda de elementos
        System.out.println("\n6. Pruebas de búsqueda:");
        int[] valoresBuscar = {5, 15, 25, 3};
        for (int valor : valoresBuscar) {
            Nodo encontrado = arbol.buscar(valor);
            if (encontrado != null) {
                System.out.println("Valor " + valor + " encontrado - Color: " +
                        (encontrado.color ? "ROJO" : "NEGRO"));
            } else {
                System.out.println("Valor " + valor + " NO encontrado");
            }
        }

        // Prueba 7: Validaciones finales
        System.out.println("\n7. Validaciones finales:");
        System.out.println("Raiz es negra: " + arbol.validarRaizNegra());
        System.out.println("No hay doble roja: " + arbol.validarNoDobleRoja(arbol.raiz));

        // Prueba 8: Caso especial - arbol vacio
        System.out.println("\n8. Probando arbol vacío:");
        ArbolRojoNegro arbolVacio = new ArbolRojoNegro();
        System.out.println("Raiz negra en árbol vacio: " + arbolVacio.validarRaizNegra());
        System.out.println("No doble roja en arbol vacio: " + arbolVacio.validarNoDobleRoja(arbolVacio.raiz));
        System.out.print("Recorrido inorder árbol vacio: ");
        arbolVacio.inorder();
    }
}