
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author richi
 */
public class ServidorB extends Thread {
    Socket conexion;
    
    public ServidorB(Socket conexion){
        this.conexion = conexion;
    }
    
   
    
    public void run(){
        try{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        PrintWriter salida = new PrintWriter(conexion.getOutputStream());
       
        String s = entrada.readLine();
        System.out.println(s);
        
        if(s.startsWith("GET /primo")){
            int indexOfQuestion = s.indexOf("?primo=");
            int indexOfHTTPMarker = s.indexOf("HTTP/1.1");
            String numeroSubstring = s.substring(indexOfQuestion+7,indexOfHTTPMarker-1);
            System.out.println(numeroSubstring);
            long numero =Long.parseLong(numeroSubstring);
            hilo t1 = new hilo(new Socket("localhost", 50000),numero,2,(numero/8)+1);
            hilo t2 = new hilo(new Socket("localhost", 50000),numero,(numero/8)+1,(numero/4)+1);
            hilo t3 = new hilo(new Socket("localhost", 50000),numero,(numero/4)+1,(3*numero/8)+1);
            hilo t4 = new hilo(new Socket("localhost", 50000),numero,(3*numero/8)+1,(numero/2)+1);
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            if(hilo.resultado.equals("NDIV")){
            String respuesta = "<html><h1>Es primo</h1></html>";
            salida.println("HTTP/1.1 200 ok");
            salida.println("Content-type: text/html; charset=utf-8");
            salida.println("Content-lenght: "+respuesta.length());
            salida.println();
            salida.flush();
            salida.println(respuesta);
            salida.flush();
            }
            else{
            String respuesta = "<html><h1>No es primo</h1></html>";
            salida.println("HTTP/1.1 200 ok");
            salida.println("Content-type: text/html; charset=utf-8");
            salida.println("Content-lenght: "+respuesta.length());
            salida.println();
            salida.flush();
            salida.println(respuesta);
            salida.flush();
            }
           hilo.resultado="NDIV"; 
        }
        
        }
        catch(Exception e){
        
        System.err.println(e.toString());
        }
    }
    
    static void read(DataInputStream f, byte[] b, int posicion, int longitud) throws Exception {

        while (longitud > 0) {
            int n = f.read(b, posicion, longitud);
            posicion += n;
            longitud -= n;
        }

    }
    
    static class hilo extends Thread {
    Socket conexion;
    long numero;
    long numero_inicial;
    long numero_final;
    static String resultado = "NDIV";
    static Object obj = new Object();
        public hilo(Socket conexion,long numero,long numero_inicial,long numero_final){
            this.conexion = conexion;
            this.numero = numero;
            this.numero_inicial = numero_inicial;
            this.numero_final = numero_final;
        }
        
        public void run(){
        try {
                DataInputStream entradaBytes = new DataInputStream(conexion.getInputStream());
                DataOutputStream salidaBytes = new DataOutputStream(conexion.getOutputStream());
                synchronized(obj){
                salidaBytes.writeLong(numero);
                salidaBytes.writeLong(numero_inicial);
                salidaBytes.writeLong(numero_final);
                byte[] buffer = new byte[4];
                read(entradaBytes, buffer, 0, 4);
                System.out.println("Sevidor: "+new String(buffer, "UTF-8"));
                if(resultado.equals("NDIV")){
                    resultado = new String(buffer, "UTF-8");
                }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        }
        
        
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException{
        ServerSocket servidor = new ServerSocket(80);
        for(;;){
            Socket conexion = servidor.accept();
            ServidorB server = new ServidorB (conexion);
            server.start();
        }
        
    }
    
}