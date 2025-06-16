package model;

import com.google.gson.Gson;

public class JsonExtractor {

    private String mail_id;
    private String mail_body_base64;

    public String getMail_id() {
        return mail_id;
    }

    public void setMail_id(String mail_id) {
        this.mail_id = mail_id;
    }

    public String getMail_body_base64() {
        return mail_body_base64;
    }

    public void setMail_body_base64(String mail_body_base64) {
        this.mail_body_base64 = mail_body_base64;
    }
}
