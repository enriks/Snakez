package Snakez;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class serpienteEjecutador extends JFrame {

    public serpienteEjecutador() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new tablero());
               
        setResizable(false);
        pack();
        
        setTitle("Serpiente");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new serpienteEjecutador();
            ex.setVisible(true);
        });
    }
}
