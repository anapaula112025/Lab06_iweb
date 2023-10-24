<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="worker" type="beans.Worker" scope="request" />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <title>Editar de un trabajador</title>
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Editar un trabajo</h1>
    <form method="post" action="<%=request.getContextPath()%>/WorkerServlet?action=edit">
        <div class="mb-3">
            <label>Nombres</label>
            <input type="text" class="form-control" name="nombres" value="<%=worker.getNombre()%>">
        </div>
        <div class="mb-3">
            <label>Apellidos</label>
            <input type="text" class="form-control" name="apellidos" value="<%=worker.getApellidos()%>">
        </div>
        <div class="mb-3">
            <label>Correo</label>
            <input type="text" class="form-control" name="correo" value="<%=worker.getCorreo()%>">
        </div>
        <div class="mb-3">
            <label>DNI</label>
            <input type="text" class="form-control" name="dni" value="<%=worker.getDni()%>">
        </div>
        <div class="mb-3">
            <label>id Sede</label>
            <input type="text" class="form-control" name="dni" value="<%=worker.getIdSede()%>">
        </div>
        <a href="<%=request.getContextPath()%>/TrabajadorServlet" class="btn btn-danger">Regresar</a>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>

</body>
</html>
