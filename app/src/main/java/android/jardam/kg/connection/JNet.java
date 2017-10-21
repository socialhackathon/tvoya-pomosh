package android.jardam.kg.connection;

import android.jardam.kg.connection.entity.AddUser;
import android.jardam.kg.connection.entity.GetAds;
import android.jardam.kg.connection.entity.Human;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JNet {
    private final static String TAG = "JNet";

    private static Retrofit net = new Retrofit.Builder()
            .baseUrl("http://jardam.urmapps.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static JService jService = net.create(JService.class);

    public static void getHuman(Callback<Human> callback) {
        jService.getHuman().enqueue(callback);
    }

    public static void addUser(Callback<AddUser> callback,
                               String fName,
                               String lName,
                               String phone,
                               String email,
                               String devID
                               ){
        jService.addUser(fName, lName, phone, email, devID).enqueue(callback);
    }

    public static void getAd(Callback<GetAds> callback){
        jService.getAd().enqueue(callback);
    }
}
