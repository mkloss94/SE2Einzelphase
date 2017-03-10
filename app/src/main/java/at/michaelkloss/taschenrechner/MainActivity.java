package at.michaelkloss.taschenrechner;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

        /*der InputMethodManager wird dafür verwendet, damit das virtuelle Keyboard am Handy nach Buttonklick
        * verschwindet, damit man nicht extra den Drilldown Button klicken muss. Dazu wird ein Objekt von der Klasse
        * InputMethodManager erzeugt und diesem Objekt von der Methode getSystemService der Kontext von der Konstante
        * zugewiesen.*/
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        /*Es wird hier auf die Methode hideSoftInputFromWindow zugegriffen. Wichtig ist, dass man hier überprüft, ob
        * der getCurrentFocus gleich null ist, dann ergibt es null, sonst wird das Keyboard ausgeblendet. Dies muss
        * deswegen gemacht werden, da es ja sein kann, dass das Keyboard schon zugeklappt ist und man es wieder zuklappen
        * will, dann bekommt man eine NullPointerException. Die Konstante zum Schluss in der Parameterliste bedeutet, dass
         * das Keyboard nicht für immer jetzt versteckt ist, was aber selbstredend ist.*/
        inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        //Nun wird der Dividend und der Divisor sowie das Ergebnis geholt.
        num1 = (EditText) findViewById(R.id.numberfieldDividend);
        num2 = (EditText) findViewById(R.id.numberfieldDivisor);
        result = (TextView) findViewById(R.id.result);
        double newResult = 0.0; //wird sicherheitshalber mit 0 initialisiert.

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

        String point = ".";

        //Diese Überprüfungen dienen erneut der Sicherheit, damit der Benutzer nicht nur einen Punkt in das Nummernfeld eingeben kann.
        if(TextUtils.equals(first, point)){
            num1.setError("Das Dividenden-Feld muss zusätzlich noch eine Zahl enthalten!");
            return;
        }

        if(TextUtils.equals(second, point)){
            num2.setError("Das Divisor-Feld muss zusätzlich noch eine Zahl enthalten!");
            return;
        }

        //jetzt werden die Strings in Double umgewandelt und wieder in neue Variablen gespeichert.
        double dividend = Double.parseDouble(first);
        double divisor = Double.parseDouble(second);

        /*Überprüfung, ob der Divisor gleich 0 ist....um Fehlerquellen zu reduzieren. Es wird anschließend
        * das TextView Element rot gefärbt und anschließend eine Fehlernachricht ausgegegeben.*/
        if(divisor == 0.0){
            result.setTextColor(Color.RED);
            result.setText("Die Division " + dividend + "/" + divisor + " ist nicht möglich!");
            return;
        } else {
            //hier wird die Farbe wieder gewechselt
            result.setTextColor(Color.BLACK);
            /*anschließend die wirkliche Berechnung, die in einem TRY-CATCH Block eingehüllt wird um
            eventuelle Fehlerquellen abzufangen.*/
            try {
                newResult = dividend / divisor;
                //String für die Ausgabe
                String txt = "Das Ergebnis aus " + dividend + "/" + divisor + " lautet: ";
                //Das Result-TextView-Element wird wieder neu beschrieben
                result.setText(txt + newResult);
            } catch (Exception e){
                System.out.println("Error! Es hat etwas nicht geklappt: " + e.getMessage());
            }
        }
    }
}
