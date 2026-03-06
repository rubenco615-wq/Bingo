package clases.model;

// es o el jugador o la maquina.
public class Jugador implements Participante {
    private String nombre;
    private Carton carton;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.carton = new Carton();// cuando creas el jugador le das un carton
    }

    // Busca el número en su cartón y lo marca si lo tiene
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
