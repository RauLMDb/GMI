package superheroes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class SuperheroesDatabase {
	private Connection conn;

	public SuperheroesDatabase() {
	}

	public boolean openConnection() {
		try {
			if (conn != null && !conn.isClosed())
				return false;
			String serverAddress = "localhost:3306";
			String db = "superheroes";
			String user = "superheroes_user";
			String pass = "superheroes_pass";
			String url = "jdbc:mysql://" + serverAddress + "/" + db;
			conn = DriverManager.getConnection(url, user, pass);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean closeConnection() {
		try {
			if (conn == null)
				return true;
			if (conn.isClosed())
				return true;
			conn.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean createTableEscena() {
		openConnection();
		String query = "CREATE TABLE escena (" + "  id_pelicula INT NOT NULL," + "  n_orden INT,"
				+ "  titulo VARCHAR(100)," + "  duracion INT," + "  PRIMARY KEY (id_pelicula, n_orden),"
				+ "  FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula)"
				+ "  ON DELETE CASCADE ON UPDATE CASCADE" + ");";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			st.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean createTableRival() {
		openConnection();
		String query = "CREATE TABLE rival (" + "  id_sup INT NOT NULL," + "  id_villano INT NOT NULL,"
				+ "  fecha_primer_encuentro DATE,  " + "  PRIMARY KEY (id_sup, id_villano),"
				+ "  FOREIGN KEY (id_sup) REFERENCES superheroe(id_sup)" + "  ON DELETE CASCADE ON UPDATE CASCADE,"
				+ "  FOREIGN KEY (id_villano) REFERENCES villano(id_villano)" + "  ON DELETE CASCADE ON UPDATE CASCADE"
				+ ");";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			st.close();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public int loadEscenas(String fileName) {
		openConnection();
		int columnas = 0;
		try {
			conn.setAutoCommit(true);
			String query = "INSERT INTO escena(id_pelicula,n_orden,titulo,duracion) VALUES (?,?,?,?);";
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String linea;
			while( (linea = br.readLine()) != null) {
				try {
					String[] data = linea.split(";");
					PreparedStatement st = conn.prepareStatement(query);

					st.setString(1, data[0]);
					st.setString(2, data[1]);
					st.setString(3, data[2]);
					st.setString(4, data[3]);

					columnas += st.executeUpdate();
					st.close();
				} catch (SQLException e) {
					//e.printStackTrace();
				}
			}
			br.close();
			return columnas;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int loadProtagoniza(String fileName) {
		openConnection();
		int columnas = 0;
		try {
			conn.setAutoCommit(false);
			String query = "INSERT INTO protagoniza(id_sup,id_villano,id_pelicula) VALUES (?,?,?);";
			String query2 = "INSERT INTO rival(id_sup,id_villano) VALUES (?,?);";
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String linea;
			boolean cont = true;
			while( (linea = br.readLine()) != null && cont) {
				try {
					String[] data = linea.split(";");

					PreparedStatement st = conn.prepareStatement(query);
					st.setString(1, data[0]);
					st.setString(2, data[1]);
					st.setString(3, data[2]);
					columnas += st.executeUpdate();
					st.close();

					try {
						PreparedStatement st2 = conn.prepareStatement(query2);
						st2.setString(1, data[0]);
						st2.setString(2, data[1]);
						columnas += st2.executeUpdate();
						st2.close();
					} catch (SQLException e) {}
				} catch (SQLException e) {
					columnas = 0;
					conn.rollback();
					cont = false;
				}
			}

			br.close();
			conn.commit();
			return columnas;
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
		} catch (SQLException e) {
			// e.printStackTrace();
		}
		return 0;
	}
	
	public String catalogo() {
		openConnection();
		String catalogo = "";
		String query = "SELECT titulo FROM pelicula ORDER BY pelicula.titulo ASC;";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				catalogo += rs.getNString(1) + ", ";
			}
			if (catalogo.length() == 0)
				catalogo = "{}";
			else
				catalogo = "{" + catalogo.substring(0, catalogo.length() - 2) + "}";
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return catalogo;
	}

	public int duracionPelicula(String nombrePelicula) {
		openConnection();
		Integer duracion = 0;
		String existequery = "SELECT pelicula.titulo FROM pelicula WHERE pelicula.titulo = ?;";
		String duracionquery = "SELECT sum(duracion) FROM escena,pelicula WHERE pelicula.titulo = ?"
				+ "AND pelicula.id_pelicula = escena.id_pelicula;";
		try {
			PreparedStatement st = conn.prepareStatement(duracionquery);
			st.setString(1, nombrePelicula);
			ResultSet rs = st.executeQuery();
			PreparedStatement st2 = conn.prepareStatement(existequery);
			st2.setString(1, nombrePelicula);
			ResultSet rs2 = st2.executeQuery();
			if (!rs2.next()) {
				duracion = -1;
			}
			st2.close();
			rs2.close();
			if (duracion != -1 && rs.next()) {
				duracion = rs.getInt(1);
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		}
		return duracion;
	}

	public String getEscenas(String nombreVillano) {
		openConnection();
		String escenas = "";
		String query = "SELECT escena.titulo" + " FROM escena,villano,protagoniza,pelicula"
				+ " WHERE villano.nombre = ?" + " AND villano.id_villano = protagoniza.id_villano"
				+ " AND protagoniza.id_pelicula = pelicula.id_pelicula"
				+ " AND pelicula.id_pelicula = escena.id_pelicula" + " GROUP BY escena.titulo"
				+ " ORDER BY pelicula.titulo ASC, escena.n_orden ASC";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, nombreVillano);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				escenas += rs.getNString(1) + ", ";
			}
			if (escenas.length() == 0)
				escenas = "{}";
			else
				escenas = "{" + escenas.substring(0, escenas.length() - 2) + "}";
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return escenas;
	}

	public boolean desenmascara(String nombre, String apellido, String filename) {
		openConnection();
		String query = "SELECT avatar FROM superheroe,persona_real" + " WHERE persona_real.nombre = ?"
				+ " AND persona_real.apellido = ?" + " AND persona_real.id_persona = superheroe.id_persona;";
		boolean res = false;
		try {
			File file = new File(filename);
			FileOutputStream output = new FileOutputStream(file);
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, nombre);
			st.setString(2, apellido);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				InputStream input = rs.getBinaryStream(1);
				if (input != null) {
					res = true;
					byte[] buffer = new byte[1024];
					while (input.read(buffer) > 0) {
						output.write(buffer);
					}
					input.close();
				}
			}
			output.close();
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return res;
	}

}