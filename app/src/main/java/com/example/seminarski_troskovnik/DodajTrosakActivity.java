package com.example.seminarski_troskovnik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.seminarski_troskovnik.Model.DatabaseHandler;
import com.example.seminarski_troskovnik.Model.Trosak;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DodajTrosakActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName, edtPrice, edtColor;
    Button btnAdd, btnGoBack;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_trosak);
        Toast.makeText(DodajTrosakActivity.this, "Ovo je dodaj trošak aktivnost", Toast.LENGTH_SHORT).show();
        edtName=(EditText) findViewById(R.id.edtName);
        edtPrice=(EditText) findViewById(R.id.edtPrice);
        edtColor=(EditText) findViewById(R.id.edtColor);

        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnGoBack=(Button) findViewById(R.id.nazad);
        databaseHandler = new DatabaseHandler(DodajTrosakActivity.this);

        btnAdd.setOnClickListener( DodajTrosakActivity.this);
        btnGoBack.setOnClickListener(DodajTrosakActivity.this);

    }

    private void dodajTrosakUBazu(){

        String nameValue=edtName.getText().toString();
        String priceValue=edtPrice.getText().toString();
        String colorValue=edtColor.getText().toString();
        Date date=new Date();
        String vrijeme = new SimpleDateFormat("yyyy-MM-dd"  ).format(date);




        try{
            double cijenaDouble=Double.parseDouble(priceValue);
            Trosak trosakObject= new Trosak(0, nameValue, cijenaDouble, colorValue,vrijeme);
            databaseHandler.dodajTrosak(trosakObject);
            Toast.makeText(DodajTrosakActivity.this, trosakObject +" trošak je spremljen",Toast.LENGTH_SHORT).show();

        }catch (Exception e){

            e.printStackTrace();

        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnAdd:
                dodajTrosakUBazu();


            case  R.id.nazad:
                this.finish();
                break;
        }
    }
}
