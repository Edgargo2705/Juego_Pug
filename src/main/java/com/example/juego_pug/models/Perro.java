package com.example.juego_pug.models;

import java.util.Observable;
import java.util.Random;

public class Perro extends Observable implements Runnable {

    private Vector vector;

    private boolean estado;

    public Perro() {
        estado = true;

    }

    public void set_position(Vector vector) {
        this.vector = vector;
    }

    public boolean isEstado() {
        return estado;
    }



    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public void run() {
        while (estado) {
            vector.setX(vector.getX() + 10);
            setChanged();
            notifyObservers(vector);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

