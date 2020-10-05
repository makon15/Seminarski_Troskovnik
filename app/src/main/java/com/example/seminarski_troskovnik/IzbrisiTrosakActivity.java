package com.example.seminarski_troskovnik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import com.example.seminarski_troskovnik.Model.DatabaseHandler;
import com.example.seminarski_troskovnik.Model.Trosak;

import java.util.ArrayList;

public class IzbrisiTrosakActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbrisi_trosak);
        databaseHandler =new DatabaseHandler(IzbrisiTrosakActivity.this);

        azurirajSucelje();



    }
    private void azurirajSucelje(){

        ArrayList<Trosak> sviTroskovi= databaseHandler.vratiTrosakObject();
        RelativeLayout relativeLayout =new RelativeLayout(IzbrisiTrosakActivity.this);
        ScrollView scrollView=new ScrollView(IzbrisiTrosakActivity.this);
        RadioGroup radioGroup=new RadioGroup(IzbrisiTrosakActivity.this);

        for(Trosak trosak: sviTroskovi){

            RadioButton radioButton=new RadioButton(IzbrisiTrosakActivity.this);
            radioButton.setId(trosak.getTrosakId());
            radioButton.setText(trosak.toString());
            radioGroup.addView(radioButton);
        }

        radioGroup.setOnCheckedChangeListener(IzbrisiTrosakActivity.this);
        Button btnBack= new Button(IzbrisiTrosakActivity.this);
        btnBack.setText("Idi nazad");
        btnBack.setOnClickListener(IzbrisiTrosakActivity.this);
        scrollView.addView(radioGroup);

        RelativeLayout.LayoutParams buttonParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.setMargins(0,0,0,70);
        relativeLayout.addView(btnBack, buttonParams);

        ScrollView.LayoutParams scroolViewParams= new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.MATCH_PARENT);
        relativeLayout.addView(scrollView,scroolViewParams);

        setContentView(relativeLayout);





    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        databaseHandler.izbrisiTrosakIzBazePomocuId(checkedId);
        Toast.makeText(IzbrisiTrosakActivity.this,"Trošak je izbrisan",Toast.LENGTH_SHORT).show();
        azurirajSucelje();
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
