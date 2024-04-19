package com.example.radiobutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextNumber = findViewById(R.id.editTextNumber);
        final TextView lblOutput = findViewById(R.id.lblOutput);
        final Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // 獲取票數
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(editTextNumber.getText().toString());
                } catch (NumberFormatException e) {
                    lblOutput.setText("請輸入有效的購買張數");
                    return; // 直接返回，不處理以下代碼
                }

                if (quantity == 0) {
                    lblOutput.setText("請輸入購買張數");
                    return; // 如果輸入為0，不進行任何計算，直接顯示提示信息並返回
                }

                // 獲取性別
                RadioButton rdbBoy = findViewById(R.id.rdbBoy);
                RadioButton rdbGirl = findViewById(R.id.rdbGirl);
                String gender = "";
                if (rdbBoy.isChecked()) {
                    gender = getResources().getString(R.string.male);
                } else if (rdbGirl.isChecked()) {
                    gender = getResources().getString(R.string.female);
                }

                // 獲取票種和計算價格
                RadioGroup rgType = findViewById(R.id.rgType);
                int ticketPrice = 0;
                String ticketType = "";
                if (rgType.getCheckedRadioButtonId() == R.id.rdbAdult) {
                    ticketType = getResources().getString(R.string.regularticket);
                    ticketPrice = 500;
                } else if (rgType.getCheckedRadioButtonId() == R.id.rdbChild) {
                    ticketType = getResources().getString(R.string.childticket);
                    ticketPrice = 250;
                } else if (rgType.getCheckedRadioButtonId() == R.id.rdbStudent) {
                    ticketType = getResources().getString(R.string.studentticket);
                    ticketPrice = 400;
                }

                int totalCost = ticketPrice * quantity;

                // 顯示結果
                String outputStr = String.format("%s\n%s: %d 張\n金額 %d 元", gender, ticketType, quantity, totalCost);
                lblOutput.setText(outputStr);

            }
        });

        final Button confirmBtn = findViewById(R.id.confirmBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                intent.putExtra("ticketInfo", lblOutput.getText().toString()); // 將 lblOutput 的文字內容作為額外資訊傳遞
                startActivity(intent);
            }
        });
    }
}
