package com.example.twitteralonso;

import android.graphics.Bitmap;

public class Tweet {

    private Bitmap imagenPerf;
    private String nomUsuario;
    private String idNomUsuario;
    private String tweet;
    private int contFav;


    public Tweet(Bitmap imagenPerf, String nomUsuario, String idNomUsuario, String tweet, int contFav) {
        this.imagenPerf = imagenPerf;
        this.nomUsuario = nomUsuario;
        this.idNomUsuario = idNomUsuario;
        this.tweet = tweet;
        this.contFav = contFav;
    }

    public Bitmap getImagenPerf() {
        return imagenPerf;
    }

    public void setImagenPerf(Bitmap imagenPerf) {
        this.imagenPerf = imagenPerf;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getIdNomUsuario() {
        return idNomUsuario;
    }

    public void setIdNomUsuario(String idNomUsuario) {
        this.idNomUsuario = idNomUsuario;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }


    public int getContFav() {
        return contFav;
    }

    public void setContFav(int contFav) {
        this.contFav = contFav;
    }


}
