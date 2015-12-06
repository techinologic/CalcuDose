package net.androidbootcamp.calcudose;

/**
 * Created by Paolo T. inocencion on 11/8/2015.
 */
public class FoodDataProvider {

    private String name;
    private String servings;
    private String carbs;
    private String fat;
    private String protein;
    private int position;


    public FoodDataProvider(String name, String servings, String carbs, String fat, String protein, int position) {
        this.name = name;
        this.servings = servings;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
        this.position = position;
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
}

