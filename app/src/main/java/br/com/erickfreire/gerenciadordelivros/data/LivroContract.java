package br.com.erickfreire.gerenciadordelivros.data;

public class LivroContract {

    public static final String TABLE_NAME = "livro";

    public static final class Columns{
        public static final String _ID = "_id";
        public static final String TITULO = "titulo";
        public static final String AUTOR = "autor";
        public static final String EDITORA = "editora";
        public static final String EMPRESTADO = "emprestado";
    }
}
