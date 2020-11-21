package hw5;

public abstract class Animal {
    protected double runLimit, jumpLimit, swimLimit;

    public Animal() {
        runLimit = 0;
        jumpLimit = 0;
        swimLimit = 0;
    }

    public Animal(double runLimit, double jumpLimit, double swimLimit) {
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
        this.swimLimit = swimLimit;
    }

    //предположим, что на вход поступают только положительные числа
    public void run(double distance) {
        System.out.println("run: " + (distance <= runLimit ? "true" : "false"));
    }

    public void jump(double distance) {
        System.out.println("jump: " + (distance <= jumpLimit ? "true" : "false"));
    }

    public void swim(double distance) {
        System.out.println("swim: " + (distance <= swimLimit ? "true" : "false"));
    }

}
