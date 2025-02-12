import { useState, useEffect } from "react";
import axios from "axios";
import InputsList from "./InputsList.tsx";
import InputAdder from "./InputAdder.tsx";

function App() {
    const [input, setInput] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    
    const fetchInputs = async () => {
        setLoading(true);        try {
            const response = await axios.get("http://localhost:8080/api/input");
            setInput(response.data.reverse());
        } catch (err) {
            setError("Error while fetching data");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchInputs();
    }, []);

    return (
        <>
            <InputAdder onInputAdded={fetchInputs} />
            <InputsList input={input} loading={loading} error={error} />
        </>
    );
}

export default App;
