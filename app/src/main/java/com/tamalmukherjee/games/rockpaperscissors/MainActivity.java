package com.tamalmukherjee.games.rockpaperscissors;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void rock(View view) {
        play(1);
    }

    public void scissors(View view) {
        play(3);
    }

    public void paper(View view) {
        play(2);
    }

    private void play(Integer userChoice) {
        Random rand = new Random();
        Integer cpuChoice = rand.nextInt(3) + 1;

        ImageView img = (ImageView) findViewById(R.id.imageView4);
        InputStream s = null;
        try {
            switch (cpuChoice) {
                case 1:
                    s = getAssets().open("rock.png");
                    break;
                case 2:
                    s = getAssets().open("paper.png");
                    break;
                case 3:
                    s = getAssets().open("scissors.png");
                    break;
            }
            if(s != null) {
                Drawable d = Drawable.createFromStream(s, null);
                img.setImageDrawable(d);
                img.refreshDrawableState();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(userChoice == cpuChoice){
            Toast.makeText(getApplicationContext(), "Draw", Toast.LENGTH_SHORT).show();
        }
        else if ((userChoice == 1 && cpuChoice == 2) ||
                (userChoice == 2 && cpuChoice == 3) ||
                (userChoice == 3 && cpuChoice == 1)) {
            Toast.makeText(getApplicationContext(), "I win!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "You win!", Toast.LENGTH_SHORT).show();
        }
    }
}
