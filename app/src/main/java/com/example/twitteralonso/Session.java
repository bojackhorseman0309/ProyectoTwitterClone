package com.example.twitteralonso;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setNomUsuario(String nom) {
        prefs.edit().putString("nomUsuario", nom).commit();
    }

    public String getNomUsuario() {
        String nom = prefs.getString("nomUsuario","");
        return nom;
    }

    public void setContra(String con) {
        prefs.edit().putString("contra", con).commit();
    }

    public String getContra() {
        String pass = prefs.getString("contra","");
        return pass;
    }

    public void setAmigo(String nom) {
        prefs.edit().putString("nomAmigo", nom).commit();
    }

    public String getAmigo() {
        String nom = prefs.getString("nomAmigo","");
        return nom;
    }

}
