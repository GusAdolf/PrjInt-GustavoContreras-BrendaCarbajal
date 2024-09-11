document.getElementById('formAgregarOdontologo').addEventListener('submit', function(event) {
    event.preventDefault(); // Evitar el comportamiento predeterminado del formulario

    // Obtener los valores del formulario
    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const matricula = document.getElementById('matricula').value;

    const odontologo = {
        nombre: nombre,
        apellido: apellido,
        matricula: matricula

    };

    // Hacer la solicitud POST enviando JSON
    fetch('/odontologos/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(odontologo) // Convertir el objeto a JSON
    })
    .then(response => {
        if (response.ok) {
            return response.json(); // Parsear la respuesta JSON
        } else {
            throw new Error('Error al guardar el odontologo');
        }
    })
    .then(data => {
        alert('Odontologo guardado exitosamente');
        console.log(data);
    })
    .catch(error => {
        console.error('Hubo un error:', error);
        alert('Hubo un problema al guardar el odontologo.');
    });
});
