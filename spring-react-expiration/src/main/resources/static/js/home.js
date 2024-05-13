const prodDiv = document.getElementById("products");
const addProductButton = document.getElementById("addProductButton");

refreshProducts();

function refreshProducts(){
    fetch('/api/v1/products/')
        .then(response => response.json())
        .then(data => showProducts(data));
}

function showProducts(products){
    // Also, useful to see difference between imperative programming in Vanilla JS vs declarative in REACT
    prodDiv.innerHTML="";
    for(let i = 0; i< products.length; i++ ){
        let addedProd = document.createElement("div");
        addedProd.innerHTML = `${products[i].id} ${products[i].name} ${products[i].price}`
        prodDiv.appendChild(addedProd);
    }
}

addProductButton.onclick = () => {
   // TODO 4 create a product JSON from the inputs
   // TODO 5 FETCH POST request to api/v1/products/ containing the new product, then call refreshProducts
}

// TODO 6 delete product
// TODO 7 modify product

