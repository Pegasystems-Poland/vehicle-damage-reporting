package com.pega.vehicledamagemodeling;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.JsonObject;

public class VehicleDamageModeling extends ApplicationAdapter {

    final private VehicleDamageReportCallback callback;

    SpriteBatch batch;
    Texture img;

    public VehicleDamageModeling(VehicleDamageReportCallback callback) {
        this.callback = callback;
    }

    public VehicleDamageModeling(JsonObject report, VehicleDamageReportCallback callback) {
        this(callback);
        // display given report
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("cat.jpg");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}