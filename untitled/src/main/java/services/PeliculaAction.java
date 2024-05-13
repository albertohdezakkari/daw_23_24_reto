package services;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entities.Pelicula;
import model.dao.PeliculaDAO;
import model.factory.DatabaseFactory;

public class PeliculaAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
            case "FILTER":
                cadDestino = findByFilter(request, response);
                break;
        }
        return cadDestino;
    }

    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        PeliculaDAO peliculaDao = new PeliculaDAO(DatabaseFactory.POSTGRE); //SELECT * FROM PELICULAS
        ArrayList<Pelicula> peliculas = peliculaDao.findAll(null);
        return Pelicula.toArrayJSon(peliculas);//[{}, {}]
    }

    private String findByFilter(HttpServletRequest request, HttpServletResponse response) {
        PeliculaDAO peliculaDao = new PeliculaDAO(DatabaseFactory.POSTGRE);
        String tipo = request.getParameter("FILTRO");
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(tipo);
        ArrayList<Pelicula> peliculas = peliculaDao.findAllByCategory(tipo);
        return Pelicula.toArrayJSon(peliculas);
    }
}
