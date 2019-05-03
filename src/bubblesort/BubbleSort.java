/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblesort;
import static java.lang.Integer.min;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
/**
 *
 * @author fcirett
 */
public class BubbleSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int limite = Integer.parseInt(args[0]);
        //int maxs   = Integer.parseInt(args[1]);
        //int menu   = Integer.parseInt(args[2]);
        int maxs = 10000;
        int menu = 6;
        
        List<Integer> list = randomNumbers(limite,maxs);
        System.out.println(list);
        
        switch (menu) {
            case 1:
                System.out.println("BubbleSort");
                bubblesort(list);
                break;
            case 2:
                System.out.println("ShellSort");
                shellsort(list);
                break;
            case 3:
                System.out.println("MergeSort");
                mergesort(list,0,list.size()-1);
                break;
            case 4:
                System.out.println("QuickSort");
                quicksort(list,0,list.size()-1);
                break;
            case 5:
                System.out.println("HeapSort");
                heapsort(list); 
                break;
            case 6:
                System.out.println("TimSort");
                timSort(list, list.size()-1);  
                break;
            case 7:
                System.out.println("insertionSort");
                doInsertionSort(list,0,list.size()-1);
                break;
        }

        System.out.println(list);
        //System.out.println("Listo!");
    }
    public static List<Integer> randomNumbers(int limite_random, int max_size) {
        int contador = 0;
        HashSet<Integer> numerosAleatorios = new HashSet();
        int numero = 0;
        while (contador < max_size){
            numero = getRandom(1,limite_random);
            if (numerosAleatorios.contains(numero)== false ) {
                numerosAleatorios.add(numero);
                contador = contador + 1;
            }
        }
        //Convertir numerosAleatorios a Lista
        List<Integer> Lista = new ArrayList<Integer>(numerosAleatorios);
        return Lista;
    }
    public static Integer getRandom(int lim_inferior,int lim_superior) {
        Random r = new Random();
        return r.nextInt(lim_superior - lim_inferior) + lim_inferior;
    }
    public static void bubblesort(List<Integer> lista) {
        int size = lista.size();
        int counter = size;
        do {
            for (int i = 0; i < size-1; i++) {
                if (lista.get(i) > lista.get(i + 1)) {
                    Integer temp1= lista.get(i + 1);
                    Integer temp2= lista.get(i);
                    lista.set(i,temp1);
                    lista.set(i+1,temp2);
                }
            }
            size = size - 1;
        } while (size != 1);
    }
    public static void shellsort(List<Integer> lista) {
        int size = lista.size();
        int j;
        for (int gap = size/2; gap > 0; gap = gap/2) {
            for (int i = gap; i < size; i++) {
                 int temp = lista.get(i);
                 for (j = i; j >= gap && lista.get(j-gap)>temp; j -= gap) {
                     lista.set(j, lista.get(j-gap) );
                 }
                 lista.set(j, temp);
                
            }
            
        }
    }
    
    public static void mergesort(List<Integer> lista, int izq, int der) {
        if (izq < der) 
        { 
            // Encuentra la mitad
            int mitad = (izq+der)/2; 
  
            // Sort first and second halves 
            mergesort(lista, izq, mitad); 
            mergesort(lista , mitad+1, der); 
  
            // Merge the sorted halves 
            merge(lista, izq, mitad, der); 
        } 
    }
    
    public static void merge(List<Integer> lista, int left, int middle, int right)  {
        // encuentra los tamaños de los arreglos a fusionar
        int n1 = middle - left + 1; 
        int n2 = right - middle; 
  
        // Crea arreglos temporales
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copia datos a los arreglos temporales*/
        for (int i=0; i<n1; ++i) 
            L[i] = lista.get(left + i); 
        for (int j=0; j<n2; ++j) 
            R[j] = lista.get(middle + 1 + j); 
  
        /* Mezcla los arreglos temporales */
        int i = 0, j = 0; 
        int k = left; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                lista.set(k,L[i]); 
                i++; 
            } 
            else
            { 
                lista.set(k,R[j]); 
                j++; 
            } 
            k++; 
        } 
        // copia el remanente de L si queda algo
        while (i < n1) 
        { 
            lista.set(k,L[i]); 
            i++; 
            k++; 
        } 
  
        // copia el remanente de R si queda algo
        while (j < n2) 
        { 
            lista.set(k, R[j]); 
            j++; 
            k++; 
        } 
    }
    public static int partition(List<Integer> lista, int low, int high) 
    { 
        int pivot = lista.get(high);  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than or 
            // equal to pivot 
            if (lista.get(j) <= pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = lista.get(i); 
                lista.set(i,lista.get(j));
                lista.set(j,temp);
            } 
        } 
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = lista.get(i+1); 
        lista.set(i+1,lista.get(high)); 
        lista.set(high,temp); 
  
        return i+1; 
    } 
    public static void quicksort(List<Integer> lista, int low, int high) 
    { 
        if (low < high) 
        { 
            //partition index
            int pi = partition(lista, low, high); 
            // Ordenar recursivamente antes y despues de partition index
            quicksort(lista, low, pi-1); 
            quicksort(lista, pi+1, high); 
        } 
    } 
    
    public static void heapsort(List<Integer> lista) 
    { 
        int n = lista.size(); 
  
        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(lista, n, i); 
  
        // One by one extract an element from heap 
        for (int i=n-1; i>=0; i--) 
        { 
            // Move current root to end 
            int temp = lista.get(0); 
            lista.set(0,i); 
            lista.set(0,temp); 
  
            // call max heapify on the reduced heap 
            heapify(lista, i, 0); 
        } 
    } 
    public static void heapify(List<Integer> lista, int n, int i) 
    { 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && lista.get(l) > lista.get(largest)) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && lista.get(r) > lista.get(largest)) 
            largest = r; 
  
        // If largest is not root 
        if (largest != i) 
        { 
            int swap = lista.get(i); 
            lista.set(i,lista.get(largest)); 
            lista.set(largest,swap); 
  
            // Recursively heapify the affected sub-tree 
            heapify(lista, n, largest); 
        }
    } 
    
    public static void insertionSort(List<Integer> lista, int left, int right) 
    { 
        System.out.println("left:"+left + " right;"+right);
        for (int i = left + 1; i <= right; i++) 
        { 
            int temp = lista.get(i); 
            int j = i - 1; 
            while (lista.get(j) > temp && j >= left) 
            { 
                lista.set(j+1,lista.get(j)); 
                j--; 
            } 
            lista.set(j+1, temp); 
        } 
    } 
    
    public static void doInsertionSort(List<Integer> input, int left, int right){
         
        int temp;
        for (int i = left; i < right; i++) {
            for(int j = i ; j > 0 ; j--){
                if(input.get(j) < input.get(j-1)){
                    temp = input.get(j);
                    input.set(j, input.get(j-1));
                    input.set(j-1, temp);
                }
            }
        }
        
    }
    public static void merges(List<Integer> lista, int l, int m, int r) 
    { 
        // original array is broken in two parts 
        // left and right array 
        int len1 = m - l + 1, len2 = r - m; 
        
        int left[] = new int [len1];
        int right[] = new int [len2];
        
        for (int i = 0; i < len1; i++) 
            left[i] = lista.get(l + i); 
        for (int i = 0; i < len2; i++) 
            right[i] = lista.get(m + 1 + i); 

        int i = 0; 
        int j = 0; 
        int k = l; 

        // after comparing, we merge those two array 
        // in larger sub array 
        while (i < len1 && j < len2) 
        { 
            if (left[i] <= right[j]) 
            { 
                lista.set(k,left[i]); 
                i++; 
            } 
            else
            { 
                lista.set(k,right[j]); 
                j++; 
            } 
            k++; 
        } 

        // copy remaining elements of left, if any 
        while (i < len1) 
        { 
            lista.set(k,left[i]); 
            k++; 
            i++; 
        } 

        // copy remaining element of right, if any 
        while (j < len2) 
        { 
            lista.set(k,right[j]); 
            k++; 
            j++; 
        } 
    } 
    public static void timSort(List<Integer> lista, int n) 
{ 
    int RUN = 32;
    // Sort individual subarrays of size RUN 
    for (int i = 0; i < n; i+=RUN) 
        doInsertionSort(lista, i, min((i+31), (n-1))); 
  
    // start merging from size RUN (or 32). It will merge 
    // to form size 64, then 128, 256 and so on .... 
    for (int size = RUN; size < n; size = 2*size) 
    { 
        // pick starting point of left sub array. We 
        // are going to merge arr[left..left+size-1] 
        // and arr[left+size, left+2*size-1] 
        // After every merge, we increase left by 2*size 
        for (int left = 0; left < n; left += 2*size) 
        { 
            // find ending point of left sub array 
            // mid+1 is starting point of right sub array 
            int mid = left + size - 1; 
            int right = min((left + 2*size - 1), (n-1)); 
  
            // merge sub array arr[left.....mid] & 
            // arr[mid+1....right] 
            merge(lista, left, mid, right); 
        } 
    } 
} 
}
