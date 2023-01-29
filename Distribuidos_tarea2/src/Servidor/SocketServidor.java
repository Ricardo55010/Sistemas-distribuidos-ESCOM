package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocketFactory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author richi
 */
public class SocketServidor extends Thread{
    Socket conexion;
    public SocketServidor(Socket conexion){
        this.conexion=conexion;
    }
    
    public void run(){
        try {
            DataInputStream entrada = new DataInputStream(conexion.getInputStream());
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
            
            DataInputStream dis = new DataInputStream(conexion.getInputStream());
            //Leemos los datos principales del archivo y creamos un flujo para escribir el archivo de salida
            byte[] b = new byte [1024];
            String nombre= dis.readUTF();
            System.out.println("Recibimos el archivo "+nombre);
            long tam = dis.readLong();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombre));
            //Preparamos los datos para recibir los paquetes de datos del archivo
            long recibidos=0;
            int n,porcentaje;
            //Definimos el ciclo donde estaremos recibiendo los datos enviados por el cliente
            while(recibidos<tam){
                n=dis.read(b);
                dos.write(b,0,n);
                dos.flush();
                recibidos=recibidos+n;
                porcentaje=(int)(recibidos*100/tam);
                System.out.println("\n\nPauqete recibido.");
            }//While
            //Cerramos los flujos, el socket y el resto del programa
            dos.flush();
            
            salida.write("OK".getBytes());
            
            
            
            conexion.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    public static void main (String [] args) throws Exception{
         //ServerSocket servidor = new ServerSocket(80);
         System.setProperty("javax.net.ssl.keyStore","keystore_servidor.jks");
         System.setProperty("javax.net.ssl.keyStorePassword","1234567");
         System.setProperty("javax.net.ssl.trustStore","keystore_cliente.jks");
         System.setProperty("javax.net.ssl.trustStore","123456");
         SSLServerSocketFactory socket_factory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
         ServerSocket servidor = socket_factory.createServerSocket(80);
        for(;;){
            Socket conexion = servidor.accept();
            SocketServidor server = new SocketServidor (conexion);
            server.start();
        }
    }
}
