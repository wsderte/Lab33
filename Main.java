package com.gmail.Genetic.embeded_lab_3_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickCalculate(View view) {

        EditText aText = findViewById(R.id.aText);
        EditText bText = findViewById(R.id.bText);
        EditText cText = findViewById(R.id.cText);
        EditText dText = findViewById(R.id.dText);
        EditText yText = findViewById(R.id.yText);

        TextView resultText = findViewById(R.id.resultText);

        EditText[] inputs = new EditText[] {aText, bText, cText, dText, yText};

        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].getText().toString().trim().length() == 0) {
                resultText.setText("Введіть данні");
                return;
            }
        }
        int y = Integer.parseInt(yText.getText().toString());
        int a = Integer.parseInt(aText.getText().toString());
        int b = Integer.parseInt(bText.getText().toString());
        int c = Integer.parseInt(cText.getText().toString());
        int d = Integer.parseInt(dText.getText().toString());
        int[] params = new int[] {a, b, c, d, y};

        for (int i = 0; i < 4; i++) {
            if (params[i] > params[4]) {
                resultText.setText("Введіть інший y");
                return;
            }
        }

        for (int i = 0; i < 4; i++) {
            String res = "0";
            if (params[i] == params[4]) {
                resultText.setText(res);
                return;
            }
        }

        GenA example = new GenA(a, b, c, d, y);
        String result = example.algorithm();
        resultText.setText(result);
    }
}
