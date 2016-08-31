package com.example.zhangping.jsonparseclass;

/**
 * Created by Zhangping on 15/12/29.
 */
public class GeneralResultResponse {
    private String result;

    private UserInfoResponse modul;

    private String errorcode;

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public UserInfoResponse getModul() {
        return modul;
    }

    public void setModul(UserInfoResponse modul) {
        this.modul = modul;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
