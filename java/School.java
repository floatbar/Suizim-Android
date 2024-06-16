package wafoot.becoming.wafoot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class School {

    private School school;

    public School() {
    }
    public School(School school) {
        this.school = school;
    }

    private List<School> list2;

    public void setList2(List<School> list2) {
        this.list2 = list2;
    }

    public List<School> getList2() {
        return list2;
    }

    @SerializedName("success")
    @Expose
    private String success;

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    @SerializedName("water_foot_print")
    @Expose
    private String water_foot_print;

    @SerializedName("student_class")
    @Expose
    private String student_class;

    @SerializedName("age")
    @Expose
    private String age;

    @SerializedName("gender")
    @Expose
    private String gender;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
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