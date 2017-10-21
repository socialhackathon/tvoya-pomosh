package android.jardam.kg.connection.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAds extends Response {

    @SerializedName("Result")
    public Ad ad;
}
