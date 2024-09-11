function cargarVista(url, contenedorId) {
    fetch(url)
        .then(response => {
            if (response.ok) {
                return response.text(); // Obtener el HTML de la respuesta
            } else {
                throw new Error('Error al cargar la vista');
            }
        })
        .then(html => {
            // Cargar el HTML recibido en el contenedor
            const contenedor = document.getElementById(contenedorId);
            contenedor.innerHTML = html;
             // Ocultar el texto de bienvenida
                        document.getElementById('bienvenida-texto').style.display = 'none';
                        document.getElementById('bienvenida-subtexto').style.display = 'none';

            // Ejecutar cualquier script embebido dentro del HTML cargado
            const scripts = contenedor.querySelectorAll('script');
            scripts.forEach(script => {
                const newScript = document.createElement('script');
                if (script.src) {
                    newScript.src = script.src;  // Para scripts con src
                } else {
                    newScript.textContent = script.textContent;  // Para scripts inline
                }
                document.body.appendChild(newScript);  // Agregar el script al DOM
            });
        })
        .catch(error => {
            console.error('Hubo un problema con la solicitud:', error);
            alert('No se pudo cargar la vista.');
        });
}
