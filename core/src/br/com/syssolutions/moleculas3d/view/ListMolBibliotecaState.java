package br.com.syssolutions.moleculas3d.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import br.com.syssolutions.moleculas3d.control.states.GameStateManager;
import br.com.syssolutions.moleculas3d.control.states.State;
import br.com.syssolutions.moleculas3d.model.FontGenerator;
import br.com.syssolutions.moleculas3d.model.Moleculas3D;

/**
 * Created by jefferson on 19/03/17.
 */

public class ListMolBibliotecaState extends State {

    private OrthographicCamera camOrtho;
    private Texture background;
    private TextField buscaTxField;
    private Stage stage;
    private Skin skin;
    private Pixmap btPixmap;
    private float alturaTextField = Gdx.graphics.getHeight() / 10;
    private float larguraTextField =(Gdx.graphics.getWidth() * (0.75f));

    private ScrollPane scrollPane;

    public ListMolBibliotecaState(GameStateManager gsm) {
        super(gsm);

        camOrtho = new OrthographicCamera();
        camOrtho.setToOrtho(false, Moleculas3D.WIDTH, Moleculas3D.HEIGHT);

        background = new Texture("ui-imagens/background.jpg");

        buildStage();
        Gdx.input.setInputProcessor(stage);

        //scrollPane = new ScrollPane();

        //ReadMoleculaXML.read();
//        Molecula mol = new Molecula();
//
//        try {
//
//
//            mol = ReadMoleculaXML.read(Biblioteca.getBiblioteca().get(0));
//
//        } catch (Exception e) {
//            System.out.println("Erro ao carregar Molécula: " + e);
//        }


    }


    private void buildStage() {


        stage = new Stage();
        stage.clear();

        skin=new Skin();
        skin.add("font", new FontGenerator(120, "Vera.ttf", null).getFont());

        //Textura do Texfield:
        btPixmap = new Pixmap((int) larguraTextField, (int) alturaTextField, Pixmap.Format.RGB888);
        skin.add("texFieldBackground", new Texture(btPixmap));

        //Layout do TextField:
        TextField.TextFieldStyle textFieldStyle= new TextField.TextFieldStyle();
        textFieldStyle.background = skin.newDrawable("texFieldBackground", 0, 0, 0, 0.6f);
        textFieldStyle.selection = new Image(new Texture("ui-imagens/listmolbiblioteca/selection.png")).getDrawable();
        textFieldStyle.font = skin.getFont("font");
        textFieldStyle.cursor = new Image(new Texture("ui-imagens/listmolbiblioteca/cursor.png")).getDrawable();
        textFieldStyle.fontColor = Color.WHITE;
        skin.add("default", textFieldStyle);

        buscaTxField = new TextField("",textFieldStyle);
        buscaTxField.setMessageText("Buscar");
        buscaTxField.setSize(larguraTextField,alturaTextField);
        buscaTxField.setPosition(((Gdx.graphics.getWidth() / 2) - (buscaTxField.getWidth() / 2)),
                Gdx.graphics.getHeight() - alturaTextField);


        stage.addActor(buscaTxField);



    }


    @Override
    protected void handleInput() {
//        if (Gdx.input.isTouched()) {
//            System.out.println("Tela foi tocada");
//            dispose();
//            gsm.set(new Visualizador3DState(gsm));
//
//
//        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camOrtho.combined); //especifica área do game world a ser mostrada
        sb.begin();
        //sb.draw(background, 0, 0, camOrtho.viewportWidth, camOrtho.viewportHeight);
        sb.end();

        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
        btPixmap.dispose();

    }


}
