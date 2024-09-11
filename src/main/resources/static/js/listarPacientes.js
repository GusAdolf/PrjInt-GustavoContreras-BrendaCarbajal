// Función para realizar la solicitud GET y obtener la lista de pacientes
function listarPacientes() {
    fetch('/pacientes', {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            const listadoDiv = document.getElementById('listadoPacientes');
            listadoDiv.innerHTML = '';  // Limpiar el contenido anterior

            // Creamos la tabla y el encabezado
            const table = document.createElement('table');
            table.classList.add('table', 'table-striped');

            const thead = document.createElement('thead');
            thead.innerHTML = `
                <tr>
                    <th>#</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>DNI</th>
                    <th>Domicilio</th>
                    <th>Fecha de Alta</th>
                </tr>
            `;
            table.appendChild(thead);

            const tbody = document.createElement('tbody');

            // Iteramos sobre la lista de pacientes y agregamos cada uno como una fila
            data.forEach((paciente, index) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td>${paciente.nombre}</td>
                    <td>${paciente.apellido}</td>
                    <td>${paciente.dni}</td>
                    <td>${paciente.domicilio}</td>
                    <td>${new Date(paciente.fechaAlta).toLocaleString()}</td>
                `;
                tbody.appendChild(row);
            });

            // Agregamos el cuerpo de la tabla y la tabla al div listadoPacientes
            table.appendChild(tbody);
            listadoDiv.appendChild(table);
        })
        .catch(error => console.error('Error:', error));
}

// Ejecutar la función listarPacientes cuando se cargue la página
document.addEventListener('DOMContentLoaded', function() {
    listarPacientes();  // Llamar a la función para listar los pacientes
});
