package com.cornez.shippingcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



public class MyActivity extends Activity {
    //DATA MODEL FOR SHIP ITEM
    private ShipItem shipItem;

    //VIEW OBJECTS FOR LAYOUT UI REFERENCE
    private EditText weightET;
    private TextView baseCostTV;
    private TextView addedCostTV;
    private TextView totalCostTV;
    // STAN'S ADDITIONS
    private TextView lengthET;
    private TextView widthET;
    private TextView heightET;
    private RadioGroup shippingOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_2);

        //CREATE THE DATA MODEL FOR STORING THE ITEM TO BE SHIPPED
        shipItem = new ShipItem();

        //TASK 3: ESTABLISH THE REFERENCES TO INPUT WEIGHT ELEMENT
        weightET = (EditText) findViewById(R.id.editText1);
        // STAN'S ADDITIONS
        lengthET = (EditText) findViewById(R.id.editLengthText);
        widthET = (EditText) findViewById(R.id.editWidthText);
        heightET = (EditText) findViewById(R.id.editHeightText);
        // radio buttons
        shippingOptions = (RadioGroup) findViewById(R.id.shippingOptions);

        //TASK 3: ESTABLISH THE REFERENCES TO OUTPUT ELEMENTS
        baseCostTV = (TextView) findViewById(R.id.textView4);
        addedCostTV = (TextView) findViewById(R.id.textView6);
        totalCostTV = (TextView) findViewById(R.id.textView8);

        //TASK 4: REGISTER THE LISTENER EVENT FOR WEIGHT INPUT
        weightET.addTextChangedListener(weightTextWatcher);
        // STAN'S ADDITIONS
        lengthET.addTextChangedListener(lengthTextWatcher);
        widthET.addTextChangedListener(widthTextWatcher);
        heightET.addTextChangedListener(heightTextWatcher);
        //radio buttons
        shippingOptions.setOnCheckedChangeListener(RadioListener);
    }

    private TextWatcher weightTextWatcher = new TextWatcher() {
        //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
        //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

        public void onTextChanged(CharSequence s,
                                  int start, int before, int count){
            //CATCH AN EXCEPTION WHEN THE INPUT IS NOT A NUMBER
            try {
                shipItem.setWeight((int) Double.parseDouble(s.toString()));
            }catch (NumberFormatException e){
                shipItem.setWeight(0);
            }
            displayShipping();
        }
        public void afterTextChanged(Editable s) {}
        public void beforeTextChanged(CharSequence s,
                                      int start, int count, int after){}
    };

    private TextWatcher lengthTextWatcher = new TextWatcher() {
        //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
        //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

        public void onTextChanged(CharSequence s,
                                  int start, int before, int count){
            //CATCH AN EXCEPTION WHEN THE INPUT IS NOT A NUMBER
            try {
                shipItem.setLength((int) Double.parseDouble(s.toString()));
            }catch (NumberFormatException e){
                shipItem.setLength(0);
            }
            displayShipping();
        }
        public void afterTextChanged(Editable s) {}
        public void beforeTextChanged(CharSequence s,
                                      int start, int count, int after){}
    };

    private TextWatcher widthTextWatcher = new TextWatcher() {
        //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
        //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

        public void onTextChanged(CharSequence s,
                                  int start, int before, int count){
            //CATCH AN EXCEPTION WHEN THE INPUT IS NOT A NUMBER
            try {
                shipItem.setWidth((int) Double.parseDouble(s.toString()));
            }catch (NumberFormatException e){
                shipItem.setWidth(0);
            }
            displayShipping();
        }
        public void afterTextChanged(Editable s) {}
        public void beforeTextChanged(CharSequence s,
                                      int start, int count, int after){}
    };

    private TextWatcher heightTextWatcher = new TextWatcher() {
        //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
        //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

        public void onTextChanged(CharSequence s,
                                  int start, int before, int count){
            //CATCH AN EXCEPTION WHEN THE INPUT IS NOT A NUMBER
            try {
                shipItem.setHeight((int) Double.parseDouble(s.toString()));
            }catch (NumberFormatException e){
                shipItem.setHeight(0);
            }
            displayShipping();
        }
        public void afterTextChanged(Editable s) {}
        public void beforeTextChanged(CharSequence s,
                                      int start, int count, int after){}
    };

    private RadioGroup.OnCheckedChangeListener RadioListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            RadioButton button = (RadioButton) radioGroup.findViewById(i);
            if(null != button
                    && i > -1 ) {
                // get the selected text
                String buttonValue = button.getText().toString();
                shipItem.setShipping(buttonValue);
                displayShipping();
            }
        }
    };


    private void displayShipping() {
        //DISPLAY THE BASE COST, ADDED COST, AND TOTAL COST
        try {
            String temp = "$" + String.format("%.02f", shipItem.getBaseCost());
            baseCostTV.setText(temp);
            temp ="$" + String.format("%.02f", shipItem.getAddedCost());
            addedCostTV.setText(temp);
            temp = "$" + String.format("%.02f", shipItem.getTotalCost());
            totalCostTV.setText(temp);
        }
        catch (Exception e) {
            AlertDialog.Builder err = new AlertDialog.Builder(this);
            err.setMessage(e.toString());
            err.create();
            err.show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
