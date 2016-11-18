package br.com.syssolutions.moleculas3d;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.syssolutions.moleculas3d.model.Atomo;
import br.com.syssolutions.moleculas3d.model.Ligacao;
import br.com.syssolutions.moleculas3d.model.Molecula;

/**
 * Created by Jefferson on 20/09/2016.
 */

public class CarregaMolecula {

    public static Molecula carregarMolecula(BufferedReader reader, String arquivo) throws IOException {


        Molecula molecula = new Molecula();
        ArrayList<Atomo> atomos = new ArrayList<>();
        molecula.atomos = atomos;
        ArrayList<Ligacao> ligacoes = new ArrayList<>();
        molecula.ligacoes = ligacoes;

        String linhaXML;
        String[] tokens;
        Pattern cml = Pattern.compile(".cml$");
        Matcher mt = cml.matcher(arquivo);

        if (mt.find()) {
            Pattern tagAtomo_ = Pattern.compile("<atom");
            Pattern atomoPosX = Pattern.compile("x3=\"(.*?)\"");
            Pattern atomoPosY = Pattern.compile("y3=\"(.*?)\"");
            Pattern atomoPosZ = Pattern.compile("z3=\"(.*?)\"");
            Pattern atomoID = Pattern.compile("id=\"(.*?)\"");
            Pattern elemento = Pattern.compile("elementType=\"(.*?)\"");

            Pattern tagLigacao = Pattern.compile("<bond ");
            Pattern refLigacao = Pattern.compile("atoms?Refs2=\" ?(.*?)\"");
            Pattern ordLigacao = Pattern.compile("order=\"(.*?)\"");


            while ((linhaXML = reader.readLine()) != null) {
                mt = tagAtomo_.matcher(linhaXML);

                if (mt.find()) {

                    Atomo atomo = new Atomo();

                    //Posição x:
                    mt = atomoPosX.matcher(linhaXML);
                    mt.find();
                    atomo.x = Float.parseFloat(mt.group(1).replaceAll(" ", ""));

                    //pos y:
                    mt = atomoPosY.matcher(linhaXML);
                    mt.find();
                    atomo.y = Float.parseFloat(mt.group(1).replaceAll(" ", ""));

                    //pos z:
                    mt = atomoPosZ.matcher(linhaXML);
                    mt.find();
                    atomo.z = Float.parseFloat(mt.group(1).replaceAll(" ", ""));

                    //(AtomType)
                    mt = elemento.matcher(linhaXML);
                    mt.find();
                    atomo.simbolo = mt.group(1).replaceAll(" ", "");

                    //ID
                    mt = atomoID.matcher(linhaXML);
                    mt.find();
                    atomo.id = mt.group(1).replaceAll(" ", "");

                    atomos.add(atomo);


                }
                mt = tagLigacao.matcher(linhaXML);
                if (mt.find()) {
                    Ligacao ligacao = new Ligacao();

                    mt = ordLigacao.matcher(linhaXML);
                    mt.find();
                    ligacao.ordemLigacao = Integer.parseInt(mt.group(1).replaceAll(" ", ""));
                    mt = refLigacao.matcher(linhaXML);
                    mt.find();

                    String ref = mt.group(1);
                    tokens = ref.split("[ ]+");

                    String id1 = tokens[0];
                    String id2 = tokens[1];


                    //Foreach???
                    for (int i = 0; i < atomos.size(); i++) {
                        if (id1.equals(atomos.get(i).id)) {
                            ligacao.primeiroAtomo = atomos.get(i);
                        }
                        if (id2.equals(atomos.get(i).id)) {
                            ligacao.segundoAtomo = atomos.get(i);
                        }


                    }
                    ligacoes.add(ligacao);


                }


            }


        }


        return molecula;
    }


}