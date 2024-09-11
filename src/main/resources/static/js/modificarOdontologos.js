document.addEventListener('DOMContentLoaded', function () {
    const buscarBtn = document.querySelector('.btn-dark');
    const matriculaInput = document.querySelector('input[placeholder="Buscar Odontólogo"]');

    // Función para buscar odontólogo por matrícula
    buscarBtn.addEventListener('click', function () {
        const matricula = matriculaInput.value.trim();

        if (matricula) {
            fetch(`/odontologos/search/${matricula}`, {
                method: 'GET'
            })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Odontólogo no encontrado');
                }
            })
            .then(odontologo => {
                // Llenar los campos del formulario con los datos del odontólogo
                document.getElementById('apellido').value = odontologo.apellido;
                document.getElementById('nombre').value = odontologo.nombre;
                document.getElementById('matricula').value = odontologo.matricula;
            })
            .catch(error => {
                console.error('Error:', error);
                alert('No se encontró el odontólogo con esa matrícula.');
            });
        } else {
            alert('Por favor, ingrese una matrícula para buscar.');
        }
    });

    // Función para guardar/editar el odontólogo (POST)
    document.getElementById('formModificarOdontologo').addEventListener('submit', function(event) {
        event.preventDefault(); // Evitar el comportamiento predeterminado del formulario

        // Obtener los valores del formulario
        const nombre = document.getElementById('nombre').value;
        const apellido = document.getElementById('apellido').value;
        const matricula = document.getElementById('matricula').value;

        // Validar que todos los campos estén llenos
        if (!nombre || !apellido || !matricula) {
            alert('Por favor, complete todos los campos.');
            return;
        }

        // Crear el objeto odontólogo
        const odontologo = {
            nombre: nombre,
            apellido: apellido,
            matricula: matricula
        };

        // Hacer la solicitud POST para guardar o actualizar el odontólogo
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
                throw new Error('Error al guardar el odontólogo');
            }
        })
        .then(data => {
            alert('Odontólogo guardado exitosamente');
            console.log(data);
        })
        .catch(error => {
            console.error('Hubo un error:', error);
            alert('Hubo un problema al guardar el odontólogo.');
        });
    });
});
