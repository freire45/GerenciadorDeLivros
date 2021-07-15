package br.com.erickfreire.gerenciadordelivros.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.erickfreire.gerenciadordelivros.dominio.Livro;

public class LivroDAO {

    private SQLiteDatabase bd;
    private static LivroDAO instance;

    private LivroDAO(Context context){
        DBHelper dbHelper = DBHelper.getInstance(context);
        bd = dbHelper.getWritableDatabase();

    }

    public static LivroDAO getInstance(Context context){
        if(instance ==  null){
            instance = new LivroDAO(context.getApplicationContext());
        }

        return instance;
    }

    public List<Livro> list(){

        String[] columns = {
                LivroContract.Columns._ID,
                LivroContract.Columns.TITULO,
                LivroContract.Columns.AUTOR,
                LivroContract.Columns.EDITORA,
                LivroContract.Columns.EMPRESTADO
        };

        List<Livro> livros = new ArrayList<>();

        try(Cursor c = bd.query(LivroContract.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                LivroContract.Columns.TITULO)){

            if(c.moveToFirst()){
                do{
                    Livro l = LivroDAO.fromCursor(c);
                    livros.add(l);

                } while(c.moveToNext());

            }

        }

        return livros;
    }

    private static Livro fromCursor(Cursor c){
        Long id = c.getLong(c.getColumnIndex(LivroContract.Columns._ID));
        String titulo = c.getString(c.getColumnIndex(LivroContract.Columns.TITULO));
        String autor = c.getString(c.getColumnIndex(LivroContract.Columns.AUTOR));
        String editora = c.getString(c.getColumnIndex(LivroContract.Columns.EDITORA));
        int emprestado = c.getInt(c.getColumnIndex(LivroContract.Columns.EMPRESTADO));

        return new Livro(id, titulo, autor, editora, emprestado);

    }

    public void save (Livro livro){

        ContentValues values = new ContentValues();
        values.put(LivroContract.Columns.TITULO, livro.getTitulo());
        values.put(LivroContract.Columns.AUTOR, livro.getAutor());
        values.put(LivroContract.Columns.EDITORA, livro.getEditora());
        values.put(LivroContract.Columns.EMPRESTADO, livro.getEmprestado());

        Long id = bd.insert(LivroContract.TABLE_NAME, null, values);
        livro.setId(id);
    }

    public void update (Livro livro){
        ContentValues values = new ContentValues();
        values.put(LivroContract.Columns.TITULO, livro.getTitulo());
        values.put(LivroContract.Columns.AUTOR, livro.getAutor());
        values.put(LivroContract.Columns.EDITORA, livro.getEditora());
        values.put(LivroContract.Columns.EMPRESTADO, livro.getEmprestado());

        bd.update(LivroContract.TABLE_NAME,values, LivroContract.Columns._ID+"=?",
                new String[]{String.valueOf(livro.getId())});
    }

    public void delete (Livro livro){
        bd.delete(LivroContract.TABLE_NAME,LivroContract.Columns._ID+"=?",
                new String[]{String.valueOf(livro.getId())});
    }

}
