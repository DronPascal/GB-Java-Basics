package hw4;

/*
    1. Создать класс «Сотрудник» с полями: ФИО, должность, email, телефон, зарплата, возраст;
    2. Конструктор класса должен заполнять эти поля при создании объекта;
    3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
    4. Создать массив из 5 сотрудников:
    Пример:
    Person[] persArray = new Person[5]; // Вначале объявляем массив объектов
    persArray[0] = new Person("Ivanov Ivan", "Engineer", " ivivan@mailbox.com ", "892312312", 30000,
    30); // потом для каждой ячейки массива задаем объект
    persArray[1] = new Person(...);
    ...
    persArray[4] = new Person(...);
    С помощью цикла вывести информацию только о сотрудниках старше 40 лет;

    5. Создать классы Собака и Кот с наследованием от класса Животное;
    6. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков);
    7. У каждого животного есть ограничения на действия (бег: кот – 200 м., собака – 500 м.; прыжок: кот – 2 м., собака – 0.5 м.; плавание: кот не умеет плавать, собака – 10 м.);
    8. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль. (Например, dog1.run(150); -> результат: run: true);
    9. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой – 600 м.
 */
public class hw4 {
    public static void main(String[] args) {
        /*
         * PART 1
         */
        Employee[] employees = new Employee[5]; //вначале объявляем массив объектов
        employees[0] = new Employee("Kyle Luis Reed", "Engineer", "kyle@mailbox.com", "84952211289", 102000, 30);
        employees[1] = new Employee("Brandon Joseph Butler", "Barman", "brandon@mailbox.com", "89999071667", 40916, 41);
        employees[2] = new Employee("Thomas Kyle King", "Coach", "thomas@mailbox.com", "84950777283", 62383, 26);
        employees[3] = new Employee("Kimberly Lily Morris", "Dairymaid", "kimberly@mailbox.com", "89264953036", 119629, 68);
        employees[4] = new Employee("Aaliyah Natalie Carter", "Driver", " aaliyah@mailbox.com", "89662211289", 81057, 47);
        for (Employee employee : employees)
            if (employee.getAge() >= 40) employee.printInfo(true);

        /*
         * PART 2
         */
        System.out.println("\n<Default cat>");  //runLimit = 200; jumpLimit = 2;
        Animal defaultCat = new Cat();
        defaultCat.run(100);
        defaultCat.jump(1.5);
        defaultCat.swim(10);

        System.out.println("\n<Default dog>");  //runLimit = 500; jumpLimit = 0.5; swimLimit = 10;
        Animal defaultDog = new Dog();
        defaultDog.run(600);
        defaultDog.jump(0.5);
        defaultDog.swim(7);

        System.out.println("\n<Custom cat>");
        Animal strongestCat = new Cat(999999, 1000, 999999);
        strongestCat.jump(2000);
        strongestCat.swim(10000);

        System.out.println("\n<Custom dog1>");
        Animal customDog1 = new Dog(400, 2, 200);
        customDog1.run(600);
        customDog1.jump(2.5);
        customDog1.swim(300);
        System.out.println("\n<Custom dog2>");
        Animal customDog2 = new Dog(600, 3, 400);
        customDog2.run(600);
        customDog2.jump(2.5);
        customDog2.swim(300);
    }
}
