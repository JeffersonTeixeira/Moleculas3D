package br.com.syssolutions.moleculas3d.control.states;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Jefferson on 31/07/2016.
 */
public abstract class State implements Cloneable{

    //protected OrthographicCamera cam; //Câmera
    protected PerspectiveCamera cam;
    protected Vector3 mouse; //para detectar os eixos X,Y,Z mouse ou touch;
    public GameStateManager gsm;


    public State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new PerspectiveCamera();
        mouse = new Vector3();
    }


    public Object clone() throws CloneNotSupportedException{
        return (State) super.clone();
    }


    protected abstract void handleInput(); //Entradas do usuário

    public abstract void update(float dt); // dt(deltatime) é a diferença entre um frame e o próximo

    public abstract void render(SpriteBatch sb); //Basicamente um conteiner com tudo que precisa ser renderizado na tela.

    public abstract void dispose(); //remover os objetos da memória

}
