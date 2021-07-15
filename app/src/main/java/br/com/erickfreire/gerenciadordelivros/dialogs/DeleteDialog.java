package br.com.erickfreire.gerenciadordelivros.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.com.erickfreire.gerenciadordelivros.dominio.Livro;

public class DeleteDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private Livro livro;

    private OnDeleteListener listener;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);

        if(!(context instanceof OnDeleteListener)){
            throw new RuntimeException("Não é um OnDeleteListener");
        }

        this.listener = (OnDeleteListener) context;
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Deseja Excluir o Livro " + livro.getTitulo());
        builder.setPositiveButton("SIM", this);
        builder.setNegativeButton("Não", this);

        return builder.create();

    }

    public void setLivro (Livro livro){
        this.livro = livro;
    }

    @Override
    public void onClick(DialogInterface dialog, int i) {
        if(i == DialogInterface.BUTTON_POSITIVE){
            listener.onDelete(livro);
        }

    }

    public interface OnDeleteListener{
        void onDelete(Livro livro);
    }
}
