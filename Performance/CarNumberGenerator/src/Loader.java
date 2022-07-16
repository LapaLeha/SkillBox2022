
public class Loader {

    public static void main(String[] args) throws Exception {
        String path1 = "res/numbers1.txt";
        String path2 = "res/numbers2.txt";
        String path3 = "res/numbers3.txt";
        String path4 = "res/numbers4.txt";
        int processorHeads = 4;
        int regionFrom1 = 1;
        int regionTo1 = 100 / processorHeads;
        int regionFrom2 = regionTo1;
        int regionTo2 = regionTo1 + 100 / processorHeads;
        int regionFrom3 = regionTo2;
        int regionTo3 = regionTo2 + 100 / processorHeads;
        int regionFrom4 = regionTo3;
        int regionTo4 = 100;

        int numberFrom1 = 1;
        int numberTo1 = 1000 / processorHeads;
        int numberFrom2 = numberTo1;
        int numberTo2 = numberTo1 + 1000 / processorHeads;
        int numberFrom3 = numberTo2;
        int numberTo3 = numberTo2 + 1000 / processorHeads;
        int numberFrom4 = numberTo3;
        int numberTo4 = 1000;

        long start = System.currentTimeMillis();
        MakeGenerationNumbers generator1
                = new MakeGenerationNumbers(path1, start, regionFrom1, regionTo1, numberFrom1, numberTo1);
        generator1.start();

        MakeGenerationNumbers generator2
                = new MakeGenerationNumbers(path2, start, regionFrom2, regionTo2, numberFrom2, numberTo2);
        generator2.start();

        MakeGenerationNumbers generator3
                = new MakeGenerationNumbers(path3, start, regionFrom3, regionTo3, numberFrom3, numberTo3);
        generator3.start();

        MakeGenerationNumbers generator4
                = new MakeGenerationNumbers(path4, start, regionFrom4, regionTo4, numberFrom4, numberTo4);
        generator4.start();
    }
}
