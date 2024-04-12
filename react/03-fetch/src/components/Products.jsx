import ProductItem from "./ProductItem";
import "./Products.css";
import Card from "./Card";

export default function Products(props) {
    let productDisplay = <p className="empty">No products</p>;
    if (props.items.length > 0) {
        productDisplay =
            props.items.map((product) => <ProductItem
                key={product.id}
                item={product} />);
    } return (
        <Card className="products">
            {productDisplay}
        </Card>);
}


