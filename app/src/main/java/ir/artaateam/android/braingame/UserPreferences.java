package ir.artaateam.android.braingame;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class UserPreferences {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static UserPreferences instance=null;
    private UserPreferences(Context context){
        sharedPreferences=context.getSharedPreferences("UserPreferences",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public static UserPreferences getInstance(Context context){
        if(instance==null){
            instance=new UserPreferences(context);
        }
        return instance;
    }
    public void putUser(User user){
        Gson gson=new Gson();
        String userJson=gson.toJson(user,User.class);
        editor.putString("User",userJson);
        editor.apply();
    }
    public User getUser(){
        Gson gson=new Gson();
        String userJson=sharedPreferences.getString("User",null);
        if(userJson==null) {
            return new User();
        }
        return gson.fromJson(userJson,User.class);
    }
}
