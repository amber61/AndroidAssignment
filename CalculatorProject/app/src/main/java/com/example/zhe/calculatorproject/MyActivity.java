package com.example.zhe.calculatorproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.TextView;
import java.lang.String;

public class MyActivity extends AppCompatActivity {

    /** fields to store the first operand */
    public static String left = "0.0";
    /** fields to store the second operand */
    public static String right = "0.0";
    /** fields to store the current operator */
    public static String operator = new String();
    /** fields to decide whether is allowed to append number to left */
    public static boolean toLeft = true;
    /** fields to decide whether the current number displayed on screen is valid */
    public static boolean valid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        updateScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    public void button1(View view){enterNumber("1");updateScreen();}
    public void button2(View view){enterNumber("2");updateScreen();}
    public void button3(View view){enterNumber("3");updateScreen();}
    public void button4(View view){enterNumber("4");updateScreen();}
    public void button5(View view){enterNumber("5");updateScreen();}
    public void button6(View view){enterNumber("6");updateScreen();}
    public void button7(View view){enterNumber("7");updateScreen();}
    public void button8(View view){enterNumber("8");updateScreen();}
    public void button9(View view){enterNumber("9");updateScreen();}
    public void button0(View view){enterNumber("0");updateScreen();}
    public void buttonDot(View view){enterDot(".");updateScreen();}
    public void buttonAddition(View view){enterOperator("+");updateScreen();}
    public void buttonSub(View view){enterOperator("-");updateScreen();}
    public void buttonMulti(View view){enterOperator("*");updateScreen();}
    public void buttonDivision(View view){enterOperator("/");updateScreen();}
    public void buttonEquation(View view){enterEquation("=");updateScreen();}

    /** methods to update the number to be displayed on the screen*/
    public void updateScreen() {
        String toDisplay = null;
        if (toLeft) {toDisplay = left;}
        else {toDisplay = right;}
        TextView textView = (TextView)findViewById(R.id.result_message);
//        textView.setTextSize(50);
        textView.setText(toDisplay);
    }

    /**
     * method to operate when user input numbers
     * @param number
     */
    public void enterNumber(String number) {
        if(!valid){
            valid = true;
            if(toLeft) {left = number;}
            else {right = number;}
        } else {
            if (toLeft) {left = left + number;}
            else {right = right + number;}
        }
    }

    /**
     * method to operate when user inpput dot
     * @param dot
     */
    public void enterDot(String dot) {
        if(!valid){
            valid = true;
            if(toLeft) {left = dot;}
            else {right = dot;}
        } else {
            if (toLeft) {
                if(left.indexOf(".") != -1);
                else {left = left + dot;}
            }
            else {
                if(right.indexOf(".")!=-1);
                else {right = right + dot;}
            }
        }
    }

    /**
     * method to operate when user input any operator
     * @param operator
     */
    public void enterOperator(String operator) {
        valid = false;
        if(!toLeft) {
            left = Double.toString(evaluation());
            right = left;
        } else {
            if(left.indexOf(".") == -1 || left.indexOf(".") == left.length()-1) {
                left = String.format("%.1f", Double.parseDouble(left));
            }
            right = left;
            toLeft = false;
        }
        this.operator = operator;
    }

    /**
     * method to operate when user input equation
     * @param equalSign
     */
    public void enterEquation(String equalSign) {
        valid=false;
        left = Double.toString(evaluation());
        right = "0.0";
        toLeft=true;
    }

    /**
     * method to calculation
     * @return double
     */
    public double evaluation() {
        if(left.compareTo(".") == 0) {left = left + "0";}
        if(right.compareTo(".") == 0) {right = right + "0";}
        double l = Double.parseDouble(left);
        double r = Double.parseDouble(right);
        switch(operator) {
            case "+": l = l + r;
                break;
            case "-": l = l - r;
                break;
            case "*": l = l * r;
                break;
            case "/": l = l / r;
                break;
            default:
                break;
        }
        return l;
    }

}
