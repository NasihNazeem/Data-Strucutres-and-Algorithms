import java.util.Random;

/**
 * Assign1
 */
public class Assign1 {

    Random random = new Random();       // Object created to fill array with random integers
    private String order;               // order inputted from user (ascending, descending, random)
    private int size;                   // user inputted size of the array
    private String algo;                // user inputted sort algorithm (selection, insertion, merge, quick)
    private int arr[];                  // array generated with random values input
    
    public static void main(String[] args) {
        Assign1 a1 = new Assign1();     // Object created to interact with static main

        a1.order = args[0];
        a1.size = Integer.parseInt(args[1]);
        a1.algo = args[2];

        a1.arr = new int [a1.size];

        a1.fillRandom();                // Filling array with random integers
        a1.chooseAlgorithm();           // match the algorithm within a switch statement, to then call upon an appropriate sorting function
    }


    /**
     * PROMISES: Choosing an algorithm that matches the algorithm the user requested.
     * REQUIRES: N/A.
     */
    public void chooseAlgorithm(){
        switch(algo){
            case "selection":
                selectionSort();
                break;
            case "insertion":
                insertionSort();
                break;

            case "merge":
                mergeSort();
                break;

            case "quick":
                quickSort();
                break;

            default:

        }
    }


    /**
     * PROMISES: The swapping of the values in two indices.
     * REQUIRES: The array, the first index, and the second.
     * @param array Main Array
     * @param left  First value being swapped
     * @param right Second vlaue being swapped
     */
    public  void swap(int [] array, int left, int right){

        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;

    }

    /**
     * PROMISES: Filling an empty array that is arbitrarily sized with random integers..These integers are within the 0 to 100 boundary.
     * REQUIRES: N/A.
     */
    public void fillRandom(){
        for(int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
    }

    /**
     * PROMISES: Looks into the order requested, and sorts the array based upon that order.
     * REQUIRES: N/A.
     */
    public void selectionSort(){
        int min;
        switch(order){
            case "ascending":
                for(int i = 0; i < arr.length - 1; i++){
                    min = i;

                    for(int j = i + 1; j < arr.length; j++) {
                        if(arr[j] < arr[min]) {
                            min = j;
                        }
                    }

                    swap(arr, min, i);
                }
                for(int i = 0; i < arr.length; i++){
                    System.out.println(arr[i]);
                }
                break;
            case "descending":
                for(int i = 0; i < arr.length - 1; i++){
                    min = i;

                    for(int j = i + 1; j < arr.length; j++) {
                        if(arr[j] > arr[min]) {
                            min = j;
                        }
                    }

                    swap(arr, min, i);
                }
                for(int i = 0; i < arr.length; i++){
                    System.out.println(arr[i]);
                }
                break;
        }

    }
    

    /**
     * PROMISES: Looks into the order requested, and sorts the array based upon that order.
     * REQUIRES: N/A.
     */
    public void insertionSort(){
        switch(order){
            case "ascending":
                for(int i = 1; i < arr.length; ++i){
                    int key = arr[i];
                    int j = i-1;


                    while( j >= 0 && arr[j] > key) {
                        arr[j+1] = arr[j];
                        j = j - 1;
                    }
                    arr[j+1] = key;
                }
                for(int i = 0; i < arr.length; i++){
                    System.out.println(arr[i]);
                }
                break;
            case "descending":
                for(int i = 1; i < arr.length; ++i){
                    int key = arr[i];
                    int j = i-1;


                    while( j >= 0 && arr[j] < key) {
                        arr[j+1] = arr[j];
                        j = j - 1;
                    }
                    arr[j+1] = key;
                }
                for(int i = 0; i < arr.length; i++){
                    System.out.println(arr[i]);
                }
                break;
        }
    }

    /**
     * PROMISES: Looks into the order requested, and sorts the array based upon that order. Calls upon a sorting function.
     * REQUIRES: N/A.
     */
    public void mergeSort(){
        sort(arr,arr.length);
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }                    
        
    }

    /**
     * PROMISES: Splits the array into two separate sub-arrays. Sorts both sub-arrays before merging with one another.
     * REQUIRES: main array with random values, length of main array.
     */
    public void sort(int arr[], int length){
        if(length < 2)
            return;

        int  mid = length/2;
        int [] l = new int[mid];
        int [] r = new int[length - mid];

        for(int i = 0; i < mid; i++){
            l[i] = arr[i];
        }
        for(int i = mid; i < length; i++){
            r[i - mid] = arr[i];
        }

        sort(l, mid);
        sort(r, length-mid);

        switch(order){
            case "ascending":
                ascendMerge(arr, l, r, mid, length - mid);
                break;
            case "descending":
                descendMerge(arr, l, r, mid, length - mid);
                break;
        }
        
        
    }

    /**
     * PROMISES: Once mergeSort has completed the sorting of the two sub-arrays, this function merges both arrays into an ascending order.
     * REQUIRES: Main array, left sub-array, right sub-array, last index of left array, last index of right array.
     */
    public void ascendMerge(int arr[], int [] l, int [] r, int left, int right){

        int i = 0, j = 0, k = 0;
        while(i < left && j < right) {
            if(l[i] <= r[j]) {
                arr[k++] = l[i++];
            }
            else {
                arr[k++] = r[j++];
            }
        }

        while(i < left) {
            arr[k++] = l[i++];
        }
        while(j < right) {
            arr[k++] = r[j++];
        }
        
    }

    /**
     * PROMISES: Once mergeSort has completed the sorting of the two sub-arrays, this function merges both arrays into an descending order.
     * REQUIRES: Main array, left sub-array, right sub-array, last index of left array, last index of right array.
     */
    public void descendMerge(int arr[], int [] l, int [] r, int left, int right){

        int i = 0, j = 0, k = 0;
        while(i < left && j < right) {
            if(l[i] >= r[j]) {
                arr[k++] = l[i++];
            }
            else {
                arr[k++] = r[j++];
            }
        }

        while(i < left) {
            arr[k++] = l[i++];
        }
        while(j < right) {
            arr[k++] = r[j++];
        }
        
    }

    /**
     * PROMISES: Uses a pivotSort function that pivots on certain values in the array to sort as reference.
     * REQUIRES: N/A.
     */
    public void quickSort(){
        pivotSort(arr, 0, arr.length - 1);
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }      
    }

    /**
     * PROMISES: Uses certain values in the array as a pivot/reference to compare the other elements in the array.
     * REQUIRES: Main array, first index, last index.
     */
    public void pivotSort(int [] arr, int low, int high) {
        int pi;
        if(low < high){
            switch(order){
                case "ascending":
                    pi = ascendPartition(arr,low,high);

                    pivotSort(arr, low, pi-1);
                    pivotSort(arr, pi +1 , high);
                    break;
                case "descending":
                    pi = descendPartition(arr,low,high);

                    pivotSort(arr, low, pi-1);
                    pivotSort(arr, pi +1 , high);
                    break;

            }
            


            
        }
    }

    /**
     * PROMISES: Arranges the array using the pivot to sort in an ascending order.
     * REQUIRES: Main array, first index, last index.
     */
    public int ascendPartition(int [] arr, int low, int high) {
        
        int pivot = arr[high];

        int i = (low - 1);

        for(int j = low; j <= high - 1; j++) {

            if(arr[j] < pivot)
            {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i+1, high);
        return (i+1);
    }

    /**
     * PROMISES: Arranges the array using the pivot to sort in an descending order.
     * REQUIRES: Main array, first index, last index.
     */
    public int descendPartition(int [] arr, int low, int high) {
        
        int pivot = arr[high];

        int i = (low - 1);

        for(int j = low; j <= high - 1; j++) {

            if(arr[j] > pivot)
            {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i+1, high);
        return (i+1);
    }

    
}