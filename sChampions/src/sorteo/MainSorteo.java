package sorteo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MainSorteo {

    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader("fase.csv"))) {
            String fase = br.readLine().split(";")[0];
            System.out.println("\n	" + fase );
            int nTeams = 0;
            switch (fase) {
                case "OCTAVOS":
                    nTeams = 16;
                    break;
                case "CUARTOS":
                    nTeams = 8;
                    break;
                default:
                    break;
            }
            Team[] teams = new Team[nTeams];
            Integer[][] gmat = new Integer[nTeams][nTeams];
            InicializateSorteo.inicializate(teams, gmat);
            Sorteo s = new Sorteo(teams, gmat, fase);
//			System.out.println("prob = "+s.prob(8, 5));
            s.print(s.sortear());
            if (fase.equals("CUARTOS")) {
                System.out.println("	\n	SEMIFINALES");
                Team[] eliminatorias = {new Team("C1", "E1", "E1", "E1", ""), new Team("C2", "E2", "E2", "E2", ""),
                        new Team("C3", "E3", "E3", "E3", ""), new Team("C4", "E4", "E4", "E4", "")};
                Integer[][] gmate = {{-1, 1, 2, 3}, {0, -1, 2, 3}, {0, 1, -1, 3}, {0, 1, 2, -1}};
                Sorteo s1 = new Sorteo(eliminatorias, gmate, fase);
                s1.print(s1.sortear());
            }
        }
    }
}

