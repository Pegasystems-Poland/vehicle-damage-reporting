package com.pega.vehicledamagemodeling;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.pega.vehicledamagemodeling.api.SelectionService;

public class PartSelectedDetector extends InputAdapter{
    private Camera camera;
    private Array<ModelInstance> parts;
    private SelectionService selectionService;

    private static final Vector2 screenTouchedMarker = new Vector2();
    private static final Vector3 intersectionPoint = new Vector3();

    public PartSelectedDetector(Camera camera, Array<ModelInstance> parts, SelectionService selectionService) {
        this.camera = camera;
        this.parts = parts;
        this.selectionService = selectionService;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        screenTouchedMarker.set(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        if (screenTouchedMarker.epsilonEquals(screenX, screenY)) {
            ModelInstance selectedPart = getSelectedPartId(screenX, screenY);

            if (selectedPart != null) {
                selectionService.setSelectedPart(selectedPart);
            }
        }
        return false;
    }


    private ModelInstance getSelectedPartId(int screenX, int screenY) {
        ModelInstance closestHitPart = null;
        float minDistance = Float.MAX_VALUE;

        Ray ray = camera.getPickRay(screenX, screenY);

        BoundingBox boundingBox = new BoundingBox();

        for (ModelInstance part: parts) {

            boundingBox = part.calculateBoundingBox(boundingBox);

            if (Intersector.intersectRayBounds(ray, boundingBox, intersectionPoint)) {

                float distanceCurrentInstance = intersectionPoint.dst(ray.origin);

                if (distanceCurrentInstance < minDistance) {
                    minDistance = distanceCurrentInstance;
                    closestHitPart = part;
                }
            }
        }
        return closestHitPart;
    }
}
