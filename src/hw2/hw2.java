package hw2;

import static java.lang.Math.abs;
import static java.lang.Math.random;
/*
1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], пройти по нему циклом, и числа, меньшие 6, умножить на 2;
4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
5. Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
6. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры: checkBalance([1, 1, 1, || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) → false, checkBalance ([10, || 10]) → true, граница показана символами ||, эти символы в массив не входят;
7. *Написать метод, которому на вход подается одномерный массив и число n (может быть положительным или отрицательным), при этом метод должен сместить все элементы массива на n позиций. Нельзя пользоваться вспомогательными массивами.
*/

public class hw2 {
    public static void main(String[] args) {
        //task 1
        int arr[] = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        printArr(arr);
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == 0)
                arr[i] = 1;
            else if (arr[i] == 1)
                arr[i] = 0;
        printArr(arr);
        System.out.println();

        //task 2
        int arr2[] = new int[8];
        for (int i = 0; i < arr2.length; i++)
            arr2[i] = i * 3;
        printArr(arr2);
        System.out.println();

        //task 3
        int arr3[] = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArr(arr3);
        for (int i = 0; i < arr2.length; i++)
            if (arr3[i] < 6)
                arr3[i] *= 2;
        printArr(arr3);
        System.out.println();

        //task 4
        int n = 5;
        int arr4[][] = new int[n][n];
        for (int i = 0; i < n; i++)
            arr4[i][i] = 1;
        print2dArr(arr4);

        //task 5
        int arr5[] = new int[8];
        for (int i = 0; i < arr5.length; i++)
            arr5[i] = (int) (Math.random() * 100);
        printArr(arr5);

        int max = arr5[0], min = arr5[0];
        for (int i : arr5) {
            if (i > max) max = i;
            if (i < min) min = i;
        }
        System.out.println("Max = " + max + " Min = " + min + "\n");

        //task 6
        System.out.println(checkBalance(new int[]{1, 1, 1, 2, 1}));  //→ true
        System.out.println(checkBalance(new int[]{2, 1, 1, 2, 1}));  //→ false
        System.out.println(checkBalance(new int[]{10, 10}));  //→ true
        System.out.println();

        //task 7
        int arr7[] = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8};  //10 эл-в
        printArr(arr7);
        displace(arr7, 12);
        printArr(arr7);
    }

    static boolean checkBalance(int[] arr) {
        int s = 0, sn = 0;
        for (int i : arr) s += i;
        for (int i : arr) {
            if (s == sn) return true;
            else {
                sn += i;
                s -= i;
            }
        }
        return false;
    }

    static void displace(int[] arr, int n) {  //можно быстрее
        int buf;
        int l = arr.length;
        boolean dir = (n > 0) ? true : false;
        n = abs(n % l);
        for (int i = 0; i < n; i++) {
            if (dir) {
                buf = arr[l - 1];
                for (int j = l - 1; j > 0; j--)
                    arr[j] = arr[j - 1];
                arr[0] = buf;
            } else {
                buf = arr[0];
                for (int j = 0; j < l - 1; j++)
                    arr[j] = arr[j + 1];
                arr[l - 1] = buf;
            }
        }
        return;
    }

    static void printArr(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

    static void print2dArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
        System.out.println("\n");
    }
}

