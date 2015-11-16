package net.androidbootcamp.calcudose;

/**
 * Created by Paolo T. inocencion on 11/15/2015.
 */
public class LogDataProvider {

    private String name;
    private String bg;
    private String dose;
    private String oras;
    private String date;

    public LogDataProvider(String name, String bg, String dose, String oras){
        this.name = name;
        this.bg = bg;
        this.dose = dose;
        this.oras = oras;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
