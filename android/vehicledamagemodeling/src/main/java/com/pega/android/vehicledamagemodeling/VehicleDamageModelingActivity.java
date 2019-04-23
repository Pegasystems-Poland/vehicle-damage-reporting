/*
 * Copyright (c) 2019 Flying Vehicle Monster team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pega.android.vehicledamagemodeling;

import android.content.Intent;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pega.vehicledamagemodeling.VehicleDamageModeling;
import com.pega.vehicledamagemodeling.VehicleDamageReportCallback;

import static com.pega.android.vehicledamagemodeling.R.id.vehicle_damage_modeling_content;
import static com.pega.android.vehicledamagemodeling.R.layout.vehicle_damage_modeling_activity;

public class VehicleDamageModelingActivity extends AndroidApplication {
    public static VehicleDamageModeling vehicleDamageModeling;

    public static final int REQUEST_CODE = 123;
    public static final String REPORT_EXTRA = "vehicle_damage_report_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vehicle_damage_modeling_activity);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = false;
        config.useCompass = false;
        config.disableAudio = true;
        ViewGroup viewGroup = findViewById(vehicle_damage_modeling_content);

        final VehicleDamageReportCallback callback = new VehicleDamageReportCallback() {
            @Override
            public void onFinished(JsonObject result) {
                String report = new GsonBuilder().setPrettyPrinting().create().toJson(result);
                Intent intent = new Intent();
                intent.putExtra(REPORT_EXTRA, report);
                setResult(RESULT_OK, intent);
                finish();
            }
        };

        String report = getIntent().getStringExtra(REPORT_EXTRA);
        if (report == null || report.isEmpty()) {
            vehicleDamageModeling = new VehicleDamageModeling(callback);
        } else {
            vehicleDamageModeling = new VehicleDamageModeling(
                    new JsonParser().parse(report).getAsJsonObject(), callback);
        }
        viewGroup.addView(initializeForView(vehicleDamageModeling, config));

    }

    public void crossButtonOnClick(View view) {
        vehicleDamageModeling.end();
    }

    public void promptIconOnClick(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    public void chechButtonOnClick(View view){
        vehicleDamageModeling.endWithModification();
    }
}
