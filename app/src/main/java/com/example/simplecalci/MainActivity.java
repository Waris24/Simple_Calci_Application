package com.example.simplecalci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


import com.google.android.material.button.MaterialButton;

//import java.nio.channels.ScatteringByteChannel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView result, input;
    MaterialButton buttonc, buttonopen, buttonclose;
    MaterialButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0;
    MaterialButton buttonpt, alclr;
    MaterialButton buttondiv, buttonadd, buttonsub, buttonmul, equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result =findViewById(R.id.result);
        input = findViewById(R.id.input);
        assign_id(buttonc, R.id.button_c);
        assign_id(buttonopen, R.id.button_open);
        assign_id(buttonclose, R.id.button_close);
        assign_id(buttondiv, R.id.button_divide);
        assign_id(buttonmul, R.id.button_multi);
        assign_id(buttonadd, R.id.button_add);
        assign_id(buttonsub, R.id.button_diff);
        assign_id(equal, R.id.button_equal);
        assign_id(buttonpt, R.id.button_point);
        assign_id(alclr, R.id.button_allclear);
        assign_id(n0, R.id.button_0);
        assign_id(n1, R.id.button_1);
        assign_id(n2, R.id.button_2);
        assign_id(n3, R.id.button_3);
        assign_id(n4, R.id.button_4);
        assign_id(n5, R.id.button_5);
        assign_id(n6, R.id.button_6);
        assign_id(n7, R.id.button_7);
        assign_id(n8, R.id.button_8);
        assign_id(n9, R.id.button_9);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = input.getText().toString();
        input.setText(dataToCalculate);

        if(buttonText.equals("AC")) {
            input.setText("");
            result.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            input.setText(result.getText());
            return;
        }

        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        }
        else {
            dataToCalculate = dataToCalculate+buttonText;
        }

        input.setText(dataToCalculate);
        String final_res = get_result(dataToCalculate);
        if(!final_res.equals("Error")) {
            result.setText(final_res);

        }
    }


    String get_result(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String final_res = context.evaluateString(scriptable, data, "javascript", 1, null).toString();
            if (final_res.endsWith(".0")) {
                final_res = final_res.replace(".0", "");
            }
            return final_res;

        } catch (Exception e) {
            return "Error";
        }
    }
    void assign_id (MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

}