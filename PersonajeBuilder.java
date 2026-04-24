public interface PersonajeBuilder {
    void buildNombre();
    void buildClase();
    void buildArma();
    void buildNivel();
    void buildVida();
    void buildHabilidad();
    PersonajeConstruido getPersonaje();
}