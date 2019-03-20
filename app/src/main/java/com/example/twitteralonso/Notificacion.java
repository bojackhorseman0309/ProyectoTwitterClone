package com.example.twitteralonso;

public class Notificacion {

    private int imagenNotif;
    private String notif;
    private String desc;

    public Notificacion(int imagenNotif, String notif, String desc) {
        this.imagenNotif = imagenNotif;
        this.notif = notif;
        this.desc = desc;
    }

    public int getImagenNotif() {
        return imagenNotif;
    }

    public void setImagenNotif(int imagenNotif) {
        this.imagenNotif = imagenNotif;
    }

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
