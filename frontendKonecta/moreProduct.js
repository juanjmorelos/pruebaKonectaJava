var mostStockButton = document.getElementById("mostStock");
mostStockButton.addEventListener("click", function () {
    fetch(baseUrl + "/product/moreStockProduct")
        .then(response => response.json())
        .then(product => {
            var content = "";
            content += createListItem("Producto ID", product.productId);
            content += createListItem("Nombre", product.productName);
            content += createListItem("Referencia", product.productReference);
            content += createListItem("Precio", product.productPrice);
            content += createListItem("Peso", product.productWeight);
            content += createListItem("Categoría", product.productCategory);
            content += createListItem("Stock", product.productStock);
            content += createListItem("Fecha de Creación", convertirFecha(product.productCreateDate));
            openModal("Producto con más stock", content)
        })
        .catch(error => console.error("Error al obtener el producto:", error));
});

var mostStockButton = document.getElementById("mostSelt");
mostStockButton.addEventListener("click", function () {
    fetch(baseUrl + "/sales/moreProductSelt")
        .then(response => response.json())
        .then(product => {
            var content = "";
            content += createListItem("Producto ID", product.productId);
            content += createListItem("Nombre", product.productName);
            content += createListItem("Referencia", product.productReference);
            content += createListItem("Precio", product.productPrice);
            content += createListItem("Peso", product.productWeight);
            content += createListItem("Categoría", product.productCategory);
            content += createListItem("Stock", product.productStock);
            content += createListItem("Fecha de Creación", convertirFecha(product.productCreateDate));
            openModal("Producto mas vendido", content)
        })
        .catch(error => console.error("Error al obtener el producto:", error));
});


function createListItem(label, value) {
    return `<li class="list-group-item d-flex justify-content-between align-items-start">
        <div class="ms-2 me-auto">
            <div class="fw-bold">${label}</div>
            ${value}
        </div>
    </li>`;
}

function openModal(title, content) {
    document.getElementById('modalTitle').innerHTML = title;
    document.getElementById('modalContent').innerHTML = content;
    $('#modalProducts').modal('show');
}

function convertirFecha(fecha) {
    const fechaObjeto = new Date(fecha);
    const dia = fechaObjeto.getDate().toString().padStart(2, '0');
    const mes = (fechaObjeto.getMonth() + 1).toString().padStart(2, '0');
    const año = fechaObjeto.getFullYear();
    return `${dia}/${mes}/${año}`;
}
