import java.util.Scanner;

public class PruebasConsola {
    public static void main(String[] args) {
        ArbolRojoNegro arbol = new ArbolRojoNegro();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("=== ARBOL ROJO-NEGRO INTERACTIVO ===");

        do {
            mostrarMenu();
            System.out.print("Selecciona una opcion: ");
            opcion = scanner.nextInt();

            switch(opcion) {
                case 1:
                    insertarValor(arbol, scanner);
                    break;
                case 2:
                    eliminarValor(arbol, scanner);
                    break;
                case 3:
                    buscarValor(arbol, scanner);
                    break;
                case 4:
                    System.out.println("\n--- RECORRIDO INORDER ---");
                    arbol.inorder();
                    break;
                case 5:
                    System.out.println("\n--- ESTRUCTURA DEL ARBOL ---");
                    arbol.imprimirArbol();
                    break;
                case 6:
                    System.out.println("\n--- VALIDACIONES ---");
                    mostrarValidaciones(arbol);
                    break;
                case 7:
                    System.out.println("\n--- INFORMACION COMPLETA ---");
                    mostrarInformacionCompleta(arbol);
                    break;
                case 8:
                    pruebaAutomatica();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intenta de nuevo.");
            }

            if(opcion != 0) {
                System.out.println("\nPresiona Enter para continuar...");
                scanner.nextLine();
                scanner.nextLine();
            }

        } while(opcion != 0);

        scanner.close();
    }
    private static void mostrarMenu() {
        System.out.println("\n=================================");
        System.out.println("           MENU PRINCIPAL");
        System.out.println("=================================");
        System.out.println("1. Insertar valor");
        System.out.println("2. Eliminar valor");
        System.out.println("3. Buscar valor");
        System.out.println("4. Mostrar recorrido inorder");
        System.out.println("5. Mostrar estructura del arbol");
        System.out.println("6. Validar propiedades");
        System.out.println("7. Mostrar informacion completa");
        System.out.println("8. Ejecutar prueba automatica");
        System.out.println("0. Salir");
        System.out.println("=================================");
    }

    private static void insertarValor(ArbolRojoNegro arbol, Scanner scanner) {
        System.out.print("\nIngresa el valor a insertar: ");
        int valor = scanner.nextInt();
        arbol.insertar(valor);
        System.out.println("Valor " + valor + " insertado correctamente.");
    }

    private static void eliminarValor(ArbolRojoNegro arbol, Scanner scanner) {
        System.out.print("\nIngresa el valor a eliminar: ");
        int valor = scanner.nextInt();
        boolean eliminado = arbol.eliminar(valor);
        if(eliminado) {
            System.out.println("Valor " + valor + " eliminado correctamente.");
        } else {
            System.out.println("El valor " + valor + " no existe en el arbol.");
        }
    }

    private static void buscarValor(ArbolRojoNegro arbol, Scanner scanner) {
        System.out.print("\nIngresa el valor a buscar: ");
        int valor = scanner.nextInt();
        Nodo encontrado = arbol.buscar(valor);
        if(encontrado != null) {
            String color = encontrado.color ? "ROJO" : "NEGRO";
            System.out.println("Valor " + valor + " encontrado - Color: " + color);
        } else {
            System.out.println("Valor " + valor + " NO encontrado en el arbol.");
        }
    }

    private static void mostrarValidaciones(ArbolRojoNegro arbol) {
        boolean raizNegra = arbol.validarRaizNegra();
        boolean noDobleRoja = arbol.validarNoDobleRoja(arbol.raiz);

        System.out.println("Raiz es negra: " + (raizNegra ? "SI" : "NO"));
        System.out.println("No hay doble roja: " + (noDobleRoja ? "SI" : "NO"));

        if(raizNegra && noDobleRoja) {
            System.out.println("Todas las propiedades se cumplen");
        } else {
            System.out.println(" Algunas propiedades no se cumplen");
        }
    }

    private static void mostrarInformacionCompleta(ArbolRojoNegro arbol) {
        System.out.println("Recorrido Inorder:");
        arbol.inorder();

        System.out.println("\nEstructura del arbol:");
        arbol.imprimirArbol();

        System.out.println("\nValidaciones:");
        mostrarValidaciones(arbol);
    }

    private static void pruebaAutomatica() {
        System.out.println("\n=== EJECUTANDO PRUEBA AUTOMATICA ===");
        ArbolRojoNegro arbol = new ArbolRojoNegro();

        int[] valoresInsertar = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};

        System.out.println("Insertando valores: 50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45");
        for(int valor : valoresInsertar) {
            arbol.insertar(valor);
        }

        System.out.println("\n--- DESPUES DE INSERCIONES ---");
        mostrarInformacionCompleta(arbol);

        System.out.println("\nEliminando valores: 20, 70, 30");
        arbol.eliminar(20);
        arbol.eliminar(70);
        arbol.eliminar(30);

        System.out.println("\n--- DESPUES DE ELIMINACIONES ---");
        mostrarInformacionCompleta(arbol);

        System.out.println("\n=== PRUEBA AUTOMATICA COMPLETADA ===");
    }
}
