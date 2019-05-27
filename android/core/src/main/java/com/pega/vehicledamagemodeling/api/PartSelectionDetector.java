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

package com.pega.vehicledamagemodeling.api;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.pega.vehicledamagemodeling.UIUpdateCallback;

public class PartSelectionDetector extends InputAdapter {
    private Camera camera;
    private Array<ModelInstance> parts;
    private SelectionService selectionService;
    private UIUpdateCallback uiUpdateCallback;
    private static final Vector2 screenTouchedMarker = new Vector2();
    private static final Vector3 intersectionPoint = new Vector3();
    private static final float SENSITIVITY = 7f;

    public PartSelectionDetector(Camera camera, Array<ModelInstance> parts, SelectionService selectionService, UIUpdateCallback uiUpdateCallback) {
        this.camera = camera;
        this.parts = parts;
        this.selectionService = selectionService;
        this.uiUpdateCallback = uiUpdateCallback;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        screenTouchedMarker.set(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        if (screenTouchedMarker.epsilonEquals(screenX, screenY, SENSITIVITY)) {
            ModelInstance selectedPart = getSelectedPartId(screenX, screenY);
            if (selectedPart != null && !selectedPart.materials.get(0).id.equals("landing-gear")) {
                uiUpdateCallback.enableCheckButton();
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
