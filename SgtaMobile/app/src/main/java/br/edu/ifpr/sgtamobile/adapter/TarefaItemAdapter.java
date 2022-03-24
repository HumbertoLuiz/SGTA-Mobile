package br.edu.ifpr.sgtamobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.edu.ifpr.sgtamobile.R;
import br.edu.ifpr.sgtamobile.model.Tarefa;
import br.edu.ifpr.sgtamobile.model.Usuario;

public class TarefaItemAdapter extends BaseAdapter {
    LayoutInflater mInflater;

    List<Tarefa> list;

    public TarefaItemAdapter(Context c, List<Tarefa> mylist) {

        list=mylist;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i).getId();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.layout_list_tarefa, null);
        TextView idTextView = (TextView) v.findViewById(R.id.id);
        TextView nameTextView = (TextView) v.findViewById(R.id.name);

        Integer id = list.get(i).getId();
        String name = list.get(i).getDescricao();



        idTextView.setText(id.toString());
        nameTextView.setText(name);

        Button deleteBtn = (Button) v.findViewById(R.id.delete_btn);
        Button addBtn = (Button)v.findViewById(R.id.add_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                list.remove(i); //or some other task
                notifyDataSetChanged();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                notifyDataSetChanged();
            }
        });

        return v;
    }
}

