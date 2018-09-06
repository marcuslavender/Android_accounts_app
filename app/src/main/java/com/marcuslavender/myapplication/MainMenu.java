package com.marcuslavender.myapplication;

import android.content.Intent;
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

    String description;

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
                switch (position)
                {
                    case 0:
                        description = "Show Balance";
                        Intent intent1 = new Intent(MainMenu.this, ShowBalance.class);
                        startActivity(intent1);
                    break;

                    case 1:
                        description = "Show Statement";
                        Intent intent2 = new Intent(MainMenu.this, ShowStatement.class);
                        startActivity(intent2);

                    break;

                    case 2:
                        description = "Deposit Money";
                        Intent intent3 = new Intent(MainMenu.this, DepositMoney.class);
                    break;

                    case 3:
                        description = "Withdraw Money";
                        Intent intent4 = new Intent(MainMenu.this, WithdrawMoney.class);
                    break;

                }

                Toast.makeText(MainMenu.this, description,
                        Toast.LENGTH_SHORT).show();
                        description = null;



            }
        });
    }

}

