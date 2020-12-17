package com.nervi.tobinary;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private boolean allowWriteDecimalsNumbers = false;
    private TextView tvBinary;
    private TextView tvDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBinary = findViewById(R.id.textViewBinary);
        tvDecimal = findViewById(R.id.textViewDecimal);
    }

    public void toBinary(View v) {
        allowWriteDecimalsNumbers = true;
        v.setBackgroundColor(Color.RED);
        findViewById(R.id.toDecimal).setBackgroundColor(Color.parseColor("#FF6200EE"));
    }

    public void toDecimal(View v) {
        allowWriteDecimalsNumbers = false;
        v.setBackgroundColor(Color.RED);
        findViewById(R.id.toBinary).setBackgroundColor(Color.parseColor("#FF6200EE"));
    }

    public void clickedNumber(View v) {
        String numberStr = ((Button) v).getText().toString();
        if (!allowWriteDecimalsNumbers) {
            String number = tvBinary.getText() + numberStr;
            if (!numberStr.matches("[2-9]")) {

                tvBinary.setText(number);
                getDecimal(number);
            }
        }

        if (allowWriteDecimalsNumbers) {
            String number = tvDecimal.getText() + numberStr;
            tvDecimal.setText(number);
            getBinary(number);
        }
    }

    private void getBinary(String decimalNumber) {
        int decimal = Integer.parseInt(decimalNumber);
        ArrayList<Integer> binaryResult = new ArrayList<>();
        do {
            binaryResult.add(decimal % 2);
            decimal = (int) (decimal / 2);
        } while (decimal != 0);

        String result = "";
        for (int i = binaryResult.size() - 1; i >= 0; i--) {
            result += binaryResult.get(i);
        }
        tvBinary.setText(result);
    }

    private void getDecimal(String binaryNumber) {
        String[] binary = binaryNumber.split("");
        int result = 0;
        for (int i = binary.length - 1; i >= 0; i--) {
            result += Integer.parseInt(binary[i]) * Math.pow(2, Math.abs(i - binary.length - 1));
        }
        tvDecimal.setText(String.valueOf(result));
    }
}