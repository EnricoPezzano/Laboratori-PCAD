#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <pthread.h>
#include <time.h>

pthread_barrier_t barrier;
int M,N,P; // Numero righe e colonne delle matrici 
int T; // Numero di thread utilizzati

void ins (int row, int column, int** matrix); // inserisce valori random da 0 a 9 nella matrice
int** createArray(int m, int n); // crea l'array bidimensionale che conterrà la matrice
void destroyArray(int** arr); // elimina la matrice, libera la memoria 
void printMatrix(int row, int column, int** matrix); // stampa la matrice

typedef struct matrix { // Ogni matrice è una struct 
    int rows; // numero righe
    int cols; // num colonne
    int **data; // array bidimensionale che contiene la matrice
}matrix;

// A B e R sono matrici globali, in modo che possano essere viste anche dai thread
struct matrix A;
struct matrix B;
struct matrix R;

void *mul(void *arg) {
    int indexRow = *((int *)arg); // ho passato *k al thread e l'ho salvato in indexRow
    free(arg); // libero k* (la zona di memoria puntata da k)
    printf("indexRow: %d\n", indexRow); // serve per il debug
    int moltiplicazione = 0; // conterrà il valore di una cella della matrice R (risultato)
    
    // Moltiplico la riga indexRow di A per tutte le colonne di B
    for(int z=indexRow; z < indexRow+(M/T); z++) { 
        for(int i=0; i<B.cols; i++) { 
            for(int j=0; j<B.rows; j++) {
                moltiplicazione += A.data[z][j]*B.data[j][i];
            }
            printf("%d ", z);
            // salvo il risultato nella riga indexRow di R, al termine del ciclo 
            // avrò calcolato tutta la riga indexRow di R

            R.data[z][i] = moltiplicazione;
            //printf("%d", R.data[z][i]);
            moltiplicazione = 0; 
        }  
        printf("\n");
    }
    pthread_barrier_wait(&barrier);
    printf("RUNNING\n");
}

int main() {

    // L'utente inserisce numero righe e colonne della prima matrice (A)
    printf("Inserire num righe matrice A: ");
    scanf("%d", &M); 
    printf("Inserire num colonne matrice A: ");
    scanf("%d", &N);
    printf("Inserire num colonne matrice B: "); 
    scanf("%d", &P);

    printf("Inserire numero di thread da utilizzare: " );
    scanf("%d", &T);

    pthread_barrier_init(&barrier, NULL, T+1); // inizializzo la barriera
    A.data = createArray(M,N); // creo la matrice A
    A.rows = M;
    A.cols = N;
    
    B.data = createArray(N,P); // creo la matrice B
    B.rows = N;
    B.cols = P;

    ins(A.rows, A.cols, A.data);
    ins(B.rows, B.cols, B.data);
    printMatrix(A.rows, A.cols, A.data);
    printMatrix(B.rows, B.cols, B.data);

    R.data = createArray(M,P); // creo la matrice R
    R.rows = M;
    R.cols = P;

    pthread_t tid[T];
    int* count = malloc(sizeof(int));
    *count = 0;
    for(int i=0; i < T*(M/T); i+=M/T) {
        int* k= malloc(sizeof(int)); 
        *k=i; 
        pthread_create(&tid[*count], NULL, &mul,  (void*) k); 
        
        printf("COUNT: %d\n", *count);
        *count =+ 1;
        // creo il thread passando k (sarebbe come passare i)
        // Se passassi direttamente i, i thread vedrebbero valori uguali o incasinati,
        // in questo modo salvo il valore di i in una zona di memoria puntata da k
        // il thread salverà questo valore e farà la free() di k*, in modo che 
        // nel prossimo ciclo a k* verrà assengato il valore di i incrementato
    }

    pthread_barrier_wait(&barrier);

    for(int i=0; i<T; i++) {
        pthread_join(tid[i], NULL);
        printf("Join %d\n", i);
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
