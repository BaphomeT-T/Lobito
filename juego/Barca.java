package juego;

import java.util.ArrayList;
import java.util.List;

public class Barca {
    private final int capacidadMax = 2;
    private final List<Elemento> elementos = new ArrayList<>();
    private String posicion = "IZQUIERDA"; // "IZQUIERDA" o "DERECHA"

    public boolean subir(Elemento e) {
        if (elementos.size() >= capacidadMax) return false;
        if (elementos.contains(e)) return false;
        elementos.add(e);
        return true;
    }

    public void bajar(Elemento e) {
        elementos.remove(e);
    }

    public void vaciar() {
        elementos.clear();
    }

    public List<Elemento> getElementos() {
        return new ArrayList<>(elementos);
    }

    public boolean contieneTipo(TipoElemento t) {
        return elementos.stream().anyMatch(x -> x.getTipo() == t);
    }

    public boolean puedeMoverse() {
        // Permitimos que la barca se mueva si contiene al Observador y tiene 1 o 2 elementos
        return contieneTipo(TipoElemento.OBSERVADOR) && elementos.size() >= 1 && elementos.size() <= capacidadMax;
    }

    public String getPosicion() {
        return posicion;
    }

    public void cambiarPosicion() {
        posicion = posicion.equals("IZQUIERDA") ? "DERECHA" : "IZQUIERDA";
    }
}
