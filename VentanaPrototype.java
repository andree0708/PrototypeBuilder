import javax.swing.*;
import java.awt.*;

public class VentanaPrototype extends JFrame {
    private Personaje original;
    private Personaje copia;
    private JTextField txtNombre, txtClase, txtArma;
    private JSpinner spnNivel;
    private JTextField txtNivelCopia, txtArmaCopia;
    private JTextArea areaOriginal;
    private JTextArea areaClon;
    private JLabel mensajeEstado;

    private JComboBox<String> comboBuilder;
    private JTextArea areaBuilderResultado;

    private static final Color FONDO = new Color(255, 240, 248);
    private static final Color PANEL = new Color(255, 250, 252);
    private static final Color BOTON = new Color(236, 164, 180);
    private static final Color BORDE = new Color(199, 121, 143);
    private static final Color TEXTO_SEC = new Color(80, 60, 70);

    public VentanaPrototype() {
        setTitle("Prototype y Builder — Personajes");
        setSize(500, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(FONDO);

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setBackground(FONDO);
        contenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Prototype y Builder — Personajes");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setForeground(TEXTO_SEC);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(titulo);
        contenido.add(Box.createVerticalStrut(16));

        contenido.add(crearTituloSeccion("Prototype: copiar un personaje"));
        contenido.add(Box.createVerticalStrut(4));
        contenido.add(crearCaja("1. Crear", "Creas el personaje que servirá de molde.", crearFormularioPersonaje()));
        contenido.add(Box.createVerticalStrut(8));
        contenido.add(crearCaja("2. Clonar", "Obtienes una copia. Original y clon son independientes.", crearPanelClonar()));
        contenido.add(Box.createVerticalStrut(8));
        contenido.add(crearCaja("3. Modificar clon", "Cambias solo la copia; el original no se toca.", crearPanelModificarClon()));
        contenido.add(Box.createVerticalStrut(10));
        contenido.add(crearSeccionEstado());
        contenido.add(Box.createVerticalStrut(6));
        mensajeEstado = new JLabel(" ");
        mensajeEstado.setFont(new Font("Segoe UI", Font.ITALIC, 10));
        mensajeEstado.setForeground(TEXTO_SEC);
        mensajeEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
        contenido.add(mensajeEstado);
        contenido.add(Box.createVerticalStrut(14));

        contenido.add(crearTituloSeccion("Builder: construir paso a paso"));
        contenido.add(Box.createVerticalStrut(4));
        JPanel panelBuilder = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        panelBuilder.setBackground(PANEL);
        panelBuilder.add(new JLabel("Tipo:"));
        comboBuilder = new JComboBox<>(new String[] { "Guerrero", "Mago" });
        panelBuilder.add(comboBuilder);
        JButton btnConstruir = new JButton("Construir");
        estilarBoton(btnConstruir);
        btnConstruir.addActionListener(e -> construirPersonaje());
        panelBuilder.add(btnConstruir);
        contenido.add(crearCaja("Construir con Builder", "El Director usa el Builder (Guerrero o Mago) y arma el personaje.", panelBuilder));
        contenido.add(Box.createVerticalStrut(6));
        areaBuilderResultado = new JTextArea(3, 28);
        areaBuilderResultado.setEditable(false);
        areaBuilderResultado.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        areaBuilderResultado.setBackground(new Color(255, 252, 254));
        areaBuilderResultado.setBorder(BorderFactory.createLineBorder(BORDE, 1));
        areaBuilderResultado.setLineWrap(true);
        areaBuilderResultado.setText("Resultado del Builder aparecerá aquí.");
        contenido.add(new JScrollPane(areaBuilderResultado));

        JScrollPane scroll = new JScrollPane(contenido);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(FONDO);
        add(scroll);
    }

    private JLabel crearTituloSeccion(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        l.setForeground(BORDE);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JPanel crearCaja(String titulo, String queHace, JPanel dentro) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(BORDE, 1), titulo + " — " + queHace, 0, 0, new Font("Segoe UI", Font.PLAIN, 11), BORDE),
            BorderFactory.createEmptyBorder(8, 10, 10, 10)
        ));
        panel.add(dentro);
        return panel;
    }

    private void construirPersonaje() {
        PersonajeBuilder builder = comboBuilder.getSelectedIndex() == 0 ? new GuerreroBuilder() : new MagoBuilder();
        PersonajeDirector director = new PersonajeDirector(builder);
        director.construirPersonaje();
        PersonajeConstruido p = builder.getPersonaje();
        areaBuilderResultado.setText(p.obtenerResumen());
    }

    private JPanel crearFormularioPersonaje() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        p.setBackground(PANEL);
        p.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(10);
        p.add(txtNombre);
        p.add(new JLabel("Nivel:"));
        spnNivel = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        p.add(spnNivel);
        p.add(new JLabel("Clase:"));
        txtClase = new JTextField(10);
        p.add(txtClase);
        p.add(new JLabel("Arma:"));
        txtArma = new JTextField(10);
        p.add(txtArma);
        JButton btnCrear = new JButton("Crear personaje");
        estilarBoton(btnCrear);
        btnCrear.addActionListener(e -> crearPersonaje());
        p.add(btnCrear);
        return p;
    }

    private JPanel crearPanelClonar() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        p.setBackground(PANEL);
        JButton btnClonar = new JButton("Clonar personaje");
        estilarBoton(btnClonar);
        btnClonar.addActionListener(e -> clonarPersonaje());
        p.add(btnClonar);
        return p;
    }

    private JPanel crearPanelModificarClon() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        p.setBackground(PANEL);
        p.add(new JLabel("Nivel clon:"));
        txtNivelCopia = new JTextField(5);
        p.add(txtNivelCopia);
        p.add(new JLabel("Arma clon:"));
        txtArmaCopia = new JTextField(10);
        p.add(txtArmaCopia);
        JButton btnModificar = new JButton("Aplicar al clon");
        estilarBoton(btnModificar);
        btnModificar.addActionListener(e -> modificarClon());
        p.add(btnModificar);
        return p;
    }

    private JPanel crearSeccionEstado() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 12, 0));
        panel.setBackground(FONDO);

        areaOriginal = crearAreaEstado();
        JPanel cardOriginal = new JPanel(new BorderLayout(6, 6));
        cardOriginal.setBackground(PANEL);
        cardOriginal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(BORDE, 1), "Original", 0, 0, new Font("Segoe UI", Font.BOLD, 11), BORDE));
        cardOriginal.add(new JScrollPane(areaOriginal), BorderLayout.CENTER);
        panel.add(cardOriginal);

        areaClon = crearAreaEstado();
        JPanel cardClon = new JPanel(new BorderLayout(6, 6));
        cardClon.setBackground(PANEL);
        cardClon.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(BORDE, 1), "Clon", 0, 0, new Font("Segoe UI", Font.BOLD, 11), BORDE));
        cardClon.add(new JScrollPane(areaClon), BorderLayout.CENTER);
        panel.add(cardClon);

        areaOriginal.setText("(vacío)");
        areaClon.setText("(vacío)");
        return panel;
    }

    private JTextArea crearAreaEstado() {
        JTextArea area = new JTextArea(4, 18);
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        area.setBackground(new Color(255, 252, 254));
        area.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        return area;
    }

    private void estilarBoton(JButton b) {
        b.setBackground(BOTON);
        b.setForeground(TEXTO_SEC);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE, 1),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
    }

    private void actualizarEstado() {
        if (original != null) {
            areaOriginal.setText(original.obtenerInfo());
            areaOriginal.repaint();
        } else {
            areaOriginal.setText("(vacío)");
        }
        if (copia != null) {
            areaClon.setText(copia.obtenerInfo());
            areaClon.repaint();
        } else {
            areaClon.setText("(vacío)");
        }
        mensajeEstado.setText(original != null && copia != null
            ? "Original y clon son objetos distintos: modifica el clon y verás que el original no cambia."
            : " ");
        revalidate();
        repaint();
    }

    private void crearPersonaje() {
        String n = txtNombre.getText().trim();
        String c = txtClase.getText().trim();
        String a = txtArma.getText().trim();
        if (n.isEmpty() || c.isEmpty() || a.isEmpty()) {
            mensajeEstado.setText("Completa nombre, clase y arma.");
            return;
        }
        int niv = (Integer) spnNivel.getValue();
        original = new Personaje(n, niv, c, a);
        copia = null;
        actualizarEstado();
        mensajeEstado.setText("Personaje creado. Ahora puedes clonarlo en el Paso 2.");
    }

    private void clonarPersonaje() {
        if (original == null) {
            mensajeEstado.setText("Primero crea un personaje en el Paso 1.");
            return;
        }
        copia = (Personaje) original.clone();
        txtNivelCopia.setText(String.valueOf(copia.getNivel()));
        txtArmaCopia.setText(copia.getArma());
        actualizarEstado();
        mensajeEstado.setText("Clon creado. Puedes modificar solo el clon en el Paso 3 y verás que el original no cambia.");
    }

    private void modificarClon() {
        if (copia == null) {
            mensajeEstado.setText("Primero clona el personaje en el Paso 2.");
            return;
        }
        try {
            int niv = Integer.parseInt(txtNivelCopia.getText().trim());
            copia.setNivel(niv);
        } catch (NumberFormatException ignored) { }
        copia.setArma(txtArmaCopia.getText().trim());
        actualizarEstado();
        mensajeEstado.setText("Solo el clon se modificó. El personaje original sigue igual — así funciona el patrón Prototype.");
    }
}