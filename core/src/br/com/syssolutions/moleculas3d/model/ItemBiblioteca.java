package br.com.syssolutions.moleculas3d.model;

/**
 * Created by jefferson on 19/03/17.
 */

public class ItemBiblioteca {
    private String[] name = new String[3];
    private String filename;
    private String patch;
    private String allNames = "";


    public String getAllNames() {
        return allNames;
    }

    public String getPatch() {
        return patch;
    }

//    public void setPatch(String patch) {
//        this.patch = patch;
//    }


    public String getFilename() {
        return filename;
    }

//    public void setFilename(String filename) {
//        this.filename = filename;
//    }

    public String[] getName() {
        return name;
    }

    public String getName(int i) {
        return name[i];
    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


    public ItemBiblioteca(String patch, String filename) {
        this.patch = patch + filename;
        this.filename = filename;
        this.name[0] = filename;
        allNames=filename;

    }


    public ItemBiblioteca(String patch, String filename, String... name) {
        this.patch = patch + filename;
        this.filename = filename;
        this.name = name;


        //allNames=String.join(",",name); //Java 8+


        //System.out.println("Construtor... : " + name.toString());

        for (String s : name) {

            allNames = allNames + s;

        }

        // System.out.println("allnames()"+allNames);


    }


}
