package hw5;
//кто знает сколько у нас будет разных контейнеров в будущем..
public abstract class FoodContainer {
    protected double    capacity, foodVolume;

    public FoodContainer() {
        capacity = 0;  //это значение тоже должна быть возможность изменять, поэтому добавлю сеттер ниже
        foodVolume = 0;
    }

    public FoodContainer(double capacity) {
        this.capacity = capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getFoodVolume() {
        return foodVolume;
    }

    public void setFoodVolume(double foodVolume) {
        if (foodVolume <= capacity) {
            this.foodVolume = foodVolume;
        }
    }
}