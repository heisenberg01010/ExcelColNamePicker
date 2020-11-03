package com.example.excelcolnamepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import com.example.excelcolnamepicker.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    boolean checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupButton();
    }

    private void setupButton() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.numberPicker.setVisibility(View.VISIBLE);
                setupNumberPicker(binding.number1.getText().toString().trim(), binding.number2.getText().toString().trim());
            }
        });
    }

    private void setupNumberPicker(String min, String max) {
        binding.numberPicker.setMinValue(Integer.parseInt(min));
        binding.numberPicker.setMaxValue(Integer.parseInt(max));

        binding.numberPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return checked ? value + " - " + getColName(value) : getColName(value);
            }
        });
    }

    public void checkBox(View view) {
        checked = !checked;
    }


    private String getColName(int col) {
        {
            // To store result (Excel column name)
            StringBuilder columnName = new StringBuilder();

            while (col > 0) {
                // Find remainder
                int rem = col % 26;

                // If remainder is 0, then a
                // 'Z' must be there in output
                if (rem == 0) {
                    columnName.append("Z");
                    col = (col / 26) - 1;
                } else {                         // If remainder is non-zero
                    columnName.append((char) ((rem - 1) + 'A'));
                    col = col / 26;
                }
            }

            // Reverse the string and print result
            return columnName.reverse().toString();
        }
    }
}
