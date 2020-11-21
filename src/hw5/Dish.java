package hw5;

//миска
public class Dish extends FoodContainer {
    //по заданию это какая-то автонаполняющаяся миска, когда становится пустой, пополняется
    //но также по заданию она не может стать пустой, потому коты не едят меньше определенно заданного значения
    //для автопополнения полгоню числа foodLimit для котов
    public Dish(double capacity) {
        super(capacity);
    }

    public double getFoodVolume() {
        System.out.println("Миска содержит " + foodVolume + " единиц еды");
        return foodVolume;
    }

    public void setFoodVolume(double foodVolume) {
        if (capacity > foodVolume) {
            this.foodVolume = foodVolume;
            System.out.println("В миске теперь " + foodVolume + " единиц еды");
        } else
            System.out.println("В миску столько не поместится");

        if (foodVolume == 0) {
            this.foodVolume = capacity;
            System.out.println("Автозаполнение миски. Теперь в ней максимальный объем еды (" + capacity + " единиц)");
        }
    }
}
