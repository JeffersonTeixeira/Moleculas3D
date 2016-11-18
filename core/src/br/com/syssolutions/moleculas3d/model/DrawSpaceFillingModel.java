package br.com.syssolutions.moleculas3d.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.utils.Array;

/**
 * Created by jefferson on 09/11/16.
 */

public class DrawSpaceFillingModel {

    private Molecula molecula;
   AssetManager manager;
    private Array<ModelInstance> instances;
    private static final String ESFERA_PATCH = "sphere.obj";

    public DrawSpaceFillingModel(Molecula molecula) {

        manager = new AssetManager();
        this.molecula = molecula;
        instances = new Array<ModelInstance>();
        assetsLoad();
    }

    public Array<ModelInstance> getModel() {

        Model sphere = manager.get(ESFERA_PATCH, Model.class);

        for (int i = 0; i < molecula.atomos.size(); i++) {
            ModelInstance sphereInstace = new ModelInstance(sphere);

            //Definir posição:
            sphereInstace.transform.setToTranslation(molecula.atomos.get(i).x,
                    molecula.atomos.get(i).y,
                    molecula.atomos.get(i).z);

            //Definir tamanho do Átomo
            float raioAtomico;
            raioAtomico=molecula.atomos.get(i).getRaioAtomico(molecula.atomos.get(i).simbolo);
            sphereInstace.transform.scale(raioAtomico, raioAtomico, raioAtomico);

            this.instances.add(sphereInstace);

            float[] cores = molecula.atomos.get(i).getCor();
            float r = cores[0];
            float g = cores[1];
            float b = cores[2];
            float a = cores[3];

            this.instances.get(i).materials.get(0).set(ColorAttribute.createDiffuse(r, g, b, a));
        }

        return this.instances;

    }

    private void assetsLoad() {
        manager.load(ESFERA_PATCH, Model.class);
        //manager.update();
        manager.finishLoading();
    }

}
