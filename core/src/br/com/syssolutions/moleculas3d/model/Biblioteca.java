package br.com.syssolutions.moleculas3d.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jefferson on 19/03/17.
 */

public abstract class Biblioteca {

    private static final String MOLFOLDER = "mol/";

    private static Map<String, ItemBiblioteca> biblioteca;
    public static Map<String, ItemBiblioteca> getBiblioteca() {

        if (biblioteca == null) {
            addItemBiblioteca();
        }

        return biblioteca;

    }

    private static void addItemBiblioteca() {
        biblioteca = new TreeMap<String, ItemBiblioteca>();

        ArrayList<ItemBiblioteca> bibliotecaArrayList = new ArrayList<ItemBiblioteca>();

        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "1_2-oxazole.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "1_2-thiazole.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "1_3-oxazole.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "1_3-thiazole.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "1H-indene.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "1H-indole.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "1H-pyrazole.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "1H-pyrrole.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "2_2_2-trichloroacetic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "2_2_2-trifluoroacetic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "2_2-dichloroacetic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "2-methylpropane.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "3-trigonal-planar.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "3-trigonal-pyramidal.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "4-planar.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "4-tetrahedral.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "5-square-pyramidal.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "5-trigonal-bipyramidal.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "6-octahedral.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "acetaldehyde.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "acetamide.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "acetic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "acetone.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "acetylene.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "adamantane.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "adenine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "adna.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-allopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-arabinopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-fucopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-galactopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-galacturonopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-glucopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-glucuronopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-gulopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-idopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-lyxopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-mannopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-psicopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-rhamnopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-ribopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-tagatopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-talopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-D-xylopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-arabinopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-fucopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-galactopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-lyxopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-rhamnopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-ribopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-sorbopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "alpha-L-xylopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "ammonia.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "aniline.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "anisole.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "anthracene.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "at-adna.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "at-bdna.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "at-zdna.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "azepane.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "b12.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "bdna.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "benzaldehyde.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "benzene.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "benzofuran.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "benzoic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "benzothiophene.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "benzoyl_chloride.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-allopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-arabinopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-fructopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-fucopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-galactopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-glucopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-gulopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-idopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-lyxopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-mannopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-rhamnopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-ribopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-tagatopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-talopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-D-xylopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-L-arabinopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-L-lyxopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-L-ribopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "beta-L-xylopyranose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "biotin.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "bsheet.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "buckminsterfullerene.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "but-1-ene.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "butanoic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "butanone.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "caffeine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "chloro_a.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "cholesterol.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "citric_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "coa.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "cubane.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "cyclobutane.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "cycloheptane.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "cyclohexane.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "cyclopentane.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "cyclopropane.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "cytosine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-alanine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-allose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-allo-threonine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-altrose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-arabinose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-arginine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-asparagine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-aspartic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-cysteine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "decanoic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-erythrose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-erythrulose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-fructose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-galactose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-glucose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-glutamic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-glutamine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-glyceraldehyde.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-gulose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-histidine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "dibutylether.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-idose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "diethylether.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "dihydroxyacetone.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "diisopropylether.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "dimethylether.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "dimethyl_sulfoxide.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "dipropylether.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-isoleucine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "di-tert-butylether.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-lactic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-leucine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-lysine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-lyxose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-malic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-mannose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-methionine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-phenylalanine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-proline.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-psicose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-ribose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-ribulose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-serine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-sorbose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-tagatose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-talose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-tartaric_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-threonine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-threose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-tryptophan.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-tyrosine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "D-valine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-xylose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "d-xylulose.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "estradiol.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "ethane.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "ethanethiol.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "ethanol.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "ethene.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "ethylamine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "ethyl_carbamate.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "ethylmethylether.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "formaldehyde.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "formic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "furan.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "gc-adna.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "gc-bdna.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "gc-zdna.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "glycine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "guanine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "heme.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "hexanoic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "isobutyric_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-4-nitrophenylalanine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-alanine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-allo-isoleucine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-arginine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-ascorbic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-asparagine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-aspartic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "lauric_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-cysteine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-glutamic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-glutamine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-histidine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "linoleic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-isoleucine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-lactic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-leucine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-lysine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-malic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-methionine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-phenylalanine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-proline.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-serine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-tartaric_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-threonine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-tryptophan.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-tyrosine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "L-valine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "methane.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "methanethiol.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "methanol.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "methylamine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "nadh.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "naphthalene.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "nitrobenzene.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "N_N-dimethylacetamide.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "N_N-dimethylformamide.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "norbornane.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "octanoic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "oleic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "oxalic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "oxamide.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "palmitic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "pentane-1-thiol.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "phenol.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "porphin.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "propan-1-ol.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "propan-2-ol.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "propane.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "propanoic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "propyne.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "pyr.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "pyridine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "ribo.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "stearic_acid.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "testosterone.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "tetrahydrofuran.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "thiamine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "thiophene.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "thymine.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "toluene.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "uracil.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "urea.cml"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "water.cml", "Água"));
        bibliotecaArrayList.add(new ItemBiblioteca(MOLFOLDER, "zdna.cml"));





        for (ItemBiblioteca item : bibliotecaArrayList
                ) {
            biblioteca.put(item.getName(),item);

        }

        bibliotecaArrayList=null;


    }


}
