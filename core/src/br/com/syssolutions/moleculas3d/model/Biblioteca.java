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


        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "1_2-oxazole.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "1_2-thiazole.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "1_3-oxazole.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "1_3-thiazole.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "1H-indene.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "1H-indole.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "1H-pyrazole.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "1H-pyrrole.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "2_2_2-trichloroacetic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "2_2_2-trifluoroacetic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "2_2-dichloroacetic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "2-methylpropane.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "3-trigonal-planar.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "3-trigonal-pyramidal.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "4-planar.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "4-tetrahedral.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "5-square-pyramidal.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "5-trigonal-bipyramidal.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "6-octahedral.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "acetaldehyde.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "acetamide.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "acetic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "acetone.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "acetylene.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "adamantane.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "adenine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "adna.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-allopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-arabinopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-fucopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-galactopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-galacturonopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-glucopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-glucuronopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-gulopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-idopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-lyxopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-mannopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-psicopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-rhamnopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-ribopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-tagatopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-talopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-xylopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-arabinopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-fucopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-galactopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-lyxopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-rhamnopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-ribopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-sorbopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-xylopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "ammonia.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "aniline.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "anisole.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "anthracene.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "at-adna.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "at-bdna.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "at-zdna.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "azepane.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "b12.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "bdna.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "benzaldehyde.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "benzene.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "benzofuran.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "benzoic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "benzothiophene.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "benzoyl_chloride.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-allopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-arabinopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-fructopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-fucopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-galactopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-glucopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-gulopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-idopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-lyxopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-mannopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-rhamnopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-ribopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-tagatopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-talopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-D-xylopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-L-arabinopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-L-lyxopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-L-ribopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "beta-L-xylopyranose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "biotin.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "bsheet.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "buckminsterfullerene.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "but-1-ene.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "butanoic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "butanone.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "caffeine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "chloro_a.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "cholesterol.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "citric_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "coa.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "cubane.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "cyclobutane.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "cycloheptane.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "cyclohexane.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "cyclopentane.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "cyclopropane.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "cytosine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-alanine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-allose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-allo-threonine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-altrose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-arabinose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-arginine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-asparagine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-aspartic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-cysteine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "decanoic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-erythrose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-erythrulose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-fructose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-galactose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-glucose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-glutamic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-glutamine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-glyceraldehyde.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-gulose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-histidine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "dibutylether.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-idose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "diethylether.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "dihydroxyacetone.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "diisopropylether.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "dimethylether.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "dimethyl_sulfoxide.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "dipropylether.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-isoleucine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "di-tert-butylether.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-lactic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-leucine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-lysine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-lyxose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-malic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-mannose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-methionine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-phenylalanine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-proline.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-psicose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-ribose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-ribulose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-serine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-sorbose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-tagatose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-talose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-tartaric_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-threonine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-threose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-tryptophan.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-tyrosine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "D-valine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-xylose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "d-xylulose.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "estradiol.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "ethane.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "ethanethiol.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "ethanol.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "ethene.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "ethylamine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "ethyl_carbamate.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "ethylmethylether.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "formaldehyde.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "formic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "furan.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "gc-adna.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "gc-bdna.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "gc-zdna.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "glycine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "guanine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "heme.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "hexanoic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "isobutyric_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-4-nitrophenylalanine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-alanine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-allo-isoleucine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-arginine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-ascorbic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-asparagine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-aspartic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "lauric_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-cysteine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-glutamic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-glutamine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-histidine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "linoleic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-isoleucine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-lactic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-leucine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-lysine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-malic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-methionine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-phenylalanine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-proline.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-serine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-tartaric_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-threonine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-tryptophan.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-tyrosine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "L-valine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "methane.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "methanethiol.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "methanol.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "methylamine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "nadh.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "naphthalene.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "nitrobenzene.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "N_N-dimethylacetamide.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "N_N-dimethylformamide.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "norbornane.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "octanoic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "oleic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "oxalic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "oxamide.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "palmitic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "pentane-1-thiol.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "phenol.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "porphin.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "propan-1-ol.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "propan-2-ol.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "propane.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "propanoic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "propyne.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "pyr.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "pyridine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "ribo.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "stearic_acid.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "testosterone.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "tetrahydrofuran.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "thiamine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "thiophene.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "thymine.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "toluene.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "uracil.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "urea.cml"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "water.cml","Água"));
        biblioteca.add(new ItemBiblioteca(MOLFOLDER, "zdna.cml"));

    }


}
