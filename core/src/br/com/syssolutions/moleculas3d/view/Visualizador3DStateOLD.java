package br.com.syssolutions.moleculas3d.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import java.util.ArrayList;

import br.com.syssolutions.moleculas3d.control.states.GameStateManager;
import br.com.syssolutions.moleculas3d.control.states.State;
import br.com.syssolutions.moleculas3d.model.Atomo;
import br.com.syssolutions.moleculas3d.model.Ligacao;
import br.com.syssolutions.moleculas3d.model.Molecula;

/**
 * Created by jefferson on 17/10/16.
 */

public class Visualizador3DStateOLD extends State {


    Molecula molecula;


    public ModelBatch modelBatch;
    public Model model;
    public ModelInstance instance;
    public Environment environment;
    public CameraInputController camController;


    public Visualizador3DStateOLD(GameStateManager gsm) {
        super(gsm);
        molecula = criaMoleculaAgua();


        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);


        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();



        //cam.setToOrtho(true, Gdx.graphics.getWidth() /2f, Gdx.graphics.getHeight() / 2f);
        //cam.position.set(0,0,10);
       // cam.lookAt(0, 0, 0);
       // cam.update();


        modelBatch= new ModelBatch();

        ModelBuilder modelBuilder = new ModelBuilder();
        model=modelBuilder.createBox(5f,5f,5f,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                Usage.Position | Usage.Normal);

        instance = new ModelInstance(model);


    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
//        Gdx.gl.glClearColor(0.3f, 1, 1, 0);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//sb.setProjectionMatrix(cam.combined);
//        Gdx.gl.glEnable(GL20.GL_BLEND);
//        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
//        Gdx.gl.glDisable(GL20.GL_BLEND);



        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        camController.update();

        modelBatch.begin(cam);
        modelBatch.render(instance,environment);
        modelBatch.end();


    }

    @Override
    public void dispose() {

    }


    public Molecula criaMoleculaAgua() {
        Molecula agua = new Molecula();
        agua.atomos = new ArrayList<Atomo>();
        agua.ligacoes = new ArrayList<Ligacao>();

        //Declaração e atribuição de valores: Átomos Início:
        Atomo a1 = new Atomo();
        Atomo a2 = new Atomo();
        Atomo a3 = new Atomo();


        a1.id = "a1";
        a1.simbolo = "H";
        a1.x = 0.631087f;
        a1.y = -0.026505f;
        a1.z = 0.474853f;
        a1.setCor();

        a2.id = "a2";
        a2.simbolo = "O";
        a2.x = 0.147925f;
        a2.y = -0.029981f;
        a2.z = -0.34219f;
        a2.setCor();

        a3.id = "a3";
        a3.simbolo = "H";
        a3.x = -0.779012f;
        a3.y = -0.003476f;
        a3.z = -0.132663f;
        a3.setCor();

        agua.atomos.add(a1);
        agua.atomos.add(a2);
        agua.atomos.add(a3);
        //Declaração e atribuição de valores: Átomos FIM:

        //Declaração e atribuição de valores: Ligações Início:
        Ligacao l1 = new Ligacao();
        Ligacao l2 = new Ligacao();

        l1.ordemLigacao = 1;
        l1.primeiroAtomo = a1;
        l1.segundoAtomo = a2;

        l2.ordemLigacao = 1;
        l2.primeiroAtomo = a2;
        l2.segundoAtomo = a3;

        agua.ligacoes.add(l1);
        agua.ligacoes.add(l2);
        //Declaração e atribuição de valores: Ligações FIM:


        return agua;

    }

}
