document.getElementById('formAgregarPaciente').addEventListener('submit', function(event) {
    event.preventDefault(); // Evitar el comportamiento predeterminado del formulario

    // Obtener los valores del formulario
    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const domicilio = document.getElementById('domicilio').value;
    const dni = document.getElementById('dni').value;
    const fechaAlta = document.getElementById('fechaAlta').value;

    // Crear un objeto de paciente
    const paciente = {
        nombre: nombre,
        apellido: apellido,
        domicilio: domicilio,
        dni: dni,
        fechaAlta: fechaAlta
    };

    // Hacer la solicitud POST enviando JSON
    fetch('/pacientes/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(paciente) // Convertir el objeto a JSON
    })
    .then(response => {
        if (response.ok) {
            return response.json(); // Parsear la respuesta JSON
        } else {
            throw new Error('Error al guardar el paciente');
        }
    })
    .then(data => {
        alert('Paciente guardado exitosamente');
        console.log(data);
    })
    .catch(error => {
        console.error('Hubo un error:', error);
        alert('Hubo un problema al guardar el paciente.');
    });
});
