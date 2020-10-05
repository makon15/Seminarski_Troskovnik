package com.example.seminarski_troskovnik;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import com.example.seminarski_troskovnik.Model.DatabaseHandler;
import com.example.seminarski_troskovnik.Model.Trosak;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHandler databaseHandler;
    private Double totalCijena;
    private ScrollView scrollView;
    private int trosakButtonWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        databaseHandler = new DatabaseHandler(MainActivity.this);
        totalCijena = 0.0;
        scrollView = (ScrollView) findViewById(R.id.ScroolView);

        Point screenWidth = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenWidth);
        trosakButtonWidth = screenWidth.x /2;

        promijeniSučelje();
    }
    private void promijeniSučelje(){

        scrollView.removeAllViewsInLayout();
        ArrayList<Trosak> sviTrosakObjects= databaseHandler.vratiTrosakObject();
        if (sviTrosakObjects.size()>0 ){



            GridLayout gridLayout=new GridLayout(MainActivity.this);
            gridLayout.setRowCount((sviTrosakObjects.size()+1)/2);
            gridLayout.setColumnCount(2);
            TrosakButton[] trosakButtons=new TrosakButton[sviTrosakObjects.size()];
            int index=0;

            for(Trosak trosakObjects: sviTrosakObjects){

                trosakButtons[index]=new TrosakButton(MainActivity.this, trosakObjects);
                trosakButtons[index].setText(trosakObjects.getTrosakIme()+"\n" +trosakObjects.getCijena() +"\n" + trosakObjects.getTrosakVrijeme());

                switch (trosakObjects.getTrosakBoja()){

                    case "crvena":
                        trosakButtons[index].setBackgroundColor(Color.RED);
                        break;

                    case "plava":
                        trosakButtons[index].setBackgroundColor(Color.BLUE);
                        break;

                    case "crna":
                        trosakButtons[index].setBackgroundColor(Color.BLACK);
                        break;
                    case "žuta":
                        trosakButtons[index].setBackgroundColor(Color.YELLOW);
                        break;
                    case "zelena":
                        trosakButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    case "ljubičasta":
                        trosakButtons[index].setBackgroundColor(Color.CYAN);
                        break;
                    case"siva":
                        trosakButtons[index].setBackgroundColor(Color.GRAY);

                        deafault:
                        trosakButtons[index].setBackgroundColor(Color.WHITE);

                        index++;


                }
                trosakButtons[index].setOnClickListener(MainActivity.this);
                gridLayout.addView(trosakButtons[index],trosakButtonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

            }
            scrollView.addView(gridLayout);

        }




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

        switch (id){
            case R.id.Dodaj_novi_trosak:
                Intent Dodaj_novi_trosak=new Intent(MainActivity.this,DodajTrosakActivity.class);
                startActivity(Dodaj_novi_trosak);

                break;

            case R.id.Izbrisi_trosak:
                Intent IzbrisiTrosak=new Intent(MainActivity.this, IzbrisiTrosakActivity.class);
                startActivity(IzbrisiTrosak);
                return  true;

            case R.id.Azuriraj_trosak:
                Intent AzurirajTrosak=new Intent(MainActivity.this,AzurirajTrosakActivity.class);
                startActivity(AzurirajTrosak);
                return true;

            case R.id.trosak_cijena_reset:
                totalCijena=0.0;
                Toast.makeText(MainActivity.this,totalCijena+"",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Prikazi_graf_bar:
                Intent PrikaziGrafBar= new Intent(MainActivity.this, PrikaziGrafBarActivity.class);
                startActivity(PrikaziGrafBar);
                return true;
            case R.id.Prikazi_pie_chart:
                Intent PrikaziPieChart= new Intent(MainActivity.this, PrikaziPieChartActivity.class);
                startActivity(PrikaziPieChart);
                return true;



        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        TrosakButton trosakButton=(TrosakButton) view;
        totalCijena=totalCijena + trosakButton.getTrosakCijena();
        String trosakCijenaFormatted= NumberFormat.getCurrencyInstance().format(totalCijena);
        Toast.makeText(MainActivity.this,trosakCijenaFormatted,Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onResume() {
        super.onResume();
        promijeniSučelje();
    }
}

