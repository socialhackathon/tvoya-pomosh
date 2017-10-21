package android.jardam.kg.connection.entity;

import com.google.gson.annotations.SerializedName;

public class AddUser extends Response {
    @SerializedName("Result")
    public int userId;
}
