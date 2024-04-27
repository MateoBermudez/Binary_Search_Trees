public class Arbol {
    public Nodo Raiz;

    public Arbol() {
        this.Raiz = null;
    }

    public void CrearArbol(char[] arbolChar) {
        Nodo n;
        int i;
        for (i = 0; i < arbolChar.length; i++) {
            n = new Nodo(arbolChar[i]);
            //La raiz es el primer dato siempre
            if (Raiz == null) {
                Raiz = n;
            }
            else {
                InsertarDato(Raiz, n);
            }
        }
    }

    public void InsertarDato(Nodo R, Nodo Insert) {
        if (Insert.getDato() < R.getDato()) {
            if (R.TieneLigaIzquierda()) {
                //Vuelve a hacer el llamado recursivo del Nodo Padre
                InsertarDato(R.getLI(), Insert);
            }
            else {
                //Inserta
                R.setLI(Insert);
            }
        }
        else{
            if (R.TieneLigaDerecha()) {
                //Vuelve a hacer el llamado recursivo del Nodo Padre
                InsertarDato(R.getLD(), Insert);
            }
            else {
                //Inserta
                R.setLD(Insert);
            }
        }
    }

    public void PreOrden(Nodo R) {
        if (R != null) {
            System.out.print(R.getDato());
            PreOrden(R.getLI());
            PreOrden(R.getLD());

        }
    }

    public void PosOrden(Nodo R) {
        if (R != null) {
            PosOrden(R.getLI());
            PosOrden(R.getLD());
            System.out.print(R.getDato());
        }
    }

    public void InOrden(Nodo R) {
        if (R != null) {
            InOrden(R.getLI());
            System.out.print(R.getDato());
            InOrden(R.getLD());
        }
    }

    public void ImpresionGrafica(Nodo raiz, String prefijo, boolean esIzquierdo) {
        if (raiz != null) {
            System.out.println(prefijo + (esIzquierdo ? "|-- " : "└-- ") + raiz.getDato());
            ImpresionGrafica(raiz.getLI(), prefijo + (esIzquierdo ? "|   " : "    "), true);
            ImpresionGrafica(raiz.getLD(), prefijo + (esIzquierdo ? "|   " : "    "), false);
        }
    }

    public void ImprimirHojas(Nodo R) {
        if (R != null) {
            ImprimirHojas(R.getLI());
            if (!R.TieneLigaIzquierda() && !R.TieneLigaDerecha()) {
                System.out.print(R.getDato());
            }
            ImprimirHojas(R.getLD());
        }
    }

    public void ImprimirPadres(Nodo R) {
        if (R != null) {
            ImprimirPadres(R.getLI());
            if (R.TieneLigaIzquierda() || R.TieneLigaDerecha()) {
                System.out.print(R.getDato());
            }
            ImprimirPadres(R.getLD());
        }
    }

    public boolean BuscarDato(Nodo R, char dato) {
        boolean foundRigth = false;
        boolean foundLeft = false;
        if (R != null) {
            if (R.getDato() == dato) {
                return true;
            }
            foundLeft = BuscarDato(R.getLI(), dato);
            foundRigth = BuscarDato(R.getLD(), dato);
        }
        return foundLeft || foundRigth;
    }

    public void InsertarDato(Nodo R, char dato) {
        Nodo nuevo = new Nodo(dato);
        //El siguiente es el insertar del arbol, ya que recibe dos nodos (Sobrecarga/Polimorfismo), no como este que recibe un nodo y un char
        InsertarDato(R, nuevo);
    }

    public void MostrarHermano(Nodo R, char dato) {
        if (R != null && R == Raiz && R.getDato() == dato) {
            System.out.println("El dato es la raiz, no tiene hermano");
            return;
        }
        if (R != null) {
            if (R.TieneLigaIzquierda() && R.getLI().getDato() == dato) {
                System.out.println((R.TieneLigaDerecha() ? "El hermano de " + dato + " es: " + R.getLD().getDato() : "No tiene hermano"));
            }
            else if (R.TieneLigaDerecha() && R.getLD().getDato() == dato) {
                System.out.println((R.TieneLigaIzquierda() ? "El hermano de " + dato + " es: " + R.getLI().getDato() : "No tiene hermano"));
            }
            else {
                MostrarHermano(R.getLI(), dato);
                MostrarHermano(R.getLD(), dato);
            }
        }
    }

    public void MostrarNivel(Nodo R, char dato, int nivel) {
        if (R != null) {
            if (R.getDato() == dato) {
                System.out.println("Nivel del dato: " + R.getDato() + " es: " + nivel);
            }
            else {
                MostrarNivel(R.getLI(), dato, nivel + 1);
                MostrarNivel(R.getLD(), dato, nivel + 1);
            }
        }
    }

    public boolean MostrarAncentros(Nodo R, char dato) {
        if (R != null) {
            if (R.getDato() == dato) {
                return true;
            }
            if (MostrarAncentros(R.getLI(), dato) || MostrarAncentros(R.getLD(), dato)) {
                System.out.print(R.getDato() + " ");
                return true;
            }
        }
        return false;
    }

    public void MostrarAltura(Nodo R, char dato) {
        int Altura;
        if (R != null) {
            if (R.getDato() == dato) {
                //Calcula la altura del nodo con el dato especifico
                Altura = CalcularAlturaNodo(R);
                System.out.println("La altura del dato es: " + Altura);
            }
            else {
                //Busca el dato
                MostrarAltura(R.getLI(), dato);
                MostrarAltura(R.getLD(), dato);
            }
        }
    }

    private int CalcularAlturaNodo(Nodo R) {
        int altIzq, altDer;
        if (R != null) {
            altIzq = CalcularAlturaNodo(R.getLI());
            altDer = CalcularAlturaNodo(R.getLD());
            return Math.max(altIzq, altDer) + 1;
        }
        return 0;
    }

}