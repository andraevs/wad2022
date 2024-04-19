import { useState } from "react";

export default function AddProduct(props) {
  const [spanText, setSpanText] = useState("Initial value");
  const [inputVal, setInputVal] = useState("");

  const textHandler = (event) => setInputVal(event.target.value);

  const clickHandler = () => {
    console.log("HI!");
    setSpanText(inputVal);
    const newProduct = {
      id: Math.random(),
      name: inputVal,
      category: "utility",
    };
    props.onAddProduct(newProduct);
  };



  return (
    <div>
      <span>{spanText}</span>
      <input type="text" value={inputVal} onChange={textHandler}></input>
      <button onClick={clickHandler}>Change value</button>
    </div>
  );
}
