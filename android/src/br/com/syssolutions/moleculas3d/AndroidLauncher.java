package br.com.syssolutions.moleculas3d;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


public class AndroidLauncher extends AndroidApplication implements br.com.syssolutions.moleculas3d.control.Moleculas3DCallBack {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
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