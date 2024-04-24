import java.util.Scanner;

public class Main {
    public static Scanner lea = new Scanner(System.in);
    public static void main(String[] args) {
        String ingresoArbol;
        char datoBus;
        boolean datoEncontrado;
        char[] arbolChar;
        Arbol arbol;
        System.out.println("Ingrese los datos (Char) del arbol.");
        ingresoArbol = lea.next();
        ingresoArbol = ingresoArbol.trim();
        ingresoArbol = ingresoArbol.toUpperCase();
        arbolChar = ingresoArbol.toCharArray();
        arbolChar = EliminarDatosRepetidos(arbolChar);
        arbol = new Arbol();
        arbol.CrearArbol(arbolChar);
        System.out.print("\nPreOrden\n");
        arbol.PreOrden(arbol.Raiz);
        System.out.print("\nPosOrden\n");
        arbol.PosOrden(arbol.Raiz);
        System.out.print("\nInOrden\n");
        arbol.InOrden(arbol.Raiz);
        System.out.print("\nImpresion Grafica del Arbol (Hijo Izquierdo: |--, Hijo Derecho: └-- \n");
        arbol.ImpresionGrafica(arbol.Raiz, "", true);
        System.out.print("\nImpresion de las hojas del arbol\n");
        arbol.ImprimirHojas(arbol.Raiz);
        System.out.print("\nImpresion de los padres del arbol\n");
        arbol.ImprimirPadres(arbol.Raiz);
        System.out.print("\nIngrese el dato a buscar: ");
        datoBus = lea.next().charAt(0);
        datoBus = Character.toUpperCase(datoBus);
        datoEncontrado = arbol.BuscarDato(arbol.Raiz, datoBus);
        if (datoEncontrado) {
            System.out.println("Dato encontrado");
        }
        else {
            System.out.println("Dato no encontrado");
        }
    }


    private static char[] EliminarDatosRepetidos(char[] aux) {
        char dato;
        int i, j;
        for (i = 0; i < aux.length; i++) {
            dato = aux[i];
            for (j = i+1; j < aux.length; j++) {
                if (dato == aux[j]) {
                    aux[j] = '0';
                }
            }
        }
        aux = Redimensionar(aux);
        return aux;
    }

    private static char[] Redimensionar(char[] aux) {
        char[] Nuevo;
        int cont = 0, i, j = 0;
        for (i = 0; i < aux.length; i++) {
            if (aux[i] != '0') {
                cont++;
            }
        }
        Nuevo = new char[cont];
        for (i = 0; i < aux.length; i++) {
            if (aux[i] != '0') {
                Nuevo[j++] = aux[i];
            }
        }
        return Nuevo;
    }
}
