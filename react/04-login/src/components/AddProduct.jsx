import { useState, useRef } from "react";

export default function AddProduct(props) {
  const inputVal = useRef("");

  const clickHandler = () => {
    const newProduct = {
      id: Math.random(),
      name: inputVal.current.value,
      category: "utility",
    };
    props.onAddProduct(newProduct);
  };

  return (
    <div>
      <span>Enter value</span>
      <input type="text" ref={inputVal}></input>
      <button onClick={clickHandler}>Change value</button>
    </div>
  );
}