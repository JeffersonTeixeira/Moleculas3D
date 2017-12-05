package br.com.syssolutions.moleculas3d.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import java.io.File;
import java.io.FileNotFoundException;

import br.com.syssolutions.moleculas3d.Utils.FontGenerator;
import br.com.syssolutions.moleculas3d.control.states.GameStateManager;
import br.com.syssolutions.moleculas3d.control.states.State;
import br.com.syssolutions.moleculas3d.model.Molecula;
import br.com.syssolutions.moleculas3d.model.Moleculas3D;
import br.com.syssolutions.moleculas3d.model.ReadMolecula;

/**
 * Created by jefferson on 24/10/17.
 */

public class FileExplorerState extends State {


    private static String lastPath = "", root = "";

    private OrthographicCamera camOrtho;

    private Skin skin;
    private Texture background;
    private TextureAtlas textureAtlas;

    private Texture folderT, fileT, molT, folderUP;

    private static Table container, backTable;


    private Stage stage;
    private ScrollPane scrollpane;
    private LabelStyle labelStyle;
    private ScrollPane.ScrollPaneStyle scrollPaneStyle;


    public FileExplorerState(GameStateManager gsm) {
        super(gsm);

        init();


        if ((lastPath != null) && !lastPath.equals("") && (root != null)) {

            try {
                if (!root.equals("")) {
                    throw new FileNotFoundException("LastPath não está na memória interna");
                }
                FileHandle fileHandle = Gdx.files.external(new FileHandle(lastPath).toString());

                showFilesEx(fileHandle);

            } catch (Exception e) {

                try {
                    FileHandle fileHandle = Gdx.files.absolute(new FileHandle(lastPath).toString());
                    showFilesAb(fileHandle, root);
                } catch (Exception ex) {
                    showStorageAvailable();

                }

            }

        } else {
            showStorageAvailable();
        }


    }


    private void init() {
        camOrtho = new OrthographicCamera();
        camOrtho.setToOrtho(false, Moleculas3D.WIDTH, Moleculas3D.HEIGHT);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();


        ScrollPane.ScrollPaneStyle style = new ScrollPane.ScrollPaneStyle();
        style.vScrollKnob = new Image(new Texture("ui-imagens/listmolbiblioteca/scrollBarThumb.png")).getDrawable();


        skin.add("default", style);


        folderT = new Texture(Gdx.files.internal("ui-imagens/folder.png"));
        fileT = new Texture(Gdx.files.internal("ui-imagens/file.png"));
        molT = new Texture(Gdx.files.internal("ui-imagens/mol.png"));
        folderUP = new Texture(Gdx.files.internal("ui-imagens/folderUP.png"));


        background = new Texture("ui-imagens/background.jpg");
        labelStyle = new Label.LabelStyle();
        FontGenerator fontTitulo = new FontGenerator(40, "VeraBd.ttf", null);
        labelStyle.font = fontTitulo.getFont();

        scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPaneStyle.hScrollKnob = new Image(new Texture("ui-imagens/listmolbiblioteca/scrollBarThumb.png")).getDrawable();


    }


    private void showStorageAvailable() {

        container = new Table();
        container.setWidth(Gdx.graphics.getWidth());
        container.setHeight(Gdx.graphics.getHeight());

        Table innerContainer = new Table();
        innerContainer.align(Align.topLeft);


        if (Gdx.files.external("").isDirectory()) {


            Table table = new Table(skin);
            //table.debugTable();
            final FileHandle file = Gdx.files.external("");

            if (file.isDirectory()) {


                table.add(new Image(folderT)).expandY().fillY().space(10f).left();
                table.add(new Label("Interno", labelStyle)).expandY().fillY().width(container.getWidth() - folderT.getWidth()).left();

                table.align(Align.left);


                table.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                        showFilesEx(Gdx.files.external(file.path()));
                    }
                });

                innerContainer.add(table);//.expand().fill();

                innerContainer.row();

            }
        }

        try {
            for (File file : Moleculas3D.iEnvironmentAndroid.getRemovabeStorages()) {

                Table table = new Table(skin);
                final FileHandle f = Gdx.files.absolute(file.getAbsolutePath());

                if (f.isDirectory()) {


                    table.add(new Image(folderT)).expandY().fillY().space(10f).left();
                    table.add(new Label("Externo", labelStyle)).expandY().fillY().width(container.getWidth() - folderT.getWidth()).left();

                    table.align(Align.left);


                    table.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {

                            showFilesAb(Gdx.files.absolute(f.path()), f.path().toString());
                        }
                    });

                    innerContainer.add(table);//.expand().fill();

                    innerContainer.row();

                }


            }
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }

        scrollpane = new ScrollPane(innerContainer, skin);
        scrollpane.setScrollingDisabled(true, false);
        container.add(scrollpane).fill().expand();

        stage = new Stage();
        stage.addActor(container);
        Gdx.input.setInputProcessor(stage);


    }


    public void showFilesEx(final FileHandle fileHandle) {

        root = "";

        container = new Table();
        container.setWidth(Gdx.graphics.getWidth());
        container.setHeight(Gdx.graphics.getHeight());
        // container.setFillParent(false);

        Table innerContainer = new Table();
        innerContainer.align(Align.topLeft);


        if (fileHandle.isDirectory()) {

            backTable = new Table(skin);
            backTable.add(new Image(folderUP)).expandY().fillY().space(10f).left();
            backTable.add(new Label("..", labelStyle)).expandY().fillY().width(container.getWidth() - folderT.getWidth()).left();

            backTable.align(Align.left);


            backTable.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    if (fileHandle.path().equals("")) {
                        backTable = null;
                        showStorageAvailable();

                    } else
                        showFilesEx(Gdx.files.external(fileHandle.parent().toString()));


                }
            });


            innerContainer.add(backTable);
            innerContainer.row();


            for (final FileHandle file : fileHandle.list()) {
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
                            showFilesEx(Gdx.files.external(fl.path()));
                        }
                    });

                    innerContainer.add(table);//.expand().fill();

                    innerContainer.row();

                } else {
                    if (!(file.extension().equals("cml") ||
                            file.extension().equals("xyz") ||
                            file.extension().equals("mol") ||
                            file.extension().equals("mol2"))) {
                        //innerContainer.add(new Image(fileT)).expandY().fillY();

                        table.add(new Image(fileT)).expandY().fillY().space(10f).left();
                        table.add(new Label(file.name(), labelStyle)).expandY().fillY().width(container.getWidth() - fileT.getWidth()).left();

                    } else {
                        //innerContainer.add(new Image(molT)).expandY().fillY();
                        table.add(new Image(molT)).expandY().fillY().space(10f).left();
                        table.add(new Label(file.name(), labelStyle)).expandY().fillY().width(container.getWidth() - molT.getWidth()).left();


                        table.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {


                                try {
                                    Molecula mol = ReadMolecula.read(fl, false);


                                    lastPath = fl.parent().toString();
                                    Visualizador3DState.setMolecula(mol);
                                    gsm.set(new Visualizador3DState(gsm, true));


                                    dispose();
                                } catch (Exception e) {
                                    System.out.println("Falha ao carregar molécula" + e);
                                }


                            }
                        });


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
            showStorageAvailable();
        }


        scrollpane = new ScrollPane(innerContainer, skin);
        scrollpane.setScrollingDisabled(true, false);
        container.add(scrollpane).fill().expand();

        stage = new Stage();
        stage.addActor(container);
        Gdx.input.setInputProcessor(stage);


    }


    public void showFilesAb(final FileHandle fileHandle, String raiz) {

        root = raiz;


        container = new Table();
        container.setWidth(Gdx.graphics.getWidth());
        container.setHeight(Gdx.graphics.getHeight());
        // container.setFillParent(false);

        Table innerContainer = new Table();
        innerContainer.align(Align.topLeft);


        if (fileHandle.isDirectory()) {

            backTable = new Table(skin);
            backTable.add(new Image(folderUP)).expandY().fillY().space(10f).left();


            backTable.add(new Label("..", labelStyle)).expandY().fillY().width(container.getWidth() - folderT.getWidth()).left();

            backTable.align(Align.left);


            backTable.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    if ((fileHandle.path().equals(root))/*||fileHandle.parent().path().toString()==root*/) {
                        backTable = null;
                        showStorageAvailable();

                    } else {
                        showFilesAb(Gdx.files.absolute(fileHandle.parent().toString()), root);
                    }


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

                            showFilesAb(Gdx.files.absolute(fl.path()), root);
                        }
                    });

                    innerContainer.add(table);//.expand().fill();

                    innerContainer.row();

                } else {

                    if (!(file.extension().equals("cml") ||
                            file.extension().equals("xyz") ||
                            file.extension().equals("mol") ||
                            file.extension().equals("mol2"))) {
                        //innerContainer.add(new Image(fileT)).expandY().fillY();

                        table.add(new Image(fileT)).expandY().fillY().space(10f).left();
                        table.add(new Label(file.name(), labelStyle)).expandY().fillY().width(container.getWidth() - fileT.getWidth()).left();

                    } else {
                        //innerContainer.add(new Image(molT)).expandY().fillY();
                        table.add(new Image(molT)).expandY().fillY().space(10f).left();
                        table.add(new Label(file.name(), labelStyle)).expandY().fillY().width(container.getWidth() - molT.getWidth()).left();


                        table.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {


                                try {
                                    Molecula mol = ReadMolecula.read(fl, true);
                                    lastPath = fl.parent().toString();

                                    Visualizador3DState.setMolecula(mol);
                                    gsm.set(new Visualizador3DState(gsm, true));
                                    dispose();
                                } catch (Exception e) {
                                    System.out.println("Falha ao carregar molécula" + e);
                                }


                            }
                        });

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
            showStorageAvailable();
        }


        scrollpane = new ScrollPane(innerContainer, skin);
        scrollpane.setScrollingDisabled(true, false);
        container.add(scrollpane).fill().expand();

        stage = new Stage();
        stage.addActor(container);
        Gdx.input.setInputProcessor(stage);


    }

    private void actionBackkey() {

        try {
            Array<EventListener> x = backTable.getListeners();
            for (EventListener event : x) {
                if (event instanceof ClickListener) {
                    Thread.sleep(300);
                    ((ClickListener) event).clicked(null, 0, 0);
                }
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        } catch (NullPointerException ex) {
            gsm.set(new MenuInicialState(gsm));
            dispose();
            System.out.println(ex);

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

        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 0f);
        sb.setProjectionMatrix(camOrtho.combined);
        sb.begin();
        //sb.draw(background, 0, 0, camOrtho.viewportWidth, camOrtho.viewportHeight);
        sb.end();

        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {

        stage.dispose();
        skin.dispose();
        textureAtlas.dispose();
        background.dispose();

    }
}
