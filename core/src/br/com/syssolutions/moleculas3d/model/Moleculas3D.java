package br.com.syssolutions.moleculas3d.model;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;

import br.com.syssolutions.moleculas3d.control.IEnvironmentAndroid;
import br.com.syssolutions.moleculas3d.control.states.GameStateManager;
import br.com.syssolutions.moleculas3d.view.MenuInicialState;

public class Moleculas3D extends ApplicationAdapter {

    //tamanho de tela usado no aplicativo:
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    public static final String TITLE = "Moléculas 3D";

    private GameStateManager gsm;
    private SpriteBatch batch;
    public static IEnvironmentAndroid iEnvironmentAndroid;


    public Moleculas3D(IEnvironmentAndroid iEnvironmentAndroid) {

        super();
        this.iEnvironmentAndroid = iEnvironmentAndroid;


    }


    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager(this);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        gsm.push(new MenuInicialState(gsm));
    }

    @Override

    //O método render deve atualizar primeiro o estado do aplicativo e depois renderizar em
    //um estado específico
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime()); //obtem a diferença entre os render time
        gsm.render(batch);


    }

}