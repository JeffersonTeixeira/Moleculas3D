package br.com.syssolutions.moleculas3d.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Array;

import java.util.Map;

import br.com.syssolutions.moleculas3d.Utils.FontGenerator;
import br.com.syssolutions.moleculas3d.control.states.GameStateManager;
import br.com.syssolutions.moleculas3d.control.states.State;
import br.com.syssolutions.moleculas3d.model.Biblioteca;
import br.com.syssolutions.moleculas3d.model.ItemBiblioteca;
import br.com.syssolutions.moleculas3d.model.Molecula;
import br.com.syssolutions.moleculas3d.model.Moleculas3D;
import br.com.syssolutions.moleculas3d.model.ReadMoleculaXML;

/**
 * Created by jefferson on 19/03/17.
 */

public class ListMolBibliotecaState extends State implements InputProcessor {

    private OrthographicCamera camOrtho;
    private Texture background;
    private TextField buscaTxField;
    private Stage stage;
    private Skin skin;
    private Pixmap btPixmap;
    private float alturaTextField = Gdx.graphics.getHeight() / 10;
    private float larguraTextField = (Gdx.graphics.getWidth() * (0.75f));

    private ScrollPane scrollPane;
    private List programList;

    private Array<String> lista;
    private Array<String> fullLista;

    private boolean listDragging;
    private boolean buscaFocus;


    private static final String UPPERCASE_ASCII =
            "AEIOU" // grave
                    + "AEIOUY" // acute
                    + "AEIOUY" // circumflex
                    + "AON" // tilde
                    + "AEIOUY" // umlaut
                    + "A" // ring
                    + "C" // cedilla
                    + "OU" // double acute
            ;

    private static final String UPPERCASE_UNICODE =
            "\u00C0\u00C8\u00CC\u00D2\u00D9"
                    + "\u00C1\u00C9\u00CD\u00D3\u00DA\u00DD"
                    + "\u00C2\u00CA\u00CE\u00D4\u00DB\u0176"
                    + "\u00C3\u00D5\u00D1"
                    + "\u00C4\u00CB\u00CF\u00D6\u00DC\u0178"
                    + "\u00C5"
                    + "\u00C7"
                    + "\u0150\u0170";


    public ListMolBibliotecaState(GameStateManager gsm) {
        super(gsm);

        camOrtho = new OrthographicCamera();
        camOrtho.setToOrtho(false, Moleculas3D.WIDTH, Moleculas3D.HEIGHT);
        background = new Texture("ui-imagens/background.jpg");

        buildStage();
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);


    }


    private void buildStage() {
        stage = new Stage();
        stage.clear();

        skin = new Skin();
        skin.add("font", new FontGenerator(120, "Vera.ttf", null).getFont());

        //Textura do Texfield:
        btPixmap = new Pixmap((int) larguraTextField, (int) alturaTextField, Pixmap.Format.RGB888);
        skin.add("texFieldBackground", new Texture(btPixmap));

        //Layout do TextField:
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.background = skin.newDrawable("texFieldBackground", 0, 0, 0, 0.6f);
        textFieldStyle.selection = new Image(new Texture("ui-imagens/listmolbiblioteca/selection.png")).getDrawable();
        textFieldStyle.font = skin.getFont("font");
        textFieldStyle.cursor = new Image(new Texture("ui-imagens/listmolbiblioteca/cursor.png")).getDrawable();
        textFieldStyle.fontColor = Color.WHITE;
        skin.add("default", textFieldStyle);

        buscaTxField = new TextField("", textFieldStyle);
        buscaTxField.setMessageText("Buscar...");
        buscaTxField.setSize(larguraTextField, alturaTextField);
        buscaTxField.setPosition(((Gdx.graphics.getWidth() / 2) - (buscaTxField.getWidth() / 2)),
                Gdx.graphics.getHeight() - alturaTextField);

        buscaTxField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                lista = molSearch(buscaTxField.getText());
                programList.setItems(lista);

                if (c == '\n' || c == '\r') {
                    carregaMoleculaSelecionada();
                }


            }
        });


        buscaTxField.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buscaFocus = true;
                return false;
            }


        });

        stage.addActor(buscaTxField);


        Skin skin2 = new Skin();

        ScrollPane.ScrollPaneStyle style = new ScrollPane.ScrollPaneStyle();
        skin2.add("default", style);

        List.ListStyle listStyle = new List.ListStyle();
        listStyle.font = new FontGenerator(90, "Vera.ttf", null).getFont();

        listStyle.selection = new Image(new Texture("ui-imagens/listmolbiblioteca/selection.png")).getDrawable();


        scrollPane = new ScrollPane(null, skin2);
        scrollPane.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - alturaTextField);
        scrollPane.setScrollingDisabled(true, false);
        programList = new List<String>(listStyle);


        lista = new Array<String>();


        lista = molSearch("");

        programList.setItems(lista);


        scrollPane.setWidget(programList);

        stage.addActor(scrollPane);


        scrollPane.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                stage.unfocus(buscaTxField);
                buscaFocus = false;
                Gdx.input.setOnscreenKeyboardVisible(false);

                return false;
            }


        });


    }


    @Override
    protected void handleInput() {


        //Tecla back android
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            gsm.set(new MenuInicialState(gsm));
            dispose();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {


        sb.setProjectionMatrix(camOrtho.combined); //especifica área do game world zoomAmount ser mostrada
        sb.begin();
        sb.draw(background, 0, 0, camOrtho.viewportWidth, camOrtho.viewportHeight);
        sb.end();

        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
        btPixmap.dispose();

    }

    public Array<String> molSearch(String search) {

        if (!search.isEmpty()) {

            Array<String> results = new Array<String>();
            for (Map.Entry<String, ItemBiblioteca> entry : Biblioteca.getBiblioteca().entrySet()) {


                if (toUpperCaseSansAccent(entry.getKey()).contains(toUpperCaseSansAccent(search)))
                    results.add(entry.getKey());
            }

            return results;

        } else {
            if (fullLista == null) {
                fullLista = new Array<String>();

                for (Map.Entry<String, ItemBiblioteca> entry : Biblioteca.getBiblioteca().entrySet()) {

                    fullLista.add(entry.getKey());
                }


                return fullLista;
            } else
                return fullLista;
        }

    }

    public static String toUpperCaseSansAccent(String txt) {

        if (txt == null) {
            return null;
        }
        String txtUpper = txt.toUpperCase();
        StringBuilder sb = new StringBuilder();
        int n = txtUpper.length();
        for (int i = 0; i < n; i++) {
            char c = txtUpper.charAt(i);
            int pos = UPPERCASE_UNICODE.indexOf(c);
            if (pos > -1) {
                sb.append(UPPERCASE_ASCII.charAt(pos));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private void carregaMoleculaSelecionada() {
        try {
            Molecula mol = ReadMoleculaXML.read(Biblioteca.getBiblioteca().get(programList.getSelected()));

            Visualizador3DState.setMolecula(mol);
            gsm.set(new Visualizador3DState(gsm));
        } catch (Exception e) {
            System.out.println("Falha ao carregar molécula" + e);
        }

        dispose();
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
        listDragging = false;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!listDragging && !buscaFocus /*&& idSelecionado == programList.getSelectedIndex() && !(TimeUtils.nanoTime() - lastTouchTime > tapCountInterval)*/) {
            carregaMoleculaSelecionada();
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        listDragging = true;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
