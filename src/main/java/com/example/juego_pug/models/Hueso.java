package com.example.juego_pug.models;

import java.util.Observable;
import java.util.Random;

public class Hueso extends Observable implements Runnable {
 private Vector vector;
 private Random random;
 private boolean estado;

 public Hueso (){
     estado = true;
     random = new Random(System.currentTimeMillis());
 }

    public void set_position(Vector vector){
        this.vector=vector;
        vector.setX(random.nextInt(622));
        vector.setY(random.nextInt(318));
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public void run() {
        while (estado){
            setChanged();
            notifyObservers(vector);
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }

    }
}








