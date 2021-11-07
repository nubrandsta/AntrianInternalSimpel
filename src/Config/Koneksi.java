/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

 



/**
 *
 * @author system64
 */public class Koneksi {
    
    public Connection hubungan;
    public String status = "Status DB";
    
    
    public Connection connect(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Koneksi Berhasil");
        
        
            }
    catch(ClassNotFoundException ex){
        System.out.println("Koneksi Gagal");
        
    }
        try{
            String url="jdbc:mysql://localhost:3306/db_antrianinternal";
            hubungan= DriverManager.getConnection(url, "root", "");
            System.out.println("Koneksi ke Database Berhasil");
            status = "OK";
        }
        catch (Exception e){
            System.out.println("Koneksi ke Database Gagal");
            status = "Error";
        }
        
            
        return hubungan;
    }
    
    public static void main(String[] args){
        java.sql.Connection conn = new Koneksi().connect();
    }


    
}
