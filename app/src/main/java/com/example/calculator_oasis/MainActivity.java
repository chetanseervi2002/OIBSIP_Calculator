package com.example.calculator_oasis;

import android.os.Bundle;
import android.text.SpannableString;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    public static final String ERROR = "Error";
    private TextView input, output;

    MaterialButton buttonC, buttonPercent, button_para1;
    MaterialButton buttonDivide, buttonMulti, buttonAdd, buttonSub, buttonEqual;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonDot;

    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPICATION = '*';
    private final char DIVISION = '/';
    private final char EQUAL = '=';
    private final char EXTRA = '@';
    private final char MODULUS = '%';
    private  char ACTION ;
    private double x = Double.NaN;
    private double y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.input_text);
        output = findViewById(R.id.output_text);
        buttonC = findViewById(R.id.button_C);
        button_para1= findViewById(R.id.button_para1);
        buttonPercent= findViewById(R.id.button_percent);
        buttonDivide= findViewById(R.id.button_divide);
        button7= findViewById(R.id.button_7);
        button8= findViewById(R.id.button_8);
        button9= findViewById(R.id.button_9);
        buttonMulti= findViewById(R.id.button_X);
        button4= findViewById(R.id.button_4);
        button5= findViewById(R.id.button_5);
        button6= findViewById(R.id.button_6);
        buttonAdd= findViewById(R.id.button_add);
        button1= findViewById(R.id.button_1);
        button2= findViewById(R.id.button_2);
        button3= findViewById(R.id.button_3);
        buttonSub= findViewById(R.id.button_sub);
        button0= findViewById(R.id.button_0);
        buttonDot= findViewById(R.id.button_dot);
        buttonEqual= findViewById(R.id.button_equal);

        button0.setOnClickListener(v -> {
            ifErrorOutput();
            maxLength();
            input.setText(String.format("%s0", input.getText().toString()));
        });

        button1.setOnClickListener(v -> {
            ifErrorOutput();
            maxLength();
            input.setText(String.format("%s1", input.getText().toString()));
        });
        button2.setOnClickListener(v -> {
            ifErrorOutput();
            maxLength();
            input.setText(String.format("%s2", input.getText().toString()));
        });
        button3.setOnClickListener(v -> {
            ifErrorOutput();
            maxLength();
            input.setText(String.format("%s3", input.getText().toString()));
        });
        button4.setOnClickListener(v -> {
            ifErrorOutput();
            maxLength();
            input.setText(String.format("%s4", input.getText().toString()));
        });
        button5.setOnClickListener(v -> {
            ifErrorOutput();
            maxLength();
            input.setText(String.format("%s5", input.getText().toString()));
        });
        button6.setOnClickListener(v -> {
            ifErrorOutput();
            maxLength();
            input.setText(String.format("%s6", input.getText().toString()));
        });
        button7.setOnClickListener(v -> {
            ifErrorOutput();
            maxLength();
            input.setText(String.format("%s7", input.getText().toString()));
        });
        button8.setOnClickListener(v -> {
            ifErrorOutput();
            maxLength();
            input.setText(String.format("%s8", input.getText().toString()));
        });
        button9.setOnClickListener(v -> {
            ifErrorOutput();
            maxLength();
            input.setText(String.format("%s9", input.getText().toString()));
        });
        buttonDot.setOnClickListener(v -> {
            maxLength();
            input.setText(String.format("%s.", input.getText().toString()));
        });
        buttonPercent.setOnClickListener(v -> {
            if(input.getText().length()>0){
                ACTION = MODULUS;
                slove();
                if(!ifDecimal()) {
                    output.setText(String.format("%s%%", x));
                } else {
                    output.setText(String.format("%s%%", (int) x));
                }
                input.setText(null);
            }else output.setText(ERROR);
        });
        buttonAdd.setOnClickListener(v -> {
            if(input.getText().length()>0){
                ACTION = ADDITION;
                slove();
                if(!ifDecimal()){
                    output.setText(String.format("%s+", x));
                }else {
                    output.setText(String.format("%s+", (int)x));
                }
                input.setText(null);
            }else output.setText(ERROR);
        });
        buttonSub.setOnClickListener(v -> {
            if(input.getText().length()>0){
                ACTION = SUBTRACTION;
                slove();
                if(input.getText().length()>0){
                    if(!ifDecimal()){
                        output.setText(String.format("%s-", x));
                    }else output.setText(String.format("%s-", (int) x));
                }
                input.setText(null);
            }else output.setText(ERROR);
        });
        buttonMulti.setOnClickListener(v -> {
            if(input.getText().length()>0){
                ACTION = MULTIPICATION;
                slove();
                if(!ifDecimal()){
                    output.setText(String.format("%s*",x));
                }else output.setText(String.format("%s*", (int)x));
                input.setText(null);
            }else output.setText(String.format(ERROR));
        });
        buttonDivide.setOnClickListener(v -> {
            if(input.getText().length()>0){
                ACTION = DIVISION;
                slove();
                if(!ifDecimal()){
                    output.setText(String.format("%s/", (int)x));
                }else output.setText(String.format("%s/", x));
                input.setText(null);
            }else output.setText(ERROR);
        });
        button_para1.setOnClickListener(v -> {
            if(!output.getText().toString().isEmpty() || !input.getText().toString().isEmpty()) {
                x = Double.parseDouble(input.getText().toString());
                ACTION = EXTRA;
                output.setText(String.format("%s-", input.getText().toString()));
                input.setText("");
            }else output.setText(ERROR);
        });
        buttonEqual.setOnClickListener(v -> {
            if(input.getText().length()>0){
                slove();
                ACTION = EQUAL;
                if(!ifDecimal()) {
                    output.setText(/*output.getText().toString() + String.valueOf(y) + "=" + */ String.valueOf(x));
                }else output.setText(String.valueOf((int)x));
                input.setText(null);
            }else output.setText(ERROR);
        });
        buttonC.setOnClickListener(v -> {
            if(input.getText().length()>0){
                CharSequence name = input.getText().toString();
                input.setText(name.subSequence(0,name.length()-1));
            }else {
                x= Double.NaN;
                y = Double.NaN;
                input.setText("");
                output.setText("");
            }
        });
        buttonC.setOnLongClickListener(v -> {
            x= Double.NaN;
            y = Double.NaN;
            input.setText("");
            output.setText("");
            return true;
        });

    }
    void slove(){
        if(!Double.isNaN(x)) {
            if(output.getText().toString().charAt(0) == '-'){
                x = (-1) * x;
            }
            y = Double.parseDouble(input.getText().toString());

            switch (ACTION){
                case ADDITION :
                    x = x + y;
                    break;
                case SUBTRACTION:
                    x = x - y;
                    break;
                case MULTIPICATION:
                    x = x * y;
                    break;
                case DIVISION:
                    x = x / y;
                    break;
                case EXTRA:
                    x = (-1) * y;
                    break;
                case MODULUS:
                    x = x % y;
                    break;
                case EQUAL:
                    break;
            }
        }else {
            x = Double.parseDouble(input.getText().toString());
        }
    }
    void ifErrorOutput(){
        if(output.getText().toString().equals(ERROR)){
            output.setText("");
        }
    }

    //value if a double or not
    boolean ifDecimal() {
        return x == (int) y;
    }


    // reduce the text size if their is many digits
    void maxLength(){
        if (input.getText().toString().length()>10){
            input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }
    }
}