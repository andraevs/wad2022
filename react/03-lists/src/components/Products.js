import "./Products.css";
import ProductItem from "./ProductItem";
import Card from "./Card";

function Products(props) {
  return (
    <Card className="products">
      {props.items.map((product) => <ProductItem 
      key = {product.id}
      item={product} />)}
    </Card>
  );
}

export default Products;
