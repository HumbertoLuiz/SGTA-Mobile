package br.edu.ifpr.sgtamobile.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifpr.sgtamobile.R;

public class ServidorHolder extends RecyclerView.ViewHolder {

    public TextView nomeServidor;
    public Button delete_btn;
    public Button add_btn;
    public ServidorHolder(@NonNull View itemView) {
        super(itemView);

        nomeServidor =(TextView) itemView.findViewById(R.id.name);
        delete_btn = (Button) itemView.findViewById(R.id.delete_btn);
        add_btn = (Button) itemView.findViewById(R.id.add_btn);
    }
}
