package br.edu.ifpr.sgtamobile.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import br.edu.ifpr.sgtamobile.fragments.CadastroAluno;
import br.edu.ifpr.sgtamobile.fragments.ListaAluno;

public class AlunoFragmentAdapter extends FragmentStateAdapter {
    public AlunoFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new CadastroAluno();

        }

        return new ListaAluno();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

