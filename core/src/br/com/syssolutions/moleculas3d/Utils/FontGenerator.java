package br.com.syssolutions.moleculas3d.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Created by jefferson on 30/10/16.
 */

public class FontGenerator extends FreeTypeFontGenerator {

    public BitmapFont getFont() {
        return font;
    }

    private BitmapFont font;

    public FontGenerator(int tamanNoProj, String fontPatch, FreeTypeFontParameter parameter) {

        super(Gdx.files.internal(fontPatch));

        if (parameter == null)
            parameter = new FreeTypeFontParameter();

         /*
        * Tamanho da fonte escolhido utilizando o emulador Nexus 5 API 23 1080x1920 xxhdpi 9.45"
        * */

     /*
       float test = Gdx.graphics.getWidth(); //1080
         float test2 = Gdx.graphics.getHeight();//1776.0
    /*
    Then you divide the new screen width by the old width and that is your scale factor.
    Developing on 2560x1440 with font size 16 and running on 1920x1080.
    Font size will be: 1920 * 16 / 2560 = 12

    Thanks @Eejin: http://gamedev.stackexchange.com/a/79497

    os resultados setados de acordo com os testes acima!
    levando em consideração que no os valores são invertidos no android: 1080x1920

    */
        parameter.size = (Gdx.graphics.getHeight() * tamanNoProj) / 1776;
        BitmapFont font = this.generateFont(parameter);
        this.font = font;

    }

    @Override
    public void dispose() {
        try {
            super.dispose();
            this.font.dispose();
        } catch (GdxRuntimeException e) {

        }


    }
}
