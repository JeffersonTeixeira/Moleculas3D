package br.com.syssolutions.moleculas3d.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.*;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.CylinderShapeBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * Created by jefferson on 09/11/16.
 */

public class DrawStickAndBallModel {

    private static final String ESFERA_PATCH = "sphere.obj";
    private static final String STICK_PATCH = "single.obj";

    private Molecula molecula;
    private AssetManager manager;
    private Array<ModelInstance> instances;


    public DrawStickAndBallModel(Molecula molecula) {
        manager = new AssetManager();
        this.molecula = molecula;
        instances = new Array<ModelInstance>();
        assetsLoad();
    }

    public Array<ModelInstance> getModel() {

        Model sphere = manager.get(ESFERA_PATCH, Model.class);
        Model bond = manager.get(STICK_PATCH, Model.class);

        for (int i = 0; i < molecula.atomos.size(); i++) {
            ModelInstance sphereInstace = new ModelInstance(sphere);

            //Definir posição:
            sphereInstace.transform.setToTranslation(molecula.atomos.get(i).x,
                    molecula.atomos.get(i).y,
                    molecula.atomos.get(i).z);

            //Definir tamanho do Átomo
            float raioAtomico;
            raioAtomico = molecula.atomos.get(i).getRaioAtomico(molecula.atomos.get(i).simbolo) / 2;
            sphereInstace.transform.scale(raioAtomico, raioAtomico, raioAtomico);

            this.instances.add(sphereInstace);

            float[] cores = molecula.atomos.get(i).getCor();
            float r = cores[0];
            float g = cores[1];
            float b = cores[2];
            float a = cores[3];

            this.instances.get(i).materials.get(0).set(ColorAttribute.createDiffuse(r, g, b, a));


        }


        for (int i = 0; i < molecula.ligacoes.size(); i++) {


            Ligacao lig = molecula.ligacoes.get(i);

            Atomo primeiro = lig.primeiroAtomo;
            Atomo segundo = lig.segundoAtomo;

            Vector3 posA = new Vector3(primeiro.x,primeiro.y,primeiro.z);
            Vector3 posB= new Vector3(segundo.x,segundo.y,segundo.z);



            Vector3 v3 = posA.cpy().add(posB).scl(0.5f);
            Vector3 v4 = posB.cpy().sub(v3).nor();
            Vector3 v5 = v4.cpy().nor().crs(Vector3.Y).nor();

            ModelInstance ligacaoInstance = new ModelInstance(bond);

            ligacaoInstance.transform.translate(posA);

            ligacaoInstance.transform.rotate(v5,
                    -(float) Math.toDegrees(Math.acos(v4.dot(Vector3.Y))));

            this.instances.add(ligacaoInstance);

        }

        return this.instances;
    }


    private void assetsLoad() {
        manager.load(ESFERA_PATCH, Model.class);
        manager.load(STICK_PATCH, Model.class);
        manager.finishLoading();

    }


}