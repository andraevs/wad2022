import "./Products.css";
import ProductItem from "./ProductItem";
import Card from "./Card";

function Products(props) {
  return (
    <Card className="products">
      <ProductItem item={props.items[0]} />
      <ProductItem item={props.items[1]} />
      <ProductItem item={props.items[2]} />
    </Card>
  );
}

export default Products;
