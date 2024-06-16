package wafoot.becoming.wafoot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentsResponse {

    @SerializedName("student_class")
    @Expose
    private String student_class;

    @SerializedName("wafootlist")
    @Expose
    private List<Student> wafootlist = null;

    @SerializedName("list")
    @Expose
    private List<Student> list;

    public List<Student> getList() {
        return list;
    }

    @SerializedName("student_name")
    @Expose
    private String student_name;

    @SerializedName("student_surname")
    @Expose
    private String student_surname;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("chat_room_id")
    @Expose
    private int chat_room_id;

    @SerializedName("room_name")
    @Expose
    private String room_name;

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("profile_photo")
    @Expose
    private String profile_photo;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("path")
    @Expose
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getProfile_photo() {
        return profile_photo;
    }
    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public String getStudent_class() {
        return student_class;
    }

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
    public void setPassword(String Password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setChat_room_id(int chat_room_id) {
        this.chat_room_id = chat_room_id;
    }

    public int getChat_room_id() {
        return chat_room_id;
    }

    public void setWafootlist(List<Student> wafootlist) {
        this.wafootlist = wafootlist;
    }

    public List<Student> getWafootlist() {
        return wafootlist;
    }
}