public class Connections {
    private String lineIn;
    private String nameIn;
    private String lineTo;
    private String nameTo;

    public Connections (String lineIn, String nameIn, String lineTo, String nameTo) {
        this.lineIn = lineIn;
        this.nameIn = nameIn;
        this.lineTo = lineTo;
        this.nameTo = nameTo;
    }

    public String getLineIn() {
        return lineIn;
    }

    public String getNameIn() {
        return nameIn;
    }

    public String getLineTo() {
        return lineTo;
    }

    public String getNameTo() {
        return nameTo;
    }
}
