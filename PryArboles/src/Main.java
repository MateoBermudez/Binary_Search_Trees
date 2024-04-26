import java.util.Scanner;

public class Main {
    public static Scanner lea = new Scanner(System.in);
    public static void main(String[] args) {
        boolean salir = false, datoExiste;
        int opcion;
        char datoAux;
        char[] arbolChar;
        String ingresoArbol;
        Arbol arbol = null;
        while (!salir) {
            Menu();
            System.out.println("Ingrese la opcion que desea realizar (1-14): ");
            opcion = lea.nextInt();
            while (opcion != 1 && arbol == null) {
                System.out.println("Primero debe crear el arbol (Opcion 1)");
                System.out.println("Ingrese la opcion que desea realizar (1-14): ");
                opcion = lea.nextInt();
            }
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese los datos (Char) del arbol.");
                    ingresoArbol = lea.next();
                    ingresoArbol = ingresoArbol.trim();
                    ingresoArbol = ingresoArbol.toUpperCase();
                    arbolChar = ingresoArbol.toCharArray();
                    arbolChar = EliminarDatosRepetidos(arbolChar);
                    arbol = new Arbol();
                    arbol.CrearArbol(arbolChar);
                    break;
                case 2:
                    System.out.print("\nPreOrden\n");
                    arbol.PreOrden(arbol.Raiz);
                    System.out.print("\nPosOrden\n");
                    arbol.PosOrden(arbol.Raiz);
                    System.out.print("\nInOrden\n");
                    arbol.InOrden(arbol.Raiz);
                    break;
                case 3:
                    System.out.print("\nImpresion Grafica del Arbol (Hijo Izquierdo: |--, Hijo Derecho: └-- \n");
                    arbol.ImpresionGrafica(arbol.Raiz, "", true);
                    break;
                case 4:
                    System.out.print("\nImpresion de las hojas del arbol\n");
                    arbol.ImprimirHojas(arbol.Raiz);
                    break;
                case 5:
                    System.out.print("\nImpresion de los padres del arbol\n");
                    arbol.ImprimirPadres(arbol.Raiz);
                    break;
                case 6:
                    System.out.print("\nIngrese el dato a insertar: ");
                    datoAux = lea.next().charAt(0);
                    datoAux = Character.toUpperCase(datoAux);
                    datoExiste = arbol.BuscarDato(arbol.Raiz, datoAux);
                    if (datoExiste) {
                        System.out.println("No se puede insertar, el dato ya existe");
                    } else {
                        //Insertar el dato
                    }
                    break;
                case 7:
                    System.out.print("\nIngrese el dato a Eliminar: ");
                    datoAux = lea.next().charAt(0);
                    datoAux = Character.toUpperCase(datoAux);
                    datoExiste = arbol.BuscarDato(arbol.Raiz, datoAux);
                    if (datoExiste) {
                        //Eliminar el dato
                    } else {
                        System.out.println("Dato no existe, no se puede eliminar");
                    }
                    break;
                case 8:
                    System.out.print("\nIngrese el dato para buscar su hermano: ");
                    datoAux = lea.next().charAt(0);
                    datoAux = Character.toUpperCase(datoAux);
                    datoExiste = arbol.BuscarDato(arbol.Raiz, datoAux);
                    if (datoExiste) {
                        //Buscar el hermano
                    } else {
                        System.out.println("Dato no existe, no se puede buscar su hermano");
                    }
                    break;
                case 9:
                    System.out.print("\nIngrese el dato para buscar su nivel: ");
                    datoAux = lea.next().charAt(0);
                    datoAux = Character.toUpperCase(datoAux);
                    datoExiste = arbol.BuscarDato(arbol.Raiz, datoAux);
                    if (datoExiste) {
                        //Buscar el nivel
                    } else {
                        System.out.println("Dato no existe, no se puede buscar su nivel");
                    }
                    break;
                case 10:
                    System.out.print("\nIngrese el dato para buscar su altura: ");
                    datoAux = lea.next().charAt(0);
                    datoAux = Character.toUpperCase(datoAux);
                    datoExiste = arbol.BuscarDato(arbol.Raiz, datoAux);
                    if (datoExiste) {
                        //Buscar la altura
                    } else {
                        System.out.println("Dato no existe, no se puede buscar su altura");
                    }
                    break;
                case 11:
                    System.out.print("\nIngrese el dato para buscar sus primos hermanos: ");
                    datoAux = lea.next().charAt(0);
                    datoAux = Character.toUpperCase(datoAux);
                    datoExiste = arbol.BuscarDato(arbol.Raiz, datoAux);
                    if (datoExiste) {
                        //Buscar los primos hermanos
                    } else {
                        System.out.println("Dato no existe, no se puede buscar sus primos hermanos");
                    }
                    break;
                case 12:
                    System.out.print("\nIngrese el dato para buscar sus ancestros: ");
                    datoAux = lea.next().charAt(0);
                    datoAux = Character.toUpperCase(datoAux);
                    datoExiste = arbol.BuscarDato(arbol.Raiz, datoAux);
                    if (datoExiste) {
                        //Buscar los ancestros
                    } else {
                        System.out.println("Dato no existe, no se puede buscar sus ancestros");
                    }
                    break;
                case 13:
                    System.out.println("Reemplazando el creado del arbol anterior y balanceandolo de la forma AVL con la cadena de datos ingresada previamente");
                    //Crear arbol AVL (Llamar metodo)
                    break;
                case 14:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
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

    private static void Menu() {
        System.out.println("1. Crear el Arbol");
        System.out.println("2. Los 3 recorridos del arbol");
        System.out.println("3. Mostrar el arbol graficamente");
        System.out.println("4. Mostrar las hojas del arbol");
        System.out.println("5. Mostrar los padres del arbol");
        System.out.println("6. Insertar Dato");
        System.out.println("7. Eliminar Dato");
        System.out.println("8. Mostrar el hermano de un dato");
        System.out.println("9. Mostrar el nivel de un dato");
        System.out.println("10. Mostrar la altura de un dato");
        System.out.println("11. Mostrar los primos hermanos de un dato");
        System.out.println("12. Mostrar los ancestros de un dato");
        System.out.println("13. Crear el arbol y balancearlo de la forma AVL con la cadena de datos ingresada previamente");
        System.out.println("14. Salir");
    }

}
