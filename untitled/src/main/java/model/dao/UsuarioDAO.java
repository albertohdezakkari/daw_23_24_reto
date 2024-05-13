package model.dao;

import model.entities.Usuario;
import model.factory.DatabaseFactory;
import model.motorsql.MotorMySQL;
import model.motorsql.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements DAO<Usuario, Integer> {

    
    private final String SQL_ADD
            = "INSERT INTO USUARIO (NOMBRE, EMAIL, PASSWORD) VALUES( ";
    /*
    private final String SQL_FINDALL
            = "SELECT * FROM peliculas WHERE 1=1 ";
    
    private final String SQL_FIND_BY_FILTER = 
            "SELECT p.titulo, p.descripcion, p.ano, c.nombre " +
                    "FROM peliculas p " +
                    "INNER JOIN peliculas_categorias pc ON p.id = pc.id_pelicula " +
                    "INNER JOIN categorias c ON pc.id_categoria = c.id ";
    
    private final String SQL_SEARCH_Start
            = "SELECT * FROM pelicula WHERE ID_Pelicula IN ( SELECT ID_Pelicula FROM clasificar WHERE ID_Clasificacion IN ( SELECT ID_Clasificacion FROM clasificar WHERE ID_Genero IN (SELECT ID_Genero FROM genero WHERE UPPER(Genero) LIKE '%";
    private final String SQL_SEARCH_Final
            ="%')))";    
    private final String SQL_DELETE = "DELETE FROM `pelicula` WHERE ID_Pelicula=";
    private final String SQL_UPDATE = "UPDATE `pelicula` SET ";
    */
    
    private MotorSQL motorSql;

    public UsuarioDAO(String db) {
        motorSql = DatabaseFactory.getDatabase(db);
    }   

    @Override
    public int add(Usuario bean) {
        this.motorSql.connect();
        String sql = "";
        sql+= "INSERT INTO USUARIO (NOMBRE, EMAIL, PASSWORD) VALUES(";
                sql+= "'" + bean.getNombre() + "'";
                sql+= ",";
                sql+= "'" + bean.getEmail() + "'";
                sql+= ",";
                sql+= "'" + bean.getPassword()+ "'";
                sql+= ")";
                
        //int lastId = "SELECT MAX(ID) from USUARIO";
        
        
        // bean.setId(lastId); //999
        
        int filasModificadas = this.motorSql.execute(sql);
        this.motorSql.disconnect();
        
        return filasModificadas;
    }

    @Override
    public int delete(Integer e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Usuario bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Usuario> findAll(Usuario bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Usuario login(String email, String password){
        String sql = "SELECT EMAIL, PASSWORD FROM USUARIO WHERE EMAIL = '"+email+"' AND PASSWORD='"+password+"' ";
        this.motorSql.connect();
        ResultSet rs =  this.motorSql.executeQuery(sql);
        Usuario usuario = new Usuario();
        try {
            if(rs.next()){
                usuario.setEmail(rs.getString(1));
                usuario.setPassword(rs.getString(2));
            }else{
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.motorSql.disconnect();
        
        return usuario;
    }
}
    
  