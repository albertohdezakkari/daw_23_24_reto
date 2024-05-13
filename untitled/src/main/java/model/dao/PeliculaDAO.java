package model.dao;

import model.entities.Pelicula;
import model.factory.DatabaseFactory;
import model.motorsql.MotorMySQL;
import model.motorsql.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PeliculaDAO implements DAO<Pelicula, Integer> {

    private final String SQL_FINDALL = "SELECT * FROM peliculas WHERE 1=1 ";
    
    private final String SQL_FIND_BY_FILTER = 
            "SELECT p.titulo, p.descripcion, p.ano, c.nombre " +
                    "FROM peliculas p " +
                    "INNER JOIN peliculas_categorias pc ON p.id = pc.id_pelicula " +
                    "INNER JOIN categorias c ON pc.id_categoria = c.id ";



    private final String SQL_SEARCH_Start
            = "SELECT * FROM pelicula WHERE ID_Pelicula IN ( SELECT ID_Pelicula FROM clasificar WHERE ID_Clasificacion IN ( SELECT ID_Clasificacion FROM clasificar WHERE ID_Genero IN (SELECT ID_Genero FROM genero WHERE UPPER(Genero) LIKE '%";
    private final String SQL_SEARCH_Final
            ="%')))";    
    private final String SQL_ADD
            = "INSERT INTO `pelicula` (`Titulo`, `Precio`, `Duracion`, `Trailer`, `Sinopsis`, `N_Votos`, `S_Puntuacion`, `Fecha_Estreno`, `URL`) VALUES ";
    private final String SQL_DELETE = "DELETE FROM `pelicula` WHERE ID_Pelicula=";
    private final String SQL_UPDATE = "UPDATE `pelicula` SET ";
    private MotorSQL motorSql;

    public PeliculaDAO(String db) {
        motorSql = DatabaseFactory.getDatabase(db);
    }   
    
    @Override
    public ArrayList<Pelicula> findAll(Pelicula bean) {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        String sql = SQL_FINDALL;
        try {
            //1º) 
            motorSql.connect();
            if (bean != null) {
                if (bean.getId() != 0) {
                    sql += "AND ID_PELICULA='" + bean.getId() + "'";
                }
                if (bean.getTitulo() != null) {
                    sql += "AND TITULO='" + bean.getTitulo() + "'";
                }

                if (bean.getDuracion() != 0) {
                    sql += "AND DURACION='" + bean.getDuracion() + "'";
                }
                if (bean.getTrailer() != null) {
                    sql += "AND TRAILER='" + bean.getTrailer() + "'";
                }
                if (bean.getSinopsis() != null) {
                    sql += "AND SINOPSIS LIKE('%" + bean.getSinopsis() + "%')";
                }
                if (bean.getnVotos() != 0) {
                    sql += "AND N_VOTOS='" + bean.getnVotos() + "'";
                }
                if (bean.getsPuntuacion() != 0) {
                    sql += "AND S_PUNTUACION='" + bean.getsPuntuacion() + "'";
                }
                if (bean.getFechaEstreno() != null) {
                    sql += "AND FECHA_ESTRENO='" + bean.getFechaEstreno() + "'";
                }
                if (bean.getUrl() != null) {
                    sql += "AND URL='" + bean.getUrl() + "'";
                }
            }

            System.out.println(sql);
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DEBASE DE DATOS A UN ARRAYLIST
                Pelicula pelicula = new Pelicula();

                pelicula.setId(rs.getInt(1));
                pelicula.setTitulo(rs.getString(2));
                pelicula.setPrecio(rs.getDouble(3));
                pelicula.setDuracion(rs.getInt(4));
                pelicula.setTrailer(rs.getString(5));
                pelicula.setSinopsis(rs.getString(6));
                pelicula.setnVotos(rs.getInt(7));
                pelicula.setsPuntuacion(rs.getInt(8));
                pelicula.setFechaEstreno(rs.getString(9));
                pelicula.setUrl(rs.getString(10));

                peliculas.add(pelicula);

            }
       
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return peliculas;
    }
     public ArrayList<Pelicula> findAllByCategory(String category) {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        String sql = SQL_FIND_BY_FILTER 
                    + " WHERE c.nombre like '%" + category+ "%'";
        
        // SELECT p.titulo, p.descripcion, p.ano, c.nombre
        //FROM peliculas p
        //INNER JOIN peliculas_categorias pc ON p.id = pc.id_pelicula
        //INNER JOIN categorias c ON pc.id_categoria = c.id
        //WHERE c.nombre like ('%Drama%');

        try {
            //1º) 
            motorSql.connect();
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DEBASE DE DATOS A UN ARRAYLIST
                Pelicula pelicula = new Pelicula();
                            String titulo = rs.getString(1);
                            String descp = rs.getString(2);
                            int anyo = rs.getInt(3);
                            
                            pelicula.setTitulo(titulo);
                            pelicula.setSinopsis(descp);
                            pelicula.setFechaEstreno(String.valueOf(anyo));
                peliculas.add(pelicula);
                            
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return peliculas;
    }

    public ArrayList<Pelicula> filterType(Pelicula bean, String tipo) {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        String sql = (SQL_SEARCH_Start+tipo+SQL_SEARCH_Final);
        try {
            //1º) 
            motorSql.connect();
            if (bean != null) {
                if (bean.getId() != 0) {
                    sql += "AND ID_PELICULA='" + bean.getId() + "'";
                }
                if (bean.getTitulo() != null) {
                    sql += "AND TITULO='" + bean.getTitulo() + "'";
                }

                if (bean.getDuracion() != 0) {
                    sql += "AND DURACION='" + bean.getDuracion() + "'";
                }
                if (bean.getTrailer() != null) {
                    sql += "AND TRAILER='" + bean.getTrailer() + "'";
                }
                if (bean.getSinopsis() != null) {
                    sql += "AND SINOPSIS LIKE('%" + bean.getSinopsis() + "%')";
                }
                if (bean.getnVotos() != 0) {
                    sql += "AND N_VOTOS='" + bean.getnVotos() + "'";
                }
                if (bean.getsPuntuacion() != 0) {
                    sql += "AND S_PUNTUACION='" + bean.getsPuntuacion() + "'";
                }
                if (bean.getFechaEstreno() != null) {
                    sql += "AND FECHA_ESTRENO='" + bean.getFechaEstreno() + "'";
                }
                if (bean.getUrl() != null) {
                    sql += "AND URL='" + bean.getUrl() + "'";
                }
            }

            System.out.println(sql);
            ResultSet rs = motorSql.
                    executeQuery(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DEBASE DE DATOS A UN ARRAYLIST
                Pelicula pelicula = new Pelicula();

                pelicula.setId(rs.getInt(1));
                pelicula.setTitulo(rs.getString(2));
                pelicula.setPrecio(rs.getDouble(3));
                pelicula.setDuracion(rs.getInt(4));
                pelicula.setTrailer(rs.getString(5));
                pelicula.setSinopsis(rs.getString(6));
                pelicula.setnVotos(rs.getInt(7));
                pelicula.setsPuntuacion(rs.getInt(8));
                pelicula.setFechaEstreno(rs.getString(9));
                pelicula.setUrl(rs.getString(10));

                peliculas.add(pelicula);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return peliculas;
    }

    @Override
    public int add(Pelicula bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getTitulo() + "', "
                    + bean.getPrecio() + ", "
                    + bean.getDuracion() + ", '"
                    + bean.getTrailer() + "', '"
                    + bean.getSinopsis() + "', "
                    + bean.getnVotos() + ", "
                    + bean.getsPuntuacion() + ", '"
                    + bean.getFechaEstreno() + "', '"
                    + bean.getUrl() + "');";

            resp = motorSql.execute(sql);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        if (resp > 0) {
            System.out.println("Película insertada con exito.");
        }
        return resp;
    }

    @Override
    public int delete(Integer id) {
        int resp = 0;
        motorSql.connect();
        try {
            String sql = SQL_DELETE + id;
            motorSql.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motorSql.execute(sql);
            motorSql.execute("SET FOREIGN_KEY_CHECKS=1;");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        if (resp > 0) {
            System.out.println("Borrado con exito.");
        } else {
            System.out.println("No se pudo borrar.");
        }
        return resp;
    }

    @Override
    public int update(Pelicula bean) {
        int resp = 0;
        String sql;
        try {
            motorSql.connect();

            if (bean == null) {
                System.out.println("Introduzca datos a modificar");
            } else {

                sql = SQL_UPDATE;
                if (bean.getTitulo() != null) {
                    sql += "TITULO='" + bean.getTitulo() + "'";
                }

                if (bean.getPrecio() != null) {
                    sql += "PRECIO='" + bean.getPrecio() + "'";
                }

                if (bean.getDuracion() > 0) {
                    sql += "DURACION='" + bean.getDuracion() + "'";
                }

                if (bean.getTrailer() != null) {
                    sql += "TRAILER='" + bean.getTrailer() + "', ";
                }

                if (bean.getSinopsis() != null) {
                    sql += "SIPNOSIS='" + bean.getSinopsis() + "'";
                }

                if (bean.getnVotos() > 0) {
                    sql += "N_Votos='" + bean.getnVotos() + "'";
                }

                if (bean.getsPuntuacion() > 0) {
                    sql += "S_Puntuacion='" + bean.getsPuntuacion() + "'";
                }

                if (bean.getFechaEstreno() != null) {
                    sql += "Fecha_Estreno='" + bean.getFechaEstreno() + "'";
                }

                sql += " WHERE `ID_Pelicula`=" + bean.getId() + ";";
                System.out.println(sql);
                resp = motorSql.execute(sql);
            }

        } catch (Exception e) {

        } finally {
            motorSql.disconnect();
        }

        if (resp > 0) {
            System.out.println("Pelicula actualizada con éxito.");
        } else {
            System.out.println("No se pudo actualizar.");
        }
        return resp;
    }

    public static void main(String[] args) {

        PeliculaDAO peliculaDAO = new PeliculaDAO(DatabaseFactory.ORACLE);

        ArrayList lstPeliculas = peliculaDAO.findAll(null);
        System.out.println(lstPeliculas.toString());
//
//        Pelicula peliprueba = new Pelicula("Joshua y los teleñecos", "www", "abc", "2015", 90, 5, 6, 9, 5.3, null);

//        //Add de registro
        // peliculaDAO.add(peliprueba);
//        //Update del registro con id pelicula 1
        //     peliculaDAO.update(new Pelicula("Titulo cambiado", null, null, null, 0, 0, 0, 1, null));
//        //Delete del registro 2
        //     peliculaDAO.delete(2);
    }

}
