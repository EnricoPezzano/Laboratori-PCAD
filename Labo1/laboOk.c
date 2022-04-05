#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <pthread.h>

#define RAND_MAX = 9;

phtread_barrier_t myBarrier;

void ins (int row, int column, int** matrix);
int** createArray(int m, int n);
void destroyArray(int** arr);
void printMatrix(int row, int column, int** matrix);
void mulMatrix(int row, int column, int column2, int** matrix1, int** matrix2, int** matrixResult);

void *foo(void *vargp) {
  int myid;
  myid = *((int *)vargp); //casting del tipo
  printf("Thread %d\n", myid); //stampa l'identificatore
  return(0);
}

void *thread1()

int main() {
    /*
    int num_threads = 2;
    pthread_t tid[num_threads];
    phread_attr_t attr;
    void *ret; //puntatore del valore di ritorno
    int *ptr; //puntatore a int, accediamo allo stack del padre
    ptr = malloc(sizeof(int)); //alloco spazio per il puntatore a int
    
    phtread_barrier_init(&myBarrier,NULL,2);
    ret = phtread_create(&tid[0], NULL, &thread1)
    */
    int M,N,P;
    printf("Inserire num righe matrice A: ");
    scanf("%d", &M);
    printf("Inserire num colonne matrice A: ");
    scanf("%d", &N);

    int** A = createArray(M,N);
    ins(M,N,A);
    printMatrix(M,N,A);

    printf("Inserire num colonne matrice B: ");
    scanf("%d", &P);
    int** B = createArray(N,P);
    ins(N,P,B);
    printMatrix(N,P,B);

    int** R = createArray(M,P);    
    mulMatrix(M,N,P,A,B,R); 
    printMatrix(M,P,R);

    int** C = createArray(P,M);
    ins(P,M,C);
    printMatrix(P,M,C);

    int** Q = createArray(P,P);
    mulMatrix(P,M,P,C,R,Q);
    printMatrix(P,P,Q);
    
    
}

int** createArray(int m, int n) {
    int* values = calloc(m*n, sizeof(int));
    int** rows = malloc(m*sizeof(int*));
    for (int i=0; i<m; ++i) {
        rows[i] = values + i*n;
    }
    return rows;
}

void destroyArray(int** arr) {
    free(*arr);
    free(arr);
}

void ins (int row, int column, int** matrix)
{
    for(int i = 0; i < row; i++) {
        for(int j = 0; j < column; j++) {
            matrix[i][j] = rand()%10;
        }
    }
}

void printMatrix(int row, int column, int** matrix)
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

void mulMatrix(int row, int column, int column2, int** matrix1, int** matrix2, int** matrixResult) 
{
    int sum;
    for(int i=0; i < row; i++) {
        for(int k=0; k < column2; k++) {
            sum = 0;
            for(int j=0; j < column; j++) {
                printf("%d * %d  ", matrix1[i][j], matrix2[j][k]);
                sum += matrix1[i][j] * matrix2[j][k];
            }
            printf(" ----%d  \n", sum);
            matrixResult[i][k] = sum;
        }
        printf("\n\n");
    }
}
