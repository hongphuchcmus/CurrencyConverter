package com.example.currencyconverter;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //USA money format (12 digits, 2 decimals)
    DecimalFormat usaDf = new DecimalFormat("###,###,###,###.##");

    // naive currency converter (USD to Euros & Colones)
    private final double EURO2USD = 1.35;
    private final char EUROSYM = '\u20AC';
    private final double COLON2USD = 0.0019;
    private final char COLONSYM = '\u20A1';
    // GUI widgets
    Button btnConvert, btnClear;
    EditText txtUSDollars, txtEuros, txtColones;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // bind local controls to GUI widgets
        txtUSDollars = (EditText) findViewById(R.id.txtUSDollars);
        // make ‘Euros’ box not-editable (no user input)
        txtEuros = (EditText) findViewById(R.id.txtEuros);
        txtEuros.setInputType(EditorInfo.TYPE_NULL);
        // No user input. See layout: android:inputType='none'
        txtColones = (EditText) findViewById(R.id.txtColones);
        // attach click behavior to buttons
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            // clear the text boxes
            @Override
            public void onClick(View v) {
                txtColones.setText("");
                txtEuros.setText("");
                txtUSDollars.setText("");
            }
        });// setOnClick…

        // do the conversion from USD to Euros and Colones
        btnConvert = (Button) findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String usdStr = txtUSDollars.getText().toString();
                    double usd = Double.parseDouble(usdStr);
                    String euros = EUROSYM + String.valueOf(usaDf.format(usd / EURO2USD));
                    String colones = COLONSYM + String.valueOf(usaDf.format(usd / COLON2USD));
                    txtEuros.setText(euros);
                    txtColones.setText(colones);
                }
                catch (NumberFormatException e) { /*ignore errors*/ }
            }
        });// setOnClick...
    }
}
