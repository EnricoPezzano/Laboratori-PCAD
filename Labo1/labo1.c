#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <pthread.h>
#include <time.h>

pthread_barrier_t barrier;
int M,N,P;


void ins (int row, int column, int** matrix);
int** createArray(int m, int n);
void destroyArray(int** arr);
void printMatrix(int row, int column, int** matrix);

typedef struct matrix {
    int rows; //numero righe
    int cols; //num colonne
    int **data;
}matrix;

struct matrix A;
struct matrix B;
struct matrix R;

void *mul(void *arg) {
    int indexRow = *((int *)arg);
    free(arg);
    printf("indexRow: %d\n", indexRow);
    int moltiplicazione = 0;
    
    for(int i=0; i<B.cols; i++) {
        for(int j=0; j<B.rows; j++) {
            moltiplicazione += A.data[indexRow][j]*B.data[j][i];
        }

        R.data[indexRow][i] = moltiplicazione;
        moltiplicazione = 0; 
    }  

    pthread_barrier_wait(&barrier);
    printf("RUNNING\n");
}

int main() {

    //L'utente inserisce numero righe e colonne della prima matrice (A)
    printf("Inserire num righe matrice A: ");
    scanf("%d", &M); 
    printf("Inserire num colonne matrice A: ");
    scanf("%d", &N);
    printf("Inserire num colonne matrice B: "); 
    scanf("%d", &P);

    pthread_barrier_init(&barrier, NULL, M+1);
    A.data = createArray(M,N); //creo la matrice A
    A.rows = M;
    A.cols = N;
    
    B.data = createArray(N,P);
    B.rows = N;
    B.cols = P;

    ins(A.rows, A.cols, A.data);
    ins(B.rows, B.cols, B.data);
    printMatrix(A.rows, A.cols, A.data);
    printMatrix(B.rows, B.cols, B.data);

    R.data = createArray(M,P);
    R.rows = M;
    R.cols = P;

    int num_threads = M;
    pthread_t tid[M];

    for(int i=0; i < num_threads; i++) {
        int* k= malloc(sizeof(int));
        *k=i;
        pthread_create(&tid[i], NULL, &mul,  (void*) k);
    }

    pthread_barrier_wait(&barrier);
    for(int i=0; i<num_threads; i++) {
        pthread_join(tid[i], NULL);
    }

    pthread_barrier_destroy(&barrier);
    printMatrix(M,P,R.data);
    destroyArray(A.data);
    destroyArray(B.data);
    destroyArray(R.data);
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
