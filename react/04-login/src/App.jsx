import Login from "./components/Login";
import Products from "./components/Products";
import { useState, useEffect } from "react";

export default function App() {
  const [products, addProduct] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState([]);

  useEffect(() => {
    fetchProductHandler();
  }, []);

  useEffect(() => {
    const token = localStorage.getItem("jwtToken");
    if (token) {
      setIsLoggedIn(true);
    } else {
      setIsLoggedIn(false);
    }
  }, []);

  async function fetchProductHandler() {
    setIsLoading(true);
    const token = localStorage.getItem("jwtToken");
    const response = await fetch("http://localhost:8080/api/v1/products/", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    if (response.ok) {
      const data = await response.json();
      const cleanedProducts = data.map((productData) => {
        return {
          id: productData.id,
          price: productData.price,
          category: productData.type,
        };
      });
      addProduct(cleanedProducts);
    }
    setIsLoading(false);
  }

  return (
    <>
      <div>{isLoggedIn ? "Logged in" : "Logged Out"}</div>
      <Login setIsLoggedIn={setIsLoggedIn} />
      <Products items={products} />
      {isLoading && <p>Loading...</p>}
    </>
  );
}
