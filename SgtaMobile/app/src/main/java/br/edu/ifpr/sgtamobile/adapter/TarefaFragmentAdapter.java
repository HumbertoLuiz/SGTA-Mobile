package br.edu.ifpr.sgtamobile.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import br.edu.ifpr.sgtamobile.fragments.CadastroTarefa;
import br.edu.ifpr.sgtamobile.fragments.ListaTarefa;

public class TarefaFragmentAdapter extends FragmentStateAdapter {
    public TarefaFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new ListaTarefa();

        }

        return new CadastroTarefa();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
