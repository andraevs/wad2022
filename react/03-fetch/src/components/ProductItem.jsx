import "./ProductItem.css";
function ProductItem(props) {
    const name = props.item.name;
    const price = props.item.price;
    const category = props.item.category;


    return (
        <div className="product-item">
            <div>{name}</div>
            <div>{price}</div>
            <div>{category}</div>
        </div>);
}

export default ProductItem;

