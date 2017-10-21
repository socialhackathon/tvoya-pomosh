package android.jardam.kg.connection.entity;

import com.google.gson.annotations.SerializedName;

public class Human {
    @SerializedName("name")
    public String name;
    @SerializedName("imageUrl")
    public String imageUrl;
    @SerializedName("age")
    public int age;
    @SerializedName("status")
    public String status;
    @SerializedName("city")
    public String city;
    @SerializedName("description")
    public String description;
    @SerializedName("necessary")
    public int necessary;
    @SerializedName("collected")
    public int collected;
}
