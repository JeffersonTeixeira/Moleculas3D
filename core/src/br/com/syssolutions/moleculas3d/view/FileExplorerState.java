package br.com.syssolutions.moleculas3d.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import br.com.syssolutions.moleculas3d.Utils.FontGenerator;
import br.com.syssolutions.moleculas3d.control.states.GameStateManager;
import br.com.syssolutions.moleculas3d.control.states.State;
import br.com.syssolutions.moleculas3d.model.Moleculas3D;

/**
 * Created by jefferson on 24/10/17.
 */

public class FileExplorerState extends State {

    private OrthographicCamera camOrtho;


    private String extRoot, locRoot, atualPatch;
    private FileHandle dirHandle;

    private FileHandle[] listFiles;


    private Texture background;
    private Skin skin;
    private TextureAtlas textureAtlas;

    private Texture folderT, fileT, molT;

    private Table container;

    private Stage stage;
    private ScrollPane scrollpane;
    private LabelStyle labelStyle;


    public FileExplorerState(GameStateManager gsm) {
        super(gsm);

        camOrtho = new OrthographicCamera();
        camOrtho.setToOrtho(false, Moleculas3D.WIDTH, Moleculas3D.HEIGHT);


        //Initial Patch:
        if (Gdx.files.isExternalStorageAvailable()) {
            extRoot = Gdx.files.getExternalStoragePath();
            //   System.out.println("external Exists: " + extRoot);
        }
        if (Gdx.files.isLocalStorageAvailable()) {
            locRoot = Gdx.files.getLocalStoragePath();

//            FileHandle file = Gdx.files.local("myfile.txt");
//            file.writeString("My god, it's full of stars", false);

            //   System.out.println("local Exists: " + locRoot);
        }

        skin = new Skin();


        folderT = new Texture(Gdx.files.internal("ui-imagens/folder.png"));
        fileT = new Texture(Gdx.files.internal("ui-imagens/file.png"));
        molT = new Texture(Gdx.files.internal("ui-imagens/mol.png"));


//Internal é do APP

        // dirHandle = Gdx.files.external(extRoot);
        dirHandle = Gdx.files.external("");


//        if (dirHandle.isDirectory()) {
//            for (FileHandle file : dirHandle.list()) {
//                System.out.println(file);
//            }
//        } else {
//            System.out.println("Não é diretório");
//        }


        background = new Texture("ui-imagens/background.jpg");

        labelStyle = new Label.LabelStyle();

        FontGenerator fontTitulo = new FontGenerator(40, "VeraBd.ttf", null);

        labelStyle.font = fontTitulo.getFont();


        showFiles(dirHandle);

    }


    private void carregaPath(FileHandle fileHandle) {


    }


    public void showFiles(final FileHandle fileHandle) {

        dirHandle = fileHandle;

        container = new Table();
        container.setWidth(Gdx.graphics.getWidth());
        container.setHeight(Gdx.graphics.getHeight());
        // container.setFillParent(false);

        Table innerContainer = new Table();
        innerContainer.align(Align.topLeft);


        if (fileHandle.isDirectory()) {

            Table backTable = new Table(skin);
            backTable.add(new Image(folderT)).expandY().fillY().space(10f).left();
            backTable.add(new Label("..", labelStyle)).expandY().fillY().width(container.getWidth() - folderT.getWidth()).left();
            backTable.align(Align.left);


            backTable.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    showFiles(Gdx.files.external(fileHandle.parent().toString()));


                }
            });


            innerContainer.add(backTable);
            innerContainer.row();


            for (FileHandle file : fileHandle.list()) {
                Table table = new Table(skin);
                //table.debugTable();
                final FileHandle fl = file;

                if (file.isDirectory()) {


                    table.add(new Image(folderT)).expandY().fillY().space(10f).left();
                    table.add(new Label(file.name(), labelStyle)).expandY().fillY().width(container.getWidth() - folderT.getWidth()).left();

                    table.align(Align.left);


                    table.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            System.out.println(fl.parent());
                            atualPatch = fl.parent().toString();
                            showFiles(Gdx.files.external(fl.path()));
                        }
                    });

                    innerContainer.add(table);//.expand().fill();

                    innerContainer.row();

                } else {
                    if (file.extension() != "mol") {
                        //innerContainer.add(new Image(fileT)).expandY().fillY();

                        table.add(new Image(fileT)).expandY().fillY().space(10f).left();
                        table.add(new Label(file.name(), labelStyle)).expandY().fillY().width(container.getWidth() - fileT.getWidth()).left();

                    } else {
                        //innerContainer.add(new Image(molT)).expandY().fillY();
                        table.add(new Image(molT)).expandY().fillY().space(10f).left();
                        table.add(new Label(file.name(), labelStyle)).expandY().fillY().width(container.getWidth() - molT.getWidth()).left();
                    }

                    table.align(Align.left);
                    innerContainer.add(table);


//                    innerContainer.add(new Label("", labelStyle)).width(10f).expandY().fillY();// a spacer
//                    innerContainer.add(new Label(file.name(), labelStyle)).expandY().fillY().left();

                    innerContainer.row();

                }

            }
        } else {
            System.out.println("Não é diretório");
        }


        scrollpane = new ScrollPane(innerContainer);
        scrollpane.setScrollingDisabled(true, false);
        container.add(scrollpane).fill().expand();
        stage = new Stage();
        stage.addActor(container);

        Gdx.input.setInputProcessor(stage);


    }


    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {

            showFiles(Gdx.files.external(dirHandle.parent().toString()));

        }


    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(camOrtho.combined);
        sb.begin();
        //sb.draw(background, 0, 0, camOrtho.viewportWidth, camOrtho.viewportHeight);
        sb.end();


        stage.act();
        stage.draw();


    }

    @Override
    public void dispose() {

    }
}
