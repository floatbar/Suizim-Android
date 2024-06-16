package wafoot.becoming.wafoot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeachersResponse {

    @SerializedName("teacher_branch")
    @Expose
    private String teacher_branch;

    @SerializedName("teacher_name")
    @Expose
    private String teacher_name;

    @SerializedName("teacher_surname")
    @Expose
    private String teacher_surname;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("profile_photo")
    @Expose
    private String profile_photo;

    @SerializedName("message")
    @Expose
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @SerializedName("list")
    @Expose
    private List<Teacher> list = null;

    @SerializedName("success")
    private String success;

    public List<Teacher> getList() {
        return list;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    @SerializedName("room_name")
    @Expose
    private String room_name;

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    @SerializedName("chat_room_id")
    @Expose
    private int chat_room_id;

    public void setChat_room_id(int chat_room_id) {
        this.chat_room_id = chat_room_id;
    }

    public int getChat_room_id() {
        return chat_room_id;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setTeacher_branch(String teacher_branch) {
        this.teacher_branch = teacher_branch;
    }

    public String getTeacher_branch() {
        return teacher_branch;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_surname(String teacher_surname) {
        this.teacher_surname = teacher_surname;
    }

    public String getTeacher_surname() {
        return teacher_surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}