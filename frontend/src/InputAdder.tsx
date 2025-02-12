import React, {useState} from "react";
import axios from "axios";

interface InputAdderProps {
    onInputAdded: () => void;
}

const InputAdder: React.FC<InputAdderProps> = ({ onInputAdded }) => {

    const [input, setInput] = useState<string>("");
    const [error, setError] = useState<string>("");

    const handleInputChanges = (e: React.ChangeEvent<HTMLInputElement>) => {
        setInput(e.target.value);
    };

    const validateInput = (text: string, type: string) => {
        if (text.trim() === "") {
            return null;
        }
        if (type === "compress") {
            if (!/^[a-z]+$/.test(text)) {
                return "Input must be only lower case letters";
            }
        } else if (type === "decompress") {
            let i = 0;
            const c = text.split("");

            while (i < c.length) {
                if (c[i] >= 'a' && c[i] <= 'z') {
                    i++;
                    if (i >= c.length || !(c[i] >= '0' && c[i] <= '9')) {
                        return "Input must be lower case letters followed by a number";
                    }
                    while (i < c.length && c[i] >= '0' && c[i] <= '9') {
                        i++;
                    }
                } else {
                    return "Input must be lower case letters followed by a number";
                }
            }
        }
        return null;
    };
    
    const handleSubmit = async (e: React.MouseEvent<HTMLButtonElement>, type: string) => {
        e.preventDefault();
        setError('');

        const validationError = validateInput(input, type);
        if (validationError) {
            setError(validationError);
            return;
        }
        
        try {
            const response = await axios.post('http://localhost:8080/api/input', { text: input, type});
            const inputID = response.data.id;
            const fetchResult =
                `http://localhost:8080/api/input/${inputID}/${type === "compress" ? "compressed" : "decompressed"}`
            await axios.get(fetchResult);
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
                    <button className="flex-1"
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
            {error && <p className="text-red-500 mt-2 pt-3">{error}</p>}
        </div>
    )
}

export default InputAdder;