package com.example.justjava;
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    boolean ch1 ,ch2;

    public void submitOrder(View view) {

       // display(numberOfCofee);
       // int price = numberOfCofee*5;
       // String priceMessage= "Price : " + price  + "$" + "\n Thank You";

        //displayMessage(priceMessage);

     //   displayPrice(numberOfCofee*5);
        EditText name = (EditText) findViewById(R.id.name);

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        final String nom= name.getText().toString();
        ch1 = checkBox1.isChecked();
        ch2 = checkBox2.isChecked();


           int p= calculatePrice(numberOfCofee,ch1,ch2);
         String s = createOrderSummary(p,nom);
         //displayMessage(s);
        String subject = "Just Java order for " + nom;
        Intent intent1 = new Intent(Intent.ACTION_SENDTO);
        intent1.setData(Uri.parse("mailto:"));
      //  intent1.putExtra(Intent.EXTRA_EMAIL,adresse);
        intent1.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent1.putExtra(Intent.EXTRA_TEXT,s);
        if (intent1.resolveActivity(getPackageManager()) != null) {
            startActivity(intent1);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView;
        priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    int numberOfCofee=1;
    public void increment(View view){
        if (numberOfCofee < 100){
            numberOfCofee= numberOfCofee + 1;
            display(numberOfCofee);
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "you can't commande more than 100 cups of coffee!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void decrement(View view){
        if (numberOfCofee>1){
            numberOfCofee -=1;
            display(numberOfCofee);
        }else{
            Context context = getApplicationContext();
            CharSequence text = "Number of Coffee can't be bellow 1!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    private int calculatePrice(int quantity,boolean cream,boolean chocklate){
        int unit = 5;
        int price;
        if (cream){
            unit = unit +1;
        }
        if (chocklate){
            unit = unit +2;
        }
        price = unit*quantity;
        return price;
    }

    private String createOrderSummary(int price,String name){

       return " Name : " + name
               + "\n Quantity : "+ numberOfCofee
               + "\n Add Whipped Cream: " + ch1
               + "\n Add Chocklate : " + ch2
               + "\n Total $: "+ price
               + "\n Thank You \n ";
    }

}
