package br.edu.ifpr.sgtamobile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import br.edu.ifpr.sgtamobile.R;
import br.edu.ifpr.sgtamobile.adapter.FragmentAdapter;

public class UsuarioActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);
        FragmentManager fm= getSupportFragmentManager();
        adapter = new FragmentAdapter(fm,getLifecycle());
        pager2.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("Cadastrar"));
        tabLayout.addTab(tabLayout.newTab().setText("Listar"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }

}