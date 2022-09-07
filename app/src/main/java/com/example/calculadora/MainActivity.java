package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    TextView textWindow;
    Boolean openParent = false;
    String prevResult = null;
    Boolean ans = false;
    List<String> operaciones = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textWindow = findViewById(R.id.textWindow);
    }

    public void Credits(View view){
        Toast.makeText(this,"Julio Garcia\nRenato Gutierrez\nHernan Lactayo\nAntonio Ortiz",Toast.LENGTH_LONG).show();
    }

    public void updateWindow(String item){
        operaciones.add(item);
        ans = false;
        String newWindow = TextUtils.join("", operaciones);
        textWindow.setText(newWindow);
    }

    public void btnOne(View v){ updateWindow("1"); }
    public void btnTwo(View v){ updateWindow("2"); }
    public void btnThree(View v){ updateWindow("3"); }
    public void btnFour(View v){ updateWindow("4"); }
    public void btnFive(View v){ updateWindow("5"); }
    public void btnSix(View v){ updateWindow("6"); }
    public void btnSeven(View v){ updateWindow("7"); }
    public void btnEight(View v){ updateWindow("8"); }
    public void btnNine(View v){ updateWindow("9"); }
    public void btnZero(View v){ updateWindow("0"); }
    public void btnDot(View v){ updateWindow("."); }
    public void btnFact(View v){ updateWindow("!"); }
    public void btnPoten(View v){ updateWindow("^"); }
    public void btnRaiz(View v){ updateWindow("√"); }
    public void btnResid(View v){ updateWindow("%"); }

    public void btnSin(View v){
        updateWindow("sin(");
        openParent = true;
    }
    public void btnCos(View v){
        updateWindow("cos(");
        openParent = true;
    }
    public void btnTan(View v){
        updateWindow("tan(");
        openParent = true;
    }

    public void btnReset(View v){
        operaciones.clear();
        textWindow.setText("0.0");
    }

    public void btnDel(View v){
        if (!operaciones.isEmpty()){
            operaciones.remove(operaciones.size()-1);

            if(operaciones.isEmpty()){
                textWindow.setText("0.0");
            }else{
                String newWindow = TextUtils.join("", operaciones);
                textWindow.setText(newWindow);
            }
        }
    }

    public void btnParent(View v){
        if(openParent){
            updateWindow(")");
            openParent = false;
        }else{
            updateWindow("(");
            openParent = true;
        }
    }

    public String Operacion(ArrayList op){
        try {
            String str = TextUtils.join("", op);
            str = str.replaceAll("x","*");
            Expression exp = new Expression(str);
            String result = String.valueOf(exp.calculate());
            return result;
        }catch(Exception e){
            return "ERROR MATH";
        }
    }

    public void btnEqual(View view){
        if(!operaciones.isEmpty()){
            String result = Operacion((ArrayList) operaciones);
            textWindow.setText(result);
            prevResult = result;
            ans = true;
            operaciones.clear();
        }
    }

    public void ButtonClick(View view){
        Button b = (Button) view;

        if(ans){ updateWindow(prevResult); }

        if(b.getId() == R.id.btnDivi){
            updateWindow("/");
        }else if(b.getId() == R.id.btnMulti){
            updateWindow("x");
        }else if(b.getId() == R.id.btnSum) {
            updateWindow("+");
        }else if(b.getId() == R.id.btnRest){
            updateWindow("-");
        }

        ans = false;
    }
}