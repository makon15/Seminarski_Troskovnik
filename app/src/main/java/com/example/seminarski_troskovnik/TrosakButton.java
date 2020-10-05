package com.example.seminarski_troskovnik;
import android.content.Context;
import androidx.appcompat.widget.AppCompatButton;
import com.example.seminarski_troskovnik.Model.Trosak;

public class TrosakButton extends AppCompatButton {

    private Trosak trosakObject;
    public  TrosakButton(Context context, Trosak trosak){

        super(context);

        trosakObject=trosak;


    }

    public String getTrosakBoja(){

        return trosakObject.getTrosakBoja();

    }

    public double getTrosakCijena(){

        return trosakObject.getCijena();
    }

    public  String getTrosakDan(){
        return trosakObject.getTrosakVrijeme();
    }

}
