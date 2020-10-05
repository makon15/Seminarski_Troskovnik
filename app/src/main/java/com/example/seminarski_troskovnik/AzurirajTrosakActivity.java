package com.example.seminarski_troskovnik;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.seminarski_troskovnik.Model.DatabaseHandler;
import com.example.seminarski_troskovnik.Model.Trosak;

import java.util.ArrayList;

public class AzurirajTrosakActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azuriraj_trosak);

        databaseHandler =new DatabaseHandler(AzurirajTrosakActivity.this);
        promjeniSucelje();
    }


    private void promjeniSucelje(){
        ArrayList<Trosak> trosakObject= databaseHandler.vratiTrosakObject();

        if (trosakObject.size()>0){
            ScrollView scrollView=new ScrollView(AzurirajTrosakActivity.this);
            GridLayout gridLayout=new GridLayout(AzurirajTrosakActivity.this);
            gridLayout.setRowCount(trosakObject.size());
            gridLayout.setColumnCount(5);

            TextView[] idTextViews= new TextView[trosakObject.size()];
            EditText[][] edtNamesPricesColors= new  EditText[trosakObject.size()][3];
            Button[] edtButton=new Button[trosakObject.size()];

            Point screenSize= new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);

            int screenWidth=screenSize.x;
            int index=0;

            for (Trosak trosakObjects: trosakObject){

                idTextViews[index]=new TextView(AzurirajTrosakActivity.this);
                idTextViews[index].setGravity(Gravity.CENTER);
                edtNamesPricesColors[index][0]= new EditText(AzurirajTrosakActivity.this);
                edtNamesPricesColors[index][1]= new EditText(AzurirajTrosakActivity.this);
                edtNamesPricesColors[index][2]=new EditText(AzurirajTrosakActivity.this);

                edtNamesPricesColors[index][0].setText(trosakObjects.getTrosakIme());
                edtNamesPricesColors[index][1].setText(trosakObjects.getCijena() +"");
                edtNamesPricesColors[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                edtNamesPricesColors[index][2].setText(trosakObjects.getTrosakBoja());
                edtNamesPricesColors[index][0].setId(trosakObjects.getTrosakId()+100);
                edtNamesPricesColors[index][1].setId(trosakObjects.getTrosakId()+200);
                edtNamesPricesColors[index][2].setId(trosakObjects.getTrosakId()+300);

                edtButton[index]=new  Button(AzurirajTrosakActivity.this);
                edtButton[index].setText("Promjeni");
                edtButton[index].setId(trosakObjects.getTrosakId());
                edtButton[index].setOnClickListener(AzurirajTrosakActivity.this);

                gridLayout.addView(idTextViews[index], (int)(screenWidth*0.10),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamesPricesColors[index][0], (int) (screenWidth*0.20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamesPricesColors[index][1],(int)(screenWidth*0.20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamesPricesColors[index][2],(int)(screenWidth*0.20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtButton[index],(int)(screenWidth*0.30),
                        ViewGroup.LayoutParams.WRAP_CONTENT);


                index++;




            }

            scrollView.addView(gridLayout);
            setContentView(scrollView);






        }
    }

    @Override
    public void onClick(View view) {

        int trosakObjectId=view.getId();
        EditText edtTrosakIme=(EditText) findViewById(trosakObjectId+100);
        EditText edtTrosakCijena=(EditText) findViewById(trosakObjectId+200);
        EditText edtTrosakBoja=(EditText) findViewById(trosakObjectId+300);

        String trosakImeStringVrijednost=edtTrosakIme.getText().toString();
        String trosakCijenaStringVrijednost=edtTrosakCijena.getText().toString();
        String trosakBojaStringVrijednost=edtTrosakBoja.getText().toString();

        try{

            double trosakCijenaDoubleVrijednost=Double.parseDouble(trosakCijenaStringVrijednost);
            databaseHandler.promijeniTrosakObject(trosakObjectId,trosakImeStringVrijednost,trosakCijenaDoubleVrijednost,trosakBojaStringVrijednost);
        }
        catch (NumberFormatException e){




        }


    }
}

