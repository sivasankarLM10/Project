import React, { useEffect, useState } from "react";
import Header from "./Header";

function Counter({ props }) {
  const [count, setCount] = useState(0);

  useEffect(() => {
    console.log("First");

    return () => {
      console.log("Second");
    }

  },[count])
  // console.log(props);
  return (
    <div>
      <button onClick={() => setCount(count + 1)}>Increment </button>
      <h1>Current Value {count}</h1>
    </div>
  );
}

export default Counter;
