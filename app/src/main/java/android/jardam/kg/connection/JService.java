package android.jardam.kg.connection;

import android.jardam.kg.connection.entity.AddUser;
import android.jardam.kg.connection.entity.GetAds;
import android.jardam.kg.connection.entity.Human;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JService {
    @GET("GetAds/")
    Call<Human> getHuman();

    @GET("AddUser/{FirstName}/{LastName}/{Phone}/{Email}/{DeviceID}")
    Call<AddUser> addUser(
            @Path("FirstName")String fName,
            @Path("LastName")String lName,
            @Path("Phone") String phone,
            @Path("Email") String email,
            @Path("DeviceID") String devId
    );

    @GET("GetAds")
    Call<GetAds> getAd();
}
