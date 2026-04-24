public class PersonajeConstruido {
    private String nombre;
    private String clase;
    private String arma;
    private int nivel;
    private int vida;
    private String habilidad;

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setClase(String clase) { this.clase = clase; }
    public void setArma(String arma) { this.arma = arma; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    public void setVida(int vida) { this.vida = vida; }
    public void setHabilidad(String habilidad) { this.habilidad = habilidad; }

    public void mostrarPersonaje() {
        System.out.println(obtenerResumen());
    }

    public String obtenerResumen() {
        return "Personaje construido: " + nombre + " | " + clase + " | " + arma + " | Nivel " + nivel + " | Vida " + vida + " | Habilidad: " + habilidad;
    }
}