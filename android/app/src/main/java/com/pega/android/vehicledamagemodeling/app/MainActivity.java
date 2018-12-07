package com.pega.android.vehicledamagemodeling.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pega.android.vehicledamagemodeling.VehicleDamageModelingActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        textView = findViewById(R.id.result_text_view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (VehicleDamageModelingActivity.REQUEST_CODE): {
                if (resultCode == Activity.RESULT_OK) {
                    String returnValue = data.getStringExtra(VehicleDamageModelingActivity.REPORT_EXTRA);
                    textView.setText(returnValue);
                }
            }
        }
    }

    public void showVehicleModel(View view) {
        Intent intent = new Intent(this, VehicleDamageModelingActivity.class);
        String report = textView.getText().toString();
        if (!report.isEmpty()) {
            intent.putExtra(VehicleDamageModelingActivity.REPORT_EXTRA, report);
        }
        startActivityForResult(intent, VehicleDamageModelingActivity.REQUEST_CODE);
    }


}
