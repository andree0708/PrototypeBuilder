public class MagoBuilder implements PersonajeBuilder {
    private PersonajeConstruido personaje = new PersonajeConstruido();

    @Override
    public void buildNombre() { personaje.setNombre("Mago predeterminado"); }
    @Override
    public void buildClase() { personaje.setClase("Mago"); }
    @Override
    public void buildArma() { personaje.setArma("Báculo místico"); }
    @Override
    public void buildNivel() { personaje.setNivel(10); }
    @Override
    public void buildVida() { personaje.setVida(80); }
    @Override
    public void buildHabilidad() { personaje.setHabilidad("Bola de fuego"); }
    @Override
    public PersonajeConstruido getPersonaje() { return personaje; }
}