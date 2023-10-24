<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Worker" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="lista" scope="request" type="ArrayList<Worker>" />
<html>
<head>
    <title>Trabajadores</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="clearfix mt-3 mt-2">
        <a href="<%=request.getContextPath()%>/WorkerServlet">
            <h1 class="float-start link-dark">Lista de Trabajadores en Bicentenario</h1>
        </a>
        <a class="btn btn-primary float-end mt-1" href="<%=request.getContextPath() %>/WorkerServlet?action=new">Crear trabajador</a>
    </div>
    <hr/>
    <table class="table table-striped mt-3">
        <tr class="table-primary">
            <th>nombre</th>
            <th>apellidos</th>
            <th>correo</th>
            <th>dni</th>
            <th></th>
            <th></th>
        </tr>
        <% for (Worker worker : lista) { %>
        <tr>
            <td><%=worker.getNombre()  %>
            </td>
            <td><%=worker.getApellidos()%>
            </td>
            <td><%=worker.getCorreo()%>
            </td>
            <td><%=worker.getDni()%>
            </td>
            <td><a class="btn btn-success" href="<%=request.getContextPath()%>/WorkerServlet?action=edit&id=<%= worker.getWorkerId() %>">Editar</a></td>
            <td><a onclick="return confirm('¿Esta seguro que desea borrar a este trabajador?')" class="btn btn-danger" href="<%=request.getContextPath()%>/WorkerServlet?action=del&id=<%= worker.getWorkerId() %>">Borrar</a></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>

