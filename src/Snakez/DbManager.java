/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snakez;

/**
 *
 * @author alumno.invitado
 */

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:../Snakez/src/database/Stick.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void updateConfig(String skin1, String skin2, String skin3, String skin4,Integer numJug, Integer r, Integer g, Integer b, int aleatorio,int musicaIndex) {
        String sql = "UPDATE configuracion SET skin1 = ? , "
                + "skin2 = ?, "
                + "skin3 = ?, "
                + "skin4 = ?, "
                + "jugadores = ?, "
                + "r = ?, "
                + "g = ?, "
                + "b = ?, "
                + "aleatorio = ?, "
                + "musica = ? "
                + "WHERE id = ?";
        System.err.println(aleatorio);
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
           pstmt.setString(1, skin1);
            pstmt.setString(2, skin2);
            pstmt.setString(3, skin3);
            pstmt.setString(4, skin4);
            pstmt.setInt(5, numJug);
            pstmt.setInt(6, r);
            pstmt.setInt(7, g);
            pstmt.setInt(8, b);
            pstmt.setInt(9, aleatorio);
            pstmt.setInt(10, musicaIndex);
            pstmt.setInt(11, 1);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void InsertPuntuacion(String nombre, int puntuacion){
        String sql = "INSERT INTO puntuacion(usuario,puntuacion) VALUES(?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, puntuacion);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean existConfig(){
        boolean exite = false;
        String sql = "SELECT * from configuracion";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            if (rs.next()) {
                exite = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exite;
    }
    
    public List selectConfig(){
        List<String> esto = new ArrayList<String>();
        
        String sql = "select skin1,skin2,skin3,skin4,jugadores,r,g,b,aleatorio,musica from configuracion";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            if (rs.next()) {
                System.out.println(rs.getString("skin1"));
                esto.add(0,rs.getString("skin1"));
                esto.add(1,rs.getString("skin2"));
                esto.add(2,rs.getString("skin3"));
                esto.add(3,rs.getString("skin4"));
                esto.add(4,Integer.toString(rs.getInt("jugadores")));
                esto.add(5,Integer.toString(rs.getInt("r")));
                esto.add(6,Integer.toString(rs.getInt("g")));
                esto.add(7,Integer.toString(rs.getInt("b")));
                esto.add(8,Integer.toString(rs.getInt("aleatorio")));
                esto.add(9,Integer.toString(rs.getInt("musica")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return esto;
    }
    
    public List selectSkins(){
        List<String> esto = new ArrayList<String>();
        
        String sql = "SELECT nombre from skins";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            int index =0;
            while (rs.next()) {
                esto.add(index,rs.getString("nombre"));
                index ++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"aaaa");
        }
        return esto;
    }
    
    public List selectPuntuaciones(){
        List<List> esto = new ArrayList<List>();
        List<String> nombres = new ArrayList<String>();
        List<Integer> puntuaciones = new ArrayList<Integer>();
        
        String sql = "SELECT * from puntuacion order by _id desc limit 5";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            int index =0;
            while (rs.next()) {
                nombres.add(index, rs.getString("usuario"));
                puntuaciones.add(index,rs.getInt("puntuacion"));
                index ++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"aaaa");
        }
        esto.add(0,nombres);
        esto.add(1,puntuaciones);
        System.out.println(esto.size());
        return esto;
    }
    public List selectMusic(){
        List<String> esto = new ArrayList<String>();
        
        String sql = "SELECT nombre from musica";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            int index =0;
            while (rs.next()) {
                esto.add(index,rs.getString("nombre"));
                index ++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"aaaa");
        }
        return esto;
    }
    public String selectReproductionMusic(int id){
        String esto = "";        
        String sql = "SELECT nombre from musica where _id="+id;
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            if (rs.next()) {
                esto=rs.getString("nombre");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"aaaa");
        }
        return esto;
    }
    
}
