package com.example.zhangping.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
/**
 * Created by zhangping on 15/11/13.
 */
public class JSONTools {
    public JSONTools() {
        super();
    }

    public  static List<String> getListString(String key, String jsonString){
        List<String> listString = new ArrayList<String>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            JSONArray jsonArray = jsonObject.getJSONArray(key);
            for (int i = 0; i< jsonArray.length(); i++){
                String msg = jsonArray.getString(i);
                listString.add(msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listString;
    }
}
