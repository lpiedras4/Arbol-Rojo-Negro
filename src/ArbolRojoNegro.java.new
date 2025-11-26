class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;
    Nodo padre;
    boolean color; // true = ROJO, false = NEGRO

    public Nodo(int valor) {
        this.valor = valor;
        this.color = true;
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }
}

public class ArbolRojoNegro {
    protected Nodo raiz;
    private final Nodo NIL;

    public ArbolRojoNegro() {
        this.NIL = new Nodo(-1);
        this.NIL.color = false;
        this.raiz = NIL;
    }

    public void insertar(int valor) {
        Nodo nuevo = new Nodo(valor);
        nuevo.izquierdo = NIL;
        nuevo.derecho = NIL;
        nuevo.color = true;

        Nodo padre = null;
        Nodo actual = raiz;

        while(actual != NIL) {
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

        if (nuevo.padre == null) {
            nuevo.color = false;
            return;
        }

        if (nuevo.padre.padre == null) {
            return;
        }

        arreglarInsercion(nuevo);
    }

    private void arreglarInsercion(Nodo k) {
        Nodo padre;
        Nodo abuelo;

        while (k.padre != null && k.padre.color) {
            padre = k.padre;
            abuelo = padre.padre;

            if (padre == abuelo.izquierdo) {
                Nodo tio = abuelo.derecho;

                if (tio != NIL && tio.color) {
                    tio.color = false;
                    padre.color = false;
                    abuelo.color = true;
                    k = abuelo;
                } else {
                    if (k == padre.derecho) {
                        k = padre;
                        rotarIzquierda(k);
                        padre = k.padre;
                        abuelo = padre.padre;
                    }
                    padre.color = false;
                    abuelo.color = true;
                    rotarDerecha(abuelo);
                }
            } else {
                Nodo tio = abuelo.izquierdo;

                if (tio != NIL && tio.color) {
                    tio.color = false;
                    padre.color = false;
                    abuelo.color = true;
                    k = abuelo;
                } else {
                    if (k == padre.izquierdo) {
                        k = padre;
                        rotarDerecha(k);
                        padre = k.padre;
                        abuelo = padre.padre;
                    }
                    padre.color = false;
                    abuelo.color = true;
                    rotarIzquierda(abuelo);
                }
            }

            if (k == raiz) break;
        }

        raiz.color = false;
    }

    public boolean eliminar(int valor) {
        Nodo nodo = buscar(valor);
        if (nodo == null || nodo == NIL) {
            System.out.println("Valor " + valor + " no encontrado para eliminar");
            return false;
        }
        eliminarNodo(nodo);
        return true;
    }

    private void eliminarNodo(Nodo z) {
        Nodo y = z;
        Nodo x;
        boolean colorOriginalY = y.color;

        if (z.izquierdo == NIL) {
            x = z.derecho;
            transplantar(z, z.derecho);
        } else if (z.derecho == NIL) {
            x = z.izquierdo;
            transplantar(z, z.izquierdo);
        } else {
            y = minimo(z.derecho);
            colorOriginalY = y.color;
            x = y.derecho;

            if (y.padre == z) {
                if (x != NIL) x.padre = y;
            } else {
                transplantar(y, y.derecho);
                y.derecho = z.derecho;
                y.derecho.padre = y;
            }

            transplantar(z, y);
            y.izquierdo = z.izquierdo;
            y.izquierdo.padre = y;
            y.color = z.color;
        }

        if (!colorOriginalY && x != NIL) {
            arreglarEliminacion(x);
        }
    }

    public void transplantar(Nodo u, Nodo v) {
        if (u.padre == null) {
            raiz = v;
        } else if (u == u.padre.izquierdo) {
            u.padre.izquierdo = v;
        } else {
            u.padre.derecho = v;
        }

        if (v != NIL) {
            v.padre = u.padre;
        }
    }

    public Nodo minimo(Nodo nodo) {
        while (nodo.izquierdo != NIL) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    private void arreglarEliminacion(Nodo x) {
        while (x != raiz && !x.color) {
            if (x == x.padre.izquierdo) {
                arreglarEliminacionCasoIzquierdo(x);
            } else {
                arreglarEliminacionCasoDerecho(x);
            }
        }
        x.color = false;
    }

    private void arreglarEliminacionCasoIzquierdo(Nodo x) {
        Nodo w = x.padre.derecho;

        if (w.color) {
            w.color = false;
            x.padre.color = true;
            rotarIzquierda(x.padre);
            w = x.padre.derecho;
        }

        if (!w.izquierdo.color && !w.derecho.color) {
            w.color = true;
            x = x.padre;
        } else {
            if (!w.derecho.color) {
                w.izquierdo.color = false;
                w.color = true;
                rotarDerecha(w);
                w = x.padre.derecho;
            }

            w.color = x.padre.color;
            x.padre.color = false;
            w.derecho.color = false;
            rotarIzquierda(x.padre);
            x = raiz;
        }
    }

    private void arreglarEliminacionCasoDerecho(Nodo x) {
        Nodo w = x.padre.izquierdo;

        if (w.color) {
            w.color = false;
            x.padre.color = true;
            rotarDerecha(x.padre);
            w = x.padre.izquierdo;
        }

        if (!w.derecho.color && !w.izquierdo.color) {
            w.color = true;
            x = x.padre;
        } else {
            if (!w.izquierdo.color) {
                w.derecho.color = false;
                w.color = true;
                rotarIzquierda(w);
                w = x.padre.izquierdo;
            }

            w.color = x.padre.color;
            x.padre.color = false;
            w.izquierdo.color = false;
            rotarDerecha(x.padre);
            x = raiz;
        }
    }

    private void rotarIzquierda(Nodo x) {
        Nodo y = x.derecho;
        x.derecho = y.izquierdo;

        if (y.izquierdo != NIL) {
            y.izquierdo.padre = x;
        }

        y.padre = x.padre;

        if (x.padre == null) {
            raiz = y;
        } else if (x == x.padre.izquierdo) {
            x.padre.izquierdo = y;
        } else {
            x.padre.derecho = y;
        }

        y.izquierdo = x;
        x.padre = y;
    }

    private void rotarDerecha(Nodo x) {
        Nodo y = x.izquierdo;
        x.izquierdo = y.derecho;

        if (y.derecho != NIL) {
            y.derecho.padre = x;
        }

        y.padre = x.padre;

        if (x.padre == null) {
            raiz = y;
        } else if (x == x.padre.derecho) {
            x.padre.derecho = y;
        } else {
            x.padre.izquierdo = y;
        }

        y.derecho = x;
        x.padre = y;
    }

    public boolean validarRaizNegra() {
        if (raiz == null || raiz == NIL) return true;
        return !raiz.color;
    }

    public boolean validarNoDobleRoja(Nodo nodo) {
        if(nodo == null || nodo == NIL) return true;

        if(nodo.color) {
            if((nodo.izquierdo != NIL && nodo.izquierdo.color) ||
                    (nodo.derecho != NIL && nodo.derecho.color)) {
                return false;
            }
        }
        return validarNoDobleRoja(nodo.izquierdo) && validarNoDobleRoja(nodo.derecho);
    }

    public Nodo buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private Nodo buscarRecursivo(Nodo nodo, int valor) {
        if (nodo == null || nodo == NIL || nodo.valor == valor) {
            return nodo == NIL ? null : nodo;
        }
        if (valor < nodo.valor) {
            return buscarRecursivo(nodo.izquierdo, valor);
        } else {
            return buscarRecursivo(nodo.derecho, valor);
        }
    }

    public void inorder() {
        inorderRecursivo(raiz);
        System.out.println();
    }

    private void inorderRecursivo(Nodo nodo) {
        if(nodo != null && nodo != NIL) {
            inorderRecursivo(nodo.izquierdo);
            System.out.print(nodo.valor + (nodo.color ? "R " : "N "));
            inorderRecursivo(nodo.derecho);
        }
    }

    public void imprimirArbol() {
        imprimirArbolRecursivo(raiz, "", true);
    }

    private void imprimirArbolRecursivo(Nodo nodo, String indent, boolean last) {
        if (nodo != null && nodo != NIL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            String color = nodo.color ? "ROJO" : "NEGRO";
            System.out.println(nodo.valor + "(" + color + ")");
            imprimirArbolRecursivo(nodo.izquierdo, indent, false);
            imprimirArbolRecursivo(nodo.derecho, indent, true);
        }
    }
}