package com.example.zhangping.jsonparseclass;

/**
 * Created by Zhangping on 16/1/25.
 */
public class XingXiangResponse {
    String result;
    Modul[] modul;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Modul[] getModul() {
        return modul;
    }

    public void setModul(Modul[] modul) {
        this.modul = modul;
    }

    public  class Modul{
        String body;
        String eyebrows;
        String eyes;
        String hair;
        String mouth;
        String glasses;
        String face;
        String filename;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getEyebrows() {
            return eyebrows;
        }

        public void setEyebrows(String eyebrows) {
            this.eyebrows = eyebrows;
        }

        public String getEyes() {
            return eyes;
        }

        public void setEyes(String eyes) {
            this.eyes = eyes;
        }

        public String getHair() {
            return hair;
        }

        public void setHair(String hair) {
            this.hair = hair;
        }

        public String getMouth() {
            return mouth;
        }

        public void setMouth(String mouth) {
            this.mouth = mouth;
        }

        public String getGlasses() {
            return glasses;
        }

        public void setGlasses(String glasses) {
            this.glasses = glasses;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }
    }
}
