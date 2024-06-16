package wafoot.becoming.wafoot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StaffResponse {

    @SerializedName("staff_name")
    @Expose
    private String staff_name;

    @SerializedName("list")
    @Expose
    private List<Staff> list = null;

    public List<Staff> getList() {
        return list;
    }

    @SerializedName("staff_surname")
    @Expose
    private String staff_surname;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("profile_photo")
    @Expose
    private String profile_photo;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("success")
    @Expose
    private String success;

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

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

    public void setChat_room_id(int chat_room_id) {
        this.chat_room_id = chat_room_id;
    }

    public int getChat_room_id() {
        return chat_room_id;
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
    public void setStaff_surname(String staff_surname) {
        this.staff_surname = staff_surname;
    }
    public String getStaff_surname() {
        return staff_surname;
    }
    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }
    public String getStaff_name() {
        return staff_name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}