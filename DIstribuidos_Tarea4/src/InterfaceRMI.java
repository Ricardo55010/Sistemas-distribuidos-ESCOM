/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.rmi.*;
/**
 *
 * @author richi
 */
public interface InterfaceRMI  extends Remote
{
    public double[][] multiplica_matrices(double[][] A, double[][] B, int N,int M)  throws RemoteException;
}
