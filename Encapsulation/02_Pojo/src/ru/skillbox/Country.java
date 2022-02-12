package ru.skillbox;

public class Country {
    String nameCountry;
    int population;
    double square;
    String capitalCountry;
    boolean haveSea;

    public Country(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public String getCapitalCountry() {
        return capitalCountry;
    }

    public void setCapitalCountry(String capitalCountry) {
        this.capitalCountry = capitalCountry;
    }

    public boolean isHaveSea() {
        return haveSea;
    }

    public void setHaveSea(boolean haveSea) {
        this.haveSea = haveSea;
    }
}
