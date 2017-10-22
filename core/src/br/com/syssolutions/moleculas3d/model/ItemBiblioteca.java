package br.com.syssolutions.moleculas3d.model;

/**
 * Created by jefferson on 19/03/17.
 */

public class ItemBiblioteca {
    private String name;
    private String filename;
    private String patch;

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ItemBiblioteca(String patch, String filename) {
        this.patch = patch+filename;
        this.filename = filename;
        this.name=filename;

    }


    public ItemBiblioteca(String patch, String filename, String name) {
        this.patch = patch+filename;
        this.filename = filename;
        this.name = name;

    }


}
