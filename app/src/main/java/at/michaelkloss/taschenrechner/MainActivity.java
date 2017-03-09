package at.michaelkloss.taschenrechner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText num1;
    EditText num2;
    TextView result;
    Button btnDividieren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDividieren = (Button) findViewById(R.id.button);
        btnDividieren.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        num1 = (EditText) findViewById(R.id.numberfieldDividend);
        num2 = (EditText) findViewById(R.id.numberfieldDivisor);
        result = (TextView) findViewById(R.id.result);
        int newResult = 0;

        String first = num1.getText().toString();
        String second = num2.getText().toString();

        if(TextUtils.isEmpty(first)){
            num1.setError("Das Dividenden-Feld darf nicht leer sein!");
            return;
        }

        if(TextUtils.isEmpty(second)){
            num2.setError("Das Divisor-Feld darf nicht leer sein!");
            return;
        }

        int dividend = Integer.parseInt(num1.getText().toString());
        int divisor = Integer.parseInt(num2.getText().toString());



        newResult = dividend / divisor;

        String txt = "Das Ergebnis aus " + dividend + "/" + divisor + " lautet: ";
        result.setText(txt + newResult);
    }
}
