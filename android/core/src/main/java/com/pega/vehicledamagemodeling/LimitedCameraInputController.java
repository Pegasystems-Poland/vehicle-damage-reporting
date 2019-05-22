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

package com.pega.vehicledamagemodeling;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class LimitedCameraInputController extends CameraInputController {
    private Vector3 tmpV1 = new Vector3();
    private Vector3 tmpV2 = new Vector3();
    private UIUpdateCallback uiUpdateCallback;

    private static final float ZOOM_IN_LIMIT = 0f;
    private static final float ZOOM_OUT_LIMIT = 200f;
    private static final float ROTATE_DOWN_LIMIT = 3.0f;
    private static final float ROTATE_UP_LIMIT = 0.5f;
    public final boolean isStartOrientationVertical;

    public LimitedCameraInputController(final PerspectiveCamera camera, UIUpdateCallback uiUpdateCallback) {
        super(camera);
        this.uiUpdateCallback = uiUpdateCallback;
        super.pinchZoomFactor = 15f;
        this.isStartOrientationVertical = camera.viewportHeight > camera.viewportWidth;
    }

    @Override
    protected boolean pinchZoom (float amount) {
        return zoom(pinchZoomFactor * amount);
    }

    @Override
    public boolean zoom(float amount) {
        if (amount == 0f) {
            return false;
        }

        camera.translate(createZoom(amount));
        camera.update();
        return true;
    }

    private Vector3 createZoom(float amount) {
        Vector3 zoom = tmpV1.set(camera.direction).scl(amount);

        if (isZoomIn(amount)) {
            return limitZoom (zoom, ZOOM_IN_LIMIT);
        }
        return limitZoom (zoom, ZOOM_OUT_LIMIT);
    }

    private boolean isZoomIn(float amount) {
        return amount > 0f;
    }

    private Vector3 limitZoom(Vector3 zoom, float zoomLimit) {
        Vector3 limitedZoom = tmpV2.set(camera.direction)
                .scl(-zoomLimit / camera.direction.len())
                .sub(camera.position);

        if (zoom.len() >= limitedZoom.len()) {
            return limitedZoom;
        } else {
            return zoom;
        }
    }

    @Override
    protected boolean process (float deltaX, float deltaY, int button) {
        uiUpdateCallback.hideRotationPrompt();
        float deltaYRotate = deltaY * rotateAngle;
        tmpV1.set(camera.direction)
                .crs(camera.up)
                .y = 0f;
        tmpV1.nor();

        if (isCrossingDownLimit(tmpV1, deltaYRotate) || isCrossingUpLimit(tmpV1, deltaYRotate)) {
            return false;
        }

        camera.rotateAround(target, tmpV1, deltaYRotate);
        camera.rotateAround(target, Vector3.Y, deltaX * -rotateAngle);

        if (autoUpdate) {
            camera.update();
        }

        return true;
    }

    private boolean isCrossingDownLimit(Vector3 rotateVector, float deltaYRotate) {
        tmpV2.set(target);
        tmpV2.sub(camera.position);
        tmpV2.rotate(rotateVector, deltaYRotate);
        return tmpV2.y > ROTATE_DOWN_LIMIT;
    }

    private boolean isCrossingUpLimit(Vector3 rotateVector, float deltaYRotate) {
        tmpV2.set(camera.up);
        tmpV2.rotate(rotateVector, deltaYRotate);
        return tmpV2.y < ROTATE_UP_LIMIT;
    }

    public void setUpPosition(BoundingBox boundingBox, int width, int height) {
        float distanceModelToCamera = Math.max(boundingBox.getDepth(), boundingBox.getWidth());
        float xPositionByPythagorasTriangle = distanceModelToCamera * 12 / 13;
        float yPositionByPythagorasTriangle = distanceModelToCamera * 5 / 13;
        camera.position.set(xPositionByPythagorasTriangle, yPositionByPythagorasTriangle,0f);
        camera.lookAt(0f,0f, 0f);

        // TODO: 22/05/2019 ustawic limity IN i OUT w relacji do distanceModelToCamera

        camera.update();
    }

    public float getMatchingFiledOfView(int width, int height) {
        int min = Math.min(width, height);
        int max = Math.max(width, height);
        float div = (float) min / max;
        float abs = 1f - Math.abs(div - 1f);
        if (isStartOrientationVertical) {
            if (isCurrentOrientationVertical(width, height)) {
                return 90;
            } else {
                return 40 + abs * 50;
            }
        } else {
            if (isCurrentOrientationVertical(width, height)) {
                return 60;
            } else {
                return abs * 90;
            }
        }
    }

    private boolean isCurrentOrientationVertical(int width, int height) {
        return width < height;
    }
}