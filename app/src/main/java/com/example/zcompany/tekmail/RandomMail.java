package com.example.zcompany.tekmail;

class RandomMail {
    private String Mail_Address;
    private String Mail_Pwd;


    public RandomMail() {
    }

    public RandomMail(String mail_Address, String mail_Pwd) {
        Mail_Address = mail_Address;
        Mail_Pwd = mail_Pwd;
    }

    public String getMail_Address() {
        return Mail_Address;
    }

    public void setMail_Address(String mail_Address) {
        Mail_Address = mail_Address;
    }

    public String getMail_Pwd() {
        return Mail_Pwd;
    }

    public void setMail_Pwd(String mail_Pwd) {
        Mail_Pwd = mail_Pwd;
    }
}
