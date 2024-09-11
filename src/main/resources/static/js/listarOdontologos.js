// Función para realizar la solicitud GET y obtener la lista de odontólogos
function listarOdontologos() {
    // Realizamos la solicitud GET a la ruta /odontologos
    fetch('/odontologos/findAll', {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            // Procesamos la respuesta, que contiene la lista de odontólogos
            const listadoDiv = document.getElementById('listadoOdontologos');
            listadoDiv.innerHTML = '';  // Limpiar el contenido anterior

            // Creamos la tabla y el encabezado
            const table = document.createElement('table');
            table.classList.add('table', 'table-striped');

            const thead = document.createElement('thead');
            thead.innerHTML = `
                <tr>
                    <th>#</th>
                    <th>Apellido</th>
                    <th>Nombre</th>
                    <th>Matrícula</th>
                </tr>
            `;
            table.appendChild(thead);

            const tbody = document.createElement('tbody');

            // Iteramos sobre la lista de odontólogos y agregamos cada uno como una fila
            data.forEach((odontologo, index) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td>${odontologo.apellido}</td>
                    <td>${odontologo.nombre}</td>
                    <td>${odontologo.matricula}</td>
                `;
                tbody.appendChild(row);
            });

            // Agregamos el cuerpo de la tabla y la tabla al div listadoOdontologos
            table.appendChild(tbody);
            listadoDiv.appendChild(table);
        })
        .catch(error => console.error('Error:', error));
}

// Ejecutar la función listarOdontologos cuando se cargue la página
document.addEventListener('DOMContentLoaded', function() {
    listarOdontologos();  // Llamar a la función para listar los odontólogos
});
