package com.app.tipsplit;
import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    private TextView tipAmountfield,tipTotalfield,feildTotal;
    private EditText totalBillBox,numberOfPeopleBox;
    private Button goButton,clearButton;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewInitilization();
        onClicks();
    }
    public void viewInitilization(){
        tipAmountfield = findViewById(R.id.tipAmountfield);
        tipTotalfield = findViewById(R.id.tipTotalfield);
        feildTotal = findViewById(R.id.feildTotal);
        totalBillBox = findViewById(R.id.totalBillBox);
        numberOfPeopleBox = findViewById(R.id.numberOfPeopleBox);
        goButton = findViewById(R.id.goButton);
        clearButton = findViewById(R.id.clearButton);
        radioGroup = findViewById(R.id.radioGroup);
    }
   @SuppressLint({"DefaultLocale", "SetTextI18n"})
   public void onClicks(){
        clearButton.setOnClickListener(v -> {
            tipAmountfield.setText("");
            tipTotalfield.setText("");
            feildTotal.setText("");
            totalBillBox.setText("");
            numberOfPeopleBox.setText("");
            RadioButton r1 = findViewById(R.id.r5);
            RadioButton r2 = findViewById(R.id.r6);
            RadioButton r3 = findViewById(R.id.r7);
            RadioButton r4 = findViewById(R.id.r8);
            r1.setChecked(false);
            r2.setChecked(false);
            r3.setChecked(false);
            r4.setChecked(false);
        });
        goButton.setOnClickListener(v -> {
            if(TextUtils.isEmpty(totalBillBox.getText().toString())){
                totalBillBox.setError("Empty, Fill the bill");
                totalBillBox.requestFocus();
                return;
            }
            if(radioGroup.getCheckedRadioButtonId() == -1){
                Toast.makeText(getApplicationContext(),"Select The Tip",Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(numberOfPeopleBox.getText().toString())){
                numberOfPeopleBox.setError("Enter Number of people");
                numberOfPeopleBox.requestFocus();
                return;
            }
            double totalAmount = Double.parseDouble(tipTotalfield.getText().toString().substring(1));
            double perPerson = totalAmount / Double.parseDouble(numberOfPeopleBox.getText().toString());
            feildTotal.setText("$"+String.format("%.2f", perPerson));
        });
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(totalBillBox.getText().toString().isEmpty()){
                RadioButton r1 = findViewById(R.id.r5);
                RadioButton r2 = findViewById(R.id.r6);
                RadioButton r3 = findViewById(R.id.r7);
                RadioButton r4 = findViewById(R.id.r8);
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r4.setChecked(false);
            }
            else if(checkedId != -1){
                double amount = Double.parseDouble(totalBillBox.getText().toString());
                double tipAmount,totalAmount;
                if(checkedId == R.id.r5){
                    tipAmount = amount*0.12;
                    tipAmountfield.setText("$"+String.format("%.2f", tipAmount));
                    totalAmount = amount + tipAmount;
                    tipTotalfield.setText("$"+String.format("%.2f", totalAmount));
                }
                else if(checkedId == R.id.r6){
                    tipAmount = amount*0.15;
                    tipAmountfield.setText("$"+String.format("%.2f", tipAmount));
                    totalAmount = amount + tipAmount;
                    tipTotalfield.setText("$"+String.format("%.2f", totalAmount));
                }
                else if(checkedId == R.id.r7){
                    tipAmount = amount*0.18;
                    tipAmountfield.setText("$"+String.format("%.2f", tipAmount));
                    totalAmount = amount + tipAmount;
                    tipTotalfield.setText("$"+String.format("%.2f", totalAmount));
                }
                else if(checkedId == R.id.r8){
                    tipAmount = amount*0.20;
                    tipAmountfield.setText("$"+String.format("%.2f", tipAmount));
                    totalAmount = amount + tipAmount;
                    tipTotalfield.setText("$"+String.format("%.2f", totalAmount));
                }
            }
        });
   }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tipAmountfield.setText(savedInstanceState.getString("tipAmount"));
        tipTotalfield.setText(savedInstanceState.getString("totalTip"));
        feildTotal.setText(savedInstanceState.getString("totalPerPerson"));
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tipAmount", tipAmountfield.getText().toString());
        outState.putString("totalTip", tipTotalfield.getText().toString());
        outState.putString("totalPerPerson", feildTotal.getText().toString());
        super.onSaveInstanceState(outState);
    }

}