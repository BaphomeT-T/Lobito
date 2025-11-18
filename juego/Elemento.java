package juego;

public class Elemento {
    private final TipoElemento tipo;
    private final String nombre;

    public Elemento(TipoElemento tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public TipoElemento getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Elemento)) return false;
        Elemento e = (Elemento) o;
        return tipo == e.tipo;
    }

    @Override
    public int hashCode() {
        return tipo.hashCode();
    }

    @Override
    public String toString() {
        return nombre;
    }
}
