package com.kanad.mindit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainScreen extends AppCompatActivity {
    FloatingActionButton fab_1,fab_2,fab_3,fab;
    Animation close_rotate_anim,from_bottom_anim,rotate_open_anim,to_bottom_anim;
    BottomNavigationView navigationView;
    Boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        if (GoogleSignIn.getLastSignedInAccount(MainScreen.this) == null) {
            Intent intent = new Intent(MainScreen.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            navigationView = findViewById(R.id.bottom_navigation);
            fab_1 = findViewById(R.id.fab_1);
            fab_2 = findViewById(R.id.fab_2);
            fab_3 = findViewById(R.id.fab_3);
            close_rotate_anim = AnimationUtils.loadAnimation(this,R.anim.fab_close);
            from_bottom_anim = AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
            rotate_open_anim = AnimationUtils.loadAnimation(this,R.anim.fab_open);
            to_bottom_anim = AnimationUtils.loadAnimation(this,R.anim.rotate_backwards);
            getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new TodaysTask()).commit();
            navigationView.setSelectedItemId(R.id.nav_home);
            fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    animateFab();
                }
            });
            fab_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment1 = new AddTask();
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment1).commit();
                    animateFab();

                }
            });
            fab_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment1 = new AddDeadline();
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment1).commit();
                    animateFab();
                }
            });
            fab_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment1 = new AddMeeting();
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment1).commit();
                    animateFab();
                }
            });
            navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()){

                        case R.id.nav_home:
                            fragment = new TodaysTask();
                            break;

                        case R.id.nav_meeting:
                            fragment = new Meetings();
                            break;

                        case R.id.nav_deadline:
                            fragment = new Deadlines();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();

                    return true;
                }
            });
        }

    }
    private void animateFab(){
        if (isOpen){
            fab.startAnimation(from_bottom_anim);
            fab_1.startAnimation(close_rotate_anim);
            fab_2.startAnimation(close_rotate_anim);
            fab_3.startAnimation(close_rotate_anim);
            fab_1.setClickable(false);
            fab_2.setClickable(false);
            fab_3.setClickable(false);
            isOpen=false;
        }else{
            fab.startAnimation(to_bottom_anim);
            fab_1.startAnimation(rotate_open_anim);
            fab_2.startAnimation(rotate_open_anim);
            fab_3.startAnimation(rotate_open_anim);
            fab_1.setClickable(true);
            fab_2.setClickable(true);
            fab_3.setClickable(true);
            isOpen=true;
        }
    }
}
