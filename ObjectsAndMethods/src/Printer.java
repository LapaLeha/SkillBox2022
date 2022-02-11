public class Printer {
    private String queue = "";
    private String name = "";
    private int numberOfPages;
    private int sumNumberOfPages;
    private static int sumNumberOfPagesForAllTime = 0;

    public Printer(String queue, String name, int numberOfPages) {
        this.queue = queue;
        this.name = name;
        this.numberOfPages = numberOfPages;
        sumNumberOfPagesForAllTime = sumNumberOfPagesForAllTime + numberOfPages;
    }

    public void append(String texst) {
        queue = queue + texst;
    }

    public void append(String texst, String nameDoc) {
        append(texst);
        name = nameDoc;
    }

    public void append(String texst, String nameDoc, int numberOfPages) {
        append(texst, nameDoc);
        numberOfPages = numberOfPages;
        sumNumberOfPages = sumNumberOfPages + numberOfPages;
    }

    public void clear() {
        queue = "";
        name = "";
        numberOfPages = 0;
        sumNumberOfPages = 0;
    }

    public void print() {
        System.out.println(queue + " " + name + " " + numberOfPages);
        clear();
    }

    public int getPendingPagesCount() {
        return sumNumberOfPages;
    }

    public int sumNumberOfPagesForAllTime() {
        return Printer.sumNumberOfPagesForAllTime;
    }


}
