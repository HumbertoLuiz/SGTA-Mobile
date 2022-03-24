package br.edu.ifpr.sgtamobile.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import br.edu.ifpr.sgtamobile.fragments.CadastrarUsuario;
import br.edu.ifpr.sgtamobile.fragments.CadastroCurso;
import br.edu.ifpr.sgtamobile.fragments.ListaCurso;
import br.edu.ifpr.sgtamobile.fragments.ListaUsuario;


public class CursoFragmentAdapter extends FragmentStateAdapter {
    public CursoFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new ListaCurso();

        }

        return new CadastroCurso();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
