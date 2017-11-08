package br.com.syssolutions.moleculas3d.control.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import br.com.syssolutions.moleculas3d.model.Moleculas3D;

/**
 * Created by Jefferson on 31/07/2016.
 * Classe que é responsável para sobrepor um estado do aplicativo sobre o outro (pilha (stack) de estados)
 */
public class GameStateManager {

    private Stack<State> estados;

    public Moleculas3D moleculas3D;


    public GameStateManager(Moleculas3D moleculas3D) {
        this.moleculas3D = moleculas3D;
        estados = new Stack<State>();

    }

    //Métodos para gerenciar as pilhas:
    // Push = para o topo da pilha
    public void push(State state) {
        estados.push(state);
    }

    //remover da pilha
    public void pop() {
        estados.pop();
    }

    //adicionar um novo estado a pilha
    public void set(State state) {
        estados.pop();
        estados.push(state);
    }


    public void setWaitState(State waitState)throws CloneNotSupportedException {

        estados.pop();
        estados.push(waitState);
        State newState=(State)waitState.clone();
        estados.push(newState);

    }

    public void getBackState(State currentState){
        estados.remove(1);
    }


    public void update(float deltatime) {
        estados.peek().update(deltatime);
    }

    public void render(SpriteBatch sb) {
        estados.peek().render(sb);
    }
}
