package br.com.syssolutions.moleculas3d.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jefferson on 21/03/17.
 */

public class ReadMolecula {
    public static Molecula read(ItemBiblioteca itemBiblioteca) throws IOException {

        String filename = itemBiblioteca.getFilename();


        InputStream is = Gdx.files.internal(itemBiblioteca.getPatch()).read();
        InputStreamReader isr = new InputStreamReader(is);


        BufferedReader reader = new BufferedReader(isr);

        return read(filename, itemBiblioteca.getName(0), reader);


    }


    public static Molecula read(FileHandle file, boolean absolute) throws IOException {


        String filename = file.name();

        InputStream is;

        if (absolute) {
            is = Gdx.files.absolute(file.path()).read();

        } else {
            is = Gdx.files.external(file.path()).read();
        }


        InputStreamReader isr = new InputStreamReader(is);

        BufferedReader reader = new BufferedReader(isr);


        return read(filename, filename, reader);


    }


    private static Molecula read(String filename, String name, BufferedReader reader) throws IOException {
        System.out.println("Reading MOL");


        Molecula molecula = new Molecula();
        molecula.titulo=name;
        ArrayList<Atomo> atoms = new ArrayList<Atomo>();
        molecula.atomos = atoms;
        ArrayList<Ligacao> bonds = new ArrayList<Ligacao>();
        molecula.ligacoes = bonds;
        String line;
        String[] tokens;
        Pattern cml = Pattern.compile(".cml$");
        Matcher m = cml.matcher(filename);
        if (m.find()) {
            Pattern atom_ = Pattern.compile("<atom ");
            Pattern atom_x = Pattern.compile("x3=\"(.*?)\"");
            Pattern atom_y = Pattern.compile("y3=\"(.*?)\"");
            Pattern atom_z = Pattern.compile("z3=\"(.*?)\"");
            Pattern atom_id = Pattern.compile("id=\"(.*?)\"");
            Pattern atom_type = Pattern.compile("elementType=\"(.*?)\"");
            Pattern bond_ = Pattern.compile("<bond ");
            Pattern bond_refs = Pattern.compile("atoms?Refs2=\" ?(.*?)\"");
            Pattern bond_order = Pattern.compile("order=\"(.*?)\"");
            while ((line = reader.readLine()) != null) {
                m = atom_.matcher(line);
                if (m.find()) {
                    Atomo atom = new Atomo();
                    m = atom_x.matcher(line);
                    m.find();
                    atom.x = Float.parseFloat(m.group(1).replaceAll(" ", ""));
                    m = atom_y.matcher(line);
                    m.find();
                    atom.y = Float.parseFloat(m.group(1).replaceAll(" ", ""));
                    m = atom_z.matcher(line);
                    m.find();
                    atom.z = Float.parseFloat(m.group(1).replaceAll(" ", ""));
                    m = atom_type.matcher(line);
                    m.find();
                    atom.simbolo = m.group(1).replaceAll(" ", "");
                    m = atom_id.matcher(line);
                    m.find();
                    atom.id = m.group(1).replaceAll(" ", "");
                    atoms.add(atom);
                }
                m = bond_.matcher(line);
                if (m.find()) {
                    Ligacao ligacao = new Ligacao();
                    m = bond_order.matcher(line);
                    m.find();
                    ligacao.ordemLigacao = Integer.parseInt(m.group(1).replaceAll(" ", ""));
                    m = bond_refs.matcher(line);
                    m.find();
                    String refs = m.group(1);
                    tokens = refs.split("[ ]+");
                    String id1 = tokens[0];
                    String id2 = tokens[1];
                    for (int i = 0; i < atoms.size(); i++) {
                        if (id1.equals(atoms.get(i).id)) {
                            ligacao.primeiroAtomo = atoms.get(i);
                        }
                        if (id2.equals(atoms.get(i).id)) {
                            ligacao.segundoAtomo = atoms.get(i);
                        }
                    }
                    bonds.add(ligacao);
                }
            }
        }
        Pattern xyz = Pattern.compile(".xyz$");
        m = xyz.matcher(filename);
        if (m.find()) {
            int num_of_atoms = Integer.parseInt(reader.readLine().replaceAll("\\s+", ""));
            reader.readLine();



            for (int i = 0; i < num_of_atoms; i++) {
                line = reader.readLine().trim();

                tokens = line.split("\\s+");
                Atomo atom = new Atomo();
                atom.simbolo = tokens[0];
                atom.x = Float.parseFloat(tokens[1]);
                atom.y = Float.parseFloat(tokens[2]);
                atom.z = Float.parseFloat(tokens[3]);
                atoms.add(atom);

            }
        }
        Pattern mol = Pattern.compile(".mol$");
        m = mol.matcher(filename);
        Pattern sd = Pattern.compile(".sd$");
        Matcher m2 = sd.matcher(filename);
        Pattern sdf = Pattern.compile(".sdf$");
        Matcher m3 = sdf.matcher(filename);
        if (m.find() || m2.find() || m3.find()) {
            reader.readLine();
            reader.readLine();
            reader.readLine();
            line = reader.readLine();
            tokens = line.split("\\s+");
            int num_of_atoms = Integer.parseInt(tokens[1]);
            int num_of_bonds = Integer.parseInt(tokens[2]);

            for (int i = 0; i < num_of_atoms; i++) {
                line = reader.readLine();
                tokens = line.split("\\s+");
                Atomo atom = new Atomo();
                atom.x = Float.parseFloat(tokens[1]);
                atom.y = Float.parseFloat(tokens[2]);
                atom.z = Float.parseFloat(tokens[3]);
                atom.simbolo = tokens[4];
                Integer i_plus_one = i + 1;
                atom.id = i_plus_one.toString();

                atoms.add(atom);
            }
            for (int i = 0; i < num_of_bonds; i++) {
                line = reader.readLine();
                tokens = line.split("\\s+");
                Ligacao ligacao = new Ligacao();
                String id1 = tokens[1];
                String id2 = tokens[2];
                try {
                    ligacao.ordemLigacao = Integer.parseInt(tokens[3]);
                } catch (NumberFormatException e) {
                    ligacao.ordemLigacao = 1;
                }

                for (int j = 0; j < atoms.size(); j++) {
                    if (id1.equals(atoms.get(j).id)) {
                        ligacao.primeiroAtomo = atoms.get(j);
                    }
                    if (id2.equals(atoms.get(j).id)) {
                        ligacao.primeiroAtomo = atoms.get(j);
                    }
                }
                bonds.add(ligacao);
            }
        }
        Pattern mol2 = Pattern.compile(".mol2$");
        m = mol2.matcher(filename);
        if (m.find()) {

            Pattern atom_begin = Pattern.compile("<TRIPOS>ATOM");
            Pattern bond_begin = Pattern.compile("<TRIPOS>BOND");
            Pattern stop = Pattern.compile("<TRIPOS>");
            while ((line = reader.readLine()) != null) {

                m = atom_begin.matcher(line);
                if (m.find()) {
                    while (true) {

                        line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        m = stop.matcher(line);
                        if (m.find()) {
                            break;
                        }

                        tokens = line.split("\\s+");
                        Atomo atomo = new Atomo();
                        atomo.x = Float.parseFloat(tokens[3]);
                        atomo.y = Float.parseFloat(tokens[4]);
                        atomo.z = Float.parseFloat(tokens[5]);
                        atomo.simbolo = tokens[6].replaceAll("\\..*", "");
                        atomo.id = tokens[1];
                        atoms.add(atomo);
                    }
                }
                m = bond_begin.matcher(line);
                if (m.find()) {
                    while (true) {
                        line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        m = stop.matcher(line);
                        if (m.find()) {
                            break;
                        }
                        tokens = line.split("\\s+");
                        Ligacao ligacao = new Ligacao();
                        ligacao.ordemLigacao = 1;
                        if (tokens[4].equals("2")) {
                            ligacao.ordemLigacao = 2;
                        }
                        if (tokens[4].equals("3")) {
                            ligacao.ordemLigacao = 3;
                        }
                        if (tokens[4].equals("4")) {
                            ligacao.ordemLigacao = 4;
                        }
                        String id1 = tokens[2];
                        String id2 = tokens[3];
                        for (int i = 0; i < atoms.size(); i++) {
                            if (id1.equals(atoms.get(i).id)) {
                                ligacao.primeiroAtomo = atoms.get(i);
                            }
                            if (id2.equals(atoms.get(i).id)) {
                                ligacao.primeiroAtomo = atoms.get(i);
                            }
                        }
                        bonds.add(ligacao);
                    }
                }
            }
        }
        return molecula;
    }


}
