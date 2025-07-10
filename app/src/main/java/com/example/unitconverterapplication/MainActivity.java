package com.example.unitconverterapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromSpinner, toSpinner;
    Button convertBtn;
    TextView resultText;

    String[] units = {"Meters", "Kilometers", "Miles", "Feet"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        convertBtn = findViewById(R.id.convertBtn);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = inputValue.getText().toString();
                if (input.isEmpty()) {
                    resultText.setText("Please enter a value.");
                    return;
                }

                double value = Double.parseDouble(input);
                String fromUnit = fromSpinner.getSelectedItem().toString();
                String toUnit = toSpinner.getSelectedItem().toString();
                double result = convert(value, fromUnit, toUnit);

                resultText.setText(String.format("%.2f %s", result, toUnit));
            }
        });
    }

    private double convert(double value, String from, String to) {
        // Convert input to meters
        double meters;
        switch (from) {
            case "Kilometers": meters = value * 1000; break;
            case "Miles": meters = value * 1609.34; break;
            case "Feet": meters = value * 0.3048; break;
            default: meters = value;
        }

        // Convert meters to target unit
        switch (to) {
            case "Kilometers": return meters / 1000;
            case "Miles": return meters / 1609.34;
            case "Feet": return meters / 0.3048;
            default: return meters;
        }
    }
}
