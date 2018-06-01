/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snakez;

import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 *
 * @author Alumno
 */
public class MusicaManager {
    private String  musica="";

    public Thread ReproducirCancion(String musica){
        Thread as= new Thread(new Runnable() {
       @Override
       public void run() {
           try{
               do{
                   FileInputStream fis = new FileInputStream("../Snakez/src/res/"+musica+".mp3");
                   Player playMP3 = new Player(fis);
                   playMP3.play();}
               while(true);
           }
           catch(Exception exc){
               exc.printStackTrace();
           }      }
   });;
   return as;
    }
    public void PararCancion(Thread cancion){
        cancion.stop();
    }
}
