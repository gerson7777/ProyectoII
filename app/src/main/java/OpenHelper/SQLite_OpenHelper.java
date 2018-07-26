package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BYTE on 04/07/2018.
 */

public class SQLite_OpenHelper extends SQLiteOpenHelper{

    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table usuarios(_ID integer primary key autoincrement, Nombre text," +
                "Correo text, Password text);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    //metodo abrir base
    public  void abrir(){
        this.getWritableDatabase();
    }
    //metodo cerrar

    public  void cerrar(){
        this.close();
    }
    //metodo insertar registros en la
    public void insertarRegistro(String nom, String cor, String pas){
        ContentValues valores = new ContentValues();
        valores.put("Nombre",nom);
        valores.put("Correo", cor);
        valores.put("Password",pas);
        this.getWritableDatabase().insert("usuarios",null,valores);
    }
    //metodo que permite validar si el usuario exixst
    public Cursor ConsultarUsuPas(String usu, String pas) throws SQLException{
        Cursor mcursor = null;
        mcursor= this.getReadableDatabase().query("usuarios", new String[]{"_ID",
                "Nombre","Correo","Password"},"Correo like '"+usu+"' " +
                "and Password like '"+pas+"'",null, null,null,null );
        return  mcursor;
    }
    //metodo que permite listar usuarios
    public  Cursor listarUsuarios() throws  SQLException{
        Cursor mcurCursor = this.getReadableDatabase().query("usuarios", new String[]
                {"_ID","Nombre","Correo","Password"},null,null,null,null,null);

        if (mcurCursor != null){
            mcurCursor.moveToFirst();
        }
        return mcurCursor;

    }
    public  Cursor listarUsuarioNom(String nom) throws  SQLException{
        Cursor mcurCursor = this.getReadableDatabase().query("usuarios", new String[]
                        {"_ID","Nombre","Correo","Password"},"Nombre like '%"+nom+"%'"
                ,null,null,null,null);

        if (mcurCursor != null){
            mcurCursor.moveToFirst();
        }
        return mcurCursor;

    }






}