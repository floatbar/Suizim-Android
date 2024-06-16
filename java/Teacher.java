package wafoot.becoming.wafoot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teacher {
    @SerializedName("teacher_branch")
    @Expose
    private String teacher_branch;

    @SerializedName("teacher_name")
    @Expose
    private String teacher_name;

    @SerializedName("teacher_surname")
    @Expose
    private String teacher_surname;

    @SerializedName("water_foot_print")
    @Expose
    private String water_foot_print;

    public String getTeacher_name() {
        return teacher_name;
    }

    public String getTeacher_surname() {
        return teacher_surname;
    }

    public String getWater_foot_print() {
        return water_foot_print;
    }

    public String getTeacher_branch() {
        return teacher_branch;
    }
}