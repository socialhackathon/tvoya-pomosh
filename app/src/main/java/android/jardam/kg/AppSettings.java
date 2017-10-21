package android.jardam.kg;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSettings {
    public static final String settinsUser = "jardam_user";
    public static final String DEF = "DEFAULT_STRING";

    public static void putString(Context context, String key, String val){
        getEditor(context).putString(key, val).apply();
    }

    public static String getString(Context context, String key){
        return getPref(context).getString(key, DEF);
    }

    public static boolean getBoolean(Context context, String key){
        return getPref(context).getBoolean(key, false);
    }

    public static void putBoolean(Context context, String key, Boolean val){
        getEditor(context).putBoolean(key, val).apply();
    }

    public static int getInt(Context context, String key){
        return getPref(context).getInt(key, 1);
    }

    public static void putInt(Context context, String key, int val){
        getEditor(context).putInt(key, val).apply();
    }

    private static SharedPreferences.Editor getEditor(Context context){
        return getPref(context).edit();
    }

    private static SharedPreferences getPref(Context context){
        return context.getSharedPreferences(settinsUser, Context.MODE_PRIVATE);
    }
}
