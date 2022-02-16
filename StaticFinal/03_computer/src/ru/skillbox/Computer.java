package ru.skillbox;

public class Computer {
    private final String vendor;
    private final String name;
    private final ComputerCPU computerCPU;
    private final RandomAccessMemory randomAccessMemory;
    private final InformationStorage informationStorage;
    private final Screen screen;
    private final Keyboard keyboard;
    private static double allWeight = 0;

    public Computer(String vendor, String name, ComputerCPU computerCPU,
                    RandomAccessMemory randomAccessMemory, InformationStorage informationStorage,
                    Screen screen, Keyboard keyboard) {
        this.vendor = vendor;
        this.name = name;
        this.computerCPU = computerCPU;
        this.randomAccessMemory = randomAccessMemory;
        this.informationStorage = informationStorage;
        this.screen = screen;
        this.keyboard = keyboard;

    }

    public Computer setComputerCPU(ComputerCPU computerCPU) {
        return new Computer(vendor, name, computerCPU, randomAccessMemory, informationStorage, screen, keyboard);
    }

    public Computer setRandomAccessMemory(RandomAccessMemory randomAccessMemory) {
        return new Computer(vendor, name, computerCPU, randomAccessMemory, informationStorage, screen, keyboard);
    }

    public Computer setInformationStorage(InformationStorage informationStorage) {
        return new Computer(vendor, name, computerCPU, randomAccessMemory, informationStorage, screen, keyboard);
    }

    public Computer setScreen(Screen screen) {
        return new Computer(vendor, name, computerCPU, randomAccessMemory, informationStorage, screen, keyboard);
    }

    public Computer setKeyboard(Keyboard keyboard) {
        return new Computer(vendor, name, computerCPU, randomAccessMemory, informationStorage, screen, keyboard);
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public ComputerCPU getComputerCPU() {
        return computerCPU;
    }

    public RandomAccessMemory getRandomAccessMemory() {
        return randomAccessMemory;
    }

    public InformationStorage getInformationStorage() {
        return informationStorage;
    }

    public Screen getTypeScreen() {
        return screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public double increaseAllWeight(Computer computer) {
        allWeight =computer.computerCPU.getWeight() +
                computer.randomAccessMemory.getWeight() +
                computer.informationStorage.getWeight() +
                computer.screen.getWeight() +
                computer.keyboard.getWeight();
        return allWeight;
    }

    public String toString() {
        return ("Информация о компьютере:\n"
                + "Процессор:"
                + "частота-" + computerCPU.getFrequency() + ";"
                + "производитель-" + computerCPU.getManufacturer() + ";"
                + "количество ядер-" + computerCPU.getNumberOfCores() + ";"
                + "количество ядер-" + computerCPU.getWeight() + ";\n"
                + "Оперативная память:"
                + "тип-" + randomAccessMemory.getType() + ";"
                + "объём-" + randomAccessMemory.getVolume() + ";"
                + "вес-" + randomAccessMemory.getWeight() + ";\n"
                +"Накопитель информации:"
                +"тип-" +informationStorage.getTypeInformationStorage()+ ";"
                +"объём памяти-" +informationStorage.getMemory()+";"
                +"вес-" +informationStorage.getWeight()+ ";\n"
                +"Экран:"
                +"диагональ-" +screen.getDiagonal()+";"
                +"тип-" +screen.getTypeScreen()+";"
                +"вес-" +screen.getWeight()+ ";\n"
                +"Экран:"
                +"тип-"+keyboard.getType()+";"
                +"наличие подсветки-" +keyboard.getIlluminationKeyboard()+";"
                +"вес-" +keyboard.getWeight()+ ";\n"
                +"А также:"
                +"Производитель -" +getVendor()+";"
                +"Имя -" +getName()+".");
    }
}
