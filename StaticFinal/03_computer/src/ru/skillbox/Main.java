package ru.skillbox;

public class Main {

    public static void main(String[] args) {

        ComputerCPU computerCPU = new ComputerCPU(332.34, "Russua", NumberOfCores.CORES2, 33.3);
        RandomAccessMemory randomAccessMemory = new RandomAccessMemory("sjsf", 43.4, 11.1);
        InformationStorage informationStorage = new InformationStorage(TypeInformationStorage.HDD, 23.2, 11.1);
        Screen screen = new Screen(18.2, TypeScreen.VA, 12.1);
        Screen screen2 = new Screen(18.2, TypeScreen.IPS, 12.1);
        Keyboard keyboard = new Keyboard("ssfsv", IlluminationKeyboard.Yes, 12.1);
        Keyboard keyboard2 = new Keyboard("ssfsv", IlluminationKeyboard.Yes, 1200000000.1);

        Computer computer = new Computer("Rsch", "Lx", computerCPU, randomAccessMemory,
                informationStorage, screen, keyboard);
        Computer computer1 = new Computer("Rsch", "Lx", computerCPU, randomAccessMemory,
                informationStorage, screen2, keyboard2);

        System.out.println(computer.toString());
        System.out.println("\n");
        System.out.println(computer1.toString());
        System.out.println(computer.allWeight());
        System.out.println(computer1.allWeight());
    }
}
