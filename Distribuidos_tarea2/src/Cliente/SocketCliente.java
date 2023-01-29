package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.net.ssl.SSLSocketFactory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author richi
 */
public class SocketCliente{

    static void read(DataInputStream f, byte[] b, int posicion, int longitud) throws Exception {

        while (longitud > 0) {
            int n = f.read(b, posicion, longitud);
            posicion += n;
            longitud -= n;
        }

    }

    
    public static void main (String [] args) throws InterruptedException{
            Socket conexion = null;
        for (;;) {
            try {
                SSLSocketFactory cliente = (SSLSocketFactory) SSLSocketFactory.getDefault();
                conexion = cliente.createSocket("localhost", 80);
                //conexion = new Socket("localhost", 80);
                DataInputStream entrada = new DataInputStream(conexion.getInputStream());
                DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
                File foto = new File("C:\\Users\\richi\\Downloads\\3CV16_L1_RiveraPérezRicardo\\build\\classes\\Blondy.jpg");
                String archivo = foto.getAbsolutePath();
                String nombre = foto.getName(); //Nombre
                long tam = foto.length(); //Tamaño
               
                System.out.println("Se envía el archivo:"+ archivo+" con nombre: "+nombre+" y tamaño: "+tam);
                
                                //Se definen dos flujos orientados a bytes, uno se usa para leer el archivo y el otro para enviar los datos por el socket
               
                DataInputStream dis = new DataInputStream(new FileInputStream(archivo));
                //Enviamos los datos generales del archivo por el socket
                salida.writeUTF(nombre);
                salida.flush();
                salida.writeLong(tam);
                salida.flush();
                //Leemos los datos  contenidos en el archivo en paquetes de 1024 y los enviamos por el socke
                byte[]b = new byte[1024];
                long enviados=0;
                int porcentaje,n;
                
                while(enviados<tam){
                    n=dis.read(b);
                    salida.write(b,0,n);
                    salida.flush();
                    enviados=enviados+n;
                    porcentaje=(int)(enviados*100/tam);
                    System.out.println("Enviado: "+porcentaje+"&\r");
                }//While
                
                byte[] buffer = new byte[2];
                read(entrada, buffer, 0, 2);
                System.out.println(new String(buffer, "UTF-8"));
                conexion.close();
                System.exit(0);
            } catch (Exception e) {
                Thread.sleep(100);
            }
        }
    }
}
