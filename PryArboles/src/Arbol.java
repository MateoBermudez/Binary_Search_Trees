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
            if (R.EsHoja()) {
                System.out.print(R.getDato());
            }
            ImprimirHojas(R.getLD());
        }
    }

    public void ImprimirPadres(Nodo R) {
        if (R != null) {
            ImprimirPadres(R.getLI());
            if (!R.EsHoja()) {
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
            if (dato > R.getDato()) {
                foundRigth = BuscarDato(R.getLD(), dato);
            }
            else {
                foundLeft = BuscarDato(R.getLI(), dato);
            }
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

    public void MostrarPrimosHermanos(char dato) {
            if (Raiz.getDato() == dato) {
                System.out.println("El dato es la raiz, no tiene primos hermanos");
            }
            else {
                int NivelPrimosHermanos = CalcularNivel(Raiz, dato, 1);
                if (NivelPrimosHermanos == 2) {
                    System.out.println("El dato no tiene primos hermanos");
                }
                else {
                    Nodo abuelo = EncontrarAbuelo(Raiz, dato);
                    MostrarDatosPorNivel(abuelo, dato, NivelPrimosHermanos);
                }

            }
    }

    private Nodo EncontrarAbuelo(Nodo R, char dato) {
        if (R != null) {
            int aux = CalcularNivel(Raiz, dato, 1) - CalcularNivel(Raiz, R.getDato(), 1);
            if (aux == 2) {
                return R;
            }
            else {
                if (R.TieneLigaIzquierda() && BuscarDato(R.getLI(), dato)) {
                    return EncontrarAbuelo(R.getLI(), dato);
                }
                else if (R.TieneLigaDerecha() && BuscarDato(R.getLD(), dato)) {
                    return EncontrarAbuelo(R.getLD(), dato);
                }

            }
        }
        return null;
    }

    private void MostrarDatosPorNivel(Nodo R, char dato, int nivelSolicitado) {
        if (R != null) {
            int NivelNodo = CalcularNivel(Raiz, R.getDato(), 1);
            //Verifica si son hermanos los que siguen del nodo actual, y si alguno de estos contiene al dato buscado
            if (!((R.TieneLigaIzquierda() && R.getLI().getDato() == dato) || (R.TieneLigaDerecha() && R.getLD().getDato() == dato))) {
                //Verifica si estan al mismo nivel
                if (NivelNodo == nivelSolicitado) {
                    System.out.print(R.getDato() + " ");
                }
                //Sigue buscando el dato
                else {
                    MostrarDatosPorNivel(R.getLI(), dato, nivelSolicitado);
                    MostrarDatosPorNivel(R.getLD(), dato, nivelSolicitado);
                }
            }
        }
    }

    public int CalcularNivel(Nodo R, char dato, int nivel) {
        if (R != null) {
            if (R.getDato() == dato) {
                return nivel;
            }
            else {
                if (R.TieneLigaIzquierda() && BuscarDato(R.getLI(), dato)) {
                    nivel = CalcularNivel(R.getLI(), dato, nivel + 1);
                }
                else if (R.TieneLigaDerecha() && BuscarDato(R.getLD(), dato)) {
                    nivel = CalcularNivel(R.getLD(), dato, nivel + 1);
                }
            }
        }
        return nivel;
    }

    public void EliminarDato(Nodo R, char dato) {
        if (Raiz.getDato() == dato) {
            if (Raiz.TieneLigaIzquierda()) {
                EncontrarSucesorIzquierda(Raiz.getLI(), Raiz);
            }
            else if (!Raiz.TieneLigaIzquierda() && Raiz.TieneLigaDerecha()) {
                EncontrarSucesorDerecha(Raiz.getLD(), Raiz);
            }
            else {
                Raiz = null;
            }
        }
        else if (R != null) {
            //sigIzq o sigDer es el nodo a eliminar, por eso se busca el sucesor de este
            Nodo sigIzq = R.getLI(), sigDer = R.getLD();
            if ((sigIzq != null && sigIzq.getDato() == dato)) {
                if (sigIzq.EsHoja()) {
                    R.setLI(null);
                }
                else if(sigIzq.TieneLigaIzquierda() && !sigIzq.TieneLigaDerecha()) {
                    R.setLI(sigIzq.getLI());
                }
                else if (!sigIzq.TieneLigaIzquierda() && sigIzq.TieneLigaDerecha()) {
                    R.setLI(sigIzq.getLD());
                }
                else {
                    if (!sigIzq.TieneLigaIzquierda()) {
                        //Se busca sucesor por la derecha de este nodo (Aquel que contiene al dato a eliminar)
                        EncontrarSucesorDerecha(sigIzq.getLD(), sigIzq);
                    } else {
                        //Se busca sucesor por la izquierda de este nodo (Aquel que contiene al dato a eliminar)
                        EncontrarSucesorIzquierda(sigIzq.getLI(), sigIzq);
                    }
                }
            }
            else if (sigDer != null && sigDer.getDato() == dato) {
                if (sigDer.EsHoja()) {
                    R.setLD(null);
                }
                else if(sigDer.TieneLigaIzquierda() && !sigDer.TieneLigaDerecha()) {
                    R.setLD(sigDer.getLI());
                }
                else if(!sigDer.TieneLigaIzquierda() && sigDer.TieneLigaDerecha()) {
                    R.setLD(sigDer.getLD());
                }
                else {
                    if (!sigDer.TieneLigaIzquierda()) {
                        //Se busca sucesor por la derecha de este nodo (Aquel que contiene al dato a eliminar)
                        EncontrarSucesorDerecha(sigDer.getLD(), sigDer);
                    } else {
                        //Se busca sucesor por la izquierda de este nodo (Aquel que contiene al dato a eliminar)
                        EncontrarSucesorIzquierda(sigDer.getLI(), sigDer);
                    }
                }
            }
            //Se sigue buscando el dato
            else {
                EliminarDato(R.getLI(), dato);
                EliminarDato(R.getLD(), dato);
            }
        }
    }

    public void EncontrarSucesorIzquierda(Nodo R, Nodo Padre) {
        //Dato auxiliar
        char dato;
        if (R != null) {
            if (R.TieneLigaDerecha()) {
                EncontrarSucesorIzquierda(R.getLD(), Padre);
            }
            else {
                //Se elimina y se busca el nuevo sucesor
                dato = R.getDato();
                EliminarDato(Padre, dato);
                Padre.setDato(dato);
            }
        }
    }

    public void EncontrarSucesorDerecha(Nodo R, Nodo Padre) {
        //Dato auxiliar
        char dato;
        if (R != null) {
            if (R.TieneLigaIzquierda()) {
                EncontrarSucesorIzquierda(R.getLI(), Padre);
            }
            else {
                //Se elimina y se busca el nuevo sucesor
                dato = R.getDato();
                EliminarDato(Padre, dato);
                Padre.setDato(dato);
            }
        }
    }

    public Nodo rotacionSimpleDerecha(Nodo k2) {
        Nodo k1 = k2.getLI();
        k2.setLI(k1.getLD());
        k1.setLD(k2);
        return k1;
    }

    public Nodo rotacionSimpleIzquierda(Nodo k1) {
        Nodo k2 = k1.getLD();
        k1.setLD(k2.getLI());
        k2.setLI(k1);
        return k2;
    }

    public void CrearArbolAVL(String cadena) {
        for (int i = 0; i < cadena.length(); i++) {
            Raiz = insertarAVL(Raiz, cadena.charAt(i));
        }
    }

    private Nodo insertarAVL(Nodo nodo, char dato) {
        if (nodo == null) {
            return (new Nodo(dato));
        }

        if (dato < nodo.getDato()) {
            nodo.setLI(insertarAVL(nodo.getLI(), dato));
        } else if (dato > nodo.getDato()) {
            nodo.setLD(insertarAVL(nodo.getLD(), dato));
        } else {
            return nodo;
        }

        int balance = getBalance(nodo);

        if (balance > 1 && dato < nodo.getLI().getDato()) {
            return rotacionSimpleDerecha(nodo);
        }
        // Caso 2 - Rotación simple a la izquierda
        if (balance < -1 && dato > nodo.getLD().getDato()) {
            return rotacionSimpleIzquierda(nodo);
        }
        // Caso 3 - Rotación doble a la derecha
        if (balance > 1 && dato > nodo.getLI().getDato()) {
            nodo.setLI(rotacionSimpleIzquierda(nodo.getLI()));
            return rotacionSimpleDerecha(nodo);
        }
        // Caso 4 - Rotación doble a la izquierda
        if (balance < -1 && dato < nodo.getLD().getDato()) {
            nodo.setLD(rotacionSimpleDerecha(nodo.getLD()));
            return rotacionSimpleIzquierda(nodo);
        }
        // Retorna el nodo padre sin modificaciones
        return nodo;
    }

    private int getBalance(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return CalcularAlturaNodo(nodo.getLI()) - CalcularAlturaNodo(nodo.getLD());
    }
}