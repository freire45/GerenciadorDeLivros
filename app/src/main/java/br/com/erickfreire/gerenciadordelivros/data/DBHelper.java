package br.com.erickfreire.gerenciadordelivros.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String BD_NAME = "livrosbd";
    public static final int BD_Version = 1;

    private static DBHelper instance;

    private static String SQL_CREATE = String.format(
                    "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "%s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL)",
                    LivroContract.TABLE_NAME,
                    LivroContract.Columns._ID,
                    LivroContract.Columns.TITULO,
                    LivroContract.Columns.AUTOR,
                    LivroContract.Columns.EDITORA,
                    LivroContract.Columns.EMPRESTADO
    );

    private static String SQL_DROP = "DROP TABLE IF EXISTS " + LivroContract.TABLE_NAME;

    private DBHelper(Context context){
        super(context, BD_NAME, null, BD_Version);

    }

    public static DBHelper getInstance(Context context){
        if(instance ==  null){
            instance = new DBHelper(context);
        }

        return instance;

    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(SQL_DROP);
        bd.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        onCreate(bd);

    }
}
