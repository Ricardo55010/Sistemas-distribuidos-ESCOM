/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author richi
 */
public class ClaseRMI extends UnicastRemoteObject implements InterfaceRMI{
    public ClaseRMI() throws RemoteException{
        super();
    }
    
    @Override
    public double[][] multiplica_matrices(double[][] A, double[][] B, int N,int M)  throws RemoteException {
        double[][] C = new double[N / 6][N/6];
        for (int i = 0; i < N / 6; i++) {
            for (int j = 0; j < N / 6; j++) {
                for (int k = 0; k < M; k++) {
                    C[i][j] += A[i][k] * B[j][k];
                }
            }
        }
        return C;
    }
    
    
}
