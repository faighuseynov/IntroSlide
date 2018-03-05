package com.example.faig.quizaz;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class MainActivity extends AppCompatActivity {


    CategoryFragment fragment = CategoryFragment.newInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
     if (fragment!=null){
       fragmentManager
                 .beginTransaction()
                 .add(R.id.fragment_container,fragment)
                 .commit();
     }

    }




    @Override
    public void onBackPressed() {

        if(getSupportFragmentManager().getBackStackEntryCount()>0){

        getSupportFragmentManager().popBackStack();
        }else {

            super.onBackPressed();
        }

    }
/*
    private boolean loadFragment(android.support.v4.app.Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;

    }*/
}
