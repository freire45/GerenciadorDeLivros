package br.com.erickfreire.gerenciadordelivros.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.com.erickfreire.gerenciadordelivros.R;
import br.com.erickfreire.gerenciadordelivros.adapter.LivroAdapter;
import br.com.erickfreire.gerenciadordelivros.dominio.Livro;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Livro> listaLivros = new ArrayList<>();

        listaLivros.add(new Livro(1L,"Android para Leigos","Michael Burton","Alta books",false));
        listaLivros.add(new Livro(2L,"Android para Programadores","Paul J, Deitel","Bookman",true));
        listaLivros.add(new Livro(3L,"Desenvolvimento para Android","Griffiths, David","Alta books",false));
        listaLivros.add(new Livro(4L,"Android Base de Dados","Queirós, Ricardo","FCA Editora",true));
        listaLivros.add(new Livro(5L,"Android em Ação","King, Chris","Elsevier - Campus",false));
        listaLivros.add(new Livro(6L,"Jogos em Android","Queirós, Ricardo","FCA - Editora",true));
        listaLivros.add(new Livro(7L,"Android Essencial com Kotlin","Ricardo R.","NOVATEC",false));


        LivroAdapter livroAdapter = new LivroAdapter(listaLivros, this);

        recyclerView.setAdapter(livroAdapter);
    }
}