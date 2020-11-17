package hw1;

public class hw1 {
    public static void main(String[] args) {
        //task 2
        byte b = 103;
        short s = 54;
        int i = 87956;
        long l = 870L;
        float f = 760.69f;
        double d = 46.456454;
        boolean bool = true;
        char c = 'A';
        String name = "Проверяющий";    //Так или иначе, мы его прошли (он есть в main'e)

        System.out.println("task 3: " + func3(1.2, 2.3, 3.4, 4.1));
        System.out.println("task 4: " + func4(12.2, 2.54));
        func5(-12);
        System.out.println("task 6: " + func6(186));
        func7(name);
        System.out.println("task 8: " + func8(2020));
    }

    //task 3
    static double func3(double a, double b, double c, double d) {
        return a * (b + (c / d));
    }

    //task 4
    static boolean func4(double a, double b) {
        return (a + b >= 10) && (a + b <= 20);
    }

    //task 5
    static void func5(int a) {
        System.out.println((a >= 0) ? "Положительное" : "Отрицательное");
    }

    //task 6
    static boolean func6(int a) {
        return a < 0;
    }

    //task 7
    static void func7(String name) {
        System.out.println("Привет, " + name + "!");
    }

    //task 8
    static boolean func8(int year) {
        return (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0));
    }
}