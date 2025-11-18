package juego;

import java.util.HashSet;
import java.util.Set;

public class Orilla {
    private final Set<Elemento> elementos = new HashSet<>();

    public void agregar(Elemento e) {
        elementos.add(e);
    }

    public void quitar(Elemento e) {
        elementos.remove(e);
    }

    public boolean contiene(Elemento e) {
        return elementos.contains(e);
    }

    public boolean contieneTipo(TipoElemento t) {
        return elementos.stream().anyMatch(x -> x.getTipo() == t);
    }

    public Set<Elemento> getElementos() {
        return new HashSet<>(elementos);
    }

    public void vaciar() {
        elementos.clear();
    }
}
