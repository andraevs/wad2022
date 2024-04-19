import ProductItem from "./ProductItem";
import "./Products.css";
import Card from "./Card";

export default function Products(props) {

    return (
        <Card className="products">
            {props.items.map((product) => <ProductItem
                key={product.id}
                item={product} />)}
        </Card>);
}


