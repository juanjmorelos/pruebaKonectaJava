//Obtenemos los productos
let editMode = false
let productId;

document.addEventListener("DOMContentLoaded", function () {
    cargarProductos()

    function cargarProductos() {
        fetch(baseUrl + '/product/getProducts', {
        
        })
        .then(response => response.json())
        .then(products => {
            const tableBody = document.getElementById('productTable');
            tableBody.innerHTML = "";
            products.forEach(product => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${product.productId}</td>
                    <td>${product.productName}</td>
                    <td>${product.productReference}</td>
                    <td>${product.productPrice}</td>
                    <td>${product.productWeight}</td>
                    <td>${product.productCategory}</td>
                    <td>${product.productStock}</td>
                    <td>${convertirFecha(product.productCreateDate)}</td>
                    <td>
                        <button class="btn btn-primary editar-btn" data-product-id="${product.productId}">Editar</button>
                        <button class="btn btn-danger eliminar-btn" data-product-id="${product.productId}">Eliminar</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error al obtener los productos:', error));
    }
    
    function convertirFecha(fecha) {
        const fechaObjeto = new Date(fecha);
        const dia = fechaObjeto.getDate().toString().padStart(2, '0');
        const mes = (fechaObjeto.getMonth() + 1).toString().padStart(2, '0');
        const año = fechaObjeto.getFullYear();
        return `${dia}/${mes}/${año}`;
    }

    const tableBody = document.getElementById('productTable');
    tableBody.addEventListener('click', (event) => {
        const target = event.target;
    
        if (target.classList.contains('editar-btn')) {
            productId = target.dataset.productId;
            obtenerProductoPorId(productId);
            editMode = true;
        }
    
        if (target.classList.contains('eliminar-btn')) {
            const productId = target.dataset.productId;
            eliminarProducto(productId);
        }
    });
    
    //agregamos el producto
    document.getElementById("btnGuardarProducto").addEventListener('click', () => {
        const nombreProducto = document.getElementById("nombreProducto").value;
        const referenciaProducto = document.getElementById("referenciaProducto").value;
        const precioProducto = parseInt(document.getElementById("precioProducto").value);
        const pesoProducto = parseInt(document.getElementById("pesoProducto").value);
        const categoriaProducto = document.getElementById("categoriaProducto").value;
        const stockProducto = parseInt(document.getElementById("stockProducto").value);
    
        let nuevoProducto;

        let endPoint = ""

        if(editMode) {
            endPoint = "/product/editProduct"
            nuevoProducto = {
                productId: productId,
                productName: nombreProducto,
                productReference: referenciaProducto,
                productPrice: precioProducto,
                productWeight: pesoProducto,
                productCategory: categoriaProducto,
                productStock: stockProducto,
              }
        } else {
            endPoint = "/product/insertProduct"
            nuevoProducto = {
                productName: nombreProducto,
                productReference: referenciaProducto,
                productPrice: precioProducto,
                productWeight: pesoProducto,
                productCategory: categoriaProducto,
                productStock: stockProducto,
            }
        }

        console.log(JSON.stringify(nuevoProducto))
    
        fetch(baseUrl + endPoint, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(nuevoProducto),
        })
          .then(response => response.json())
          .then(data => {
            
            if (data.result === "OK") {
                mostrarToast("Producto creado exitosamente", "success");
                cargarProductos()
                if(editMode) {
                    editMode = false;
                }
              } else {
                mostrarToast(data.cause, "error");
              }
    
          })
          .catch(error => {
            console.error("Error al crear el producto:", error);
          });
    })
    
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

    function eliminarProducto(id) {
        const confirmacion = confirm("¿Estás seguro de que deseas eliminar este producto?");
        if (!confirmacion) {
          return;
        }
      
        fetch(`${baseUrl}/product/deleteProduct?productId=${id}`, {
          method: "DELETE"
        })
          .then(response => response.json())
          .then(data => {
            if (data.Result === "Ok") {
              mostrarToast("Producto eliminado exitosamente", "success");
              cargarProductos(); // Actualizar la tabla después de eliminar
            } else {
              mostrarToast("Error al eliminar el producto", "error");
            }
          })
          .catch(error => {
            console.error("Error al eliminar el producto:", error);
            mostrarToast("Error al eliminar el producto", "error");
          });
    }

    function obtenerProductoPorId(productId) {
        const url = `${baseUrl}/product/getProductsById?productId=${productId}`;
    
        fetch(url)
        .then(response => response.json(), {
            method: "GET"
          })
        .then(data => {
            if (data.result === 'OK') {
                const product = data.data;
                document.getElementById("nombreProducto").value = product.productName;
                document.getElementById("referenciaProducto").value = product.productReference;
                document.getElementById("precioProducto").value = product.productPrice;
                document.getElementById("pesoProducto").value = product.productWeight;
                document.getElementById("categoriaProducto").value = product.productCategory;
                document.getElementById("stockProducto").value = product.productStock;

                
                $('#modalAgregarProducto').modal('show');
            }
        })
        .catch(error => console.error('Error al obtener el producto:', error));
    }

    $('#modalAgregarProducto').on('hidden.bs.modal', function () {
        if(editMode) {
            editMode = false;
        }
        document.getElementById("nombreProducto").value = "";
        document.getElementById("referenciaProducto").value = "";
        document.getElementById("precioProducto").value = "";
        document.getElementById("pesoProducto").value = "";
        document.getElementById("categoriaProducto").value = "";
        document.getElementById("stockProducto").value = "";
      });
})

