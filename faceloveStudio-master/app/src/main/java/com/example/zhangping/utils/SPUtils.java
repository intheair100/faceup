package com.example.zhangping.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
import java.util.Objects;

/**
 * Created by Zhangping on 15/12/20.
 */
public class SPUtils {

    public static final String FILENAME = MacroClass.SHAREDPREFERENCES;

    public static void put(Context context, String key, Object object)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (object instanceof String)
        {
            editor.putString(key,(String)object);
        }
        else  if (object instanceof Integer)
        {
            editor.putInt(key,(Integer)object);
        }
        else if (object instanceof Boolean)
        {
            editor.putBoolean(key,(Boolean)object);
        }
        else if (object instanceof Long)
        {
            editor.putLong(key,(Long)object);
        }
        else if (object instanceof Float)
        {
            editor.putFloat(key,(Float)object);
        }
        else
        {
            editor.putString(key,object.toString());
        }
        editor.commit();
    }

    public static Object get(Context context,String key, Object defaultObject)
    {

        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        if (defaultObject instanceof String)
        {
            return sharedPreferences.getString(key,(String)defaultObject);
        }
        else if (defaultObject instanceof Boolean)
        {
            return sharedPreferences.getBoolean(key,(Boolean)defaultObject);
        }
        else if (defaultObject instanceof Long)
        {
            return sharedPreferences.getLong(key,(Long)defaultObject);
        }
        else if (defaultObject instanceof Float)
        {
            return sharedPreferences.getFloat(key,(Float)defaultObject);
        }
        else if (defaultObject instanceof Integer)
        {
            return sharedPreferences.getInt(key,(Integer)defaultObject);
        }
        return null;
    }
}
