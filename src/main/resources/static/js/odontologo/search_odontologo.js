// js/odontologo/search_odontologo.js

document.querySelector("#search_odontologo_form").addEventListener('submit', function (event) {
    event.preventDefault();

    const odontologoId = document.querySelector("#odontologo_id_search").value;
    const url = `/odontologos/${odontologoId}`;

    fetch(url)
        .then(response => response.json())
        .then(odontologo => {
            if (odontologo) {
                document.querySelector("#search_response").innerHTML = `
                    <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded">
                        <p><strong>Id:</strong> ${odontologo.id}</p>
                        <p><strong>Nombre:</strong> ${odontologo.nombre}</p>
                        <p><strong>Apellido:</strong> ${odontologo.apellido}</p>
                        <p><strong>Matrícula:</strong> ${odontologo.matricula}</p>
                    </div>
                `;
                document.querySelector("#search_response").style.display = "block";
            } else {
                document.querySelector("#search_response").innerHTML = `
                    <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded">
                        Odontólogo no encontrado.
                    </div>
                `;
                document.querySelector("#search_response").style.display = "block";
            }
        })
        .catch(error => console.error('Error:', error));
});
