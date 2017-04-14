package br.com.syssolutions.moleculas3d.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntFloatMap;

import java.util.ArrayList;

import br.com.syssolutions.moleculas3d.control.states.GameStateManager;
import br.com.syssolutions.moleculas3d.control.states.State;
import br.com.syssolutions.moleculas3d.model.Biblioteca;
import br.com.syssolutions.moleculas3d.model.FontGenerator;
import br.com.syssolutions.moleculas3d.model.ItemBiblioteca;
import br.com.syssolutions.moleculas3d.model.Molecula;
import br.com.syssolutions.moleculas3d.model.Moleculas3D;
import br.com.syssolutions.moleculas3d.model.ReadMoleculaXML;

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
    private float larguraTextField = (Gdx.graphics.getWidth() * (0.75f));

    private ScrollPane scrollPane;
    private List programList;

    public ListMolBibliotecaState(GameStateManager gsm) {
        super(gsm);

        camOrtho = new OrthographicCamera();
        camOrtho.setToOrtho(false, Moleculas3D.WIDTH, Moleculas3D.HEIGHT);

        background = new Texture("ui-imagens/background.jpg");

        buildStage();
        Gdx.input.setInputProcessor(stage);

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


        stage.addActor(buscaTxField);


        Skin skin2 = new Skin();

        ScrollPane.ScrollPaneStyle style = new ScrollPane.ScrollPaneStyle();
        //style.vScrollKnob = skin.newDrawable("texFieldBackground", 0, 0, 0, 0.6f);

        //style.vScroll = new Image(new Texture("ui-imagens/listmolbiblioteca/selection.png")).getDrawable();
        skin2.add("default", style);

        List.ListStyle listStyle = new List.ListStyle();
        listStyle.font = new FontGenerator(90, "Vera.ttf", null).getFont();
        //listStyle.fontColorSelected = Color.BLACK;
        //listStyle.fontColorUnselected = Color.BLUE;
        listStyle.selection = new Image(new Texture("ui-imagens/listmolbiblioteca/selection.png")).getDrawable();


        scrollPane = new ScrollPane(null, skin2);
        scrollPane.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - alturaTextField);
        programList = new List<String>(listStyle);


        Array<String> lista = new Array<String>();


        for (ItemBiblioteca itemBiblioteca : Biblioteca.getBiblioteca()) {
            lista.add(itemBiblioteca.getName());

        }



        programList.setItems(lista);


        scrollPane.setWidget(programList);
        // scrollPane.setFillParent(true);

       //scrollPane.cancelTouchFocus();








        stage.addActor(scrollPane);


    }


    @Override
    protected void handleInput() {

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            gsm.set(new MenuInicialState(gsm));
            dispose();
        }


        System.out.println(scrollPane.isDragging());




//        if(scrollPane.isDragging()){
//
//            System.out.println("Rolando!!!");


     //   }
        if (Gdx.input.isTouched(1)) {

            if(scrollPane.isDragging()){

                System.out.println("Rolando!!!");


            }





            try {

                Molecula mol = ReadMoleculaXML.read(Biblioteca.getBiblioteca().get(programList.getSelectedIndex()));

                Visualizador3DState.setMolecula(mol);

            } catch (Exception e) {
                System.out.println("Falha ao carregar molécula" + e);
            }
            gsm.set(new Visualizador3DState(gsm));
            dispose();

        }



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
