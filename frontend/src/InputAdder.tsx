import {useState} from "react";
import axios from "axios";

const InputAdder = ({ onInputAdded }) => {
    
    const [input, setInput] = useState('');
    const [error, setError] = useState('');
    const [output, setOutput] = useState('');

    const handleInputChanges = (e) => {
        setInput(e.target.value);
    };
    
    const handleSubmit = async (e, type) => {
        e.preventDefault();
        setError('');
        setOutput('');
        try {
            const response = await axios.post('http://localhost:8080/api/input', { text: input, type});
            const inputID = response.data.id;
            const fetchResult =
                `http://localhost:8080/api/input/${inputID}/${type === "compress" ? "compressed" : "decompressed"}`
            const resultResponse = await axios.get(fetchResult);
            setOutput(resultResponse.data.text);
            setInput('');
            onInputAdded();
        } catch (err) {
            setError("Error while processing input ");
        }
    };
    
    return (
        <div className="bg-[#3F4F44] p-6 rounded-xl shadow-lg max-w-md mx-auto text-center shadow-xl">
            <form>
                <input
                    className="text-sm rounded-lg w-full p-3 border border-gray-600 bg-[#DCD7C9] text-black"
                    type="text"
                    value={input}
                    onChange={handleInputChanges}
                    placeholder="Enter text..." 
                    required
                />
                <div className="flex gap-2 mt-4">
                    <button
                        className="flex-1"
                        onClick={(e) =>
                        handleSubmit(e, "compress")}
                    >Compress</button>
                    <button
                        className="flex-1"
                        onClick={(e) =>
                        handleSubmit(e, "decompress")}
                    >Decompress</button>
                </div>
            </form>
            {error && <p className="text-red-500 mt-2">{error}</p>}
        </div>
    )
}

export default InputAdder;