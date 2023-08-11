document.addEventListener("DOMContentLoaded", function () {
    var productSelect = document.getElementById("productSelect");
    var stockInput = document.getElementById("stockInput");
    var cantidadInput = document.getElementById("cantidad");
    var venderButton = document.getElementById("venderProd");
    var productsArray = [];

    getProducts()

    function getProducts() {
        fetch(baseUrl + "/product/getProducts")
        .then(response => response.json())
        .then(products => {
            productsArray = products
            products.forEach(product => {
                var option = document.createElement("option");
                option.value = product.productId;
                option.textContent = product.productName;
                productSelect.appendChild(option);
            });
        })
        .catch(error => console.error("Error al obtener los productos:", error));

        productSelect.addEventListener("change", function () {
            var selectedProductId = productSelect.value;
            var selectedProduct = productsArray.find(product => product.productId == selectedProductId);
            if (selectedProduct) {
                stockInput.value = selectedProduct.productStock;
            }
        });
    }

    venderButton.addEventListener("click", function () {
        var selectedProductId = productSelect.value;
        var amount = parseInt(cantidadInput.value);

        var requestData = {
            productId: selectedProductId,
            amount: amount
        };

        fetch(baseUrl + "/sales/sellProduct", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestData)
        })
        .then(response => response.json())
        .then(data => {
            if(data.result == "OK") {
                mostrarToast("El producto se vendió exitosamente", "success")
                productSelect.innerHTML = '<option selected>Selecciona producto a vender</option>';
                stockInput.value = ""
                cantidadInput.value = ""
                productsArray = []
                getProducts()
            } else {
                mostrarToast(data.msg, "error")
            }
        })
        .catch(error => console.error("Error al vender el producto:", error));
    });

    function mostrarToast(mensaje, tipo) {
        const toastContainer = document.getElementById("toastContainer");
        const toast = document.createElement("div");
        toast.className = `toast show bg-${tipo}`;
        toast.setAttribute("role", "alert");
        toast.setAttribute("aria-live", "assertive");
        toast.setAttribute("aria-atomic", "true");
    
        const toastBody = document.createElement("div");
        toastBody.className = "toast-body";
        toastBody.textContent = mensaje;
    
        toast.appendChild(toastBody);
        toastContainer.appendChild(toast);
    
        const bsToast = new bootstrap.Toast(toast);
        bsToast.show();
    
        // Eliminar el toast después de unos segundos
        setTimeout(function () {
          toastContainer.removeChild(toast);
        }, 5000);
    }
});