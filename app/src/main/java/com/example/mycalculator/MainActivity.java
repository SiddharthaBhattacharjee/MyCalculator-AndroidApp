package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;


public class MainActivity extends AppCompatActivity {
    private TextView OUT_VIEW;
    String s = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OUT_VIEW = findViewById(R.id.OUT_VIEW);
    }
    public void for_b1(View view){
        if(s.length()<= 25) {
            s = s + "1";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_b2(View view){
        if(s.length()<= 25) {
            s = s + "2";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_b3(View view){
        if(s.length()<= 25) {
            s = s + "3";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_b4(View view){
        if(s.length()<= 25) {
            s = s + "4";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_b5(View view){
        if(s.length()<= 25) {
            s = s + "5";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_b6(View view){
        if(s.length()<= 25) {
            s = s + "6";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_b7(View view){
        if(s.length()<= 25) {
            s = s + "7";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_b8(View view){
        if(s.length()<= 25) {
            s = s + "8";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_b9(View view){
        if(s.length()<= 25) {
            s = s + "9";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_b0(View view){
        if(s.length()<= 25) {
            s = s + "0";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_plus(View view){
        if(s.length()<= 25) {
            s = s + "+";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_minus(View view){
        if(s.length()<= 25) {
            s = s + "-";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_into(View view){
        if(s.length()<= 25) {
            s = s + "*";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_divide(View view){
        if(s.length()<= 25) {
            s = s + "/";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_open(View view){
        if(s.length()<= 25) {
            s = s + "(";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_close(View view){
        if(s.length()<= 25) {
            s = s + ")";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }
    public void for_back(View view){
        if(!s.equals(" ")){
            String substring = s.substring(0, s.length() - 1);
            s = substring;
            OUT_VIEW.setText(s);
        }
    }
    public void for_c(View view){
        s = " ";
        OUT_VIEW.setText(s);
    }

    public double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    public int countocc(String a,char b){
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b) {
                count++;
            }
        }
        return count;
    }

    public void equals(View view){
        if(s.equals(" 404")){
            Toast.makeText(MainActivity.this, "@SiddharthaBhattacharjee : Hi! and sorry this result couldn't be found : )", Toast.LENGTH_SHORT).show();
        }
        if(s.equals(" 2345")){
            Toast.makeText(MainActivity.this, "@SiddharthaBhattacharjee : Hi! please follow me on twitter @Siddhartha_2345", Toast.LENGTH_SHORT).show();
        }
        try{
            double x = eval(s);
            s = " "+x;
            OUT_VIEW.setText(s);
        }
        catch (Exception e){Toast.makeText(MainActivity.this, "Invalid Expression !", Toast.LENGTH_SHORT).show();}
    }

    public void for_decimal(View view){
        if(s.length()<= 25) {
            s = s + ".";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }

    public void for_power(View view){
        if(s.length()<= 25) {
            s = s + "^";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }

    public void for_sqrt(View view){
        if(s.length()<= 20) {
            s = s + "sqrt(";
            OUT_VIEW.setText(s);
        }
        else{
            Toast.makeText(MainActivity.this,"Too long Input",Toast.LENGTH_SHORT).show();
        }
    }

}