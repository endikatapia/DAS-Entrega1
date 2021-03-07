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
        //Crear la tabla de Pedidos (codigoPedido(PK): INTEGER, elementosPedidos: VARCHAR(255), precioPedido: REAL);
        sqLiteDatabase.execSQL("CREATE TABLE Pedidos ('CodigoPedido' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Elementos' VARCHAR(255), 'Precio' REAL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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

    public boolean contraseñaCorrecta(String user, String password) {
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

    public void guardarPedido(String elementosPedidosSinRepeticion, double precioTotal) {
        SQLiteDatabase bd = getWritableDatabase();
        //añadimos a la BD Pedidos el ultimo pedido realizado
        bd.execSQL("INSERT INTO Pedidos('Elementos','Precio') VALUES ('"+elementosPedidosSinRepeticion+"','"+precioTotal+"')");
        System.out.println("Pedido añadido con estos ELEMENTOS: " + elementosPedidosSinRepeticion + " y este PRECIO: "+ precioTotal);


    }


}
