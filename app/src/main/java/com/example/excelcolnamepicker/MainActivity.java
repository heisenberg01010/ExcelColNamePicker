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
        StringBuilder builder = new StringBuilder();
        while (col > 0) {
            int remainder = col % 26;
            if (remainder == 0) {
                builder.append('Z');
                col = (col / 26) - 1;
            } else {
                builder.append((char) (64 + remainder));
                col = col / 26;
            }
        }

        return builder.reverse().toString();
    }

}
