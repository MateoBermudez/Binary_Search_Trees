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
}