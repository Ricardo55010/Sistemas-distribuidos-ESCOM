
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author richi
 */
public class ServidorA extends Thread {
    Socket conexion;
    
    
    public ServidorA(Socket conexion){
        this.conexion = conexion;
    }
    public void run(){
        try{
        DataInputStream entrada = new DataInputStream(conexion.getInputStream());
        DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
        long numero = entrada.readLong();
        long numero_inicial = entrada.readLong();
        long numero_final = entrada.readLong();
        System.out.println("Numero original: "+numero);
        System.out.println("Numero inicial: "+numero_inicial);
        System.out.println("Numero final: "+numero_final);
        //System.out.println(numero);
        int aux=0;
        while(numero_inicial<numero_final&&numero_inicial!=numero&&numero_inicial>1){
            if(numero%numero_inicial==0){
                salida.write("SDIV".getBytes());
                System.out.println(numero_inicial + " Divide a: " +numero +" por ello no es primo");
                aux+=1;
            }
            numero_inicial+=1;
        }
        
        if(aux==0){
            salida.write("NDIV".getBytes());
            System.out.println(numero + "Es primo para este rango");
        }
        }
        catch(Exception e){
        
        System.err.println(e.toString());
        }
    }
   
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        ServerSocket servidor = new ServerSocket(50000);
        for(;;){
            Socket conexion = servidor.accept();
            ServidorA server = new ServidorA (conexion);
            server.start();
        }
    }
    
}
