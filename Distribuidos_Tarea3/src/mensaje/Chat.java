/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensaje;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author richi
 */
public class Chat {

    static class Worker extends Thread {

        public void run() {

            try {
                System.setProperty("java.net.preferIPv4Stack", "true");
                InetAddress grupo = InetAddress.getByName("239.10.10.10");
                MulticastSocket socket = new MulticastSocket(10000);
                socket.joinGroup(grupo);
                for (;;) {

                    byte[] a = recibe_mensaje_multicast(socket, 200);
                    System.out.println(new String(a, "UTF-8"));
                }

                //En un ciclo infiito se recibirán los mensajes enviados al 
                //grupo 239.10.10.10 a través del puerto 10000 y se desplegarán en la pantalla
            } catch (Exception ex) {
                System.err.println(ex);
            }

        }
    }

    static void envia_mensaje_multicast(byte[] buffer, String ip, int puerto) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        socket.send(new DatagramPacket(buffer, buffer.length, InetAddress.getByName(ip), puerto));
        socket.close();
    }

    static byte[] recibe_mensaje_multicast(MulticastSocket socket, int longitud_mensaje) throws IOException {
        byte[] buffer = new byte[longitud_mensaje];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        return paquete.getData();
    }

    public static void main(String[] args) throws IOException {
        System.setProperty("java.net.preferIPv4Stack", "true");
        new Worker().start();
        String nombre = args[0];
        System.out.println(nombre);
        for (;;) {
            Scanner teclado = new Scanner(System.in);
            String mensaje = teclado.nextLine();
            String mensajeF = nombre + ":-" + mensaje;
            envia_mensaje_multicast(mensajeF.getBytes(), "239.10.10.10", 10000);
        }
        //en un ciclo infinito se leerá cada mensaje del teclado y se enviará el mensaje al
        //grupo 239.10.10.10 a traves del puerto 10000
    }
}
