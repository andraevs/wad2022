import Products from "./components/Products";
import { useState, useEffect } from "react";

export default function App() {

  const [products, addProduct] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  useEffect(() => {
    fetchProductHandler();
  }, []);



  async function fetchProductHandler() {
    setIsLoading(true);
    const response = await fetch('http://localhost:8080/api/v1/products/')
    const data = await response.json();
    const cleanedProducts = data.map((productData) => {
      return {
        id: productData.id,
        price: productData.price,
        category: productData.type,
      };
    });
    addProduct(cleanedProducts);
    setIsLoading(false);
  }




  return (
    <>
      <Products items={products} />
      {isLoading && <p>Loading...</p>}
    </>
  );


}

