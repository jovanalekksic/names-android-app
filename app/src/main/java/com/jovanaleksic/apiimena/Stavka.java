package com.jovanaleksic.apiimena;

public class Stavka {
    int slika;
    String ime;
    String state;

    public Stavka(int slika, String ime, String state) {
        this.slika = slika;
        this.ime = ime;
        this.state = state;
    }

    public int getSlika() {
        return slika;
    }

    public void setSlika(int slika) {
        this.slika = slika;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

