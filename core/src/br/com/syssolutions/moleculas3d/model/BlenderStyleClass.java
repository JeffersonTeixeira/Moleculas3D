package br.com.syssolutions.moleculas3d.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by jefferson on 23/10/16.
 */

public class BlenderStyleClass extends ControllableCamera {
        public static final Vector3 ORIGIN = new Vector3(0, 0, 0);

        private static boolean shiftIsPressed = false, controlIsPressed = false,
                isScrollingUp = false, isScrollingDown = false,
                isSingleTouched = false, justSingleTouched = false;

        private float aspectRatio;
        private int x = -1, y = -1;
        private float dx = 0.0f, dy = 0.0f;
        private final Vector3 tmp = new Vector3();

        // fields related to pinch-to-zoom
        private int numberOfFingers = 0;
        private int fingerOnePointer;
        private int fingerTwoPointer;
        private float lastDistance = 0;
        private final Vector3 fingerOne = new Vector3();
        private final Vector3 fingerTwo = new Vector3();

        public BlenderStyleClass(int fieldOfView, int width, int height) {
            super(fieldOfView, width, height);
            aspectRatio = viewportHeight / viewportWidth;
            Gdx.input.setInputProcessor(this);
            up.set(0.0f, 1.0f, 0.0f);
            position.set(0.0f, 0.0f, 30.0f);
            far = 300.0f;
            lookAt(0, 0, 0);
            translate(0.0f, 0.0f, 2.1f);
            lookAt(0, 0, 0);
            update();


        }

        public void pause() {
            numberOfFingers = 0;
        }

        @Override
        public void resize(int width, int height) {
            viewportWidth = width;
            viewportHeight = height;
            aspectRatio = viewportHeight / viewportWidth;
            update();
        }

        @Override
        public void render() {
            if (isSingleTouched) {

                // This gets the change in touch position and
                // compensates for the aspect ratio.
                if (x == -1 || y == -1 || justSingleTouched) {
                    x = Gdx.input.getX();
                    y = Gdx.input.getY();
                } else {
                    dx = (x - Gdx.input.getX());
                    dy = (y - Gdx.input.getY()) / aspectRatio;
                }

                // This zooms when control is pressed.
                if (controlIsPressed && dy > 0) {
                    scrollIn();
                } else if (controlIsPressed && dy < 0) {
                    scrollOut();
                }

                // This translates the camera blender-style
                // if shift is pressed.
                // Note that this will look weird with a
                // perspective camera.
                else if (shiftIsPressed) {
                    translateTangentially();
                }

                // Default is to rotate the object
                // (actually rotate the camera about a sphere
                // that surrounds the object).
                else {
                    travelAround();
                }

                x = Gdx.input.getX();
                y = Gdx.input.getY();

                justSingleTouched = false;
            }

            // this zooms when the mouse wheel is rotated
            if (isScrollingUp) {
                scrollIn();
                isScrollingUp = false;
            } else if (isScrollingDown) {
                scrollOut();
                isScrollingDown = false;
            }

            // Some key controls
            if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
                translateTangentially(1, 0);
            } else if (Gdx.input.isKeyPressed(Keys.RIGHT)
                    || Gdx.input.isKeyPressed(Keys.D)) {
                translateTangentially(-1, 0);
            }

            if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) {
                translateTangentially(0, 1);
            } else if (Gdx.input.isKeyPressed(Keys.DOWN)
                    || Gdx.input.isKeyPressed(Keys.S)) {
                translateTangentially(0, -1);
            }

            update();
        }

        // These methods create the pinch zoom
        // and set some flags for logic in render method.
        @Override
        public boolean touchDown(int x, int y, int pointer, int button) {
            // for pinch-to-zoom
            numberOfFingers++;
            if (numberOfFingers == 1) {
                isSingleTouched = true;
                justSingleTouched = true;
                fingerOnePointer = pointer;
                fingerOne.set(x, y, 0);
            } else if (numberOfFingers == 2) {
                isSingleTouched = false;
                fingerTwoPointer = pointer;
                fingerTwo.set(x, y, 0);

                float distance = fingerOne.dst(fingerTwo);
                lastDistance = distance;
            }
            return true;
        }

        @Override
        public boolean touchDragged(int x, int y, int pointer) {
            if (numberOfFingers > 1) {
                if (pointer == fingerOnePointer) {
                    fingerOne.set(x, y, 0);
                }
                if (pointer == fingerTwoPointer) {
                    fingerTwo.set(x, y, 0);
                }

                float distance = fingerOne.dst(fingerTwo);

                if (lastDistance > distance) {
                    scrollOut();
                } else if (lastDistance < distance) {
                    scrollIn();
                }

                lastDistance = distance;
                update();
            }
            return true;
        }

        @Override
        public boolean touchUp(int x, int y, int pointer, int button) {
            isSingleTouched = false;
            if (numberOfFingers == 1) {
                Vector3 touchPoint = new Vector3(x, y, 0);
                unproject(touchPoint);
            }
            numberOfFingers--;

            // just some error prevention... clamping number of fingers (ouch! :-)
            if (numberOfFingers < 0) {
                numberOfFingers = 0;
            }

            lastDistance = 0;
            return false;
        }

        // These methods set flags for logic in render method.
        @Override
        public boolean keyDown(int keycode) {

            switch (keycode) {
                case (Keys.SHIFT_LEFT):
                case (Keys.SHIFT_RIGHT):
                    shiftIsPressed = true;
                    break;
                case (Keys.CONTROL_LEFT):
                case (Keys.CONTROL_RIGHT):
                    controlIsPressed = true;
                    break;
                case (Keys.O):
                    this.up.set(0.0f, 1.0f, 0.0f);
                    this.position.set(0.0f, 0.0f, 30.0f);
                    this.lookAt(0, 0, 0);
                    this.update();
            }
            return true;
        }

        @Override
        public boolean keyUp(int arg0) {
            shiftIsPressed = controlIsPressed = false;
            return true;
        }

        @Override
        public boolean scrolled(int direction) {
            if (direction == -1) {
                isScrollingUp = true;
            } else if (direction == 1) {
                isScrollingDown = true;
            }
            return true;
        }

        // The rest of the methods translate the camera.
        public void scrollIn() {
            float magnitude = 1.0f;
            scrollIn(magnitude);
        }

        public void scrollIn(float magnitude) {
            if (position.dst2(ORIGIN) > 2.0f) {
                tmp.set(position);
                tmp.nor();
                this.translate(-tmp.x * magnitude, -tmp.y * magnitude, -tmp.z
                        * magnitude);
                update();
            }
        }

        public void scrollOut() {
            float magnitude = 1.0f;
            scrollOut(magnitude);
        }

        public void scrollOut(float magnitude) {
            tmp.set(position);
            tmp.nor();
            this.translate(tmp.x * magnitude, tmp.y * magnitude, tmp.z * magnitude);
            update();
        }

        private void travelAround() {
            tmp.set(up);
            rotateAround(ORIGIN, tmp, dx);
            tmp.crs(position).nor();
            rotateAround(ORIGIN, tmp, dy);
        }

        private void translateTangentially() {
            translateTangentially(dx, dy);
        }

        private void translateTangentially(float dx, float dy) {
            tmp.set(up);
            tmp.crs(position);
            if (dx > 0) {
                translate(tmp.x / 15.0f, tmp.y / 15.0f, tmp.z / 15.0f);
            } else if (dx < 0) {
                translate(-tmp.x / 15.0f, -tmp.y / 15.0f, -tmp.z / 15.0f);
            }

            if (dy > 0) {
                translate(-up.x, -up.y, -up.z);
            } else if (dy < 0) {
                translate(up);
            }
        }

    }

