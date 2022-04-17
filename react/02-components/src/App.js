import Products from "./components/Products";
import AddProduct from "./components/AddProduct";

function App() {
  const products = [
    { id: 1, name: "fridge", price: 12.5, category: "utility" },
    { id: 2, name: "washing machine", price: 30, category: "utility" },
    { id: 3, name: "windows", price: 7.5, category: "software" },
  ];

  const addProductHandler = (product) => console.log(product);

  return (
    <div>
      <AddProduct onAddProduct={addProductHandler}></AddProduct>
      <Products items={products} />
    </div>
  );
}

export default App;
