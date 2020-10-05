package com.example.seminarski_troskovnik;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.seminarski_troskovnik.Model.DatabaseHandler;
import com.example.seminarski_troskovnik.Model.Trosak;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.ArrayList;
import java.util.Date;

public class PrikaziGrafBarActivity extends AppCompatActivity {
    DatabaseHandler databaseHandler;
    GraphView graphView;
    LineGraphSeries<DataPoint> series;
    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prikazi_graf_bar);
        databaseHandler = new DatabaseHandler(PrikaziGrafBarActivity.this);
        ArrayList<Trosak> troskovi = databaseHandler.vratiTrosakObjectByMjesec();
        int x;
        double y;
        graphView = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        for (Trosak trosakObjects : troskovi) {


            x= trosakObjects.getTrosakId();
            y=trosakObjects.getCijena();


            series.appendData(new DataPoint(x, y), true, 30);



        }
        graphView.addSeries(series);

    }








}



