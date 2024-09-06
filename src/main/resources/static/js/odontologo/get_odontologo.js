function fetchOdontologos() {
    const url = "/odontologos";
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#odontologoTableBody");
            tableBody.innerHTML = "";

            data.forEach(odontologo => {
                const row = `
                    <tr id="odontologo-${odontologo.id}">
                        <td class="px-4 py-2">${odontologo.id}</td>
                        <td class="px-4 py-2 odontologo-nombre">${odontologo.nombre}</td>
                        <td class="px-4 py-2 odontologo-apellido">${odontologo.apellido}</td>
                        <td class="px-4 py-2 odontologo-matricula">${odontologo.matricula}</td>
                        <td class="px-4 py-2">
                            <button onclick="editOdontologo(${odontologo.id})" class="bg-yellow-500 text-white py-2 px-4 rounded hover:bg-yellow-700">Editar</button>
                            <button onclick="deleteOdontologo(${odontologo.id})" class="bg-red-500 text-white py-2 px-4 rounded hover:bg-red-700">Eliminar</button>
                        </td>
                    </tr>
                `;
                tableBody.insertAdjacentHTML('beforeend', row);
            });
        });
}

window.onload = function() {
    fetchOdontologos();  // Cargar los odontólogos cuando se carga la página
};
