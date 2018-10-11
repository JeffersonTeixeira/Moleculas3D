package br.com.syssolutions.moleculas3d.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static ModelBatch modelBatch;
    private TextureAtlas textureAtlasPack;

    public Array<ModelInstance> instances = new Array<ModelInstance>();
    //public ModelInstance instance;
    public static Environment environment;
    public boolean loading;

    private boolean mostrarInfo = true;
    private Window winMolInfo;

    //Itens do menu de configurações:
    private Stage stage;
    private Skin skinVisualizador3D, skinWin;


    private ButtonGroup visuGroup;
    private Button spaceFillingBtn;
    private Button stickAndBallBtn;
    private Button stickBtn;

    private Button voltarbtn;
    private Button ajudabtn;
    private Button infobtn;

    private Label.LabelStyle labelStyle;
    private Label labelTitulo;

    private String patchInfo = "mol/info/";

    //Variáveis para mostrar ajuda
    private boolean mostrarAjudaLoaded;
    private static Image rotacionarIcon;
    private static Image zoomMaisIcon;
    private static Image zoomMenosIcon;

    private FontGenerator fontTituloMol, labelFont;

    private Pixmap windowsBackGroundPixmap;
    private Texture winTexture;


    //Variáveis para controlar os Gestos com touch
    float zoom; //Mantem o Valor do zoom atual;
    float zoomAmount;//armazena o montante do zoom (mais ou menos)


    public static void setMolecula(Molecula molecula) {
        Visualizador3DState.molecula = molecula;
    }

    public Visualizador3DState(GameStateManager gsm, boolean voltaArmazenamento) {
        super(gsm);
        this.voltaArmazenamento = voltaArmazenamento;


        if (modelBatch == null) {
            modelBatch = new ModelBatch();
        }


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

        spaceFillingBtn.setChecked(true);
    }

    private void buildStage() {


        textureAtlasPack = new TextureAtlas("ui-imagens/visualizador3d.pack");


        skinVisualizador3D = new Skin(

                Gdx.files.internal("ui-imagens/visualizador3d.json"), textureAtlasPack);

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

        float windowWidth = Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 0.10f);
        float windowHeight = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 0.15f);

        skinWin = new Skin();

        //Criando Background
        windowsBackGroundPixmap = new Pixmap((int) windowWidth, (int) windowHeight, Pixmap.Format.RGB888);
        winTexture = new Texture(windowsBackGroundPixmap);
        skinWin.add("background", winTexture);

        //Criando a fonte
        //FontGenerator fontTituloWin = new FontGenerator(10, "VeraBd.ttf", null);
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = fontTituloMol.getFont();//Usada a font

        winMolInfo = new Window("", windowStyle);
        winMolInfo.setBackground(skinWin.newDrawable("background", 0, 0, 0, 0.85f));


        labelFont = new FontGenerator(50, "VeraBd.ttf", null);

        if (infoReader() == null) {


            Label.LabelStyle labelStyle = new Label.LabelStyle();
            labelStyle.font = labelFont.getFont();


            Label none = new Label("Nenhuma informação disponível.", labelStyle);
            none.setWrap(true);

            winMolInfo.add(none).width(windowWidth).row();

        } else {
            Table container = new Table();

            ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
            ScrollPane scrollPane;


            for (Widget widget : infoReader()) {

                container.add(widget).width(windowWidth).align(Align.topLeft).row();

            }

            scrollPane = new ScrollPane(container, scrollPaneStyle);

            winMolInfo.add(scrollPane);

            scrollPane.setFillParent(true);
        }

        winMolInfo.setSize(windowWidth, windowHeight);
        winMolInfo.setPosition((Gdx.graphics.getWidth() / 2) - (winMolInfo.getWidth() / 2), (Gdx.graphics.getHeight() - infobtn.getHeight()) - winMolInfo.getHeight());

        stage.addActor(winMolInfo);
    }

    private ArrayList<Widget> infoReader() {

        if (molecula.filename == null) {
            return null;

        } else if (!voltaArmazenamento) {
            try {

                FileHandle fh = new FileHandle(molecula.filename);

                String name = fh.nameWithoutExtension();

                InputStream is = Gdx.files.internal(patchInfo + name + ".info").read();

                Scanner scanner = new Scanner(is);

                ArrayList<Widget> arrayList = new ArrayList<Widget>();

                Label.LabelStyle style = new Label.LabelStyle();
                style.font = labelFont.getFont();

                while (scanner.hasNext()) {

                    String line = scanner.nextLine();

                    if (line.contains("#img")) {

                        String token[] = line.split("#img");

                        String patchImg = token[1].trim();

                        Image image = new Image(new Texture(Gdx.files.internal(patchInfo + patchImg)));

                        //image.setSize(100f,100f);


                        arrayList.add(image);

                    } else {
                        Label label = new Label(line, style);
                        label.setWrap(true);
                        arrayList.add(label);
                    }

                }
                return arrayList;

            } catch (GdxRuntimeException e) {

                return null;
            }


        } else {
            return null;
        }


    }

    private void mostrarTituloMol(String titulo) {

        if (!titulo.equals("")) {


            labelStyle = new Label.LabelStyle();

            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.borderWidth = 5f;

            fontTituloMol = new FontGenerator(70, "VeraBd.ttf", parameter);
            labelStyle.font = fontTituloMol.getFont();


            if (titulo.length() > 13) {

                String nomeDividido;
                // titulo = nomeDividido;


                if (titulo.charAt(13) == '-' || titulo.charAt(13) == ' ') {

                    System.out.println("CharAt11 - ou \"\" ");

                    String s1, s2;

                    s1 = titulo.substring(0, 13);
                    s2 = titulo.substring(14, titulo.length());

                    System.out.println("s1= " + s1);
                    System.out.println("s2= " + s2);

                    nomeDividido = s1 + "\n" + s2;
                } else {

                    String s1, s2;

                    s1 = titulo.substring(0, 13);
                    s2 = titulo.substring(13, titulo.length());


                    //nomeDividido=s1+"-\n"+s2;
                    nomeDividido = s1 + "\n" + s2;


                }


                if (nomeDividido.length() > 25) {

                    nomeDividido = nomeDividido.substring(0, 23) + "...";


                }


                labelTitulo = new Label(nomeDividido, labelStyle);


                //Dividir e terminar na linha abaixo

            } else { //Não é necessário dividir
                labelTitulo = new Label(titulo, labelStyle);
            }


            labelTitulo.setPosition(((Gdx.graphics.getWidth() / 2) - (labelTitulo.getWidth() / 2)), Gdx.graphics.getHeight() - labelTitulo.getHeight());
            stage.addActor(labelTitulo);

        }


    }

    private void mostrarInfo() {

        if (winMolInfo == null) {
            buildInfoWindow();
        }


        winMolInfo.setVisible(mostrarInfo);
        mostrarInfo = !mostrarInfo;


    }

    private void mostrarAjuda() {

        if (mostrarAjudaLoaded) {

            rotacionarIcon.setVisible(!rotacionarIcon.isVisible());
            zoomMaisIcon.setVisible(!zoomMaisIcon.isVisible());
            zoomMenosIcon.setVisible(!zoomMenosIcon.isVisible());

        } else {

            float iconSize = Gdx.graphics.getWidth() * 0.25f;


            if (rotacionarIcon == null) {
                rotacionarIcon = new Image(new Texture("ui-imagens/ajudaicones/rotacionar.png"));//.getDrawable();
                rotacionarIcon.setSize(iconSize, iconSize);
                rotacionarIcon.setPosition(0, ajudabtn.getHeight());
            }

            if (zoomMaisIcon == null) {
                zoomMaisIcon = new Image(new Texture("ui-imagens/ajudaicones/maiszoom.png"));
                zoomMaisIcon.setSize(iconSize, iconSize);
                zoomMaisIcon.setPosition(iconSize, ajudabtn.getHeight());
            }

            if (zoomMenosIcon == null) {
                zoomMenosIcon = new Image(new Texture("ui-imagens/ajudaicones/menoszoom.png"));
                zoomMenosIcon.setSize(iconSize, iconSize);
                zoomMenosIcon.setPosition(iconSize * 2, ajudabtn.getHeight());
            }

            stage.addActor(rotacionarIcon);
            stage.addActor(zoomMaisIcon);
            stage.addActor(zoomMenosIcon);

            mostrarAjudaLoaded = true;


        }


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
                mostrarInfo();

            }
        });

        ajudabtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mostrarAjuda();

            }
        });


    }


    private void carregaConfAmbiente() {

        if (environment == null) {
            environment = new Environment();
            environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
            environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        }


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

        try {
            if (winMolInfo.isVisible()) {
                winMolInfo.setVisible(false);
                Thread.sleep(300);
            } else {
                throw new NullPointerException();
            }

        } catch (NullPointerException e) {
            if (voltaArmazenamento) {

                gsm.set(new FileExplorerState(gsm));


            } else {
                gsm.set(new ListMolBibliotecaState(gsm));

            }
            dispose();
        } catch (InterruptedException e) {

        }


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
            skinWin.dispose();

            fontTituloMol.dispose();
            labelFont.dispose();
            textureAtlasPack.dispose();
            windowsBackGroundPixmap.dispose();
            winTexture.dispose();


        } catch (Exception ex) {

        }


    }
}