package br.com.syssolutions.moleculas3d;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


public class AndroidLauncher extends AndroidApplication implements br.com.syssolutions.moleculas3d.control.Moleculas3DCallBack {


    public int RESULTPERMISSION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RESULTPERMISSION);
            }
        }

        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new br.com.syssolutions.moleculas3d.model.Moleculas3D(this), config);

    }

    @Override
    public void startActivity() {
        Intent intent = new Intent(this, Biblioteca.class);
        startActivity(intent);

    }
}