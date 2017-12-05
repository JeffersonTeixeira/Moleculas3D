package br.com.syssolutions.moleculas3d.view;

/**
 * Created by Jefferson on 31/07/2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import br.com.syssolutions.moleculas3d.Utils.FontGenerator;
import br.com.syssolutions.moleculas3d.model.Moleculas3D;
import br.com.syssolutions.moleculas3d.control.states.GameStateManager;
import br.com.syssolutions.moleculas3d.control.states.State;

public class MenuInicialState extends State {

    private float btnAltura = Gdx.graphics.getHeight() / 8; // Tela dividida em 8 partes
    private float btnLargura = (Gdx.graphics.getWidth() * (0.85f));// botões ocupando 85% da largura da tela

    private Stage stage;

    private Skin skin;
    private Texture background;
    private Pixmap btPixmap;
    private Label labelTitulo;

    private TextButton btBiblioteca;
    private TextButton btCartaoSD;


    private OrthographicCamera camOrtho;


    public MenuInicialState(final GameStateManager gsm) {
        super(gsm);

        camOrtho = new OrthographicCamera();
        camOrtho.setToOrtho(false, Moleculas3D.WIDTH, Moleculas3D.HEIGHT);

        background = new Texture("ui-imagens/background.jpg");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        skin = new Skin();

        carregaTituloApp();

        carregaBotoes();


    }


    @Override
    public void handleInput() {

    }

    public void update(float dt) {
        //handleInput();
    }

    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(camOrtho.combined); //especifica área do game world zoomAmount ser mostrada
        sb.begin();
        sb.draw(background, 0, 0, camOrtho.viewportWidth, camOrtho.viewportHeight);
        sb.end();

        stage.act();
        stage.draw();
    }


    private void carregaBotoes() {

        //Skin Botões:
        //Fonte do botão:
        skin.add("btnFont", new FontGenerator(120, "Vera.ttf", null).getFont());

        //Textura dos botões:
        btPixmap = new Pixmap((int) btnLargura, (int) btnAltura, Pixmap.Format.RGB888);
        skin.add("btBackground", new Texture(btPixmap));

        //Layout do botão:
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("btBackground", 0, 0, 0, 0.6f);
        textButtonStyle.down = skin.newDrawable("btBackground", Color.BLACK);
        textButtonStyle.font = skin.getFont("btnFont");
        skin.add("default", textButtonStyle);
        //---------


        btBiblioteca = new TextButton("Biblioteca", skin);
        btBiblioteca.setPosition(((Gdx.graphics.getWidth() / 2) - (btnLargura / 2)), Gdx.graphics.getHeight() / 2);
        stage.addActor(btBiblioteca);

        btCartaoSD = new TextButton("Buscar", skin);
        btCartaoSD.setPosition(((Gdx.graphics.getWidth() / 2) - (btnLargura / 2)), Gdx.graphics.getHeight() / 3);
        stage.addActor(btCartaoSD);


        btCartaoSD.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                gsm.set(new FileExplorerState(gsm));
                dispose();

            }
        }));

        btBiblioteca.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.set(new ListMolBibliotecaState(gsm));
                dispose();
            }
        });

    }

    private void carregaTituloApp() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.borderWidth = 5f;

        FontGenerator fontTitulo = new FontGenerator(130, "VeraBd.ttf", parameter);

        labelStyle.font = fontTitulo.getFont();

        labelTitulo = new Label("Moléculas3D", labelStyle);
        labelTitulo.setPosition(((Gdx.graphics.getWidth() / 2) - (labelTitulo.getWidth() / 2)),
                Gdx.graphics.getHeight() - btnAltura);

        stage.addActor(labelTitulo);
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
        skin.dispose();
        btPixmap.dispose();


    }

}
