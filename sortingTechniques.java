import java.util.*;
public class sortingTechniques {
    
    static int arr[];
    static Scanner readData = new Scanner(System.in);
    static String userName;

    public static void swapping(int arr[] , int first , int last){
        int tempElement = arr[first];
        arr[first]      = arr[last];
        arr[last]       = tempElement;
    }

    public static void selectionSort(int array[]){
        int outerLoop , innerLoop , minElement;
        
        for(outerLoop = 0 ; outerLoop < array.length ; outerLoop++){
            minElement = outerLoop;
            
            for (innerLoop = outerLoop + 1; innerLoop < array.length; innerLoop++) {
                if(array[innerLoop] < array[minElement]){
                    minElement = innerLoop;
                }
            }swapping(array,outerLoop,minElement);
        }
    }

    public static void insertionSort(int array[]){
        int outerLoop , innerLoop , temp;

        for(outerLoop = 1 ; outerLoop < array.length ; outerLoop++){
            innerLoop = outerLoop;
            temp = array[outerLoop];

            while(innerLoop > 0 && array[innerLoop-1] >= temp ){
                array[innerLoop] = array[innerLoop - 1];
                innerLoop-=1;
            }
            array[innerLoop] = temp;   
        }
    }

    public static void bubbleSort(int array[]){
        int outerLoop , innerLoop;
        for(outerLoop = array.length-1 ; outerLoop >= 0 ; outerLoop--){
            
            for(innerLoop = 0 ; innerLoop < outerLoop ; innerLoop++){
                if(array[innerLoop] > array[innerLoop+1]){
                    swapping(array , innerLoop , innerLoop+1);
                }
            }
        }
    }

    public static void insertionSortString(String array[]){
        int outer , inner;
        for(outer = 1 ; outer < array.length ; outer++){
            String temp = array[outer];
            inner = outer-1;

            while(inner >= 0 && array[inner].compareTo(temp) > 0){
                array[inner+1] = array[inner];
                inner -=1;
            }
            array[inner+1] = temp;
        }
    }

    public static int summationElement(int arr[]){
        int sum = 0;
        for (int i = 0 ; i < arr.length ; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static int minimumElement(int arr[]){
        int min = summationElement(arr);
        for (int i = 0 ; i < arr.length ; i++) {
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }

    public static int maximumElement(int arr[]){
        int max = arr[0];
        for (int i = 0 ; i < arr.length ; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }

    public static int repeatedElement(int arr[],int neededNumber){
        int counter = 0;

        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i] == neededNumber){
                counter+=1;
            }
        }

        if(counter == 0){
            return 0;
        }else{
            return counter;
        }
    }    
    
    public static int[] sortedSection(int mainElement[] , int countingElement[]){
        int size = mainElement.length;
        int sortBase[] = new int[size];

        int counterOFSize = size-1;
        while(counterOFSize >= 0){
            // -1 in this section : sortBase[countingElement[mainElement[counterOFSize]]-1]
            // Because converting from One-based to Zero-Based

            sortBase[countingElement[mainElement[counterOFSize]]-1] = mainElement[counterOFSize];
            countingElement[mainElement[counterOFSize]]-=1;
            counterOFSize-=1;
        }
        return sortBase;
    }

    public static void countingSort(int arr[]){
        int sizeOFCounter = maximumElement(arr);
        int arrCounter[] = new int[sizeOFCounter+1];

        for(int i = 0 ; i < arrCounter.length ; i++){
            arrCounter[i] = repeatedElement(arr,i);
        }

        for(int i = 1 ; i < arrCounter.length ; i++){
            arrCounter[i] +=arrCounter[i-1];
        }

        System.out.println("Elements after sorting: " + (Arrays.toString(sortedSection(arr,arrCounter))));        
    }

    public static void mergeSort(int arr[]){
        int arrSize = arr.length;
        int middle  = arr.length / 2;
        
        int arrLeft[]  = new int [middle];
        int arrRight[] = new int [arrSize - middle];

        if(arrSize <= 1) return; // as a base case for merge sort
        
        int j = 0;
        // Move element from main array into left + right section arrays 
        for(int i = 0 ; i < arrSize ; i++){
            // 0 - 5 for example
            if(i < middle){
                // copy element from main array to new array in left tree part 
                arrLeft[i] = arr[i];
            }else{
                // copy element from main array to new array in right tree part 
                arrRight[j] = arr[i];
                j+=1;
            }
        }

        // to do de-composition again to each part
        mergeSort(arrLeft);
        mergeSort(arrRight);

        compositionElement(arrLeft,arrRight,arr);
    }

    private static void compositionElement(int[] arrLeft, int[] arrRight, int[] arr) {
        int leftSection  = arr.length/2;
        int rightSection = arr.length - leftSection;

        int index = 0;
        int left  = 0;
        int right = 0;

        // First while loop: Merge elements from arrLeft and arrRight into arr
        while(left < leftSection && right < rightSection){
            if(arrLeft[left] < arrRight[right]){
                arr[index] = arrLeft[left];
                index+=1;
                left+=1;
            }else{
                arr[index] = arrRight[right];
                index+=1;
                right+=1;
            }
        }

        // Second while : This happens if there are elements left in arrLeft after the first while loop.
        // where will do movement into main arr to do sorting 

        while(left < leftSection){
            arr[index] = arrLeft[left];
            index+=1;
            left+=1;
        }

        // Same Second while but for right part
        while(right < rightSection){
            arr[index] = arrRight[right];
            index+=1;
            right+=1;
        }
    }

    public static void quickSort(int arr[], int startingPoint , int endingPoint){        
        if(startingPoint < endingPoint){
            int pivot = partitionSection(arr,startingPoint,endingPoint);
            quickSort(arr,startingPoint,pivot-1);
            quickSort(arr,pivot+1,endingPoint);
        }
    }

    public  static int partitionSection(int[] arr, int startingPoint, int endingPoint) {
        int pivot = arr[endingPoint];
        int i = startingPoint - 1;

        for(int j = startingPoint ; j < endingPoint ; j++){
            if(arr[j] <= pivot){
                i+=1;
                swapping(arr,i,j);
            }
        }
        swapping(arr,i+1,endingPoint);
        return i+1;
    }


    public static void heapIFY(int arr[] , int arrSize , int parentNode){
        int leftPart  = 2 * parentNode + 1;
        int rightPart = 2 * parentNode + 2;

        int maxNode = parentNode;

        if(leftPart < arrSize  && arr[leftPart] > arr[maxNode]){
            maxNode = leftPart;
        }
        if(rightPart < arrSize && arr[rightPart] > arr[maxNode]){
            maxNode = rightPart;
        }
        if(maxNode != parentNode){
            swapping(arr,parentNode,maxNode);
            heapIFY(arr,arrSize,maxNode);
        }
    }

    public static void heapSort(int arr[]){
        int arrSize = arr.length;
        
        for(int i = (arrSize/2)-1 ; i >= 0 ; i--  ){
            heapIFY(arr,arrSize,i);
        }

        for(int j = arrSize-1 ; j > 0 ; j--){
            swapping(arr,0,j);
            heapIFY(arr,j,0);
        }
    }

    public static void main(String[] args){
        show();
    }

    private static void show() {        
        userScreen();        
        System.out.print("\nAre you want to re-sort element (True/False)? : ");
        boolean answerOption = readData.nextBoolean();

        if(answerOption){
            userScreen();
        }System.out.println("\nFinally , thank you " + (userName) + " for visit us [ Good Luck :) ]\n");
    }

    public static void userScreen() {        
        System.out.print("\nHello , enter your first name please: ");
        userName = readData.next();

        System.out.println("\nWelcome " + (userName) + " in sorting application\n");
        selectSortingAlgorithm(showSortingAlgorithm());
    }

    public static void selectSortingAlgorithm(int numberOFAlgorithm){
        switch(numberOFAlgorithm){
            case 1:
                selectionSort(getElement());
                System.out.println("Your element's after sorting: " + (Arrays.toString(arr)));
                break;
            case 2:
                bubbleSort(getElement());
                System.out.println("Your element's after sorting: " + (Arrays.toString(arr)));
                break;
            case 3:
                insertionSort(getElement());
                System.out.println("Your element's after sorting: " + (Arrays.toString(arr)));
                break;
            case 4:
                mergeSort(getElement());
                System.out.println("Your element's after sorting: " + (Arrays.toString(arr)));
                break;
            case 5:
                System.out.print("\nEnter size of your element's: ");
                int arrSize = readData.nextInt();
                int arrQuick[] = new int[arrSize];

                for(int index = 0 ; index < arrSize ; index +=1){
                    System.out.print("\nEnter element number " + (index+1) + " : ");
                    arrQuick[index] = readData.nextInt();
                }
                quickSort(arrQuick,0,arrSize-1);
                System.out.println("Your element's after sorting: " + (Arrays.toString(arrQuick)));
                break;
            case 6:
                countingSort(getElement());
                break;
            case 7:
                heapSort(getElement());
                System.out.println("Your element's after sorting: " + (Arrays.toString(arr)));
                break;
            case 8:
                System.out.println("No problem and thank you for visit us");
                break;
            default:
                System.out.println("Sorry , please enter correct number as exist in you screen..!");
                break;
        }
    }

    public static int [] getElement() {
        System.out.print("\nEnter size of your element's: ");
        int arrSize = readData.nextInt();
        arr = new int[arrSize];

        for(int index = 0 ; index < arrSize ; index +=1){
            System.out.print("\nEnter element number " + (index+1) + " : ");
            arr[index] = readData.nextInt();
        }
        return arr;
    }

    public static int showSortingAlgorithm() {
        System.out.println("1.Selection sort");
        System.out.println("2.Bubble    sort");
        System.out.println("3.insertion sort");
        System.out.println("4.merge     sort");
        System.out.println("5.quick     sort");
        System.out.println("6.counter   sort");
        System.out.println("7.Heap      sort");
        System.out.println("8.None ");

        System.out.print("\nPlease enter number of any algorithm as you need: ");
        int algorithmNumber = readData.nextInt();
        
        return algorithmNumber;
    }
}
