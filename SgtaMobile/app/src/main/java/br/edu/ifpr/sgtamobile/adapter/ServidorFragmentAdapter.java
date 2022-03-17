package br.edu.ifpr.sgtamobile.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import br.edu.ifpr.sgtamobile.fragments.CadastroServidor;
import br.edu.ifpr.sgtamobile.fragments.ListaServidor;

public class ServidorFragmentAdapter extends FragmentStateAdapter {
    public ServidorFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new ListaServidor();

        }

        return new CadastroServidor();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}