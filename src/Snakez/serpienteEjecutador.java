package Snakez;

import java.awt.EventQueue;
import java.awt.Label;
import javax.swing.JFrame;

public class serpienteEjecutador extends JFrame {
    static int num;
    static String skin1, skin2, skin3;
    static String skin4;
    static int r, g, b;

    public serpienteEjecutador(int num,String skin1,String skin2,String skin3, String skin4,int r, int g, int b) {
        
        initUI(num,skin1,skin2,skin3,skin4,r,g,b);
    }
    
    private void initUI(int num,String skin1,String skin2,String skin3, String skin4,int r, int g, int b) {
        //TO DO poner la musica bein y crear la carptea de mierda
        add(new tablero(num,skin1,skin2,skin3,skin4,r,g,b));
               
        setResizable(false);
        pack();
        
        setTitle("Serpiente");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new serpienteEjecutador(num,skin1,skin2,skin3,skin4,r,g,b);
            ex.setVisible(true);
        });
    }
}
