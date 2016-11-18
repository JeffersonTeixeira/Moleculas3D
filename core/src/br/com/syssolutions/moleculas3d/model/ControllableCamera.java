package br.com.syssolutions.moleculas3d.model;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.PerspectiveCamera;

/**
 * Created by jefferson on 23/10/16.
 */

public abstract class ControllableCamera extends PerspectiveCamera implements InputProcessor {

    abstract public void resize(int width, int height);
    abstract public void render();

    public ControllableCamera(int fieldOfView, int width, int height) {
        super(fieldOfView, width, height);
    }

    @Override
    public boolean keyDown(int keyCode) {
        return false;
    }

    @Override
    public boolean keyTyped(char arg0) {
        return false;
    }

    @Override
    public boolean keyUp(int arg0) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean mouseMoved(int arg0, int arg1) {
        return false;
    }

    @Override
    public boolean scrolled(int direction) {
        return false;
    }
}