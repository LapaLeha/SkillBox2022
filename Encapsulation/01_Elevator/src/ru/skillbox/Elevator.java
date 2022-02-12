package ru.skillbox;

public class Elevator {
    int currentFloor = 1;
    int minFloor;
    int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveDown() {
        if (currentFloor > minFloor) {
            currentFloor = currentFloor - 1;
        } else {
            System.out.println("Лифт находится на минимальном этаже - ниже лифт ехать не может");
        }
    }

    public void moveUp() {
        if (currentFloor < maxFloor) {
            currentFloor = currentFloor + 1;
        } else {
            System.out.println("Лифт находится на максимальном этаже - выше лифт ехать не может");
        }
    }

    public void move(int floor) {
        if (floor<minFloor || floor>maxFloor){
            System.out.println("Указанного этажа в лифте не существует -введите этаж от "+minFloor+" до "+maxFloor);
        }else {
            if (currentFloor>floor){ //если надо вниз
                System.out.println(getCurrentFloor());
                while (currentFloor!=floor){
                    moveDown();
                    System.out.println(getCurrentFloor());

                }
            }else if (currentFloor<floor){ //если надо вверх
                System.out.println(getCurrentFloor());
                while (currentFloor!=floor){
                    moveUp();
                    System.out.println(getCurrentFloor());
                }
            }else { //уже на выбранном этаже
                System.out.println("Вы уже на выбранном этаже");
            }
        }

    }

}
