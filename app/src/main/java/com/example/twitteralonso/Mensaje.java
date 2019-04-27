package com.example.twitteralonso;

import android.graphics.Bitmap;

public class Mensaje {

    private Bitmap imagenPerf;
    private String nomUsuario;
    private String idUsuario;
    private String fecha;
    private String mensaje;
    private String origen;

    public Mensaje(Bitmap imagenPerf, String nomUsuario, String idUsuario, String fecha, String mensaje, String origen) {
        this.imagenPerf = imagenPerf;
        this.nomUsuario = nomUsuario;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.origen = origen;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
