package edu.depaul.csc472.onlinequiz;

import android.app.Activity;

/**
 * Created by Akshu on 11/17/2015.
 */
public class User extends Activity {
    private String _fname;
    private String _lname;
    private String _emailid;
    private String _password;

    public User() {

    }

    public User(String fname, String lname, String emailid, String password) {
        this._fname = fname;
        this._lname = lname;
        this._emailid = emailid;
        this._password = password;
    }

    public String getFname() {
        return this._fname;
    }

    public void setFname(String fname) {
        this._fname = fname;
    }

    public String getLname() {
        return this._lname;
    }

    public void setLname(String lname) {
        this._lname = lname;
    }

    public String getEmailid() {
        return this._emailid;
    }

    public void setEmailid(String emailid) {
        this._emailid = emailid;
    }

    public String getPassword() {
        return this._password;
    }

    public void setPassword(String password) {
        this._password = password;
    }
}
