package br.edu.ifpr.sgtamobile.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import br.edu.ifpr.sgtamobile.fragments.AlunoMyItemRecyclerViewAdapter;
import br.edu.ifpr.sgtamobile.fragments.CadastroAluno;

public class AlunoFragmentAdapter extends FragmentStateAdapter {

    public AlunoFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new AlunoMyItemRecyclerViewAdapter.ListaAluno();

        }

        return new CadastroAluno();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
