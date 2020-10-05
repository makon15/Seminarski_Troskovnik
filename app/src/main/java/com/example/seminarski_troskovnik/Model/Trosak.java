package com.example.seminarski_troskovnik.Model;

import androidx.annotation.NonNull;

public class Trosak {
    private int trosakId;
    private String trosakIme;
    private double trosakCijena;
    private String trosakBoja;
    private String trosakVrijeme;



    public Trosak(int id,String ime, double Cijena,String boja, String vrijeme){
        setTrosakId(id);
        setTrosakIme(ime);
        setCijena(trosakCijena);
        setTrosakBoja(boja);
        setTrosakVrijeme(vrijeme);


        trosakId=id;
        trosakIme=ime;
        trosakCijena=Cijena;
        trosakBoja=boja;
        trosakVrijeme=vrijeme;



    }



    public int getTrosakId() {
        return trosakId;
    }

    public String getTrosakIme() {
        return trosakIme;
    }

    public double getCijena() {
        return trosakCijena;
    }

    public String getTrosakBoja() {
        return trosakBoja;
    }

    public String getTrosakVrijeme() {return trosakVrijeme;}


    public void setTrosakId(int trosakId) {
        this.trosakId = trosakId;
    }

    public void setTrosakIme(String trosakIme) {
        this.trosakIme = trosakIme;
    }

    public void setCijena(double cijena) {
        this.trosakCijena= cijena;
    }

    public void setTrosakBoja(String trosakBoja) {
        this.trosakBoja = trosakBoja;
    }

    public void setTrosakVrijeme(String trosakVrijeme){this.trosakVrijeme=trosakVrijeme;}


    @NonNull
    @Override
    public String toString() {

        return getTrosakIme() +"\n" + getCijena() +"\n" + getTrosakBoja() + "\n" + getTrosakVrijeme() ;

    }
}
