import logo from './logo.svg';
import './App.css';
import React from 'react';

function App() {
  // message is a state variable, change it with setMessage
  const [message, setMessage] = React.useState(null);
  function handleSumbit(){
    //alert("Clicked!");
    console.log('click!');
    //api call to java
    const body = {
      firstName: 'Jiayi',
      lastName: 'Gu',
    };
    const options = {
      method: 'post',
      body: JSON.stringify(body),
    };
    fetch('/api/postTest', options)
      .then(res => res.json()) // do this after api call finsihes
      .then(data => {
        console.log(data);
        setMessage(data.message);
      });
  }

  return (
    <div className="App">
      <button onClick={handleSumbit}>Sumbit</button>
      <h1>{message}</h1>
    </div>
  );
}

export default App;
