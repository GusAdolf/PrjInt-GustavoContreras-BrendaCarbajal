// Función para realizar la solicitud GET y obtener la lista de turnos
function listarTurnos() {
    fetch('/turnos', {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            const listadoDiv = document.getElementById('listadoTurnos');
            listadoDiv.innerHTML = '';  // Limpiar el contenido anterior

            // Crear la tabla
            const table = document.createElement('table');
            table.classList.add('table', 'table-striped');

            const thead = document.createElement('thead');
            thead.innerHTML = `
                <tr>
                    <th>#</th>
                    <th>Paciente</th>
                    <th>Odontólogo</th>
                    <th>Fecha</th>
                    <th>Hora</th>
                </tr>
            `;
            table.appendChild(thead);

            const tbody = document.createElement('tbody');

            // Iterar sobre los turnos
            data.forEach((turno, index) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
                    <td>${turno.odontologo.nombre} ${turno.odontologo.apellido}</td>
                    <td>${new Date(turno.fecha).toLocaleDateString()}</td>
                    <td>${new Date(turno.fecha).toLocaleTimeString()}</td>
                `;
                tbody.appendChild(row);
            });

            // Agregar el cuerpo de la tabla al div listadoTurnos
            table.appendChild(tbody);
            listadoDiv.appendChild(table);
        })
        .catch(error => console.error('Error:', error));
}

// Ejecutar la función listarTurnos cuando se cargue la página
document.addEventListener('DOMContentLoaded', function() {
    listarTurnos();
});
