package edu.ifpb.monteiro.ads.util;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * Funções para se aplicar máscaras aos controles do JavaFX
 * 
 * @author Paulo Henrique Luvisoto - paulobitfranca@gmail.com
 */
public class MascarasFX {

    public static void mascaraNumeroInteiro(TextField textField){

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }

    public static void mascaraNumero(TextField textField){

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            newValue = newValue.replaceAll(",",".");
            if(newValue.length()>0){
                try{
                    Double.parseDouble(newValue);
                    textField.setText(newValue.replaceAll(",","."));
                }catch(Exception e){
                    textField.setText(oldValue);
                }
            }
        });

    } 

    public static void mascaraCEP(TextField textField){

        textField.setOnKeyTyped((KeyEvent event) -> {
            if("0123456789".contains(event.getCharacter())==false){
                event.consume();
            }

            if(event.getCharacter().trim().length()==0){ // apagando

                if(textField.getText().length()==6){
                    textField.setText(textField.getText().substring(0,5));
                    textField.positionCaret(textField.getText().length());
                }

            }else{ // escrevendo

                if(textField.getText().length()==9) event.consume();

                if(textField.getText().length()==5){
                    textField.setText(textField.getText()+"-");
                    textField.positionCaret(textField.getText().length());
                }


            }
        });

        textField.setOnKeyReleased((KeyEvent evt) -> {

            if(!textField.getText().matches("\\d-*")){
                textField.setText(textField.getText().replaceAll("[^\\d-]", ""));
                textField.positionCaret(textField.getText().length());
            }
        });

    }

    public static void mascaraData(TextField textField){

        textField.setOnKeyTyped((KeyEvent event) -> {
            if("0123456789".contains(event.getCharacter())==false){
                event.consume();
            }

            if(event.getCharacter().trim().length()==0){ // apagando

                if(textField.getText().length()==3){
                    textField.setText(textField.getText().substring(0,2));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==6){
                    textField.setText(textField.getText().substring(0,5));
                    textField.positionCaret(textField.getText().length());
                }

            }else{ // escrevendo

                if(textField.getText().length()==10) event.consume();

                if(textField.getText().length()==2){
                    textField.setText(textField.getText()+"/");
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==5){
                    textField.setText(textField.getText()+"/");
                    textField.positionCaret(textField.getText().length());
                }

            }
        });

        textField.setOnKeyReleased((KeyEvent evt) -> {

            if(!textField.getText().matches("\\d/*")){
                textField.setText(textField.getText().replaceAll("[^\\d/]", ""));
                textField.positionCaret(textField.getText().length());
            }
        });

    }

    public static void mascaraData(DatePicker datePicker){

        datePicker.getEditor().setOnKeyTyped((KeyEvent event) -> {
            if("0123456789".contains(event.getCharacter())==false){
                event.consume();
            }

            if(event.getCharacter().trim().length()==0){ // apagando
                if(datePicker.getEditor().getText().length()==3){
                    datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0,2));
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                }
                if(datePicker.getEditor().getText().length()==6){
                    datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0,5));
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                }

            }else{ // escrevendo

                if(datePicker.getEditor().getText().length()==10) event.consume();

                if(datePicker.getEditor().getText().length()==2){
                    datePicker.getEditor().setText(datePicker.getEditor().getText()+"/");
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                }
                if(datePicker.getEditor().getText().length()==5){
                    datePicker.getEditor().setText(datePicker.getEditor().getText()+"/");
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                }

            }
        });

        datePicker.getEditor().setOnKeyReleased((KeyEvent evt) -> {

            if(!datePicker.getEditor().getText().matches("\\d/*")){
                datePicker.getEditor().setText(datePicker.getEditor().getText().replaceAll("[^\\d/]", ""));
                datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
            }
        });

    }

    public static void mascaraCPF(TextField textField){

        textField.setOnKeyTyped((KeyEvent event) -> {
            if("0123456789".contains(event.getCharacter())==false){
                event.consume();
            }

            if(event.getCharacter().trim().length()==0){ // apagando

                if(textField.getText().length()==4){
                    textField.setText(textField.getText().substring(0,3));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==8){
                    textField.setText(textField.getText().substring(0,7));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==12){
                    textField.setText(textField.getText().substring(0,11));
                    textField.positionCaret(textField.getText().length());
                }

            }else{ // escrevendo

                if(textField.getText().length()==14) event.consume();

                if(textField.getText().length()==3){
                    textField.setText(textField.getText()+".");
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==7){
                    textField.setText(textField.getText()+".");
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==11){
                    textField.setText(textField.getText()+"-");
                    textField.positionCaret(textField.getText().length());
                }

            }
        });

        textField.setOnKeyReleased((KeyEvent evt) -> {

            if(!textField.getText().matches("\\d.-*")){
                textField.setText(textField.getText().replaceAll("[^\\d.-]", ""));
                textField.positionCaret(textField.getText().length());
            }
        });

    }

    public static void mascaraCNPJ(TextField textField){

        textField.setOnKeyTyped((KeyEvent event) -> {
            if("0123456789".contains(event.getCharacter())==false){
                event.consume();
            }

            if(event.getCharacter().trim().length()==0){ // apagando

                if(textField.getText().length()==3){
                    textField.setText(textField.getText().substring(0,2));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==7){
                    textField.setText(textField.getText().substring(0,6));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==11){
                    textField.setText(textField.getText().substring(0,10));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==16){
                    textField.setText(textField.getText().substring(0,15));
                    textField.positionCaret(textField.getText().length());
                }

            }else{ // escrevendo

                if(textField.getText().length()==18) event.consume();

                if(textField.getText().length()==2){
                    textField.setText(textField.getText()+".");
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==6){
                    textField.setText(textField.getText()+".");
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==10){
                    textField.setText(textField.getText()+"/");
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==15){
                    textField.setText(textField.getText()+"-");
                    textField.positionCaret(textField.getText().length());
                }

            }
        });

        textField.setOnKeyReleased((KeyEvent evt) -> {

            if(!textField.getText().matches("\\d./-*")){
                textField.setText(textField.getText().replaceAll("[^\\d./-]", ""));
                textField.positionCaret(textField.getText().length());
            }
        });

    }

    public static void mascaraEmail(TextField textField){

        textField.setOnKeyTyped((KeyEvent event) -> {
            if("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz._-@".contains(event.getCharacter())==false){
                event.consume();
            }

            if("@".equals(event.getCharacter())&&textField.getText().contains("@")){
                event.consume();
            }

            if("@".equals(event.getCharacter())&&textField.getText().length()==0){
                event.consume();
            }
        });

    }

    public static void mascaraTelefone(TextField textField){

        textField.setOnKeyTyped((KeyEvent event) -> {
            if("0123456789".contains(event.getCharacter())==false){
                event.consume();
            }

            if(event.getCharacter().trim().length()==0){ // apagando

                if(textField.getText().length()==9&&textField.getText().substring(8,9).equals("-")){
                    textField.setText(textField.getText().substring(0,8));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==9&&textField.getText().substring(7,8).equals("-")){
                    textField.setText(textField.getText().substring(0,7));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==4){
                    textField.setText(textField.getText().substring(0,3));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==1){
                    textField.setText("");
                }

            }else{ //escrevendo

                if(textField.getText().length()==13) event.consume();

                if(textField.getText().length()==0){
                    textField.setText("("+event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }
                if(textField.getText().length()==3){
                    textField.setText(textField.getText()+")"+event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }
                if(textField.getText().length()==7){
                    textField.setText(textField.getText()+"-"+event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }
                if(textField.getText().length()==8&&textField.getText().substring(7,8)!="-"){
                    textField.setText(textField.getText()+"-"+event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }
                if(textField.getText().length()==12&&textField.getText().substring(7,8).equals("-")){
                    textField.setText(textField.getText().substring(0,7)+textField.getText().substring(8,9)+"-"+textField.getText().substring(9,12)+event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }

            }

        });

        textField.setOnKeyReleased((KeyEvent evt) -> {

            if(!textField.getText().matches("\\d()-*")){
                textField.setText(textField.getText().replaceAll("[^\\d()-]", ""));
                textField.positionCaret(textField.getText().length());
            }
        });

    }
}