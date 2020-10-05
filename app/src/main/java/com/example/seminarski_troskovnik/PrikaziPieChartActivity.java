package com.example.seminarski_troskovnik;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;

import com.example.seminarski_troskovnik.Model.DatabaseHandler;
import com.example.seminarski_troskovnik.Model.Trosak;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;


public class PrikaziPieChartActivity extends AppCompatActivity {


    DatabaseHandler databaseHandler;
    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    ArrayList PieEntryLabels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prikazi_pie_chart);
        pieChart = findViewById(R.id.pieChart);
        getEntries();
        pieDataSet = new PieDataSet(pieEntries, "");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(Color.RED,Color.BLUE,Color.BLACK, Color.YELLOW,Color.GREEN,Color.CYAN,Color.GRAY );
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setSliceSpace(5f);
    }
    private void getEntries() {
        pieEntries = new ArrayList<>();
        databaseHandler = new DatabaseHandler(PrikaziPieChartActivity.this);
        ArrayList<Trosak> troskoviBoja = databaseHandler.vratiTrosakObjectByMjesec();
        int cr = 0, pl = 0, bl = 0, zu = 0, ze = 0, lju = 0, si = 0;
        for (Trosak trosakObject: troskoviBoja) {

            switch (trosakObject.getTrosakBoja()) {

                case "crvena":
                    cr=cr + (int)trosakObject.getCijena();
                    break;
                case "plava":
                    pl=pl+ (int)trosakObject.getCijena();
                    break;
                case "crna":
                    bl=bl+ (int)trosakObject.getCijena();
                    break;
                case "žuta":
                    zu=zu+ (int)trosakObject.getCijena();
                    break;
                case "zelena":
                    ze=ze+ (int)trosakObject.getCijena();
                    break;
                case "ljubičasta":
                    lju=lju+ (int)trosakObject.getCijena();
                    break;
                case "siva":
                    si=si+ (int)trosakObject.getCijena();
                    break;
                case "":
                    si=si+ (int)trosakObject.getCijena();
                    break;

            }

        }
        pieEntries.add(new PieEntry(cr, 0));
        pieEntries.add(new PieEntry(pl, 1));
        pieEntries.add(new PieEntry(bl, 2));
        pieEntries.add(new PieEntry(zu, 3));
        pieEntries.add(new PieEntry(ze, 4));
        pieEntries.add(new PieEntry(lju, 5));
        pieEntries.add(new PieEntry(si, 6));



    }
}
