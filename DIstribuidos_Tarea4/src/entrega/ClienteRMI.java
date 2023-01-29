/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.rmi.*;

/**
 *
 * @author richi
 */
public class ClienteRMI {

    public static class hilo extends Thread {

        int N = 0;
        int M = 0;
        int opcion = 0;
        double[][] A = new double[N / 6][M];
        double[][] B = new double[N / 6][M];
        double[][] C;

        public hilo(double[][] A, double[][] B, int N, int M, int opcion) {
            this.A = A;
            this.B = B;
            this.N = N;
            this.M = M;
            this.opcion = opcion;
        }

        public void run() {
            try {
                if (opcion == 0) {
                    C = multiplica_matrices(A, B, N, M);
                } else {
                    String url = (this.opcion == 1) ? "rmi://localhost/prueba" : "rmi://localhost/prueba";
                    InterfaceRMI r = (InterfaceRMI) Naming.lookup(url);
                    C = r.multiplica_matrices(A, B, N, M);
                    mostrar_matriz(C, N / 6, N / 6);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public double[][] getC() {
            return C;
        }
    }

    static void mostrar_matriz(double[][] A, int N, int M) {
        if (N == 6 && M==5) {
            System.out.print("[");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (j < M - 1) {
                        System.out.print(A[i][j] + " ,");
                    } else if (j < M && i < N - 1) {
                        System.out.print(A[i][j] + ";");
                    } else {
                        System.out.print(A[i][j] + "]");
                    }
                }
                System.out.println("");
            }
        }
    }

    static double[][] separa_matriz(double[][] A, int inicio, int N, int Mn) {
        double[][] M = new double[N / 6][Mn];
        for (int i = 0; i < N / 6; i++) {
            for (int j = 0; j < Mn; j++) {

                M[i][j] = A[i + inicio][j];
            }
        }
        return M;
    }

    static double[][] multiplica_matrices(double[][] A, double[][] B, int N, int M) throws RemoteException {
        double[][] C = new double[N / 6][N / 6];
        for (int i = 0; i < N / 6; i++) {
            for (int j = 0; j < N / 6; j++) {
                for (int k = 0; k < M; k++) {
                    C[i][j] += A[i][k] * B[j][k];
                }
            }
        }
        return C;
    }

    static void acomoda_matriz(double[][] C, double[][] A, int renglon, int columna, int N) {
        for (int i = 0; i < N / 6; i++) {
            for (int j = 0; j < N / 6; j++) {
                C[i + renglon][j + columna] = A[i][j];
            }
        }
    }

    static void checksum(double[][] C, int N, int M) {
        double checksum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                checksum += C[i][j];
                //       System.out.println("Checksum actual:" + checksum);
            }
        }
        System.out.println("");
        System.out.println("Checksum:" + checksum);
    }

    public static void main(String args[]) throws Exception {

        int N = 6000;
        int M = 5000;
        double[][] A = new double[N][M];
        double[][] B = new double[M][N];

        //RELLENADO
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                A[i][j] = 3 * i + 2 * j;

            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {

                B[i][j] = 2 * i - 3 * j;
            }
        }

        //MOSTRAR 
        System.out.println("Matriz A:");
        mostrar_matriz(A, N, M);
        //System.out.println("Matriz B");
        //mostrar_matriz(B, M, N);
        //TRSND
        double[][] BT = new double[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                BT[i][j] = B[j][i];
            }
        }
        System.out.println("Matriz BT");
        mostrar_matriz(BT, N, M);

        //separa matriz a
        double[][] A1 = separa_matriz(A, 0, N, M);
        double[][] A2 = separa_matriz(A, N / 6, N, M);
        double[][] A3 = separa_matriz(A, (2 * N) / 6, N, M);
        double[][] A4 = separa_matriz(A, (3 * N) / 6, N, M);
        double[][] A5 = separa_matriz(A, (4 * N) / 6, N, M);
        double[][] A6 = separa_matriz(A, (5 * N) / 6, N, M);
        //System.out.println("Matriz A3:");
        //mostrar_matriz(A3, N / 6, M);

        //separa matriz B
        double[][] B1 = separa_matriz(BT, 0, N, M);
        double[][] B2 = separa_matriz(BT, N / 6, N, M);
        double[][] B3 = separa_matriz(BT, (2 * N) / 6, N, M);
        double[][] B4 = separa_matriz(BT, (3 * N) / 6, N, M);
        double[][] B5 = separa_matriz(BT, (4 * N) / 6, N, M);
        double[][] B6 = separa_matriz(BT, (5 * N) / 6, N, M);
        //System.out.println("Matriz b3:");
        //mostrar_matriz(B3, N / 6, M);
        //mostrar_matriz(B5, N / 6, M);

        //hilos locales
        hilo c1 = new hilo(A1, B1, N, M, 0);
        c1.start();
        hilo c2 = new hilo(A1, B2, N, M, 0);
        c2.start();
        hilo c3 = new hilo(A1, B3, N, M, 0);
        c3.start();
        hilo c4 = new hilo(A1, B4, N, M, 0);
        c4.start();
        hilo c5 = new hilo(A1, B5, N, M, 0);
        c5.start();
        hilo c6 = new hilo(A1, B6, N, M, 0);
        c6.start();
        //for (int i = 0; i < 36; i++) {
        //   hilo c = new hilo(i);
        //  c.start();
        //}

        hilo c7 = new hilo(A2, B1, N, M, 0);
        c7.start();
        hilo c8 = new hilo(A2, B2, N, M, 0);
        c8.start();
        hilo c9 = new hilo(A2, B3, N, M, 0);
        c9.start();
        hilo c10 = new hilo(A2, B4, N, M, 0);
        c10.start();
        hilo c11 = new hilo(A2, B5, N, M, 0);
        c11.start();
        hilo c12 = new hilo(A2, B6, N, M, 0);
        c12.start();
        
        //hilos rmi T5-2019630289-1
        hilo c13 = new hilo(A3, B1, N, M, 1);
        c13.start();
        hilo c14 = new hilo(A3, B2, N, M, 1);
        c14.start();
        hilo c15 = new hilo(A3, B3, N, M, 1);
        c15.start();
        hilo c16 = new hilo(A3, B4, N, M, 1);
        c16.start();
        hilo c17 = new hilo(A3, B5, N, M, 1);
        c17.start();
        hilo c18 = new hilo(A3, B6, N, M, 1);
        c18.start();

        hilo c19 = new hilo(A4, B1, N, M, 1);
        c19.start();
        hilo c20 = new hilo(A4, B2, N, M, 1);
        c20.start();
        hilo c21 = new hilo(A4, B3, N, M, 1);
        c21.start();
        hilo c22 = new hilo(A4, B4, N, M, 1);
        c22.start();
        hilo c23 = new hilo(A4, B5, N, M, 1);
        c23.start();
        hilo c24 = new hilo(A4, B6, N, M, 1);
        c24.start();
        
        //hilos rmi T5-2019630289-2
        hilo c25 = new hilo(A5, B1, N, M, 2);
        c25.start();
        hilo c26 = new hilo(A5, B2, N, M, 2);
        c26.start();
        hilo c27 = new hilo(A5, B3, N, M, 2);
        c27.start();
        hilo c28 = new hilo(A5, B4, N, M, 2);
        c28.start();
        hilo c29 = new hilo(A5, B5, N, M, 2);
        c29.start();
        hilo c30 = new hilo(A5, B6, N, M, 2);
        c30.start();

        hilo c31 = new hilo(A6, B1, N, M, 2);
        c31.start();
        hilo c32 = new hilo(A6, B2, N, M, 2);
        c32.start();
        hilo c33 = new hilo(A6, B3, N, M, 2);
        c33.start();
        hilo c34 = new hilo(A6, B4, N, M, 2);
        c34.start();
        hilo c35 = new hilo(A6, B5, N, M, 2);
        c35.start();
        hilo c36 = new hilo(A6, B6, N, M, 2);
        c36.start();
        //esperamos a que los hilos terminen
        c1.join();
        c2.join();
        c3.join();
        c4.join();
        c5.join();
        c6.join();
        c7.join();
        c8.join();
        c9.join();
        c10.join();
        c11.join();
        c12.join();
        c13.join();
        c14.join();
        c15.join();
        c16.join();
        c17.join();
        c18.join();
        c19.join();
        c20.join();
        c21.join();
        c22.join();
        c23.join();
        c24.join();
        c25.join();
        c26.join();
        c27.join();
        c28.join();
        c29.join();
        c30.join();
        c31.join();
        c32.join();
        c33.join();
        c34.join();
        c35.join();
        c36.join();

        //acomodo de matriz
        //JUNTAMOS
        double[][] C = new double[N][N];
        acomoda_matriz(C, c1.getC(), 0, 0, N);
        acomoda_matriz(C, c2.getC(), 0, (N) / 6, N);
        acomoda_matriz(C, c3.getC(), 0, (2 * N) / 6, N);
        acomoda_matriz(C, c4.getC(), 0, (3 * N) / 6, N);
        acomoda_matriz(C, c5.getC(), 0, (4 * N) / 6, N);
        acomoda_matriz(C, c6.getC(), 0, (5 * N) / 6, N);

        acomoda_matriz(C, c7.getC(), (N) / 6, 0, N);
        acomoda_matriz(C, c8.getC(), (N) / 6, (N) / 6, N);
        acomoda_matriz(C, c9.getC(), (N) / 6, (2 * N) / 6, N);
        acomoda_matriz(C, c10.getC(), (N) / 6, (3 * N) / 6, N);
        acomoda_matriz(C, c11.getC(), (N) / 6, (4 * N) / 6, N);
        acomoda_matriz(C, c12.getC(), (N) / 6, (5 * N) / 6, N);

        acomoda_matriz(C, c13.getC(), (2 * N) / 6, 0, N);
        acomoda_matriz(C, c14.getC(), (2 * N) / 6, (N) / 6, N);
        acomoda_matriz(C, c15.getC(), (2 * N) / 6, (2 * N) / 6, N);
        acomoda_matriz(C, c16.getC(), (2 * N) / 6, (3 * N) / 6, N);
        acomoda_matriz(C, c17.getC(), (2 * N) / 6, (4 * N) / 6, N);
        acomoda_matriz(C, c18.getC(), (2 * N) / 6, (5 * N) / 6, N);

        acomoda_matriz(C, c19.getC(), (3 * N) / 6, 0, N);
        acomoda_matriz(C, c20.getC(), (3 * N) / 6, (N) / 6, N);
        acomoda_matriz(C, c21.getC(), (3 * N) / 6, (2 * N) / 6, N);
        acomoda_matriz(C, c22.getC(), (3 * N) / 6, (3 * N) / 6, N);
        acomoda_matriz(C, c23.getC(), (3 * N) / 6, (4 * N) / 6, N);
        acomoda_matriz(C, c24.getC(), (3 * N) / 6, (5 * N) / 6, N);

        acomoda_matriz(C, c25.getC(), (4 * N) / 6, 0, N);
        acomoda_matriz(C, c26.getC(), (4 * N) / 6, (N) / 6, N);
        acomoda_matriz(C, c27.getC(), (4 * N) / 6, (2 * N) / 6, N);
        acomoda_matriz(C, c28.getC(), (4 * N) / 6, (3 * N) / 6, N);
        acomoda_matriz(C, c29.getC(), (4 * N) / 6, (4 * N) / 6, N);
        acomoda_matriz(C, c30.getC(), (4 * N) / 6, (5 * N) / 6, N);

        acomoda_matriz(C, c31.getC(), (5 * N) / 6, 0, N);
        acomoda_matriz(C, c32.getC(), (5 * N) / 6, (N) / 6, N);
        acomoda_matriz(C, c33.getC(), (5 * N) / 6, (2 * N) / 6, N);
        acomoda_matriz(C, c34.getC(), (5 * N) / 6, (3 * N) / 6, N);
        acomoda_matriz(C, c35.getC(), (5 * N) / 6, (4 * N) / 6, N);
        acomoda_matriz(C, c36.getC(), (5 * N) / 6, (5 * N) / 6, N);

        System.out.println("Matriz c:");
        mostrar_matriz(C, N, N);
        checksum(C, N, N);
    }
}
