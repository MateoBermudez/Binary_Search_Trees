public class Nodo {
    private char Dato;
    private Nodo LI, LD;
    public Nodo(char Dato) {
        this.Dato = Dato;
        this.LI = null;
        this.LD = null;
    }

    public char getDato() {
        return Dato;
    }

    public void setDato(char dato) {
        Dato = dato;
    }

    public Nodo getLI() {
        return LI;
    }

    public void setLI(Nodo LI) {
        this.LI = LI;
    }

    public Nodo getLD() {
        return LD;
    }

    public void setLD(Nodo LD) {
        this.LD = LD;
    }

    public boolean TieneLigaDerecha() {
        return this.LD != null;
    }
    public boolean TieneLigaIzquierda() {
        return this.LI != null;
    }
}
