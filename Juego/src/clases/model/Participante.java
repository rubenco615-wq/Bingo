package clases.model;

// Puede ser implementada tanto por jugadores humanos como por la máquina.
public interface Participante {
    // Marca un número en el cartón del participante si este lo contiene.
    void marcarNumero(int numero);

    // Obtiene el cartón asociado al participante.
    Carton getCarton();

    // Obtiene el nombre del participante.
    String getNombre();
}
