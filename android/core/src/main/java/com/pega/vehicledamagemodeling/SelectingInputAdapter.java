package com.pega.vehicledamagemodeling;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;

public class SelectingInputAdapter extends InputAdapter{
    private Camera camera;
    private Array<ModelInstance> instances;

    public SelectingInputAdapter(Camera camera, Array<ModelInstance> instances) {
        this.camera = camera;
        this.instances = instances;
    }

    private Vector2 screenTouchedDown = new Vector2();

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        screenTouchedDown.set(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        if (screenTouchedDown.epsilonEquals(screenX, screenY)) {
            getObject(screenX, screenY);
        }
        return false;
    }


    private int getObject(int screenX, int screenY) {
        int result = -1;
        float distance = Float.MAX_VALUE;

        Ray ray = camera.getPickRay(screenX, screenY);

        BoundingBox boundingBox = new BoundingBox();

        Vector3 vector2 = new Vector3();

        for (int i = 0; i < instances.size; i++) {

            ModelInstance instance = instances.get(i);

            boundingBox = instance.calculateBoundingBox(boundingBox);


            if (Intersector.intersectRayBounds(ray, boundingBox, vector2)) {

                float distance2 = vector2.dst(ray.origin);

                if (distance2 < distance) {
                    distance = distance2;
                    result = i;
                }
            }
        }

        if (result > -1) {
            ModelInstance instance = instances.get(result);
            setColor(instance, Color.RED);
        }

        return 1;
    }

    private void setColor(ModelInstance instance, Color color) {
        instance.materials.get(0).set(ColorAttribute.createDiffuse(color));
    }

}
