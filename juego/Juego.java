package juego;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Juego {
    private final Orilla orillaIzquierda = new Orilla();
    private final Orilla orillaDerecha = new Orilla();
    private final Barca barca = new Barca();
    private final Elemento observador = new Elemento(TipoElemento.OBSERVADOR, "Observador");
    private final Elemento lobo = new Elemento(TipoElemento.LOBO, "Lobo");
    private final Elemento caperucita = new Elemento(TipoElemento.CAPERUCITA, "Caperucita");
    private final Elemento uvas = new Elemento(TipoElemento.UVAS, "Uvas");

    public Juego() {
        reiniciar();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            mostrarEstado();
            System.out.println("Seleccione elemento para acompañar al Observador en la barca: (L)obo, (C)aperucita, (U)vas, (S)olo el Observador | (R)einiciar | (Q)uitar");
            String line = sc.nextLine().trim().toUpperCase();
            if (line.isEmpty()) continue;
            if (line.equals("Q")) {
                System.out.println("Saliendo...");
                break;
            }
            if (line.equals("R")) {
                reiniciar();
                continue;
            }

            TipoElemento elegidoTipo = null;
            if (line.equals("L")) elegidoTipo = TipoElemento.LOBO;
            else if (line.equals("C")) elegidoTipo = TipoElemento.CAPERUCITA;
            else if (line.equals("U")) elegidoTipo = TipoElemento.UVAS;
            else if (line.equals("S")) elegidoTipo = null; // Observador cruza solo
            else {
                System.out.println("Opción no válida.");
                continue;
            }

            boolean ok = seleccionarYMover(elegidoTipo);
            if (!ok) continue;

            String razon = verificarProhibiciones();
            if (razon != null) {
                mostrarEstado();
                System.out.println("Fin del juego: " + razon);
                System.out.println("Pulse R para reiniciar o Q para salir.");
                String opt = sc.nextLine().trim().toUpperCase();
                if (opt.equals("R")) {
                    reiniciar();
                    continue;
                } else break;
            }

            if (verificarVictoria()) {
                mostrarEstado();
                System.out.println("¡Victoria! Todos llegaron a la orilla derecha respetando las reglas.");
                System.out.println("Pulse R para reiniciar o Q para salir.");
                String opt = sc.nextLine().trim().toUpperCase();
                if (opt.equals("R")) {
                    reiniciar();
                    continue;
                } else break;
            }
        }
        sc.close();
    }

    private boolean seleccionarYMover(TipoElemento tipoAdicional) {
        Elemento adicional = tipoAdicional == null ? null : elementoPorTipo(tipoAdicional);
        // verificar que Observador esté en la misma orilla que la barca
        Orilla desde = barca.getPosicion().equals("IZQUIERDA") ? orillaIzquierda : orillaDerecha;
        Orilla hacia = barca.getPosicion().equals("IZQUIERDA") ? orillaDerecha : orillaIzquierda;

        if (!desde.contiene(observador)) {
            System.out.println("El Observador no está en la orilla donde está la barca. Reinicie si hay inconsistencia.");
            return false;
        }
        if (adicional != null && !desde.contiene(adicional)) {
            System.out.println("El elemento seleccionado no está en la misma orilla que la barca.");
            return false;
        }

        // preparar la barca: subir Observador y, si aplica, el adicional
        barca.vaciar();
        boolean sub1 = barca.subir(observador);
        boolean sub2 = true;
        if (adicional != null) sub2 = barca.subir(adicional);
        if (!sub1 || !sub2) {
            System.out.println("No se pudo subir a la barca (capacidad o duplicado).");
            barca.vaciar();
            return false;
        }

        if (!barca.puedeMoverse()) {
            System.out.println("La barca no cumple condiciones para moverse (debe llevar al menos al Observador).");
            barca.vaciar();
            return false;
        }

        // quitar de orilla 'desde'
        desde.quitar(observador);
        if (adicional != null) desde.quitar(adicional);

        // mover barca
        barca.cambiarPosicion();

        // bajar en orilla 'hacia'
        hacia.agregar(observador);
        if (adicional != null) hacia.agregar(adicional);

        // vaciar barca (queda vacío en la orilla de llegada)
        barca.vaciar();

        System.out.println("Cruce realizado: Observador" + (adicional != null ? (" + " + adicional.getNombre()) : " (solo)") + " a la orilla " + barca.getPosicion() + ".");
        return true;
    }

    public void reiniciar() {
        orillaIzquierda.vaciar();
        orillaDerecha.vaciar();
        // todos en izquierda
        orillaIzquierda.agregar(observador);
        orillaIzquierda.agregar(lobo);
        orillaIzquierda.agregar(caperucita);
        orillaIzquierda.agregar(uvas);
        // asegurar barca en IZQUIERDA vacía
        if (barca.getPosicion().equals("DERECHA")) barca.cambiarPosicion();
        barca.vaciar();
    }

    public String verificarProhibiciones() {
        // Devuelve null si no hay prohibición; si hay, devuelve mensaje indicando qué pasó y en qué orilla
        String izq = prohibidoEn(orillaIzquierda, "IZQUIERDA");
        if (izq != null) return izq;
        String der = prohibidoEn(orillaDerecha, "DERECHA");
        if (der != null) return der;
        return null;
    }

    private String prohibidoEn(Orilla o, String nombreOrilla) {
        boolean obs = o.contieneTipo(TipoElemento.OBSERVADOR);
        boolean l = o.contieneTipo(TipoElemento.LOBO);
        boolean c = o.contieneTipo(TipoElemento.CAPERUCITA);
        boolean u = o.contieneTipo(TipoElemento.UVAS);
        if (!obs && l && c) return "El lobo se comió a Caperucita en la orilla " + nombreOrilla + ".";
        if (!obs && c && u) return "Caperucita se comió las Uvas en la orilla " + nombreOrilla + ".";
        return null;
    }

    public boolean verificarVictoria() {
        // todos en orilla derecha
        Set<Elemento> elems = orillaDerecha.getElementos();
        return elems.stream().map(Elemento::getTipo).collect(Collectors.toSet()).containsAll(
                Set.of(TipoElemento.OBSERVADOR, TipoElemento.LOBO, TipoElemento.CAPERUCITA, TipoElemento.UVAS)
        );
    }

    private Elemento elementoPorTipo(TipoElemento t) {
        switch (t) {
            case OBSERVADOR:
                return observador;
            case LOBO:
                return lobo;
            case CAPERUCITA:
                return caperucita;
            case UVAS:
                return uvas;
            default:
                return null;
        }
    }

    private String listarOrilla(Orilla o) {
        return o.getElementos().stream()
                .map(Elemento::getNombre)
                .sorted()
                .collect(Collectors.joining(", "));
    }

    private String listarBarca() {
        return barca.getElementos().stream()
                .map(Elemento::getNombre)
                .sorted()
                .collect(Collectors.joining(", "));
    }

    public void mostrarEstado() {
        System.out.println("----- Estado actual -----");
        System.out.println("Barca posicion: " + barca.getPosicion() + " | En la barca: " + (listarBarca().isEmpty() ? "(vacía)" : listarBarca()));
        System.out.println("Orilla Izquierda: " + (listarOrilla(orillaIzquierda).isEmpty() ? "(vacía)" : listarOrilla(orillaIzquierda)));
        System.out.println("Orilla Derecha   : " + (listarOrilla(orillaDerecha).isEmpty() ? "(vacía)" : listarOrilla(orillaDerecha)));
        System.out.println("-------------------------");
    }
}
