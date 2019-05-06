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

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.pega.vehicledamagemodeling.UIUpdateCallback;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PartSelectionDetectorTest {
    private int screenX;
    private int screenY;
    private SelectionService selectionService;

    @Before
    public void setUp() {
        screenX = (int)(Math.random() % 100);
        screenY = (int)(Math.random() % 100);
        selectionService = mock(SelectionService.class);
    }

    @Test
    public void whenSwipeThenIgnoreAction() {
        // given
        PartSelectionDetector selectedDetector = new PartSelectionDetector(null, null, selectionService, mock(UIUpdateCallback.class));

        // when
        selectedDetector.touchDown(screenX, screenY, 0, 0);
        selectedDetector.touchUp(screenX + 20, screenY + 15, 0, 0);

        // then
        verify(selectionService, times(0)).setSelectedPart(any());
    }

    @Test
    public void whenTapThenDetectAction() {
        // given
        Camera camera = mock(PerspectiveCamera.class);
        Ray ray = new Ray(new Vector3(0, -2, 0), new Vector3(0, 2, 0));
        when(camera.getPickRay(screenX, screenY)).thenReturn(ray);
        Array<ModelInstance> modelInstances = new Array<>();
        ModelInstance modelInstance = mock(ModelInstance.class);
        BoundingBox boundingBox = new BoundingBox(new Vector3(-1, -1, -1), new Vector3(1, 1, 1));
        when(modelInstance.calculateBoundingBox(any(BoundingBox.class))).thenReturn(boundingBox);
        modelInstances.add(modelInstance);
        PartSelectionDetector selectedDetector = new PartSelectionDetector(camera, modelInstances, selectionService,mock(UIUpdateCallback.class));

        // when
        selectedDetector.touchDown(screenX, screenY, 0, 0);
        selectedDetector.touchUp(screenX, screenY, 0, 0);

        // then
        verify(selectionService, times(1)).setSelectedPart(any());
    }
}