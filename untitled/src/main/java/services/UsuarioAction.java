package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entities.Usuario;
import model.dao.UsuarioDAO;
import model.factory.DatabaseFactory;

public class UsuarioAction implements IAction{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "LOGIN":
                cadDestino = login(request, response);
                break;
            case "REGISTER":
                //cadDestino = findByFilter(request, response);
                break;
            case "FIND":
                //cadDestino = findByFilter(request, response);
                break;
                
        }
        return cadDestino;
    }
    
     private String login(HttpServletRequest request, HttpServletResponse response) {
        // SELECT * FROM USUARIO WHERE EMAIL = 'a@svalero.com'  AND PASSWORD='1234'
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        //String nombre = request.getParameter("NOMBRE");
        
       /* Usuario usuario =new Usuario();
            usuario.setEmail(email);
            usuario.setPassword(password);*/
            //usuario.setNombre(nombre);
         
        UsuarioDAO usuarioDAO = new UsuarioDAO(DatabaseFactory.POSTGRE);
         
            //int numUsuarios = usuarioDAO.add(usuario);
         
        Usuario usuario2 = usuarioDAO.login(email, password);
        
        // String resp = "['message:'good', 'usuario':{email:'"+email+"', password:'1234'}]";
        
        return Usuario.fromObjectToJSON(usuario2) ; 
    }
}
