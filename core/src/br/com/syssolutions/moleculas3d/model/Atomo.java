package br.com.syssolutions.moleculas3d.model;

/**
 * Created by Jefferson on 31/07/2016.
 */
public class Atomo {
    public float x, y, z;
    public String simbolo;
    public float cor[];
    public String id;


    public float[] getCor() {
        setCor();
        return this.cor;
    }


    public void setCor() {

        float[] cores = new float[4];

        cores[0] = 150f;
        cores[1] = 150f;
        cores[2] = 150f;
        cores[3] = 1f;

        //switch (this.simbolo){


        if (this.simbolo.contentEquals("H")) {
            cores[0] = 255f;
            cores[1] = 255f;
            cores[2] = 255f;
        } else if (this.simbolo.contentEquals("He")) {
            cores[0] = 217f;
            cores[1] = 255f;
            cores[2] = 255f;
        } else if (this.simbolo.contentEquals("Li")) {
            cores[0] = 204f;
            cores[1] = 128f;
            cores[2] = 255f;
        } else if (this.simbolo.contentEquals("Be")) {
            cores[0] = 194f;
            cores[1] = 255f;
            cores[2] = 0f;
        } else if (this.simbolo.contentEquals("B")) {
            cores[0] = 255f;
            cores[1] = 181f;
            cores[2] = 181f;
        } else if (this.simbolo.contentEquals("C")) {
            cores[0] = 50f;
            cores[1] = 50f;
            cores[2] = 50f;
        } else if (this.simbolo.contentEquals("N")) {
            cores[0] = 48f;
            cores[1] = 80f;
            cores[2] = 248f;
        } else if (this.simbolo.contentEquals("O")) {
            cores[0] = 255f;
            cores[1] = 13f;
            cores[2] = 13f;
        } else if (this.simbolo.contentEquals("F")) {
            cores[0] = 144f;
            cores[1] = 224f;
            cores[2] = 80f;
        } else if (this.simbolo.contentEquals("Ne")) {
            cores[0] = 179f;
            cores[1] = 227f;
            cores[2] = 245f;
        } else if (this.simbolo.contentEquals("Na")) {
            cores[0] = 171f;
            cores[1] = 92f;
            cores[2] = 242f;
        } else if (this.simbolo.contentEquals("Mg")) {
            cores[0] = 138f;
            cores[1] = 255f;
            cores[2] = 0f;
        } else if (this.simbolo.contentEquals("Al")) {
            cores[0] = 191f;
            cores[1] = 166f;
            cores[2] = 166f;
        } else if (this.simbolo.contentEquals("Si")) {
            cores[0] = 240f;
            cores[1] = 200f;
            cores[2] = 160f;
        } else if (this.simbolo.contentEquals("P")) {
            cores[0] = 255f;
            cores[1] = 128f;
            cores[2] = 0f;
        } else if (this.simbolo.contentEquals("S")) {
            cores[0] = 255f;
            cores[1] = 255f;
            cores[2] = 48f;
        } else if (this.simbolo.contentEquals("Cl")) {
            cores[0] = 31f;
            cores[1] = 240f;
            cores[2] = 31f;
        } else if (this.simbolo.contentEquals("Ar")) {
            cores[0] = 128f;
            cores[1] = 209f;
            cores[2] = 227f;
        } else if (this.simbolo.contentEquals("K")) {
            cores[0] = 143f;
            cores[1] = 64f;
            cores[2] = 212f;
        } else if (this.simbolo.contentEquals("Ca")) {
            cores[0] = 61f;
            cores[1] = 255f;
            cores[2] = 0f;
        } else if (this.simbolo.contentEquals("Sc")) {
            cores[0] = 230f;
            cores[1] = 230f;
            cores[2] = 230f;
        } else if (this.simbolo.contentEquals("Ti")) {
            cores[0] = 191f;
            cores[1] = 194f;
            cores[2] = 199f;
        } else if (this.simbolo.contentEquals("V")) {
            cores[0] = 166f;
            cores[1] = 166f;
            cores[2] = 171f;
        } else if (this.simbolo.contentEquals("Cr")) {
            cores[0] = 138f;
            cores[1] = 153f;
            cores[2] = 199f;
        } else if (this.simbolo.contentEquals("Mn")) {
            cores[0] = 156f;
            cores[1] = 122f;
            cores[2] = 199f;
        } else if (this.simbolo.contentEquals("Fe")) {
            cores[0] = 224f;
            cores[1] = 102f;
            cores[2] = 51f;
        } else if (this.simbolo.contentEquals("Co")) {
            cores[0] = 240f;
            cores[1] = 144f;
            cores[2] = 160f;
        } else if (this.simbolo.contentEquals("Ni")) {
            cores[0] = 80f;
            cores[1] = 208f;
            cores[2] = 80f;
        } else if (this.simbolo.contentEquals("Cu")) {
            cores[0] = 200f;
            cores[1] = 128f;
            cores[2] = 51f;
        } else if (this.simbolo.contentEquals("Zn")) {
            cores[0] = 125f;
            cores[1] = 128f;
            cores[2] = 176f;
        } else if (this.simbolo.contentEquals("Ga")) {
            cores[0] = 194f;
            cores[1] = 143f;
            cores[2] = 143f;
        } else if (this.simbolo.contentEquals("Ge")) {
            cores[0] = 102f;
            cores[1] = 143f;
            cores[2] = 143f;
        } else if (this.simbolo.contentEquals("As")) {
            cores[0] = 189f;
            cores[1] = 128f;
            cores[2] = 227f;
        } else if (this.simbolo.contentEquals("Se")) {
            cores[0] = 255f;
            cores[1] = 161f;
            cores[2] = 0f;
        } else if (this.simbolo.contentEquals("Br")) {
            cores[0] = 166f;
            cores[1] = 41f;
            cores[2] = 41f;
        } else if (this.simbolo.contentEquals("Kr")) {
            cores[0] = 92f;
            cores[1] = 184f;
            cores[2] = 209f;
        } else if (this.simbolo.contentEquals("Rb")) {
            cores[0] = 112f;
            cores[1] = 46f;
            cores[2] = 176f;
        } else if (this.simbolo.contentEquals("Sr")) {
            cores[0] = 0f;
            cores[1] = 255f;
            cores[2] = 0f;
        } else if (this.simbolo.contentEquals("Y")) {
            cores[0] = 148f;
            cores[1] = 255f;
            cores[2] = 255f;
        } else if (this.simbolo.contentEquals("Zr")) {
            cores[0] = 148f;
            cores[1] = 224f;
            cores[2] = 224f;
        } else if (this.simbolo.contentEquals("Nb")) {
            cores[0] = 115f;
            cores[1] = 194f;
            cores[2] = 201f;
        } else if (this.simbolo.contentEquals("Mo")) {
            cores[0] = 84f;
            cores[1] = 181f;
            cores[2] = 181f;
        } else if (this.simbolo.contentEquals("Tc")) {
            cores[0] = 59f;
            cores[1] = 158f;
            cores[2] = 158f;
        } else if (this.simbolo.contentEquals("Ru")) {
            cores[0] = 36f;
            cores[1] = 143f;
            cores[2] = 143f;
        } else if (this.simbolo.contentEquals("Rh")) {
            cores[0] = 10f;
            cores[1] = 125f;
            cores[2] = 140f;
        } else if (this.simbolo.contentEquals("Pd")) {
            cores[0] = 0f;
            cores[1] = 105f;
            cores[2] = 133f;
        } else if (this.simbolo.contentEquals("Ag")) {
            cores[0] = 192f;
            cores[1] = 192f;
            cores[2] = 192f;
        } else if (this.simbolo.contentEquals("Cd")) {
            cores[0] = 255f;
            cores[1] = 217f;
            cores[2] = 143f;
        } else if (this.simbolo.contentEquals("In")) {
            cores[0] = 166f;
            cores[1] = 117f;
            cores[2] = 115f;
        } else if (this.simbolo.contentEquals("Sn")) {
            cores[0] = 102f;
            cores[1] = 128f;
            cores[2] = 128f;
        } else if (this.simbolo.contentEquals("Sb")) {
            cores[0] = 158f;
            cores[1] = 99f;
            cores[2] = 181f;
        } else if (this.simbolo.contentEquals("Te")) {
            cores[0] = 212f;
            cores[1] = 122f;
            cores[2] = 0f;
        } else if (this.simbolo.contentEquals("I")) {
            cores[0] = 148f;
            cores[1] = 0f;
            cores[2] = 148f;
        } else if (this.simbolo.contentEquals("Xe")) {
            cores[0] = 66f;
            cores[1] = 158f;
            cores[2] = 176f;
        } else if (this.simbolo.contentEquals("Cs")) {
            cores[0] = 87f;
            cores[1] = 23f;
            cores[2] = 143f;
        } else if (this.simbolo.contentEquals("Ba")) {
            cores[0] = 0f;
            cores[1] = 201f;
            cores[2] = 0f;
        } else if (this.simbolo.contentEquals("La")) {
            cores[0] = 112f;
            cores[1] = 212f;
            cores[2] = 255f;
        } else if (this.simbolo.contentEquals("Ce")) {
            cores[0] = 255f;
            cores[1] = 255f;
            cores[2] = 199f;
        } else if (this.simbolo.contentEquals("Pr")) {
            cores[0] = 217f;
            cores[1] = 255f;
            cores[2] = 199f;
        } else if (this.simbolo.contentEquals("Nd")) {
            cores[0] = 199f;
            cores[1] = 255f;
            cores[2] = 199f;
        } else if (this.simbolo.contentEquals("Pm")) {
            cores[0] = 163f;
            cores[1] = 255f;
            cores[2] = 199f;
        } else if (this.simbolo.contentEquals("Sm")) {
            cores[0] = 143f;
            cores[1] = 255f;
            cores[2] = 199f;
        } else if (this.simbolo.contentEquals("Eu")) {
            cores[0] = 97f;
            cores[1] = 255f;
            cores[2] = 199f;
        } else if (this.simbolo.contentEquals("Gd")) {
            cores[0] = 69f;
            cores[1] = 255f;
            cores[2] = 199f;
        } else if (this.simbolo.contentEquals("Tb")) {
            cores[0] = 48f;
            cores[1] = 255f;
            cores[2] = 199f;
        } else if (this.simbolo.contentEquals("Dy")) {
            cores[0] = 31f;
            cores[1] = 255f;
            cores[2] = 199f;
        } else if (this.simbolo.contentEquals("Ho")) {
            cores[0] = 0f;
            cores[1] = 255f;
            cores[2] = 156f;
        } else if (this.simbolo.contentEquals("Er")) {
            cores[0] = 0f;
            cores[1] = 230f;
            cores[2] = 117f;
        } else if (this.simbolo.contentEquals("Tm")) {
            cores[0] = 0f;
            cores[1] = 212f;
            cores[2] = 82f;
        } else if (this.simbolo.contentEquals("Yb")) {
            cores[0] = 0f;
            cores[1] = 191f;
            cores[2] = 56f;
        } else if (this.simbolo.contentEquals("Lu")) {
            cores[0] = 0f;
            cores[1] = 171f;
            cores[2] = 36f;
        } else if (this.simbolo.contentEquals("Hf")) {
            cores[0] = 77f;
            cores[1] = 194f;
            cores[2] = 255f;
        } else if (this.simbolo.contentEquals("Ta")) {
            cores[0] = 77f;
            cores[1] = 166f;
            cores[2] = 255f;
        } else if (this.simbolo.contentEquals("W")) {
            cores[0] = 33f;
            cores[1] = 148f;
            cores[2] = 214f;
        } else if (this.simbolo.contentEquals("Re")) {
            cores[0] = 38f;
            cores[1] = 125f;
            cores[2] = 171f;
        } else if (this.simbolo.contentEquals("Os")) {
            cores[0] = 38f;
            cores[1] = 102f;
            cores[2] = 150f;
        } else if (this.simbolo.contentEquals("Ir")) {
            cores[0] = 23f;
            cores[1] = 84f;
            cores[2] = 135f;
        } else if (this.simbolo.contentEquals("Pt")) {
            cores[0] = 208f;
            cores[1] = 208f;
            cores[2] = 224f;
        } else if (this.simbolo.contentEquals("Au")) {
            cores[0] = 255f;
            cores[1] = 209f;
            cores[2] = 35f;
        } else if (this.simbolo.contentEquals("Hg")) {
            cores[0] = 184f;
            cores[1] = 184f;
            cores[2] = 208f;
        } else if (this.simbolo.contentEquals("Tl")) {
            cores[0] = 166f;
            cores[1] = 84f;
            cores[2] = 77f;
        } else if (this.simbolo.contentEquals("Pb")) {
            cores[0] = 87f;
            cores[1] = 89f;
            cores[2] = 97f;
        } else if (this.simbolo.contentEquals("Bi")) {
            cores[0] = 158f;
            cores[1] = 79f;
            cores[2] = 181f;
        } else if (this.simbolo.contentEquals("Po")) {
            cores[0] = 171f;
            cores[1] = 92f;
            cores[2] = 0f;
        } else if (this.simbolo.contentEquals("At")) {
            cores[0] = 117f;
            cores[1] = 79f;
            cores[2] = 69f;
        } else if (this.simbolo.contentEquals("Rn")) {
            cores[0] = 66f;
            cores[1] = 130f;
            cores[2] = 150f;
        } else if (this.simbolo.contentEquals("Fr")) {
            cores[0] = 66f;
            cores[1] = 0f;
            cores[2] = 102f;
        } else if (this.simbolo.contentEquals("Ra")) {
            cores[0] = 0f;
            cores[1] = 125f;
            cores[2] = 0f;
        } else if (this.simbolo.contentEquals("Ac")) {
            cores[0] = 112f;
            cores[1] = 171f;
            cores[2] = 250f;
        } else if (this.simbolo.contentEquals("Th")) {
            cores[0] = 0f;
            cores[1] = 186f;
            cores[2] = 255f;
        } else if (this.simbolo.contentEquals("Pa")) {
            cores[0] = 0f;
            cores[1] = 161f;
            cores[2] = 255f;
        } else if (this.simbolo.contentEquals("U")) {
            cores[0] = 0f;
            cores[1] = 143f;
            cores[2] = 255f;
        } else if (this.simbolo.contentEquals("Np")) {
            cores[0] = 0f;
            cores[1] = 128f;
            cores[2] = 255f;
        } else if (this.simbolo.contentEquals("Pu")) {
            cores[0] = 0f;
            cores[1] = 107f;
            cores[2] = 255f;
        } else if (this.simbolo.contentEquals("Am")) {
            cores[0] = 84f;
            cores[1] = 92f;
            cores[2] = 242f;
        } else if (this.simbolo.contentEquals("Cm")) {
            cores[0] = 120f;
            cores[1] = 92f;
            cores[2] = 227f;
        } else if (this.simbolo.contentEquals("Bk")) {
            cores[0] = 138f;
            cores[1] = 79f;
            cores[2] = 227f;
        } else if (this.simbolo.contentEquals("Cf")) {
            cores[0] = 161f;
            cores[1] = 54f;
            cores[2] = 212f;
        } else if (this.simbolo.contentEquals("Es")) {
            cores[0] = 179f;
            cores[1] = 31f;
            cores[2] = 212f;
        } else if (this.simbolo.contentEquals("Fm")) {
            cores[0] = 179f;
            cores[1] = 31f;
            cores[2] = 186f;
        } else if (this.simbolo.contentEquals("Md")) {
            cores[0] = 179f;
            cores[1] = 13f;
            cores[2] = 166f;
        } else if (this.simbolo.contentEquals("No")) {
            cores[0] = 189f;
            cores[1] = 13f;
            cores[2] = 135f;
        } else if (this.simbolo.contentEquals("Lr")) {
            cores[0] = 199f;
            cores[1] = 0f;
            cores[2] = 102f;
        } else if (this.simbolo.contentEquals("Rf")) {
            cores[0] = 204f;
            cores[1] = 0f;
            cores[2] = 89f;
        } else if (this.simbolo.contentEquals("Db")) {
            cores[0] = 209f;
            cores[1] = 0f;
            cores[2] = 79f;
        } else if (this.simbolo.contentEquals("Sg")) {
            cores[0] = 217f;
            cores[1] = 0f;
            cores[2] = 69f;
        } else if (this.simbolo.contentEquals("Bh")) {
            cores[0] = 224f;
            cores[1] = 0f;
            cores[2] = 56f;
        } else if (this.simbolo.contentEquals("Hs")) {
            cores[0] = 230f;
            cores[1] = 0f;
            cores[2] = 46f;
        } else if (this.simbolo.contentEquals("Mt")) {
            cores[0] = 235f;
            cores[1] = 0f;
            cores[2] = 38f;
        }


        cores[0] = cores[0] / 255;
        cores[1] = cores[1] / 255;
        cores[2] = cores[2] / 255;


        this.cor = cores;


    }



    public float getRaioAtomico(String symbol) {

        float radius = 140f;

        if (symbol.contentEquals("H")) {
            radius = 38f;
        } else if (symbol.contentEquals("He")) {
            radius = 32f;
        } else if (symbol.contentEquals("Li")) {
            radius = 134f;
        } else if (symbol.contentEquals("Be")) {
            radius = 90f;
        } else if (symbol.contentEquals("B")) {
            radius = 82f;
        } else if (symbol.contentEquals("C")) {
            radius = 77f;
        } else if (symbol.contentEquals("N")) {
            radius = 75f;
        } else if (symbol.contentEquals("O")) {
            radius = 73f;
        } else if (symbol.contentEquals("F")) {
            radius = 71f;
        } else if (symbol.contentEquals("Ne")) {
            radius = 69f;
        } else if (symbol.contentEquals("Na")) {
            radius = 154f;
        } else if (symbol.contentEquals("Mg")) {
            radius = 130f;
        } else if (symbol.contentEquals("Al")) {
            radius = 118f;
        } else if (symbol.contentEquals("Si")) {
            radius = 111f;
        } else if (symbol.contentEquals("P")) {
            radius = 106f;
        } else if (symbol.contentEquals("S")) {
            radius = 102f;
        } else if (symbol.contentEquals("Cl")) {
            radius = 99f;
        } else if (symbol.contentEquals("Ar")) {
            radius = 97f;
        } else if (symbol.contentEquals("K")) {
            radius = 196f;
        } else if (symbol.contentEquals("Ca")) {
            radius = 174f;
        } else if (symbol.contentEquals("Sc")) {
            radius = 144f;
        } else if (symbol.contentEquals("Ti")) {
            radius = 136f;
        } else if (symbol.contentEquals("V")) {
            radius = 125f;
        } else if (symbol.contentEquals("Cr")) {
            radius = 127f;
        } else if (symbol.contentEquals("Mn")) {
            radius = 139f;
        } else if (symbol.contentEquals("Fe")) {
            radius = 125f;
        } else if (symbol.contentEquals("Co")) {
            radius = 126f;
        } else if (symbol.contentEquals("Ni")) {
            radius = 121f;
        } else if (symbol.contentEquals("Cu")) {
            radius = 138f;
        } else if (symbol.contentEquals("Zn")) {
            radius = 131f;
        } else if (symbol.contentEquals("Ga")) {
            radius = 126f;
        } else if (symbol.contentEquals("Ge")) {
            radius = 122f;
        } else if (symbol.contentEquals("As")) {
            radius = 119f;
        } else if (symbol.contentEquals("Se")) {
            radius = 116f;
        } else if (symbol.contentEquals("Br")) {
            radius = 114f;
        } else if (symbol.contentEquals("Kr")) {
            radius = 110f;
        } else if (symbol.contentEquals("Rb")) {
            radius = 211f;
        } else if (symbol.contentEquals("Sr")) {
            radius = 192f;
        } else if (symbol.contentEquals("Y")) {
            radius = 162f;
        } else if (symbol.contentEquals("Zr")) {
            radius = 148f;
        } else if (symbol.contentEquals("Nb")) {
            radius = 137f;
        } else if (symbol.contentEquals("Mo")) {
            radius = 145f;
        } else if (symbol.contentEquals("Tc")) {
            radius = 156f;
        } else if (symbol.contentEquals("Ru")) {
            radius = 126f;
        } else if (symbol.contentEquals("Rh")) {
            radius = 135f;
        } else if (symbol.contentEquals("Pd")) {
            radius = 131f;
        } else if (symbol.contentEquals("Ag")) {
            radius = 153f;
        } else if (symbol.contentEquals("Cd")) {
            radius = 148f;
        } else if (symbol.contentEquals("In")) {
            radius = 144f;
        } else if (symbol.contentEquals("Sn")) {
            radius = 141f;
        } else if (symbol.contentEquals("Sb")) {
            radius = 138f;
        } else if (symbol.contentEquals("Te")) {
            radius = 135f;
        } else if (symbol.contentEquals("I")) {
            radius = 133f;
        } else if (symbol.contentEquals("Xe")) {
            radius = 130f;
        } else if (symbol.contentEquals("Cs")) {
            radius = 225f;
        } else if (symbol.contentEquals("Ba")) {
            radius = 198f;
        } else if (symbol.contentEquals("La")) {
            radius = 169f;
        } else if (symbol.contentEquals("Lu")) {
            radius = 160f;
        } else if (symbol.contentEquals("Hf")) {
            radius = 150f;
        } else if (symbol.contentEquals("Ta")) {
            radius = 138f;
        } else if (symbol.contentEquals("W")) {
            radius = 146f;
        } else if (symbol.contentEquals("Re")) {
            radius = 159f;
        } else if (symbol.contentEquals("Os")) {
            radius = 128f;
        } else if (symbol.contentEquals("Ir")) {
            radius = 137f;
        } else if (symbol.contentEquals("Pt")) {
            radius = 128f;
        } else if (symbol.contentEquals("Au")) {
            radius = 144f;
        } else if (symbol.contentEquals("Hg")) {
            radius = 149f;
        } else if (symbol.contentEquals("Tl")) {
            radius = 148f;
        } else if (symbol.contentEquals("Pb")) {
            radius = 147f;
        } else if (symbol.contentEquals("Bi")) {
            radius = 146f;
        } else if (symbol.contentEquals("Rn")) {
            radius = 145f;
        }

        return radius / 100;
    }


}