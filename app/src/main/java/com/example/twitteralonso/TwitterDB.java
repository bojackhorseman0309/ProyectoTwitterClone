package com.example.twitteralonso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TwitterDB extends SQLiteOpenHelper {

    public TwitterDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table tweet (idTweet integer primary key, imagen int," +
                                        "nombre text, aliasUsuario text, " +
                                        "tweet text, conResp text, contRetw text, contFav text)");

        db.execSQL("create table notificacion (idNotif integer primary key, imagen int, notificacion text, descripcion text)");
        db.execSQL("create table mensaje (idMens integer primary key, imagen int, nombre text, idUsuario text, fecha text, mensaje text)");

        db.execSQL("create table usuario (idUsuario integer primary key, correo text, contrasenha text)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table tweet (idTweet integer primary key, imagen int," +
                "nombre text, aliasUsuario text, " +
                "tweet text, conResp text, contRetw text, contFav text)");

        db.execSQL("create table notificacion (idNotif integer primary key, imagen int, notificacion text, descripcion text)");
        db.execSQL("create table mensaje (idMens integer primary key, imagen int, nombre text, idUsuario text, fecha text, mensaje text)");

        db.execSQL("create table usuario (idUsuario integer primary key, correo text, contrasenha text)");
    }

}
