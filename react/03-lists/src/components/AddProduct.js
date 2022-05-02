import { useState,useRef } from "react";

function AddProduct(props) {
  // const [spanText, setSpanText] = useState("Initial value");
  // const [inputVal, setInputVal] = useState("");
  const inputVal = useRef();

  // const textHandler = (event) => setInputVal(event.target.value);

  const clickHandler = () => {
    console.log("HI!");
    // setSpanText("Updated");
    // setSpanText(inputVal);
    const newProduct = {
      id: Math.random(),
      name: inputVal.current.value,
      category: "utility",
    };
    props.onAddProduct(newProduct);
  };

  return (
    <div>
      <input type="text" ref={inputVal}></input>
      <button onClick={clickHandler}>Change value</button>
    </div>
  );
}

export default AddProduct;
