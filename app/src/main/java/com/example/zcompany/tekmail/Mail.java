package com.example.zcompany.tekmail;

public class Mail {
    private String mail_uid;
    private String Mail_Address;
    private String Mail_Body;
    private String Mail_Date;
    private String Mail_From_Address;
    private String Mail_From_Name;
    private String Mail_Subject;
    private String Mail_To;

    public Mail() {
    }

    public Mail(String mail_uid, String mail_Address, String mail_Body, String mail_Date, String mail_From_Address, String mail_From_Name, String mail_Subject, String mail_To) {
        this.mail_uid = mail_uid;
        Mail_Address = mail_Address;
        Mail_Body = mail_Body;
        Mail_Date = mail_Date;
        Mail_From_Address = mail_From_Address;
        Mail_From_Name = mail_From_Name;
        Mail_Subject = mail_Subject;
        Mail_To = mail_To;
    }

    public String getMail_uid() {
        return mail_uid;
    }

    public void setMail_uid(String mail_uid) {
        this.mail_uid = mail_uid;
    }

    public String getMail_Address() {
        return Mail_Address;
    }

    public void setMail_Address(String mail_Address) {
        Mail_Address = mail_Address;
    }

    public String getMail_Body() {
        return Mail_Body;
    }

    public void setMail_Body(String mail_Body) {
        Mail_Body = mail_Body;
    }

    public String getMail_Date() {
        return Mail_Date;
    }

    public void setMail_Date(String mail_Date) {
        Mail_Date = mail_Date;
    }

    public String getMail_From_Address() {
        return Mail_From_Address;
    }

    public void setMail_From_Address(String mail_From_Address) {
        Mail_From_Address = mail_From_Address;
    }

    public String getMail_From_Name() {
        return Mail_From_Name;
    }

    public void setMail_From_Name(String mail_From_Name) {
        Mail_From_Name = mail_From_Name;
    }

    public String getMail_Subject() {
        return Mail_Subject;
    }

    public void setMail_Subject(String mail_Subject) {
        Mail_Subject = mail_Subject;
    }

    public String getMail_To() {
        return Mail_To;
    }

    public void setMail_To(String mail_To) {
        Mail_To = mail_To;
    }
}


