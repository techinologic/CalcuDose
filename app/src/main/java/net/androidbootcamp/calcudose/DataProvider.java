package net.androidbootcamp.calcudose;

/**
 * Created by Paolo T. inocencion on 11/8/2015.
 */
public class DataProvider {

    private String name;
    private String servings;
    private String carbs;
    private String fat;
    private String protein;

    private String bg;
    private String dose;
    private String date;


    public DataProvider(String name, String servings, String carbs, String fat, String protein){
        this.name = name;
        this.servings = servings;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
    }

    public DataProvider(String name, String bg, String dose, String date){
        this.name = name;
        this.bg = bg;
        this.dose = dose;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
