package com.example.zhangping.jsonparseclass;

/**
 * Created by Zhangping on 16/1/7.
 */
public class CampusCenterResponse {

    private String result;
    private UserInfoResponse[] campus;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public UserInfoResponse[] getCampus() {
        return campus;
    }

    public void setCampus(UserInfoResponse[] campus) {
        this.campus = campus;
    }
}
