package ir.artaateam.android.braingame;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import ir.artaateam.android.braingame.app.MyApplication;

public class UserPreferences {
    private SharedPreferences sharedPreferences;
    private static UserPreferences instance=null;
    private UserPreferences(Context context){
        sharedPreferences=context.getSharedPreferences("UserPreferences",Context.MODE_PRIVATE);
    }
    public static UserPreferences getInstance(){
        if(instance==null){
            instance=new UserPreferences(MyApplication.getContext());
        }
        return instance;
    }
    public void putUser(User user){
        Gson gson=new Gson();
        String userJson=gson.toJson(user,User.class);
        sharedPreferences.edit().putString("User",userJson).apply();
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
