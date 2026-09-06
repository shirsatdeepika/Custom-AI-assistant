import React from 'react';

function ChatComponent() {
  
    const [prompt , setPrompt] = React.useState('');
    const [chatResponse , setChatResponse] = React.useState('');

    const askAI = async() => {

         try{
           // const response = await fetch('http://localhost:8080/ask-ai?prompt=${prompt}')  //make API call to backened server using fetch
            const response = await fetch(`/api/ask-ai?prompt=${encodeURIComponent(prompt)}`);
            const data = await response.text(); 
            console.log(data);
            setChatResponse(data);  //update chat response to state
            }
        catch(error)
        {
          console.error('Error generating chat response:', error);
        }
    };
    return (
        <div>
            <h2>Talk to AI</h2>
            <input type="text" value={prompt} onChange={(e) => setPrompt(e.target.value)} 
            placeholder="Enter prompt for AI" />
            <button onClick={askAI}>Ask AI</button>
            <div className="output">
                <h3>AI Response:</h3>
                <p>{chatResponse}</p>
            </div>
        </div>
    );
}

export default ChatComponent;
