package android.jardam.kg.connection.entity;

import com.google.gson.annotations.SerializedName;

public class Ad {
    @SerializedName("ID")
    public int ID;
    @SerializedName("Subject")
    public String subject;
    @SerializedName("Description")
    public String description;
    @SerializedName("ImgPath")
    public String image;
    @SerializedName("Sum")
    public int sum;
    @SerializedName("VideoUrl")
    public String video;
    @SerializedName("LoginID")
    public String loginID;
    @SerializedName("IsActive")
    public boolean isActive;
    @SerializedName("DateCreate")
    public String date;
}
