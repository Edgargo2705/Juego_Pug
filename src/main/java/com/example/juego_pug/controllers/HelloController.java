package com.example.juego_pug.controllers;

import com.example.juego_pug.models.*;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class HelloController implements Observer{
    private boolean mover = false;
    private Random random;

    private Perro pug;
    private Hueso comida;

    private Comida croqueta;

    private Agua agua;

    private Basura basura;
    @FXML
    private ImageView imgFood;
    @FXML
    private ImageView imgPug;
    @FXML
    private ImageView imgcomida;

    @FXML
    private ImageView imgagua;

    @FXML
    private ImageView imgbasura;
    @FXML
    private Button btnEmpezar;
    @FXML
    private Button btnSalir;
    @FXML
    private Label game_over;


    @FXML
    void empezarOnMouseClicked(MouseEvent event) {
        comida = new Hueso();
        comida.set_position(new Vector(1,396,154));
        comida.addObserver(this);
        new Thread(comida).start();

        mover = true;

        croqueta = new Comida();
        croqueta.set_position(new Vector(1,396,154));
        croqueta.addObserver(this);
        new Thread(croqueta).start();

        mover = true;

        agua = new Agua();
        agua.set_position(new Vector(1,396,154));
        agua.addObserver(this);
        new Thread(agua).start();

        mover = true;

        basura = new Basura();
        basura.set_position(new Vector(1,396,154));
        basura.addObserver(this);
        new Thread(basura).start();

        mover = true;

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(50),btnEmpezar);
        fadeTransition.setByValue(0);
        fadeTransition.play();
        fadeTransition.setOnFinished(e -> {
            btnEmpezar.setManaged(false);
            btnEmpezar.setVisible(false);
        });
    }
    @FXML
    void salirOnMouseClicked(MouseEvent event) {
        System.exit(1);
    }

    @FXML
    void characterOnKeyPressed (KeyEvent event) {
        if (mover==true){
            if(event.getCode() == KeyCode.D) {
                System.out.println("tecla D");
                if (imgPug.getLayoutX()>532){
                    imgPug.setLayoutX(imgPug.getLayoutX());
                }else{
                    imgPug.setLayoutX(imgPug.getLayoutX()+15);
                }
            }
            if(event.getCode() == KeyCode.A) {
                System.out.println("tecla A");
                if (imgPug.getLayoutX()<0){
                    imgPug.setLayoutX(imgPug.getLayoutX());
                }else{
                    imgPug.setLayoutX(imgPug.getLayoutX()-15);
                }
            }
            if(event.getCode() == KeyCode.W) {
                System.out.println("tecla W");
                if (imgPug.getLayoutY()<0){
                    imgPug.setLayoutY(imgPug.getLayoutY());
                }else{
                    imgPug.setLayoutY(imgPug.getLayoutY()-15);
                }
            }
            if(event.getCode() == KeyCode.S) {
                System.out.println("tecla S");
                if (imgPug.getLayoutY()>226){
                    imgPug.setLayoutY(imgPug.getLayoutY());
                }else{
                    imgPug.setLayoutY(imgPug.getLayoutY()+15);
                }
            }
        }

    }





    @Override
    public void update(Observable o, Object arg) {

        random = new Random(System.currentTimeMillis());
        Vector vector = (Vector) arg;

        if(imgPug.getBoundsInParent().intersects(imgFood.getBoundsInParent())){
            System.out.println("Pug comio su huesito");

            this.comida.setEstado(false);
            this.imgFood.setVisible(false);

            if (!comida.isEstado()){
                int vecX = random.nextInt(622);
                int vecY = random.nextInt(318);

                this.comida.setEstado(true);
                comida.set_position(new Vector(1,vecX,vecY));
                this.imgFood.setVisible(true);
                imgFood.setLayoutX(vecX);
                imgFood.setLayoutY(vecY);
            }
        }

        if(imgPug.getBoundsInParent().intersects(imgcomida.getBoundsInParent())){
            System.out.println("Pug comio su croqueta");

            this.croqueta.setEstado(false);
            this.imgcomida.setVisible(false);

            if (!croqueta.isEstado()){
                int vecX = random.nextInt(622);
                int vecY = random.nextInt(300);

                this.croqueta.setEstado(true);
                croqueta.set_position(new Vector(1,vecX,vecY));
                this.imgcomida.setVisible(true);
                imgcomida.setLayoutX(vecX);
                imgcomida.setLayoutY(vecY);
            }
        }

        if(imgPug.getBoundsInParent().intersects(imgagua.getBoundsInParent())){
            System.out.println("Pug tomo agua");

            this.agua.setEstado(false);
            this.imgagua.setVisible(false);

            if (!agua.isEstado()){
                int vecX = random.nextInt(622);
                int vecY = random.nextInt(300);

                this.agua.setEstado(true);
                agua.set_position(new Vector(1,vecX,vecY));
                this.imgagua.setVisible(true);
                imgagua.setLayoutX(vecX);
                imgagua.setLayoutY(vecY);
            }
        }

        if(imgPug.getBoundsInParent().intersects(imgbasura.getBoundsInParent())) {
            System.out.println("Pug comio basura y se enfermo");

            this.basura.setEstado(false);
            this.imgbasura.setVisible(false);
            agua.setEstado(false);
            comida.setEstado(false);
            basura.setEstado(false);
            game_over.setVisible(true);
            mover=false;

            if (!basura.isEstado()) {
                int vecX = random.nextInt(622);
                int vecY = random.nextInt(300);

                this.basura.setEstado(true);
                basura.set_position(new Vector(1, vecX, vecY));
                this.imgbasura.setVisible(true);
                imgbasura.setLayoutX(vecX);
                imgbasura.setLayoutY(vecY);
            }
        }
    }
}