package clases.model;

// Representa a un jugador en el bingo (ya sea humano o máquina).
public class Jugador implements Participante {
    private final String nombre;
    private final Carton carton;

    // Crea un nuevo jugador y le asigna un cartón generado automáticamente.
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.carton = new Carton();
    }

    // Busca el número en su cartón y lo marca si lo tiene.
    @Override
    public void marcarNumero(int numero) {
        carton.marcarNumero(numero);
    }

    @Override
    public Carton getCarton() {
        return carton;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
