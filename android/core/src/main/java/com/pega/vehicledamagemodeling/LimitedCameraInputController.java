package com.pega.vehicledamagemodeling;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;

public class LimitedCameraInputController extends CameraInputController {
    private static final float ZOOM_IN_LIMIT = 18f;
    private static final float ZOOM_OUT_LIMIT = 40f;
    private static final float ROTATE_DOWN_LIMIT = 3.0f;
    private static final float ROTATE_UP_LIMIT = 0.5f;
    private Vector3 tmpV1 = new Vector3();
    private Vector3 tmpV2 = new Vector3();

    public LimitedCameraInputController(final Camera camera) {
        super(camera);
        super.pinchZoomFactor = 15f;
    }

    @Override
    protected boolean pinchZoom (float amount) {
        return zoom(pinchZoomFactor * amount);
    }

    @Override
    public boolean zoom(float amount) {
        if (amount == 0f) return false;

        camera.translate(createZoom(amount));
        camera.update();
        return true;
    }

    private Vector3 createZoom(float amount) {
        tmpV1.set(camera.direction).scl(amount);

        if (isZoomIn(amount)) {
            return limitZoom (tmpV1, ZOOM_IN_LIMIT);
        }
        return limitZoom (tmpV1, ZOOM_OUT_LIMIT);
    }

    private boolean isZoomIn(float amount) {
        return amount > 0f;
    }

    private Vector3 limitZoom(Vector3 zoom, float zoomLimit) {
        tmpV2.set(camera.direction)
                .scl(-zoomLimit / camera.direction.len())
                .sub(camera.position);

        if (zoom.len() >= tmpV2.len()) {
            return tmpV2;
        } else {
            return zoom;
        }
    }

    @Override
    protected boolean process (float deltaX, float deltaY, int button) {
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

        if (autoUpdate) camera.update();

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


}
