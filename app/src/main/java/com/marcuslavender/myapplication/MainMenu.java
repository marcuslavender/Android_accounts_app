package com.marcuslavender.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by marcus.lavender on 29/07/2018.
 */

public class MainMenu extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        /**
         * Created by marcus.lavender on 20/08/2018.
         */


        GridView gridview = (GridView) findViewById(R.id.mainmenu);


        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {




                //Toast.makeText(MainMenu.this, "" + position,
                        //Toast.LENGTH_SHORT).show();
            }
        });
    }
}


