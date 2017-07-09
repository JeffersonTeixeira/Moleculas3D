package br.com.syssolutions.moleculas3d.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
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
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

import br.com.syssolutions.moleculas3d.control.states.GameStateManager;
import br.com.syssolutions.moleculas3d.control.states.State;
import br.com.syssolutions.moleculas3d.model.DrawSpaceFillingModel;
import br.com.syssolutions.moleculas3d.model.DrawStickAndBallModel;
import br.com.syssolutions.moleculas3d.model.ModelResources;
import br.com.syssolutions.moleculas3d.model.Molecula;
import br.com.syssolutions.moleculas3d.model.Visualizacao;

import static br.com.syssolutions.moleculas3d.model.Visualizacao.SPACE_FILLING;
import static br.com.syssolutions.moleculas3d.model.Visualizacao.STICK;
import static br.com.syssolutions.moleculas3d.model.Visualizacao.STICK_AND_BALL;

/**
 * Created by jefferson on 17/10/16.
 */

public class Visualizador3DState extends State implements InputProcessor, GestureListener {

    private final float BUTTONSIZE = Gdx.graphics.getWidth() / 8; //média de 12% da largura da tela

    static Molecula molecula;

    private Visualizacao visualizacao; //(LINE,    SPACE_FILLING,    STICK,    STICK_AND_BALL)

    // public VisualizadorCameraInputController camController;

    public ModelBatch modelBatch;

    public Array<ModelInstance> instances = new Array<ModelInstance>();
    //public ModelInstance instance;
    public Environment environment;
    public boolean loading;

    //Itens do menu de configurações:
    private Stage stage;
    private Skin skinVisualizador3D;


    private ButtonGroup visuGroup;
    private Button spaceFillingBtn;
    private Button stickAndBallBtn;
    private Button stickBtn;

    private Button voltarbtn;
    private Button ajudabtn;
    private Button infobtn;


    //Variáveis para controlar os Gestos com touch
    float zoom; //Mantem o Valor do zoom atual;
    float zoomAmount;//armazena o montante do zoom (mais ou menos)


    Vector3 tmpV1 = new Vector3();
    Vector3 target = new Vector3(0, 0, 0);
    float rotateAngle = 360f;

    /**
     * The key which must be pressed to activate rotate, translate and forward or 0 to always activate.
     */
    public int activateKey = 0;
    /**
     * Indicates if the activateKey is currently being pressed.
     */
    protected boolean activatePressed;

    protected boolean forwardPressed;
    public int backwardKey = Input.Keys.S;

    public int forwardKey = Input.Keys.W;

    protected boolean backwardPressed;

    public int rotateRightKey = Input.Keys.A;
    protected boolean rotateRightPressed;

    public int rotateLeftKey = Input.Keys.D;
    protected boolean rotateLeftPressed;


    /**
     * The current (first) button being pressed.
     */
    protected int button = -1;

    float startX;
    float startY;


    public static void setMolecula(Molecula molecula) {
        Visualizador3DState.molecula = molecula;
    }

    public Visualizador3DState(GameStateManager gsm) {
        super(gsm);
        //molecula = criaMoleculaAgua();

        modelBatch = new ModelBatch();

        visualizacao = SPACE_FILLING;

        carregaConfAmbiente();

        //Carrega todos recursos Model (esfera e tipos de ligações)
        ModelResources.assetsLoad();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(7f, 7f, 7f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

//        camera_position = cam.position;

        // camController = new VisualizadorCameraInputController(cam);

        buildStage();

        //Adicionar
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        GestureDetector gd = new GestureDetector(this);
        // multiplexer.addProcessor(camController);
        multiplexer.addProcessor(gd);
        multiplexer.addProcessor(this);
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

        addListenerBotoes();
    }

    private void addListenerBotoes() {
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
                visualizacao = STICK_AND_BALL;
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
        instances = new DrawStickAndBallModel(molecula).getModel();
        loading = false;
    }


    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            gsm.set(new ListMolBibliotecaState(gsm));
            dispose();
        }
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
                cam.update();
                break;

            case STICK_AND_BALL:

                if (loading)
                    loadStickBallModel();
                cam.update();
                break;

            case LINE:

                if (loading)

                    cam.update();
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


    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {


        // System.out.println("Button: "+button);


        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {

        //zoom Amount
        if (zoom < distance)
            zoomAmount = 0.5f;
        else
            zoomAmount = -0.5f;

        cam.position.add(cam.direction.x * zoomAmount, cam.direction.y * zoomAmount, cam.direction.z * zoomAmount);
        cam.update();

        zoom = distance;

        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }


    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

//        cam.update();
//
//      tmpV1.set(0,0,0);
//        System.out.println("Touchup!!!");
//        System.out.println("tmpV1 X: " + tmpV1.x + " tmpV1 Y: " + tmpV1.y + " tmpV1 Z: " + tmpV1.z);

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        final float deltaX = (screenX - startX) / Gdx.graphics.getWidth();
        final float deltaY = (startY - screenY) / Gdx.graphics.getHeight();
        startX = screenX;
        startY = screenY;








        tmpV1.set(cam.direction).crs(cam.up).y = 0f;
        cam.rotateAround(target, tmpV1.nor(), deltaY * rotateAngle);
        cam.rotateAround(target, Vector3.Y, deltaX * -rotateAngle);

        //target.set();

        System.out.println("tmpV1 X: " + tmpV1.x + " tmpV1 Y: " + tmpV1.y + " tmpV1 Z: " + tmpV1.z);
        System.out.println("Target X: " + target.x + " target Y: " + target.y + " target Z: " + target.z);

        cam.update();



        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    /*
    protected static class CameraGestureListener extends GestureDetector.GestureAdapter {
        public CameraInputController controller;
        private float previousZoom;

        @Override
        public boolean touchDown (float x, float y, int pointer, int button) {
            previousZoom = 0;
            return false;
        }

        @Override
        public boolean tap (float x, float y, int count, int button) {
            return false;
        }

        @Override
        public boolean longPress (float x, float y) {
            Gdx.input.vibrate(100);
            return true;
        }

        @Override
        public boolean fling (float velocityX, float velocityY, int button) {
            return false;
        }

        @Override
        public boolean pan (float x, float y, float deltaX, float deltaY) {
            return false;
        }

        @Override
        public boolean zoom (float initialDistance, float distance) {
            float newZoom = distance - initialDistance;
            float amount = newZoom - previousZoom;
            previousZoom = newZoom;
            float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
           // return controller.pinchZoom(amount / ((w > h) ? h : w));
            return false;
        }

        @Override
        public boolean pinch (Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
            return false;
        }
    };*/
}
