function deleteOdontologo(id) {
    const url = `/odontologos/${id}`;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
        .then(response => {
            if (response.ok) {
                alert("Odontólogo eliminado correctamente");
                fetchOdontologos(); // Actualiza la lista después de eliminar
            } else {
                alert("Error al eliminar odontólogo");
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
}
