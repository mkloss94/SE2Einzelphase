package at.michaelkloss.taschenrechner;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Die Initialisierungen
    EditText num1;
    EditText num2;
    TextView result;
    Button btnDividieren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Der Button wird "geholt" und anschließend ein onClickListener aufgerufen. (Deswegen das Interface View. OnClickListener)
        btnDividieren = (Button) findViewById(R.id.button);
        btnDividieren.setOnClickListener(this);

    }

    //diese Methode wird durch das Interface bereitgestellt.
    @Override
    public void onClick(View v) {

        //Nun wird der Dividend und der Divisor sowie das Ergebnis geholt.
        num1 = (EditText) findViewById(R.id.numberfieldDividend);
        num2 = (EditText) findViewById(R.id.numberfieldDivisor);
        result = (TextView) findViewById(R.id.result);
        int newResult = 0; //wird sicherheitshalber mit 0 initialisiert.

        //Nun werden die Werte der beiden EditText-Felder in die jeweiligen Variablen zugewiesen. (ACHTUNG als String)
        String first = num1.getText().toString();
        String second = num2.getText().toString();

        //Diese Überprüfungen dienen der Sicherheit, damit der User kein Feld leer lässt.
        if(TextUtils.isEmpty(first)){
            num1.setError("Das Dividenden-Feld darf nicht leer sein!");
            return;
        }

        if(TextUtils.isEmpty(second)){
            num2.setError("Das Divisor-Feld darf nicht leer sein!");
            return;
        }

        //jetzt werden die Strings in Integer umgewandelt und wieder in neue Variablen gespeichert.
        int dividend = Integer.parseInt(first);
        int divisor = Integer.parseInt(second);

        /*Überprüfung, ob der Divisor gleich 0 ist....um Fehlerquellen zu reduzieren. Es wird anschließend
        * das TextView Element rot gefärbt und anschließend eine Fehlernachricht ausgegegeben.*/
        if(divisor == 0){
            result.setTextColor(Color.RED);
            result.setText("Die Division " + dividend +"/0 ist nicht möglich");
            return;
        } else {
            //hier wird die Farbe wieder gewechselt
            result.setTextColor(Color.BLACK);
            /*anschließend die wirkliche Berechnung, die in einem TRY-CATCH Block eingehüllt wird um
            eventuelle Fehlerquellen abzufangen.*/
            try {
                newResult = dividend / divisor;
                //String für die Ausgabe
                String txt = "Das Ergebnis aus " + dividend + ":" + divisor + " lautet: ";
                //Das Result-TextView-Element wird wieder neu beschrieben
                result.setText(txt + newResult);
            } catch (Exception e){
                System.out.println("Error! Es hat etwas nicht geklappt: " + e.getMessage());
            }
        }
    }
}
