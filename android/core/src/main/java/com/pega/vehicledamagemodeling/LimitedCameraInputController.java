package com.pega.vehicledamagemodeling;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;

public class LimitedCameraInputController extends CameraInputController {
    private static final float ZOOM_IN_LIMIT = 18f;
    private static final float ZOOM_OUT_LIMIT = 40f;

    private static final float pinchZoomFactor = 15f;

    public LimitedCameraInputController(final Camera camera) {
        super(camera);
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
        Vector3 zoom = new Vector3(camera.direction).scl(amount);

        if (isZoomIn(amount)) {
            return limitZoom (zoom, ZOOM_IN_LIMIT);
        }
        return limitZoom (zoom, ZOOM_OUT_LIMIT);
    }

    private boolean isZoomIn(float amount) {
        return amount > 0f;
    }

    private Vector3 limitZoom(Vector3 zoom, float zoomLimit) {
        Vector3 limit = new Vector3(camera.direction)
                .scl(-zoomLimit / camera.direction.len())
                .sub(camera.position);

        if (zoom.len() >= limit.len()) {
            return limit;
        } else {
            return zoom;
        }
    }
}
