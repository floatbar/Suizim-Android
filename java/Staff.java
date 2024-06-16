package wafoot.becoming.wafoot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Staff {

    @SerializedName("staff_name")
    @Expose
    private String staff_name;

    @SerializedName("staff_surname")
    @Expose
    private String staff_surname;

    @SerializedName("water_foot_print")
    @Expose
    private String water_foot_print;

    public String getWater_foot_print() {
        return water_foot_print;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public String getStaff_surname() {
        return staff_surname;
    }
}