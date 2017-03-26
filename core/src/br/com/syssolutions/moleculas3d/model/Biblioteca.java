package br.com.syssolutions.moleculas3d.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jefferson on 19/03/17.
 */

public class Biblioteca {

    private static final String MOLFOLDER = "mol/";


    //PRIVATEEE!!!!!!!!!!!!
    private static List<ItemBiblioteca> biblioteca;


    public static List<ItemBiblioteca> getBiblioteca() {

        if (biblioteca == null) {
            addItemBiblioteca();
        }

        return biblioteca;

    }

    private static void addItemBiblioteca() {
        biblioteca = new ArrayList<ItemBiblioteca>();


        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "water.cml", "Água"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "1_2-oxazole.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "1_2-oxazole.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "1_2-oxazole.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "1_2-oxazole.cml"));


    }


}
