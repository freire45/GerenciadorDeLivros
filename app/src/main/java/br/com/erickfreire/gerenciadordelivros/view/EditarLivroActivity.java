package br.com.erickfreire.gerenciadordelivros.view;

import androidx.appcompat.app.AppCompatActivity;
import br.com.erickfreire.gerenciadordelivros.R;
import br.com.erickfreire.gerenciadordelivros.data.LivroDAO;
import br.com.erickfreire.gerenciadordelivros.dominio.Livro;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class EditarLivroActivity extends AppCompatActivity {

    private EditText edt_titulo;
    private EditText edt_autor;
    private EditText edt_editora;
    private CheckBox chk_emprestado;
    private LivroDAO livroDAO;
    private Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_livro);

        edt_titulo = findViewById(R.id.edt_titulo);
        edt_autor = findViewById(R.id.edt_autor);
        edt_editora = findViewById(R.id.edt_editora);
        chk_emprestado = findViewById(R.id.check_emprestado);

        livroDAO = LivroDAO.getInstance(this);

        livro = (Livro) getIntent().getSerializableExtra("livro");

        if(livro != null){
            edt_titulo.setText(livro.getTitulo());
            edt_autor.setText(livro.getAutor());
            edt_editora.setText(livro.getEditora());
            chk_emprestado.setChecked((livro.getEmprestado() == 1) ? true : false);
        }

    }

    public void cancelar(View view){
        setResult(RESULT_CANCELED);
        finish();

    }

    public void processar(View view){

        String titulo = edt_titulo.getText().toString();
        String autor = edt_autor.getText().toString();
        String editora = edt_editora.getText().toString();
        int emprestado = (chk_emprestado.isChecked()) ? 1 : 0;
        String msg;

        if(livro == null){
            Livro livro = new Livro(titulo, autor, editora, emprestado);
            livroDAO.save(livro);
            msg = "Livro Adicionado com sucesso! ID= " + livro.getId();
        } else {
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setEditora(editora);
            livro.setEmprestado(emprestado);

            livroDAO.update(livro);

            msg = "Livro Atualizado com sucesso! ID= " + livro.getId();
        }


        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();


    }
}