package com.pega.vehicledamagemodeling;

import com.google.gson.JsonObject;

public interface VehicleDamageReportCallback {

    void onFinished(JsonObject result);
}
