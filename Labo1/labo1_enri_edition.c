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


int main(int argc, char* argv[]) {
    // printf("You have entered %d arguments:\n", argc);
    // for (int i = 0; i < argc; ++i)
    //     printf("%d ", atoi(argv[i]));

    int M = atoi(argv[1]);
    int N = atoi(argv[2]); 
    int P = atoi(argv[3]);

    int **A = createMatrix(M, N);
    int **B = createMatrix(N, P);
    int **C = createMatrix(P, M);

    
    
  

    return 0;    
}