package com.example.taruc.lab41sharedpref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewName;
    private ImageView imageViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewName = (TextView)findViewById(R.id.textViewName);
        imageViewProfile = (ImageView)findViewById(R.id.imageViewProfile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPref();
    }

    private void loadPref() {
        String title="";

        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String name = mySharedPreferences.getString("name_text",getString(R.string.pref_default_display_name));
        String gender = mySharedPreferences.getString("gender_list", getString(R.string.pref_title_gender));
        if(gender.equals("-1")){
            imageViewProfile.setImageResource(R.drawable.profile);
        }else if(gender.equals("1")){
            title = getString(R.string.male_title);
            imageViewProfile.setImageResource(R.drawable.male);
        }else {
            title = getString(R.string.female_title);
            imageViewProfile.setImageResource(R.drawable.female);
        }
        textViewName.setText(title + " " + name);
    }
}
