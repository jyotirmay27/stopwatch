package sample;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private JFXTextField display;
    @FXML
    private JFXTextArea display2;

    int elapsedTime ;
    int sec ;
    int min;
    int hr;
    boolean status ;
    String seconds_string ;
    String minutes_string ;
    String hours_string ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        elapsedTime = 0;
         sec =0;
        min =0;
        hr =0;
         status = false;
         seconds_string = String.format("%02d", sec);
         minutes_string = String.format("%02d", min);
         hours_string = String.format("%02d", hr);
        display.setText(hours_string +":" + minutes_string+":"+seconds_string);
        display2.setText("");
    }
    class Thread1 extends Thread {

        @Override
        public void run() {

            try{
                while(status){
                    Thread.sleep(1000);
                    sec++;
                    if(sec==60){
                        min++;
                        sec=0;
                    }
                    if(min==60){
                        hr++;
                        min=0;
                    }
                    // UI display code.
                    seconds_string = String.format("%02d", sec);
                    minutes_string = String.format("%02d", min);
                    hours_string = String.format("%02d", hr);
                    display.setText(hours_string +":" + minutes_string+":"+seconds_string);
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

    }



    public void stop(){
        status=false;
    }

    public void onReset(ActionEvent actionEvent) {
        sec =0;
        min =0;
        hr =0;
        seconds_string = String.format("%02d", sec);
        minutes_string = String.format("%02d", min);
        hours_string = String.format("%02d", hr);
        display.setText(hours_string +":" + minutes_string+":"+seconds_string);
        display2.setText("");
        stop();
    }

    public void onPause(ActionEvent actionEvent) {
        String s= display2.getText();
        display2.setText(display.getText()+"\n"+s);
    }

    public void onStart(ActionEvent actionEvent) {
        if(elapsedTime%2 == 0)
        {
            status=true;
            Thread t1 = new Thread1();
            t1.start();
            elapsedTime++;

        }
        else{
            stop();
            elapsedTime++;
        }


    }
}
