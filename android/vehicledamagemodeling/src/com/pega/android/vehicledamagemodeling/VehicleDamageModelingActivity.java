package com.pega.android.vehicledamagemodeling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pega.vehicledamagemodeling.VehicleDamageModeling;
import com.pega.vehicledamagemodeling.VehicleDamageReportCallback;

public class VehicleDamageModelingActivity extends AndroidApplication {

    public static final int REQUEST_CODE = 123;
    public static final String REPORT_EXTRA = "vehicle_damage_report_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_damage_modeling_activity);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = false;
        config.useCompass = false;
        config.disableAudio = true;
        ViewGroup viewGroup = findViewById(R.id.vehicle_damage_modeling_content);

        final VehicleDamageReportCallback callback = new VehicleDamageReportCallback() {
            @Override
            public void onFinished(JsonObject result) {
                String report = new GsonBuilder().setPrettyPrinting().create().toJson(result);
                Intent intent = new Intent();
                intent.putExtra(REPORT_EXTRA, report);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        };

        String report = getIntent().getStringExtra(REPORT_EXTRA);
        VehicleDamageModeling vehicleDamageModeling;
        if (report == null || report.isEmpty()) {
            vehicleDamageModeling = new VehicleDamageModeling(callback);
        } else {
            vehicleDamageModeling = new VehicleDamageModeling(
                    new JsonParser().parse(report).getAsJsonObject(), callback);
        }
        viewGroup.addView(initializeForView(vehicleDamageModeling, config));
    }
}
