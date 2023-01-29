/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author richi
 */
public class matriz {

    public static double[][] multiplica_matrices(double[][] A, double[][] B, int N,int M) {
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

    static double[][] separa_matriz(double[][] A, int inicio, int N,int Mn) {
        double[][] M = new double[N / 6][Mn];
        for (int i = 0; i < N / 6; i++) {
            for (int j = 0; j < Mn; j++) {

                M[i][j] = A[i + inicio][j];
            }
        }
        return M;
    }

    static void acomoda_matriz(double[][] C, double[][] A, int renglon, int columna, int N) {
        for (int i = 0; i < N / 6; i++) {
            for (int j = 0; j < N /6; j++) {
                C[i + renglon][j + columna] = A[i][j];
            }
        }
    }

    static void mostrar_matriz(double[][] A, int N, int M) {
       if(false){
         System.out.print("[");
           for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j < M - 1) {
                    System.out.print(A[i][j] + " ,");
                }
                else if(j<M&&i<N-1){
                System.out.print(A[i][j] + ";");
                }
                else {
                    System.out.print(A[i][j] + "]");
                }
            }
            System.out.println("");
        }
       }
    }
    
    static void checksum(double[][] C, int N, int M) {
        double checksum=0;  
        for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        checksum += C[i][j];
                        System.out.println("Checksum actual:" + checksum);
                    }
                }
                System.out.println("");
                System.out.println("Checksum:" + checksum);
        }
    
  

    public static void main(String args[]) {
        int N = 6;
        int M = 5;
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
        System.out.println("Matriz B");
        mostrar_matriz(B, M,N);
        //TRSND
        double[][] BT = new double[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                BT[i][j] = B[j][i];
            }
        }

        //MOSTRAR bt
        
        
        System.out.println("Matriz Bt");
        mostrar_matriz(BT, N,M);
        //separa matriz a
        double[][] A1 = separa_matriz(A, 0, N,M);
        double[][] A2 = separa_matriz(A, N / 6, N,M);
        double[][] A3 = separa_matriz(A, (2 * N) / 6, N,M);
        double[][] A4 = separa_matriz(A, (3 * N) / 6, N,M);
        double[][] A5 = separa_matriz(A, (4 * N) / 6, N,M);
        double[][] A6 = separa_matriz(A, (5 * N) / 6, N,M);
        System.out.println("Matriz A3:");
        mostrar_matriz(A3, N / 6, M);
        //separa matriz B
        double[][] B1 = separa_matriz(BT, 0, N,M);
        double[][] B2 = separa_matriz(BT, N / 6, N,M);
        double[][] B3 = separa_matriz(BT, (2 * N) / 6, N,M);
        double[][] B4 = separa_matriz(BT, (3 * N) / 6, N,M);
        double[][] B5 = separa_matriz(BT, (4 * N) / 6, N,M);
        double[][] B6 = separa_matriz(BT, (5 * N) / 6, N,M);
        System.out.println("Matriz b3:");
        mostrar_matriz(B3, N / 6, M);
        //multiplicamos
        double[][] C1 = multiplica_matrices(A1, B1, N,M);
        double[][] C2 = multiplica_matrices(A1, B2, N,M);
        double[][] C3 = multiplica_matrices(A1, B3, N,M);
        double[][] C4 = multiplica_matrices(A1, B4, N,M);
        double[][] C5 = multiplica_matrices(A1, B5, N,M);
        double[][] C6 = multiplica_matrices(A1, B6, N,M);
        double[][] C7 = multiplica_matrices(A2, B1, N,M);
        double[][] C8 = multiplica_matrices(A2, B2, N,M);
        double[][] C9 = multiplica_matrices(A2, B3, N,M);
        double[][] C10 = multiplica_matrices(A2, B4, N,M);
        double[][] C11 = multiplica_matrices(A2, B5, N,M);
        double[][] C12 = multiplica_matrices(A2, B6, N,M);
        double[][] C13 = multiplica_matrices(A3, B1, N,M);
        double[][] C14 = multiplica_matrices(A3, B2, N,M);
        double[][] C15 = multiplica_matrices(A3, B3, N,M);
        double[][] C16 = multiplica_matrices(A3, B4, N,M);
        double[][] C17 = multiplica_matrices(A3, B5, N,M);
        double[][] C18 = multiplica_matrices(A3, B6, N,M);
        double[][] C19 = multiplica_matrices(A4, B1, N,M);
        double[][] C20 = multiplica_matrices(A4, B2, N,M);
        double[][] C21 = multiplica_matrices(A4, B3, N,M);
        double[][] C22 = multiplica_matrices(A4, B4, N,M);
        double[][] C23 = multiplica_matrices(A4, B5, N,M);
        double[][] C24 = multiplica_matrices(A4, B6, N,M);
        double[][] C25 = multiplica_matrices(A5, B1, N,M);
        double[][] C26 = multiplica_matrices(A5, B2, N,M);
        double[][] C27 = multiplica_matrices(A5, B3, N,M);
        double[][] C28 = multiplica_matrices(A5, B4, N,M);
        double[][] C29 = multiplica_matrices(A5, B5, N,M);
        double[][] C30 = multiplica_matrices(A5, B6, N,M);
        double[][] C31 = multiplica_matrices(A6, B1, N,M);
        double[][] C32 = multiplica_matrices(A6, B2, N,M);
        double[][] C33 = multiplica_matrices(A6, B3, N,M);
        double[][] C34 = multiplica_matrices(A6, B4, N,M);
        double[][] C35 = multiplica_matrices(A6, B5, N,M);
        double[][] C36 = multiplica_matrices(A6, B6, N,M);
        System.out.println("Matriz c5:");
        mostrar_matriz(C5, N / 6, N/6);
        //JUNTAMOS
        double[][] C = new double[N][N];
        acomoda_matriz(C, C1, 0, 0, N);
        acomoda_matriz(C, C2, 0, (N) / 6, N);
        acomoda_matriz(C, C3, 0, (2 * N) / 6, N);
        acomoda_matriz(C, C4, 0, (3 * N) / 6, N);
        acomoda_matriz(C, C5, 0, (4 * N) / 6, N);
        acomoda_matriz(C, C6, 0, (5 * N) / 6, N);

        acomoda_matriz(C, C7, (N) / 6, 0, N);
        acomoda_matriz(C, C8, (N) / 6, (N) / 6, N);
        acomoda_matriz(C, C9, (N) / 6, (2 * N) / 6, N);
        acomoda_matriz(C, C10, (N) / 6, (3 * N) / 6, N);
        acomoda_matriz(C, C11, (N) / 6, (4 * N) / 6, N);
        acomoda_matriz(C, C12, (N) / 6, (5 * N) / 6, N);

        acomoda_matriz(C, C13, (2 * N) / 6, 0, N);
        acomoda_matriz(C, C14, (2 * N) / 6, (N) / 6, N);
        acomoda_matriz(C, C15, (2 * N) / 6, (2 * N) / 6, N);
        acomoda_matriz(C, C16, (2 * N) / 6, (3 * N) / 6, N);
        acomoda_matriz(C, C17, (2 * N) / 6, (4 * N) / 6, N);
        acomoda_matriz(C, C18, (2 * N) / 6, (5 * N) / 6, N);

        acomoda_matriz(C, C19, (3 * N) / 6, 0, N);
        acomoda_matriz(C, C20, (3 * N) / 6, (N) / 6, N);
        acomoda_matriz(C, C21, (3 * N) / 6, (2 * N) / 6, N);
        acomoda_matriz(C, C22, (3 * N) / 6, (3 * N) / 6, N);
        acomoda_matriz(C, C23, (3 * N) / 6, (4 * N) / 6, N);
        acomoda_matriz(C, C24, (3 * N) / 6, (5 * N) / 6, N);

        acomoda_matriz(C, C25, (4 * N) / 6, 0, N);
        acomoda_matriz(C, C26, (4 * N) / 6, (N) / 6, N);
        acomoda_matriz(C, C27, (4 * N) / 6, (2 * N) / 6, N);
        acomoda_matriz(C, C28, (4 * N) / 6, (3 * N) / 6, N);
        acomoda_matriz(C, C29, (4 * N) / 6, (4 * N) / 6, N);
        acomoda_matriz(C, C30, (4 * N) / 6, (5 * N) / 6, N);

        acomoda_matriz(C, C31, (5 * N) / 6, 0, N);
        acomoda_matriz(C, C32, (5 * N) / 6, (N) / 6, N);
        acomoda_matriz(C, C33, (5 * N) / 6, (2 * N) / 6, N);
        acomoda_matriz(C, C34, (5 * N) / 6, (3 * N) / 6, N);
        acomoda_matriz(C, C35, (5 * N) / 6, (4 * N) / 6, N);
        acomoda_matriz(C, C36, (5 * N) / 6, (5 * N) / 6, N);

        System.out.println("Matriz c:");
        mostrar_matriz(C, N, N);
        checksum(C, N, N);
    }
}
