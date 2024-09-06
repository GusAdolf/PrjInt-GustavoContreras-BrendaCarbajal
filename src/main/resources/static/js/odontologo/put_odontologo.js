window.addEventListener('load', function () {
    const params = new URLSearchParams(window.location.search);
    const odontologoId = params.get('id');

    if (odontologoId) {
        const url = `/odontologos/${odontologoId}`;
        fetch(url)
            .then(response => response.json())
            .then(data => {
                document.querySelector('#odontologo_id').value = data.id;
                document.querySelector('#nombre').value = data.nombre;
                document.querySelector('#apellido').value = data.apellido;
                document.querySelector('#matricula').value = data.matricula;
            });
    }

    document.querySelector('#edit_odontologo_form').addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value
        };

        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        };

        fetch(`/odontologos/${odontologoId}`, settings)
            .then(response => response.json())
            .then(data => {
                alert('Odontólogo actualizado');
                window.location.href = 'odontologoList.html';
            })
            .catch(error => {
                alert('Error al actualizar el odontólogo');
            });
    });
});
