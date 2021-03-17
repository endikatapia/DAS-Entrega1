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

    //este metodo mirara si el usuario esta en la BBDD Usuarios devolviendo un boolean como respuesta
    //recibe como parametro el usuario introducido en el EditText
    public boolean esta(String user) {
        SQLiteDatabase bd = getWritableDatabase();
        boolean esta=false;
        Cursor c = bd.rawQuery("SELECT * FROM Usuarios WHERE Usuario='"+user+"'", null);
        //Si esta metido el usuario, es decir, si en el cursor hay 1 elemento, el usuario existe.
        if (c.moveToNext()){
            esta= true;
        }
        c.close(); //cerramos el cursor
        return esta;
    }

    //este metodo mirara si el usuario ha introducido su contraseña correctamente y devolvera un boolean como respuesta
    //recibe como parametros el usuario y la contraseña introducidos en el EditText
    public boolean contraseñaCorrecta(String user, String password) {
        SQLiteDatabase bd = getWritableDatabase();
        boolean correcta=false;
        Cursor c = bd.rawQuery("SELECT * FROM Usuarios WHERE Usuario='"+user+"' AND Contraseña='"+password+"'", null);
        //Si el cursor nos devuelve 1 elemento la contraseña de ese usuario será correcta
        if (c.moveToNext()){
            correcta= true;
        }
        c.close(); //cerramos el cursor
        return correcta;

    }

    //este metodo inserta al usuario en la BBDD Usuarios pasandole de parametros el usuario y la contraseña
    public void insertarEnLaBBDD(String user, String password) {
        SQLiteDatabase bd = getWritableDatabase();
        bd.execSQL("INSERT INTO Usuarios VALUES ('"+user+"','"+password+"')");
        System.out.println("Usuario: " + user + " añadido en la BBDD Usuarios");
    }


    //este metodo inserta un nuevo pedido en la BBDD Pedidos pasandole de parametros
    //un String que contendra los elementos Pedidos y un Double que sera el precio total del pedido
    public void guardarPedido(String elementosPedidosSinRepeticion, double precioTotal) {
        SQLiteDatabase bd = getWritableDatabase();
        //añadimos a la BD Pedidos el ultimo pedido realizado
        bd.execSQL("INSERT INTO Pedidos('Elementos','Precio') VALUES ('"+elementosPedidosSinRepeticion+"','"+precioTotal+"')");
        System.out.println("Pedido añadido con estos ELEMENTOS: " + elementosPedidosSinRepeticion + " y este PRECIO: "+ precioTotal);


    }


}
