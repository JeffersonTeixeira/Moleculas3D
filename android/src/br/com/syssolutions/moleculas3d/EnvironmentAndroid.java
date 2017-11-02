package br.com.syssolutions.moleculas3d;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.List;

import br.com.syssolutions.moleculas3d.control.IEnvironmentAndroid;

/**
 * Created by jefferson on 31/10/17.
 */

public class  EnvironmentAndroid implements IEnvironmentAndroid {

    //private Environment environment;
    private static List<File> list;


    EnvironmentAndroid(Context c){
        getRemovabeStorages(c);
    }


    @Override
    public List<File> getRemovabeStorages() {

        return list;
    }


    private static List<File> getRemovabeStorages(Context c) {

        try {
            list = RemovableStorageFinder.getRemovabeStorages(c);
            return list;
        } catch (Exception ex) {
            System.out.println("falha ao obter os dispositivos de armazenamento");
            return null;
        }

    }


    //
//    EnvironmentAndroid() {
//        // environment=new Environment();
//    }
//
//
//    @Override
//    public File getExternalStorageDirectory() {
//        return Environment.getExternalStorageDirectory();
//
//    }
//
//    @Override
//    public String getAbsolutePath() {
//        return Environment.getExternalStorageDirectory().getAbsolutePath();
//
//
//    }
//


}
