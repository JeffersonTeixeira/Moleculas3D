package br.com.syssolutions.moleculas3d.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.sun.org.apache.regexp.internal.RE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import br.com.syssolutions.moleculas3d.control.states.GameStateManager;
import br.com.syssolutions.moleculas3d.control.states.State;
import br.com.syssolutions.moleculas3d.model.Biblioteca;
import br.com.syssolutions.moleculas3d.model.Molecula;
import br.com.syssolutions.moleculas3d.model.Moleculas3D;
import br.com.syssolutions.moleculas3d.model.ReadMoleculaXML;

/**
 * Created by jefferson on 19/03/17.
 */

public class ListMolBibliotecaState extends State {

    private OrthographicCamera camOrtho;

    private ScrollPane scrollPane;

    public ListMolBibliotecaState(GameStateManager gsm) {
        super(gsm);

        camOrtho = new OrthographicCamera();
        camOrtho.setToOrtho(false, Moleculas3D.WIDTH, Moleculas3D.HEIGHT);


        Biblioteca.getBiblioteca();

        //scrollPane = new ScrollPane();

        //ReadMoleculaXML.read();
        Molecula mol = new Molecula();

        try {


            mol = ReadMoleculaXML.read(Biblioteca.getBiblioteca().get(0));

        } catch (Exception e) {
            System.out.println("Erro ao carregar Molécula: " + e);
        }



    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camOrtho.combined); //especifica área do game world a ser mostrada
//        sb.begin();
//        sb.draw(0, 0, camOrtho.viewportWidth, camOrtho.viewportHeight);
//        sb.end();
    }

    @Override
    public void dispose() {

    }


}
