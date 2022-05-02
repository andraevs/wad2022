import Products from "./components/Products";
import React, {useState, useEffect} from "react"

function App() {


  const [products,addProduct] =useState([]);
  const [isLoading, setIsLoading] = useState(false);

  // function fetchProductHandler(){
  //   fetch('http://localhost:8080/api/v1/products/')
  //     .then((response) => {
  //       return response.json();
  //     })
  //     .then((data) => {
  //       const cleanedProducts = data.map((productData) => {
  //         return {
  //           id: productData.id,
  //           price: productData.price,
  //           category: productData.type,
  //         };
  //       });
  //       addProduct(cleanedProducts);
  //     });
  // }

  async function fetchProductHandler(){
    setIsLoading(true);
    const response = await fetch('http://localhost:8080/api/v1/products/');
    const data = await response.json();
    const cleanedProducts = data.map((productData) => {
              return {
                id: productData.id,
                price: productData.price,
                category: productData.type,
              };   });         
        addProduct(cleanedProducts);
        setIsLoading(false);
  }

  useEffect(() => {
    fetchProductHandler();
  }, []);

  return (
    <React.Fragment>
      {/* <button onClick={fetchProductHandler}>Fetch Products</button> */}
      <Products items={products} />
      {isLoading && <p>Loading...</p>}
    </React.Fragment>
  );
}

export default App;
