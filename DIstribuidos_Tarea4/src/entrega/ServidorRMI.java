/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.rmi.*;
/**
 *
 * @author richi
 */
public class ServidorRMI {
    public static void main(String[] args) throws Exception{
        String url = "rmi://localhost/prueba";
        ClaseRMI obj = new ClaseRMI();
        
        Naming.rebind(url,obj);
    }
}
