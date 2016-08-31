package com.example.zhangping.jsonparseclass;

/**
 * Created by Zhangping on 16/1/3.
 */
public class UserInfoArrayResponse {

    private String result;

    private UserInfoResponse[] modul;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public UserInfoResponse[] getModul() {
        return modul;
    }

    public void setModul(UserInfoResponse[] modul) {
        this.modul = modul;
    }
}
