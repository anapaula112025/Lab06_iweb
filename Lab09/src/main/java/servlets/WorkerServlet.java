package servlets;

import beans.Worker;
import daos.WorkerDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "WorkerServlet", value = "/WorkerServlet")
public class WorkerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        WorkerDao workerDao = new WorkerDao();

        switch (action){
            case "lista":
                ArrayList<Worker> list = workerDao.listar();

                //mandar la lista a la vista -> job/lista_2.jsp
                request.setAttribute("lista",list);
                RequestDispatcher rd = request.getRequestDispatcher("worker/lista.jsp");
                rd.forward(request,response);
                break;
            case "new":
                request.getRequestDispatcher("worker/form_new.jsp").forward(request,response);
                break;
            case "edit":
                String dni = request.getParameter("dni");
                Worker worker = workerDao.buscarPorDni(dni);

                if(worker != null){
                    request.setAttribute("worker",worker);
                    request.getRequestDispatcher("worker/form_edit.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/WorkerServlet");
                }
                break;
            case "del":
                String dnii = request.getParameter("dni");
                Worker workerr = workerDao.buscarPorDni(dnii);

                if(workerr != null){
                    try {
                        workerDao.borrar(dnii);
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/WorkerServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        WorkerDao workerDao = new WorkerDao();

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action) {
            case "crear"://voy a crear un nuevo trabajo
                String nombre = request.getParameter("nombres");
                String apellido = request.getParameter("apellidos");
                String correo = request.getParameter("correo");
                String dni = request.getParameter("dni");
                String idSede = request.getParameter("idsede");

                boolean isAllValid = true;

                if (nombre.length() > 35) {
                    isAllValid = false;
                }

                if (apellido.length() > 35) {
                    isAllValid = false;
                }

                if (isAllValid) {

                    Worker worker = workerDao.buscarPorDni(dni);

                    if (worker == null) {
                        workerDao.crear(nombre, apellido, correo, dni, Integer.parseInt(idSede));
                        response.sendRedirect(request.getContextPath() + "/WorkerServlet");
                    } else {
                        request.getRequestDispatcher("worker/form_new.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("worker/form_new.jsp").forward(request, response);
                }
                break;
            case "e": //voy a actualizar
                String nombre2 = request.getParameter("nombres");
                String apellido2 = request.getParameter("apellidos");
                String correo2 = request.getParameter("correo");
                String dni2 = request.getParameter("dni");

                boolean isAllValid2 = true;

                if (nombre2.length() > 35) {
                    isAllValid2 = false;
                }

                if (apellido2.length() > 35) {
                    isAllValid2 = false;
                }
                if (isAllValid2) {
                    Worker worker = new Worker();
                    worker.setNombre(nombre2);
                    worker.setApellidos(apellido2);
                    worker.setCorreo(correo2);
                    worker.setDni(dni2);

                    workerDao.actualizar(worker);
                    response.sendRedirect(request.getContextPath() + "/WorkerServlet");
                } else {
                    request.setAttribute("worker", workerDao.buscarPorDni(dni2));
                    request.getRequestDispatcher("worker/form_edit.jsp").forward(request, response);
                }
                break;
        }
    }
}

