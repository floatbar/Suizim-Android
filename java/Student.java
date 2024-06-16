package wafoot.becoming.wafoot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("student_class")
    @Expose
    private String student_class;

    @SerializedName("student_name")
    @Expose
    private String student_name;

    @SerializedName("student_surname")
    @Expose
    private String student_surname;

    @SerializedName("water_foot_print")
    @Expose
    private String water_foot_print;


    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_surname(String student_surname) {
        this.student_surname = student_surname;
    }

    public String getStudent_surname() {
        return student_surname;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public String getStudent_class() {
        return student_class;
    }

    public void setWater_foot_print(String water_foot_print) {
        this.water_foot_print = water_foot_print;
    }

    public String getWater_foot_print() {
        return water_foot_print;
    }
}