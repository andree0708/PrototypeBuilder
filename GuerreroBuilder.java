public class GuerreroBuilder implements PersonajeBuilder {
    private PersonajeConstruido personaje = new PersonajeConstruido();

    @Override
    public void buildNombre() { personaje.setNombre("Guerrero predeterminado"); }
    @Override
    public void buildClase() { personaje.setClase("Guerrero"); }
    @Override
    public void buildArma() { personaje.setArma("Espada de acero"); }
    @Override
    public void buildNivel() { personaje.setNivel(10); }
    @Override
    public void buildVida() { personaje.setVida(150); }
    @Override
    public void buildHabilidad() { personaje.setHabilidad("Golpe devastador"); }
    @Override
    public PersonajeConstruido getPersonaje() { return personaje; }
}