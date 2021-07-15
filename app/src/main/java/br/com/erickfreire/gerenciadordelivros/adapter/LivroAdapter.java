package br.com.erickfreire.gerenciadordelivros.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.erickfreire.gerenciadordelivros.R;
import br.com.erickfreire.gerenciadordelivros.dominio.Livro;

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.LivroHolder> {

    private List<Livro> livros;
    private Context context;

    private OnLivroListener onLivroListener;

    public LivroAdapter(List<Livro> livros, Context context, OnLivroListener onLivroListener) {
        this.livros = livros;
        this.context = context;
        this.onLivroListener = onLivroListener;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public LivroHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_livro, parent, false);

        LivroHolder livroHolder = new LivroHolder(view, onLivroListener);

        return livroHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull LivroAdapter.LivroHolder holder, int position) {

        Livro livro = livros.get(position);

        holder.txtTitulo.setText(livro.getTitulo());
        holder.txtAutor.setText(livro.getAutor());
        holder.txtEditora.setText(livro.getEditora());

        if(livro.getEmprestado() == 1){
            holder.ic_livro.setColorFilter(Color.GRAY);
            holder.ic_star.setVisibility(View.VISIBLE);
        } else {
            holder.ic_livro.setColorFilter(Color.parseColor("#0455BF"));
            holder.ic_star.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    public void setItems(List<Livro> livros){
        this.livros = livros;

    }

    public Livro getItem(int posicao){
        return livros.get(posicao);
    }

    public class LivroHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView txtTitulo;
        public TextView txtAutor;
        public TextView txtEditora;
        public ImageView ic_livro;
        public ImageView ic_star;

        public OnLivroListener onLivroListener;

        public LivroHolder(View view, OnLivroListener onLivroListener){

            super(view);

            txtTitulo = view.findViewById(R.id.txtTitulo);
            txtAutor = view.findViewById(R.id.txtAutor);
            txtEditora = view.findViewById(R.id.txtEditora);
            ic_livro = view.findViewById(R.id.ic_livro);
            ic_star = view.findViewById(R.id.ic_star);

            this.onLivroListener = onLivroListener;

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onLivroListener.onLivroClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            onLivroListener.onLivroLongClick(getAdapterPosition());

            return true;
        }
    }

    public interface OnLivroListener{
        void onLivroClick(int posicao);
        void onLivroLongClick(int posicao);
    }
}
