package com.pega.vehicledamagemodeling;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;

public class LimitedCameraInputController extends CameraInputController {
    private static final float ZOOM_IN_LIMIT = 18f;
    private static final float ZOOM_OUT_LIMIT = 40f;
    private Vector3 tmpV1 = new Vector3();
    private Vector3 tmpV2 = new Vector3();
    private Vector3 tmpV3 = new Vector3();

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
        Vector3 limit = tmpV1.set(camera.direction)
                .scl(-zoomLimit / camera.direction.len())
                .sub(camera.position);

        if (zoom.len() >= limit.len()) {
            return limit;
        } else {
            return zoom;
        }
    }

    @Override
    protected boolean process (float deltaX, float deltaY, int button) {
        tmpV1.set(camera.direction).crs(camera.up).y = 0f;
        tmpV1.nor();

        float deltaYRotate = deltaY * rotateAngle;

        tmpV2.set(target);
        tmpV2.sub(camera.position);
        tmpV2.rotate(tmpV1, deltaYRotate);

        tmpV3.set(camera.up);
        tmpV3.rotate(tmpV1, deltaYRotate);

        if (tmpV2.y > 3 || tmpV3.y < 0.5f) {
            return false;
        }

        camera.rotateAround(target, tmpV1, deltaYRotate);
        camera.rotateAround(target, Vector3.Y, deltaX * -rotateAngle);

        if (autoUpdate) camera.update();

        return true;
    }


}
