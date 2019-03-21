package com.example.twitteralonso;

public class Mensaje {

    private int imagenPerf;
    private String nomUsuario;
    private String idUsuario;
    private String fecha;
    private String mensaje;

    public Mensaje(int imagenPerf, String nomUsuario, String idUsuario, String fecha, String mensaje) {
        this.imagenPerf = imagenPerf;
        this.nomUsuario = nomUsuario;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.mensaje = mensaje;
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
