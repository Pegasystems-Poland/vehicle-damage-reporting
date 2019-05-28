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

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class LimitedCameraInputController extends CameraInputController {
    private Vector3 tmpV1 = new Vector3();
    private Vector3 tmpV2 = new Vector3();
    private UIUpdateCallback uiUpdateCallback;
    private float zoomInLimit;
    private float zoomOutLimit;
    private final boolean isStartOrientationVertical;
    private static final float VERTICAL_FILED_OF_VIEW = 90f;
    private static final float HORIZONTAL_FILED_OF_VIEW = 60f;
    private static final int PYTHAGORAS_TRIANGLE_5_12_13_A = 5;
    private static final int PYTHAGORAS_TRIANGLE_5_12_13_B = 12;
    private static final int PYTHAGORAS_TRIANGLE_5_12_13_C = 13;
    private static final float ZOOM_IN_LIMIT_FACTOR = 0.7f;
    private static final float ZOOM_OUT_LIMIT_FACTOR = 1.4f;
    private static final float ROTATE_UP_LIMIT = 0.5f;
    private static final float ROTATE_DOWN_LIMIT = 3.0f;

    public LimitedCameraInputController(final PerspectiveCamera camera, UIUpdateCallback uiUpdateCallback) {
        super(camera);
        this.uiUpdateCallback = uiUpdateCallback;
        super.pinchZoomFactor = 15f;
        this.isStartOrientationVertical = camera.viewportHeight > camera.viewportWidth;
    }

    @Override
    protected boolean pinchZoom(float amount) {
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
            return limitZoom(zoom, zoomInLimit);
        }
        return limitZoom(zoom, zoomOutLimit);
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
    protected boolean process(float deltaX, float deltaY, int button) {
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

    public void setUpPosition(BoundingBox boundingBox) {
        float distanceModelToCamera = Math.max(boundingBox.getDepth(), boundingBox.getWidth());
        float xPositionByPythagorasTriangle = distanceModelToCamera * PYTHAGORAS_TRIANGLE_5_12_13_B / PYTHAGORAS_TRIANGLE_5_12_13_C;
        float yPositionByPythagorasTriangle = distanceModelToCamera * PYTHAGORAS_TRIANGLE_5_12_13_A / PYTHAGORAS_TRIANGLE_5_12_13_C;
        camera.position.set(xPositionByPythagorasTriangle, yPositionByPythagorasTriangle, 0f);
        camera.lookAt(0f, 0f, 0f);

        zoomInLimit = distanceModelToCamera * ZOOM_IN_LIMIT_FACTOR;
        zoomOutLimit = distanceModelToCamera * ZOOM_OUT_LIMIT_FACTOR;

        camera.update();
    }

    public float getMatchingFieldOfView(int width, int height) {
        float factor = getFactor(width, height);
        if (isStartOrientationVertical) {
            if (isCurrentOrientationVertical(width, height)) {
                return VERTICAL_FILED_OF_VIEW;
            } else {
                return getChangedFiledOfView(factor, VERTICAL_FILED_OF_VIEW, 2);
            }
        } else {
            if (isCurrentOrientationVertical(width, height)) {
                return HORIZONTAL_FILED_OF_VIEW;
            } else {
                return getChangedFiledOfView(factor, HORIZONTAL_FILED_OF_VIEW, 3);
            }
        }
    }

    private float getFactor(int width, int height) {
        int min = Math.min(width, height);
        int max = Math.max(width, height);
        float div = (float) min / max;
        return 1f - Math.abs(div - 1f);
    }

    private float getChangedFiledOfView(float factor, float filedOfView, float ratio) {
        return (filedOfView / ratio) + factor * (filedOfView * (ratio - 1)  / ratio);
    }

    private boolean isCurrentOrientationVertical(int width, int height) {
        return width < height;
    }
}