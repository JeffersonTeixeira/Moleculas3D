package br.com.syssolutions.moleculas3d.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import br.com.syssolutions.moleculas3d.control.states.GameStateManager;
import br.com.syssolutions.moleculas3d.control.states.State;
import br.com.syssolutions.moleculas3d.model.Atomo;
import br.com.syssolutions.moleculas3d.model.DrawSpaceFillingModel;
import br.com.syssolutions.moleculas3d.model.DrawStickAndBallsModel;
import br.com.syssolutions.moleculas3d.model.Ligacao;
import br.com.syssolutions.moleculas3d.model.Molecula;
import br.com.syssolutions.moleculas3d.model.Visualizacao;

import static br.com.syssolutions.moleculas3d.model.Visualizacao.SPACE_FILLING;
import static br.com.syssolutions.moleculas3d.model.Visualizacao.STICK;
import static br.com.syssolutions.moleculas3d.model.Visualizacao.STICK_AND_BALLS;

/**
 * Created by jefferson on 17/10/16.
 */

public class Visualizador3DState extends State {

    private final float BUTTONSIZE = Gdx.graphics.getWidth() / 8; //média de 12% da largura da tela

    Molecula molecula;

    private Visualizacao visualizacao; //(LINE,    SPACE_FILLING,    STICK,    STICK_AND_BALLS)

    public CameraInputController camController;
    public ModelBatch modelBatch;

    public Array<ModelInstance> instances = new Array<ModelInstance>();
    //public ModelInstance instance;
    public Environment environment;
    public boolean loading;

    //Itens do menu de configurações:
    private Stage stage;
    private Skin skinVisualizador3D;
    private TextureAtlas atlasbtnConf;

    private ButtonGroup visuGroup;
    private Button spaceFillingBtn;
    private Button stickAndBallBtn;
    private Button stickBtn;

    private Button voltarbtn;
    private Button ajudabtn;
    private Button infobtn;


    public Visualizador3DState(GameStateManager gsm) {
        super(gsm);
        molecula = criaMoleculaAgua();

        modelBatch = new ModelBatch();

        visualizacao = SPACE_FILLING;

        carregaConfAmbiente();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(7f, 7f, 7f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);

        buildStage();

        //Adicionar
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(camController);
        Gdx.input.setInputProcessor(multiplexer);

    }

    private void buildStage() {


        skinVisualizador3D = new Skin(
                Gdx.files.internal("ui-imagens/visualizador3d.json"),
                new TextureAtlas("ui-imagens/visualizador3d.pack"));

        stage = new Stage();
        stage.clear();

        ajudabtn = new Button(skinVisualizador3D, "AJUDA");
        ajudabtn.setSize(BUTTONSIZE, BUTTONSIZE);
        ajudabtn.setPosition(0, 0);

        voltarbtn = new Button(skinVisualizador3D, "VOLTAR");
        voltarbtn.setSize(BUTTONSIZE, BUTTONSIZE);
        voltarbtn.setPosition(0, Gdx.graphics.getHeight() - BUTTONSIZE);

        infobtn = new Button(skinVisualizador3D, "INFO");
        infobtn.setSize(BUTTONSIZE, BUTTONSIZE);
        infobtn.setPosition(Gdx.graphics.getWidth() - BUTTONSIZE, Gdx.graphics.getHeight() - BUTTONSIZE);

        stage.addActor(ajudabtn);
        stage.addActor(voltarbtn);
        stage.addActor(infobtn);

        criaBotoesVisualizacao();

    }

    private void criaBotoesVisualizacao() {

        spaceFillingBtn = new Button(skinVisualizador3D, "SPACE_FILLING");
        spaceFillingBtn.setSize(BUTTONSIZE, BUTTONSIZE);
        spaceFillingBtn.setPosition(Gdx.graphics.getWidth() - BUTTONSIZE, 0);

        stickAndBallBtn = new Button(skinVisualizador3D, "BALL_AND_STICK");
        stickAndBallBtn.setSize(BUTTONSIZE, BUTTONSIZE);
        stickAndBallBtn.setPosition(Gdx.graphics.getWidth() - BUTTONSIZE, BUTTONSIZE * 1.05f);

        stickBtn = new Button(skinVisualizador3D, "STICK");
        stickBtn.setSize(BUTTONSIZE, BUTTONSIZE);
        stickBtn.setPosition(Gdx.graphics.getWidth() - BUTTONSIZE, (BUTTONSIZE * 2) * 1.05f);

        visuGroup = new ButtonGroup(spaceFillingBtn, stickAndBallBtn, stickBtn);

        stage.addActor(spaceFillingBtn);
        stage.addActor(stickAndBallBtn);
        stage.addActor(stickBtn);

        addListenerBotoesVisualizacao();
    }

    private void addListenerBotoesVisualizacao() {
        spaceFillingBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                visualizacao = SPACE_FILLING;
                loading = true;


            }
        });

        stickAndBallBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                visualizacao = STICK_AND_BALLS;
                loading = true;
            }
        });

        stickBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                visualizacao = STICK;
                loading = false;
            }
        });

    }


    private void carregaConfAmbiente() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

    }


    private void loadSpaceFillingModel() {

        instances.clear();
        instances = new DrawSpaceFillingModel(molecula).getModel();
        loading = false;
    }


    private void loadStickBallModel() {

        instances.clear();
        instances = new DrawStickAndBallsModel(molecula).getModel();
        loading = false;
    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        switch (visualizacao) {

            case SPACE_FILLING:

                if (loading)
                    loadSpaceFillingModel();
                camController.update();
                break;

            case STICK_AND_BALLS:

                if (loading)
                    loadStickBallModel();
                camController.update();
                break;

            case LINE:

                if (loading)

                    camController.update();
                break;

        }


        Gdx.gl.glClearColor(0.3f, 1, 1, 0);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();

        stage.act();
        stage.draw();

    }


    @Override
    public void dispose() {
        modelBatch.dispose();
        instances.clear();
        stage.dispose();
        skinVisualizador3D.dispose();
        atlasbtnConf.dispose();

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
//        a1.setCor();

        a2.id = "a2";
        a2.simbolo = "O";
        a2.x = 0.147925f;
        a2.y = -0.029981f;
        a2.z = -0.34219f;
//        a2.setCor();

        a3.id = "a3";
        a3.simbolo = "H";
        a3.x = -0.779012f;
        a3.y = -0.003476f;
        a3.z = -0.132663f;
//        a3.setCor();

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
