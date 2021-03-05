package com.example.das_entrega1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class miBD extends SQLiteOpenHelper {
    public miBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Crear la tabla de Usuarios (usuario(PK): VARCHAR(255); contraseña: VARCHAR(255))
        sqLiteDatabase.execSQL("CREATE TABLE Usuarios ('Usuario' VARCHAR(255) PRIMARY KEY NOT NULL, 'Contraseña' VARCHAR(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String insert(String word){
        SQLiteDatabase bd = getWritableDatabase();
        String respuesta= "";
        Cursor c = bd.rawQuery("SELECT * FROM Palabras WHERE Palabra='"+word+"'", null);
        //Si esta metida la palabra, es decir, si en el cursor hay 1 elemento
        if (c.moveToNext()){
            respuesta = c.getString(1);
        }
        return  respuesta;
    }

    public void guardar(String word) {
        SQLiteDatabase bd = getWritableDatabase();
        bd.execSQL("INSERT INTO Palabras ('Palabra') VALUES ('"+word+"')");
        System.out.println("Añadida");
    }


    public boolean esta(String user) {
        SQLiteDatabase bd = getWritableDatabase();
        boolean esta=false;
        Cursor c = bd.rawQuery("SELECT * FROM Usuarios WHERE Usuario='"+user+"'", null);
        //Si esta metida el usuario, es decir, si en el cursor hay 1 elemento
        if (c.moveToNext()){
            esta= true;
        }
        c.close();
        return esta;
    }

    public void insertarEnLaBBDD(String user, String password) {
        SQLiteDatabase bd = getWritableDatabase();
        bd.execSQL("INSERT INTO Usuarios VALUES ('"+user+"','"+password+"')");
        System.out.println("Usuario: " + user + " añadido en la BBDD Usuarios");
    }

    public boolean contraseñCorrecta(String user, String password) {
        SQLiteDatabase bd = getWritableDatabase();
        boolean correcta=false;
        Cursor c = bd.rawQuery("SELECT * FROM Usuarios WHERE Usuario='"+user+"' AND Contraseña='"+password+"'", null);
        //Si esta metida el usuario, es decir, si en el cursor hay 1 elemento
        if (c.moveToNext()){
            correcta= true;
        }
        c.close();
        return correcta;

    }
}
