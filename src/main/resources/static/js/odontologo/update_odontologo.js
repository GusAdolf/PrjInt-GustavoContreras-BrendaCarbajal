function editOdontologo(id) {
    const row = document.querySelector(`#odontologo-${id}`);
    const nombre = row.querySelector(".odontologo-nombre").textContent;
    const apellido = row.querySelector(".odontologo-apellido").textContent;
    const matricula = row.querySelector(".odontologo-matricula").textContent;

    row.innerHTML = `
        <td class="px-4 py-2">${id}</td>
        <td class="px-4 py-2"><input type="text" id="edit-nombre-${id}" value="${nombre}" class="border border-gray-300 rounded py-1 px-2"></td>
        <td class="px-4 py-2"><input type="text" id="edit-apellido-${id}" value="${apellido}" class="border border-gray-300 rounded py-1 px-2"></td>
        <td class="px-4 py-2"><input type="text" id="edit-matricula-${id}" value="${matricula}" class="border border-gray-300 rounded py-1 px-2"></td>
        <td class="px-4 py-2">
            <button onclick="saveOdontologo(${id})" class="bg-green-500 text-white py-2 px-4 rounded hover:bg-green-700">Guardar</button>
            <button onclick="cancelEdit(${id})" class="bg-red-500 text-white py-2 px-4 rounded hover:bg-red-700">Cancelar</button>
        </td>
    `;
}

function saveOdontologo(id) {
    const nombre = document.querySelector(`#edit-nombre-${id}`).value;
    const apellido = document.querySelector(`#edit-apellido-${id}`).value;
    const matricula = document.querySelector(`#edit-matricula-${id}`).value;

    const formData = { nombre, apellido, matricula };

    const url = `/odontologos/${id}`;
    const settings = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData)
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(() => {
            alert("Odontólogo actualizado correctamente");
            fetchOdontologos();  // Actualizar la lista después de guardar
        })
        .catch(error => console.error('Error:', error));
}

function cancelEdit(id) {
    fetchOdontologos();  // Recargar la lista para cancelar la edición
}
