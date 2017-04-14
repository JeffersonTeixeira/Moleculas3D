package br.com.syssolutions.moleculas3d.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;

/**
 * Created by jefferson on 13/04/17.
 */

public class ModelResources {

    private static final String ESFERA_PATCH = "sphere.obj";
    private static final String SINGLE_STICK_PATCH = "single.obj";
    private static final String DOUBLE_STICK_PATCH = "double.obj";
    private static final String TRIPLE_STICK_PATCH = "triple.obj";
    private static final String QUAD_STICK_PATCH = "quad.obj";

    public static Model getSPHERE() {
        return SPHERE;
    }

    public static Model getBOND1() {
        return BOND1;
    }

    public static Model getBOND2() {
        return BOND2;
    }

    public static Model getBOND3() {
        return BOND3;
    }

    public static Model getBOND4() {
        return BOND4;
    }

    private static Model SPHERE;
    private static Model BOND1;
    private static Model BOND2;
    private static Model BOND3;
    private static Model BOND4;

    private static AssetManager manager;
    private static boolean LOADED = false;


    public static void assetsLoad() {
        if (LOADED == false) {
            manager = new AssetManager();

            manager.load(ESFERA_PATCH, Model.class);
            manager.load(SINGLE_STICK_PATCH, Model.class);
            manager.load(DOUBLE_STICK_PATCH, Model.class);
            manager.load(TRIPLE_STICK_PATCH, Model.class);
            manager.load(QUAD_STICK_PATCH, Model.class);

            manager.finishLoading();

            SPHERE = manager.get(ESFERA_PATCH, Model.class);
            BOND1 = manager.get(SINGLE_STICK_PATCH, Model.class);
            BOND2 = manager.get(DOUBLE_STICK_PATCH, Model.class);
            BOND3 = manager.get(TRIPLE_STICK_PATCH, Model.class);
            BOND4 = manager.get(QUAD_STICK_PATCH, Model.class);

            LOADED = true;
        }


    }

}
