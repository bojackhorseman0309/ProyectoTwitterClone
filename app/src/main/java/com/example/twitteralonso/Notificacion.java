package com.example.twitteralonso;

import android.graphics.Bitmap;

public class Notificacion {

    private Bitmap imagenNotif;
    private String notif;
    private String desc;

    public Notificacion(Bitmap imagenNotif, String notif, String desc) {
        this.imagenNotif = imagenNotif;
        this.notif = notif;
        this.desc = desc;
    }

    public Bitmap getImagenNotif() {
        return imagenNotif;
    }

    public void setImagenNotif(Bitmap imagenNotif) {
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
