public class PersonajeDirector {
    private PersonajeBuilder builder;

    public PersonajeDirector(PersonajeBuilder builder) {
        this.builder = builder;
    }

    public void construirPersonaje() {
        builder.buildNombre();
        builder.buildClase();
        builder.buildArma();
        builder.buildNivel();
        builder.buildVida();
        builder.buildHabilidad();
    }
}