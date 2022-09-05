package mk.ukim.finki.aps.kodovi.kod5_Sort;

import java.util.*;

public class Sorters {

	public static <T extends Comparable<? super T>> void insertionSort(T[] array) {
		T key;
		int begin = 0;
		int end = array.length - 1;
		int i;

		for (int index = begin + 1; index <= end; index++) {
			key = array[index];
			i = index - 1;
			while (i >= begin && array[i].compareTo(key) > 0) {
				array[i + 1] = array[i];
				i = i - 1;
			}
			array[i + 1] = key;
		}
	}

	public static <T extends Comparable<? super T>> void bubbleSort(T[] array) {
		int begin = 0;
		int end = array.length - 1;
		T temp; boolean flipped = false;

		for (int i = end; i >= begin; i--) {
			flipped = false;
			for (int j = 1; j <= i; j++) {
				if (array[j - 1].compareTo(array[j]) > 0) {
					temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
					flipped = true;
				}
			}
			if(!flipped) break;
		}
	}

	public static <T extends Comparable<? super T>> void merge(T a[], int l,
			int mid, int r) {
		int numel = r - l + 1;
		// nova niza za privremeno cuvanje
		// na sortiranite elementi
		@SuppressWarnings("unchecked")
		T temp[] = (T[]) new Comparable[a.length];

		int i = l, j = mid + 1, k = 0;

		while ((i <= mid) && (j <= r)) {
			if (a[i].compareTo(a[j]) < 0) {
				temp[k] = a[i];
				i++;
			} else {
				temp[k] = a[j];
				j++;
			}
			k++;
		}
		while (i <= mid) {
			temp[k] = a[i];
			i++;
			k++;
		}

		while (j <= r) {
			temp[k] = a[j];
			j++;
			k++;
		}

		for (k = 0; k < numel; k++) {
			a[l + k] = temp[k];
		}
	}

	public static <T extends Comparable<? super T>> void mergeSort(T a[],
			int l, int r) {
		if (l == r) {
			return;
		}

		int mid = (l + r) / 2;
		mergeSort(a, l, mid);
		mergeSort(a, mid + 1, r);
		merge(a, l, mid, r);
	}

	public static <T extends Comparable<? super T>> void swap(T A[], int x, int y) {
		T temp = A[x];
		A[x] = A[y];
		A[y] = temp;
	}

	public static <T extends Comparable<? super T>> int partition(T A[],
			int left, int right) {
		
		int i = left, j = right;
		T pivot = A[(left + right) / 2];
		while (i <= j) {
			while (A[i].compareTo(pivot) < 0)
				i++;
			while (A[j].compareTo(pivot) > 0)
				j--;
			if (i <= j) {
				swap(A, i, j);
				i++;
				j--;
			}
		}
		return i;
	}
	

	public static <T extends Comparable<? super T>> void quickSort(T A[], int left, int right) {
		int pivot_indeks = partition(A,left,right);
		if(left < pivot_indeks-1)
			quickSort(A, left, pivot_indeks-1);
		if(pivot_indeks < right)
			quickSort(A, pivot_indeks, right);
	}
	
	public static void countingSort(Integer A[], int k)
	{
	    int i, j;  
		Integer c[] = new Integer[k+1];
	    Integer b[] = new Integer[A.length];
		
	    for (i=0; i <= k; i++)
	       c[i] = 0;
	    for (j=0; j < A.length; j++)
	       c[A[j]] = c[A[j]] + 1;
	    for (i = 1;  i <= k;  i++)
	       c[i] = c[i] + c[i-1]; 
	    for (j = A.length-1 ; j >= 0; j--)
	    {
	        b[c[A[j]] - 1] = A[j];
	        c[A[j]] = c[A[j]] - 1;
	    }
	    
	    for (j=0; j < A.length; j++)
		       A[j] = b[j];
	}
	
	public static void bucketSort(Integer A[], int maxVal){
        Integer [] bucket=new Integer[maxVal+1];

        for (int i=0; i<bucket.length; i++){
            bucket[i]=0;
        }

        for (int i=0; i<A.length; i++){
            bucket[A[i]]++;
        }

        int outPos=0;
        for (int i=0; i<bucket.length; i++){
            for (int j=0; j<bucket[i]; j++){
                A[outPos++]=i;
            }
        }
    }
	
	public static void radixSort(Integer A[]){
		Integer[] pom = new Integer[A.length];
        int i, max = A[0], exp = 1;
        
        for (i = 0; i < A.length; i++)
        {
          if (A[i] > max)
            max = A[i];
        }
       
        while (max / exp > 0)
        {
          int bucket[] =  new int[10];
          for (i = 0; i < A.length; i++)
            bucket[A[i] / exp % 10]++;
          for (i = 1; i < 10; i++)
            bucket[i] += bucket[i - 1];
          for (i = A.length - 1; i >= 0; i--)
            pom[--bucket[A[i] / exp % 10]] = A[i];
          for (i = 0; i < A.length; i++)
            A[i] = pom[i];
          exp *= 10;
        }

    }
	
	public static <T extends Comparable<? super T>> boolean proverka(T[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i].compareTo(array[i - 1]) < 0) // mozhe i >> array[i-1].compareTo(array[i]) > 0
				return false;
		}
		return true;
	}

	public static <T extends Comparable<? super T>> void copyarray(T[] array1, T[] array2) {
		for (int i = 0; i < array1.length; i++) {
			array2[i] = array1[i];
		}
	}

	public static void main(String[] args) {

		Integer[] array = new Integer[100];
		int maxVal = 1000;
		Random r = new Random();
		//Se generiraat slucajni broevi od 0 do maxVal za nizata 
		for (int i = 0; i < 100; i++) {
			array[i] = r.nextInt(maxVal);
		}
		Integer[] pomarray = new Integer[100];
		copyarray(array, pomarray);

		System.out.println("Insertion");
		insertionSort(array);
		System.out.println(proverka(array));

		copyarray(pomarray, array);
		System.out.println("Bubble");
		bubbleSort(array);
		System.out.println(proverka(array));

		copyarray(pomarray, array);
		System.out.println("Merge");
		mergeSort(array, 0, array.length - 1);
		System.out.println(proverka(array));

		copyarray(pomarray, array);
		System.out.println("Quick");
		quickSort(array, 0, array.length - 1);
		System.out.println(proverka(array));
		
		copyarray(pomarray, array);
		System.out.println("Counting");
		countingSort(array, maxVal);
		System.out.println(proverka(array));
		
		copyarray(pomarray, array);
		System.out.println("Bucket");
		bucketSort(array, maxVal);
		System.out.println(proverka(array));

		copyarray(pomarray, array);
		System.out.println("Radix");
		radixSort(array);
		System.out.println(proverka(array));
	}
}