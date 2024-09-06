document.getElementById("add_new_odontologo").onsubmit = function (e) {
    e.preventDefault();

    const formData = {
        nombre: document.querySelector('#nombre').value,
        apellido: document.querySelector('#apellido').value,
        matricula: document.querySelector('#matricula').value
    };

    const url = '/odontologos';
    const settings = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    }

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let successAlert = '<div class="bg-green-100 border-t border-b border-green-500 text-green-700 px-4 py-3" role="alert">Odontólogo agregado correctamente</div>';
            document.querySelector('#response').innerHTML = successAlert;
            document.querySelector('#response').style.display = "block";
            resetUploadForm();
            fetchOdontologos(); // Actualiza la lista de odontólogos automáticamente
        })
        .catch(error => {
            let errorAlert = '<div class="bg-red-100 border-t border-b border-red-500 text-red-700 px-4 py-3" role="alert">Error al agregar el odontólogo</div>';
            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";
        });
};

function resetUploadForm() {
    document.querySelector('#nombre').value = "";
    document.querySelector('#apellido').value = "";
    document.querySelector('#matricula').value = "";
}
