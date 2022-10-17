import { useState } from 'react';
import './App.css'
import Counter from './Components/Counter';
import Header from './Components/Header';



function App() {

  let [count, setCount] = useState(false);

  // const addCount = () => {
  //   setCount(count + 1)
  // }

  let emp = [
    { name: 'any name', age: '12' },
    { name: 'jp', age: '22' }
  ]


  return (
    <div>
      {/* <Header /> */}
      <h1 onClick={() => setCount(!count)} > show /Hide </h1>

      {count && <Counter data={emp} />}

      {/* <button onClick={addCount}> Add</button>
      {
        emp.map((obj, index)=>
           (
            <Counter   {...obj} key ={index}/>
          )
        )
      } */}

    </div>
  );
}

export default App;
