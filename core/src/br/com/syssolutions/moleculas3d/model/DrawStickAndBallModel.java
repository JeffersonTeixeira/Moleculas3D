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

            //Defonir tamanho do Átomo
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


            float v1x,v1y,v1z, v2x, v2y, v2z;

            v1x= primeiro.x;
            v1y=primeiro.y;
            v1z= primeiro.z;

            v2x= segundo.x;
            v2y=segundo.y;
            v2z= segundo.z;


            float v, vx, vy, vz;

            vx = v2x-v1x;
            vy =  v2y-v1y;
            vz =  v2z-v1z;


            //handle the degenerate case of z1 == z2 with an approximation

            if (vz > -0.000001 && vz < 0.000001) {

                if (vz >= 0.0) {

                    vz = 0.000001f;

                } else {

                    vz = -0.000001f;

                }

            }


            if (vy > -0.000001 && vy < 0.000001) {

                if (vy >= 0.0) {

                    vy = -0.000001f;

                } else {

                    vy = 0.000001f;

                }

            }


            if (vx > -0.000001 && vx < 0.000001) {

                if (vx >= 0.0) {

                    vx = 0.000001f;

                } else {

                    vx = -0.000001f;

                }

            }


            v = (float) Math.sqrt(vx * vx + vy * vy + vz * vz);

            double ax = (180 / Math.PI) * Math.acos(vz / v);


            if (vz < 0.0) ax = -ax;


            ModelInstance ligacaoInstance = new ModelInstance(bond);
            //ligacaoInstance.materials.get(0).set(ColorAttribute.createDiffuse());
            ligacaoInstance.transform.setToTranslation(primeiro.x, primeiro.y, primeiro.z);
            ligacaoInstance.transform.scale(1, v, 1);
            //ligacaoInstance.transform.setToLookAt(vetorDirecao);
            //ligacaoInstance.transform.setToRotation(vx,-vy,0f,(float)ax);


            this.instances.add(ligacaoInstance);


//            Model lineModel;
//
//            ModelBuilder modelBuilderline = new ModelBuilder();
//            modelBuilderline.begin();
//            MeshPartBuilder builder = modelBuilderline.part("teste", 1, 3, new Material());
//            builder.line(primeiro.x, primeiro.y, primeiro.z, segundo.x, segundo.y, segundo.z);
//
//            lineModel = modelBuilderline.end();
//            ModelInstance lineInstance = new ModelInstance(lineModel);
//
//
//
//            instances.add(lineInstance);


/*

            Model cylModel;

            ModelBuilder modelBuilder = new ModelBuilder();
            modelBuilder.begin();
            CylinderShapeBuilder ligacaoteste = new CylinderShapeBuilder();
            MeshPartBuilder builder = modelBuilder.part("teste", 1, 3, new Material());
            ligacaoteste.build(builder, 1f, 1f, 1f, 2);

            cylModel = modelBuilder.end();
            ModelInstance ligacaoInstance = new ModelInstance(cylModel);


            instances.add(ligacaoInstance);
            */


//
//
//            ModelBuilder modelBuilder = new ModelBuilder();
//            MeshBuilder meshBuilder = new MeshBuilder();
//
//            meshBuilder.begin(1,3);
//            meshBuilder.cylinder(4f, 6f, 4f, 16);
//            Mesh cylinder1 = meshBuilder.end();
//
//            meshBuilder.begin(1,3);
//            meshBuilder.cylinder(4f, 6f, 4f, 16);
//            Mesh cylinder2 = meshBuilder.end();
//
//
//            modelBuilder.begin();
//
//            modelBuilder.part("cylinder1",
//                    cylinder1,
//                    Usage.Position | Usage.Normal | Usage.TextureCoordinates,
//                    new Material(
//                          //  TextureAttribute.createDiffuse(),
//                            ColorAttribute.createSpecular(1,1,1,1),
//                            FloatAttribute.createShininess(8f)));
//
//            modelBuilder.part("cylinder2",
//                    cylinder2,
//                    Usage.Position | Usage.Normal | Usage.TextureCoordinates,
//                    new Material(
//                            //TextureAttribute.createDiffuse(red),
//                            ColorAttribute.createSpecular(1,1,1,1),
//                            FloatAttribute.createShininess(8f)))
//                    .mesh.transform(new Matrix4().translate(0, 0, -2f));
//
//            Model finalCylinder = modelBuilder.end();
//
//            ModelInstance cilindro = new ModelInstance(finalCylinder);

//
//            ModelBuilder modelBuilder = new ModelBuilder();
//
//            modelBuilder.begin();
//
//            MeshPartBuilder meshBuilder;
//
//            meshBuilder = modelBuilder.part("p1", GL20.GL_TRIANGLES, Usage.Position | Usage.Normal,
//                    new Material(
//                            ColorAttribute.createDiffuse(Color.RED)
//                    ));
//
//            CylinderShapeBuilder cylinderShapeBuilder = new CylinderShapeBuilder();
//            cylinderShapeBuilder.build(meshBuilder, 0.5f, distanceBetween, 0, 30);
//            Model cylindroTeste = modelBuilder.end();


            // Model teste = cylindroTeste;


            //  ModelInstance testeinstance = new ModelInstance(teste);
            //testeinstance.transform.setToLookAt(primeiro.x,primeiro.y,primeiro.z);

//cylinderShapeBuilder.build();


            // instances.add(testeinstance);


        }

        return this.instances;
    }


    private void assetsLoad() {
        manager.load(ESFERA_PATCH, Model.class);
        manager.load(STICK_PATCH, Model.class);
        manager.finishLoading();

    }


}