public class Personaje implements IPrototype {
    private String nombre;
    private int nivel;
    private String clase;
    private String arma;

    public Personaje(String nombre, int nivel, String clase, String arma) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.clase = clase;
        this.arma = arma;
    }

    @Override
    public IPrototype clone() {
        return new Personaje(nombre, nivel, clase, arma);
    }

    @Override
    public void mostrar() {
        System.out.println(obtenerInfo());
    }

    public String obtenerInfo() {
        return nombre + " | Nivel " + nivel + " | " + clase + " | Arma: " + arma;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public String getArma() {
        return arma;
    }
}