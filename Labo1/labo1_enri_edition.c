#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <pthread.h>
#include <time.h>

int** createMatrix(int m, int n) {
    int* values = calloc(m*n, sizeof(int));
    int** rows = malloc(m*sizeof(int*));
    for (int i=0; i<m; ++i) {
        rows[i] = values + i*n;
    }
    return rows;
}

void destroyMatrix(int** m) {
    free(*m);
    free(m);
}

void fillMatrix (int row, int column, int** matrix)
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

void *my_task(void *vargp){
    // gestione della moltiplicazione tra BLOCCHI, gestiti per indirizzo
    return 0;
}


int main(int argc, char* argv[]) {
    // printf("You have entered %d arguments:\n", argc);
    // for (int i = 0; i < argc; ++i)
    //     printf("%d ", atoi(argv[i]));
    // You have entered 6 arguments:
    // 0 1 2 3 4 5 %

    int M = atoi(argv[1]);
    int N = atoi(argv[2]); 
    int P = atoi(argv[3]);
    int T = 4; // da capire come calcolare T

    int **A = createMatrix(M, N); // da completare funzione
    int **B = createMatrix(N, P);
    int **R = createMatrix(N, P);
    int **C = createMatrix(P, M);

    fillMatrix(M,N,A); // da completare funzione
    fillMatrix(N,P,B);

    pthread_t tid[T]; // da capire come calcolare T
    my_task_args = 0; // valori/indirizzi degli array delle matrici da moltiplicare
    for(int i=0; i<T; i++)
        if(pthread_create(*tid[i], NULL, my_task, my_task_args) == -1)
            fprintf(stderr, "thread creation error");



    
    
  

    return 0;    
}