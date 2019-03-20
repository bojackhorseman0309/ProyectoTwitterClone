package com.example.twitteralonso;

public class Tweet {

    private int imagenPerf;
    private String nomUsuario;
    private String idNomUsuario;
    private String tweet;
    private String contRes;
    private String contRetw;
    private String contFav;


    public Tweet(int imagenPerf, String nomUsuario, String idNomUsuario, String tweet, String contRes, String contRetw, String contFav) {
        this.imagenPerf = imagenPerf;
        this.nomUsuario = nomUsuario;
        this.idNomUsuario = idNomUsuario;
        this.tweet = tweet;
        this.contRes = contRes;
        this.contRetw = contRetw;
        this.contFav = contFav;
    }

    public int getImagenPerf() {
        return imagenPerf;
    }

    public void setImagenPerf(int imagenPerf) {
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

    public String getContRes() {
        return contRes;
    }

    public void setContRes(String contRes) {
        this.contRes = contRes;
    }

    public String getContRetw() {
        return contRetw;
    }

    public void setContRetw(String contRetw) {
        this.contRetw = contRetw;
    }

    public String getContFav() {
        return contFav;
    }

    public void setContFav(String contFav) {
        this.contFav = contFav;
    }


}
