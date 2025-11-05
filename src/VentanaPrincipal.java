import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    private JLabel titulo;
    private JPanel miPanel;

    public VentanaPrincipal(){
        titulo.setVisible(true);
      this.setTitle("Ventana principal");
      this.setContentPane(this.miPanel);
      this.setSize(500, 400);
      this.setVisible(true);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
