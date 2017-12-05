package br.com.syssolutions.moleculas3d.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import br.com.syssolutions.moleculas3d.Utils.FontGenerator;
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

public class Visualizador3DState extends State {


    private boolean voltaArmazenamento;

    private final float BUTTONSIZE = Gdx.graphics.getWidth() / 8; //média de 12% da largura da tela

    static Molecula molecula;

    private Visualizacao visualizacao; //(LINE,    SPACE_FILLING,    STICK,    STICK_AND_BALL)

    public CameraInputController camController;

    public ModelBatch modelBatch;

    public Array<ModelInstance> instances = new Array<ModelInstance>();
    //public ModelInstance instance;
    public Environment environment;
    public boolean loading;

    private boolean mostrarInfo = true;
    private Window molinfo;

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

    private Label.LabelStyle labelStyle;
    private Label labelTitulo;


    //Variáveis para controlar os Gestos com touch
    float zoom; //Mantem o Valor do zoom atual;
    float zoomAmount;//armazena o montante do zoom (mais ou menos)


    public static void setMolecula(Molecula molecula) {
        Visualizador3DState.molecula = molecula;
    }

    public Visualizador3DState(GameStateManager gsm, boolean voltaArmazenamento) {
        super(gsm);
        this.voltaArmazenamento = voltaArmazenamento;
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
        camController = new CameraInputController(cam);

        buildStage();


        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(camController);
        Gdx.input.setInputProcessor(multiplexer);

        //Carrega o Modelo padrão de visualização:
//?????????????????

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


        mostrarTituloMol(molecula.titulo);


    }


    private void buildInfoWindow() {

        Table container = new Table();


        float windowWidth = Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 0.10f);
        float windowHeight = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 0.15f);


        Skin skinWin = new Skin();

        //Criando Background
        Pixmap btPixmap = new Pixmap((int) windowWidth, (int) windowHeight, Pixmap.Format.RGB888);
        skinWin.add("background", new Texture(btPixmap));

        //Criando a fonte
        FontGenerator fontTitulo = new FontGenerator(50, "VeraBd.ttf", null);
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = fontTitulo.getFont();


        molinfo = new Window("", windowStyle);
        molinfo.setBackground(skinWin.newDrawable("background", 0, 0, 0, 0.85f));
        //molinfo.pack();


        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        ScrollPane scrollPane;// = new ScrollPane(container, scrollPaneStyle);








//        molinfo.debugTable();


        if (infoReader() == null) {
            Label.LabelStyle labelStyle = new Label.LabelStyle();
            labelStyle.font = new FontGenerator(70, "VeraBd.ttf", null).getFont();


            Label none = new Label("Nenhuma informação disponível.", labelStyle);
            none.setWrap(true);

            molinfo.add(none).width(windowWidth).row();

            scrollPane = null;
        }else{
            container.add(infoReader()).width(windowWidth).align(Align.topLeft).row();
            container.debug();

            scrollPane = new ScrollPane(container,scrollPaneStyle);



            molinfo.add(scrollPane);
        }


        molinfo.debug();


        molinfo.setSize(windowWidth, windowHeight);
        molinfo.setPosition((Gdx.graphics.getWidth() / 2) - (molinfo.getWidth() / 2), (Gdx.graphics.getHeight() - infobtn.getHeight()) - molinfo.getHeight());

        scrollPane.setFillParent(true)        ;
        //molinfo.add(scrollPane).fill().expand();
        stage.addActor(molinfo);
    }

    private Label infoReader() {


        Label.LabelStyle style = labelStyle;




        Label label = new Label("A Molécula é assim assim e assado foi decoberta por alguém em algum ano " +
                "do calendario gregoriano " +
                "ed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? ", style);
        label.setWrap(true);


        return label;


    }

    private void mostrarTituloMol(String titulo) {

        if (!titulo.equals("")) {


            labelStyle = new Label.LabelStyle();

            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.borderWidth = 5f;

            FontGenerator fontTitulo = new FontGenerator(70, "VeraBd.ttf", parameter);
            labelStyle.font = fontTitulo.getFont();


            if (titulo.length() > 10 && titulo.length() < 18) {

            }


            labelTitulo = new Label(titulo, labelStyle);


            //labelTitulo.setWrap(true);


//            float width = 300f ;//(voltarbtn.getWidth()+infobtn.getWidth())-Gdx.graphics.getWidth();
//
//            labelTitulo.setWidth(width);
//            labelTitulo.pack();
//            labelTitulo.setWidth(width);

            //   labelTitulo = new Label("@@@@@@@@@\n@@@@@@@@@", labelStyle);


            labelTitulo.setPosition(((Gdx.graphics.getWidth() / 2) - (labelTitulo.getWidth() / 2)), Gdx.graphics.getHeight() - labelTitulo.getHeight());
            stage.addActor(labelTitulo);

        }


    }

    private void mostrarInfo() {

        if (molinfo == null) {
            buildInfoWindow();
        }


        molinfo.setVisible(mostrarInfo);
        mostrarInfo = !mostrarInfo;


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
        // stage.addActor(stickBtn);

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

        voltarbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                actionBackkey();
            }
        });

        infobtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("oi");
                mostrarInfo();

            }
        });


    }


    private void carregaConfAmbiente() {


        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        //environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, (cam.up.x - 1f), (cam.up.x - 0.8f), (cam.up.x - 0.2f)));


        //cam.up.x;

        //cam.direction
        //environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));


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

    private void actionBackkey() {
        if (voltaArmazenamento) {
            gsm.set(new FileExplorerState(gsm));
            dispose();

        } else {
            gsm.set(new ListMolBibliotecaState(gsm));
            dispose();
        }

        dispose();
    }


    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            actionBackkey();

        }
    }


    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {


        //carregaConfAmbiente();
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


        Gdx.gl.glClearColor(153 / 250f, 191 / 250f, 230 / 250f, 0f);
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
        try {
            modelBatch.dispose();
            instances.clear();
            stage.dispose();
            skinVisualizador3D.dispose();
        } catch (IllegalArgumentException ex) {

        }


    }
}