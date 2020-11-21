package hw5;

import hw4.Animal;

public class Cat extends Animal {
    //по заданию сказано, что это свойство КОТОВ, поэтому не станем менять класс Animal
    protected double eatLimit;
    protected boolean satiety;

    public Cat() {
        super(200, 2, 0);
        eatLimit = 10;
        satiety = false;
    }

    public Cat(double runLimit, double jumpLimit, double swimLimit, double eatLimit) {
        super(runLimit, jumpLimit, swimLimit);
        if (eatLimit > 0) this.eatLimit = eatLimit;
        else this.eatLimit = 10;
        satiety = false;
    }

    //создал сеттер чтобы можно было настроить только свойство проглотства
    public void setEatLimit(double eatLimit) {
        this.eatLimit = eatLimit;
    }

    public void eatFrom(FoodContainer foodContainer) {
        if (foodContainer!=null) {
            double foodVolume = foodContainer.getFoodVolume();

            if (foodVolume == 0)
                System.out.println("Коту нечего жрать.");
            else if (foodVolume < eatLimit)
                System.out.println("Кот отказался кушать так мало!");
            else if (foodVolume == eatLimit)
                System.out.println("Кот обожрался.");
            else
                System.out.println("Кот обожрался и еще осталось.");

            if (foodVolume >= eatLimit)
                foodContainer.setFoodVolume(foodVolume - eatLimit);
        }
        else System.out.println("Ошибка.");
    }
}
