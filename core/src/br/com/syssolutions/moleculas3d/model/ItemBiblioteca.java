package br.com.syssolutions.moleculas3d.model;

/**
 * Created by jefferson on 19/03/17.
 */

public class ItemBiblioteca {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    private String name;
    private String patch;

public ItemBiblioteca(String name, String patch){
    this.name=name;
    this.patch=patch;
}



}
