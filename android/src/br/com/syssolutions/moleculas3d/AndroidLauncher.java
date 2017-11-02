package br.com.syssolutions.moleculas3d;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import java.io.File;
import java.util.List;

import br.com.syssolutions.moleculas3d.control.IEnvironmentAndroid;
import br.com.syssolutions.moleculas3d.model.Moleculas3D;


public class AndroidLauncher extends AndroidApplication {


    public int RESULTPERMISSION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Permissões de armazenamento
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RESULTPERMISSION);
            }
        }

        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();



        IEnvironmentAndroid environmentAndroid = new EnvironmentAndroid(this.getApplicationContext());
        initialize(new br.com.syssolutions.moleculas3d.model.Moleculas3D(environmentAndroid), config);


        // String sdcardpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        // System.out.println(sdcardpath);

    }

//    @Override
//    public void startActivity() {
//        Intent intent = new Intent(new Moleculas3D);
//        startActivity(intent);
//
//    }
}