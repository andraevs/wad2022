import Products from "./components/Products";
import AddProduct from "./components/AddProduct";
import React, {useState} from "react"

function App() {
  const INITIAL_PRODUCTS = [
    { id: 1, name: "fridge", price: 12.5, category: "utility" },
    { id: 2, name: "washing machine", price: 30, category: "utility" },
    { id: 3, name: "windows", price: 7.5, category: "software" },
  ];

  // const INITIAL_PRODUCTS=[];

  const [products,addProduct] =useState(INITIAL_PRODUCTS);

  const addProductHandler = (product) => {
    addProduct((previous_products) => [product, ...previous_products]);
  }

  return (
    <React.Fragment>
      <AddProduct onAddProduct={addProductHandler}></AddProduct>
      <Products items={products} />
    </React.Fragment>
  );
}

export default App;
