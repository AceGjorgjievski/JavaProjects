package mk.ukim.finki.aps.kodovi.kod2_BruteForceDivNConq;

public class DivideAndConquer {

    int INF = 1000000;

    //spojuvanje na dve sortirani nizi [l, mid], [mid+1, r]
    //rezultatot e nova sortirana niza
    void merge(int a[], int l, int mid, int r) {
        int numel = r - l + 1;//br elementi
        int temp[] = new int[100]; //nova niza za privremeno cuvanje na sortiranite elementi
        int i, j, k = 0;

        i = l;
        j = mid + 1;

        while ((i <= mid) && (j <= r)) {
            if (a[i] < a[j]) {
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

    void mergesort(int a[], int l, int r) {
        if (l == r) {
            return;
        }
        int mid = (l + r) / 2;
        mergesort(a, l, mid);
        mergesort(a, mid + 1, r);
        merge(a, l, mid, r);
    }

    int pow(int x, int n) {
        int r;

        if (n == 0) {
            return (1);
        } else if (n % 2 == 0) {
            r = pow(x, (n / 2));
            return r * r;
        } else {
            r = pow(x, (n / 2));
            return x * r * r;
        }
    }

    int pow2(int x, int n){//3^2
        if(n == 0) return 1;
        else if(n%2 == 0){
            return pow2(x,(n/2)) * pow2(x,(n/2));
        } else {
            return pow2(x,(n/2)) * pow2(x,(n/2)) * x;
        }
    }

    DvaNajmali pronajdi(int a[], int l, int r) {
        if (l == r) {
            DvaNajmali dn = new DvaNajmali(a[l], INF);
            return dn;
        }

        int mid = (l + r) / 2;

        DvaNajmali r1 = pronajdi(a, l, mid); // kje sleguva nadula se dodeka nema 1 element
        DvaNajmali r2 = pronajdi(a, mid + 1, r);// isto kako gore

        DvaNajmali r3 = new DvaNajmali();

        if (r1.a < r2.a) {
            r3.a = r1.a;
            if (r1.b < r2.a) {
                r3.b = r1.b;
            } else {
                r3.b = r2.a;
            }
        } else {
            r3.a = r2.a;
            if (r1.a < r2.b) {
                r3.b = r1.a;
            } else {
                r3.b = r2.b;
            }
        }
        return r3;
    }

    public static void main(String[] args) {
        int i;

        DivideAndConquer dac = new DivideAndConquer();

        int a[] = new int[]{9, 2, 4, 6, 0, 8, 7, 3, 1, 5};
        int a2[] = new int[]{4, 1, 2, 3};

        dac.mergesort(a2, 0, 3);

        for (i = 0; i < a2.length; i++) {
            System.out.print(a2[i] + " ");
        }
        System.out.println();

//        System.out.println("pow: " + dac.pow(2, 10));
//        System.out.println("pow: " + dac.pow2(2, 2));

//        int niza[] = new int[]{9, 2, 4, 6, 0, 8, 7, 3, 1, 5};
//        int niza2[] = new int[]{4,2,3,1};
//
//        DvaNajmali rez = dac.pronajdi(niza2, 0, 3);

       // System.out.println("Dva najmali: "+rez.a+" "+rez.b);
        
    }
}
