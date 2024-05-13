import services.PeliculaAction;
import services.UsuarioAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
// ENDPOINTS DE EJEMPLOS PARA QUE VEÁIS LA ESTRUCTURA
        //http://localhost:8080/untitled/Controller?ACTION=PELICULA.FIND_ALL
        // http://localhost:8080/API_JAVA_MYSQL/Controller?ACTION=PELICULA.FILTER&FILTRO=DRAMA
//
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir acceso desde cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // Permitir los métodos HTTP
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Permitir ciertos encabezados
        response.setHeader("Access-Control-Max-Age", "3600"); // Cache de opciones preflight durante 1 hora

        PrintWriter out = response.getWriter();
        String action = request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");

        switch (arrayAction[0]) {
            case "USUARIO":
                UsuarioAction usuarioAction = new UsuarioAction();
                String respUser = usuarioAction.execute(request, response);
                out.print(respUser);
                break;
            case "PELICULA":
                String resp = new PeliculaAction().execute(request, response);
                System.out.println(resp);
                out.print(resp);

                break;

    }
}
}
