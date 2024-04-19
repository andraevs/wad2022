import AddProduct from "./components/AddProduct";
import Products from "./components/Products";
import { useState } from "react";

export default function App() {
  const INITIAL_PRODUCTS = [
    { id: 1, name: "fridge", price: 12.5, category: "utility" },
    { id: 2, name: "washing machine", price: 30, category: "utility" },
    { id: 3, name: "windows", price: 7.5, category: "software" },
  ];

  const [products, addProduct] = useState(INITIAL_PRODUCTS);
  const addProductHandler = (product) => {
    addProduct((previous_products) => [product, ...previous_products]);
  }


  return (
    <div>
      <AddProduct onAddProduct={addProductHandler}></AddProduct>
      <Products items={products} />
    </div>
  );
}

