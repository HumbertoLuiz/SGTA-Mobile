package br.edu.ifpr.sgtamobile.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifpr.sgtamobile.R;
import br.edu.ifpr.sgtamobile.api.ApiClient;
import br.edu.ifpr.sgtamobile.api.ServidorApiRestService;
import br.edu.ifpr.sgtamobile.model.Servidor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServidorAdapter extends RecyclerView.Adapter<ServidorHolder> {

    private final List<Servidor> servidores;

    public  ServidorAdapter(List<Servidor>servidores){
        this.servidores = servidores;
    }
    public  void adicionarServidor(Servidor servidor){
        servidores.add(servidor);
        notifyItemInserted(getItemCount());
    }

    private Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }


    @Override
    public ServidorHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        return  new ServidorHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.layout_list_servidor,parent,false));
    }

    @Override
    public void onBindViewHolder(final ServidorHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nomeServidor.setText(servidores.get(position).getNome());
        final Servidor servidor = servidores.get(position);

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação")
                        .setMessage("Tem certeza que deseja excluir")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Servidor servidor = servidores.get(position);
                                ServidorApiRestService service =  ApiClient.getClient().create(ServidorApiRestService.class);
                                Call<Servidor> call1 = service.deleteServidor(servidor.getId());
                                call1.enqueue(new Callback<Servidor>() {
                                    @Override
                                    public void onResponse(Call<Servidor> call, Response<Servidor> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<Servidor> call, Throwable t) {

                                    }
                                });

                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create()
                        .show();

            }
        });

        holder.add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = getActivity(view);
                Intent intent = activity.getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("servidor", servidor );
                activity.finish();
                activity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return servidores != null ? servidores.size() : 0;
    }

    public  void atualizarServidor(Servidor servidor){
        servidores.set(servidores.indexOf(servidor), servidor);
        notifyItemInserted(servidores.indexOf(servidor));
    }

    public void removerCliente(Servidor servidor){
        int position = servidores.indexOf(servidor);
       // servidor.remove(position);
        notifyItemInserted(position);
    }
}
