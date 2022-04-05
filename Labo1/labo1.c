#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <pthread.h>

#define RAND_MAX = 9;

void ins (int row, int column, int matrix[row][column]);
void printMatrix(int row, int column, int matrix[row][column]);

int main()
{
    int M = 6;
    int N = 3;
    int P = 2;

    int A[M][N];
    int B[N][P];
    int C[P][M];
    int R[M][P];
    //int Q[P][P];

    ins(M,N,A);
    printMatrix(M,N,A);
    
    ins(N,P,B);
    printMatrix(N,P,B);

    ins(P,M,C);
    printMatrix(P,M,C);

    //print(P,M,C);

    int sum;
    for(int i=0; i < M; i++) {
        for(int k=0; k < P; k++) {
            sum = 0;
            for(int j=0; j < N; j++) {
                printf("%d * %d  ", A[i][j], B[j][k]);
                sum += A[i][j] * B[j][k];
            }
            printf("/n ----%d  ", sum);
            R[i][k] = sum;
        }
    }
    printMatrix(M,P,R);

    int Q[P][P];

    for(int i=0; i < P; i++) {
        for(int k=0; k < P; k++) {
            int sum = 0;
            for(int j=0; j < M; j++) {
                printf("%d * %d  ", C[i][j], R[j][k]);
                sum += C[i][j] * R[j][k];
            }
            printf("/n ----%d  ", sum);
            Q[i][k] = sum;
        }
    }

    printf("\n");
    printMatrix(P,P,Q);
}

void ins (int row, int column, int matrix[row][column])
{
    for(int i = 0; i < row; i++) {
        for(int j = 0; j < column; j++) {
            matrix[i][j] = rand()%10;
        }
    }
}

void printMatrix(int row, int column, int matrix[row][column])
{
    for(int i = 0; i < row; i++) {
        printf("|   ");
        for(int j = 0; j < column; j++) {
            printf("%d   ", matrix[i][j]);
        }
        printf("|\n");
    }
    printf("\n\n");
}
//pippo