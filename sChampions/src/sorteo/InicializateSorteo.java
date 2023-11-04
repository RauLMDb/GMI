package sorteo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

interface InicializateSorteo {

    public static void inicializate(Team[] teams, Integer[][] gmat) throws IOException {
        String name = "";
        String pais = "";
        String grupo = "";
        String pos = "";
        String image = "";
        int k = 0;
        BufferedReader br = new BufferedReader(new FileReader("fase.csv"));
        String linea = br.readLine();
        while ((linea = br.readLine()) != null) {
            String[] data = linea.split(";");
            name = data[0];
            pais = data[1];
            grupo = data[2];
            pos = data[3];
            image = data[4];
            Team t = new Team(name, pais, pos, grupo,image);
            teams[k++] = t;
        }
        br.close();
        for (int i = 0; i < teams.length; i++) {
            for (int j = i; j < teams.length; j++) {
                gmat[i][j] = -1;
                gmat[j][i] = -1;
                if ((teams.length != 8 && !teams[i].getName().equals(teams[j].getName())
                        && !teams[i].getNation().equals(teams[j].getNation())
                        && !teams[i].getGroup().equals(teams[j].getGroup())
                        && !teams[i].getPos().equals(teams[j].getPos()))
                        || (teams.length == 8 && !teams[i].getName().equals(teams[j].getName()))) {
                    gmat[i][j] = j;
                    gmat[j][i] = i;
                }
            }
        }
    }
}
