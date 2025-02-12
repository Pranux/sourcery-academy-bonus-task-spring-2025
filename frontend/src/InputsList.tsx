import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface InputItem {
    id: string;
    text: string;
    type: string;
}

interface InputsListProps {
    input: InputItem[];
    loading: boolean;
    error: string;
}

const InputsList: React.FC<InputsListProps> = ({ input, loading, error }) => {
    const [processedData, setProcessedData] = useState<Record<string, string>>({});

    const fetchProcessedData = async (id: string, type: string) => {
        try {
            const endpoint = type === "compress" ? "compressed" : "decompressed";
            const response = await axios.get(`http://localhost:8080/api/input/${id}/${endpoint}`);
            setProcessedData(prev => ({
                ...prev,
                [id]: response.data.text !== undefined ? response.data.text : ""
            }));
        } catch (err) {
            console.error("Error while fetching processed data");
        }
    };

    useEffect(() => {
        input.forEach(item => {
            fetchProcessedData(item.id, item.type);
        });
    }, [input]);

    if (loading) return <div className="text-[#DCD7C9]">Loading...</div>;
    if (error) return <div className="text-red-500">{error}</div>;

    return (
        <div className="max-w-full overflow-x-auto mt-6 shadow-xl">
            <table className="rounded-lg overflow-hidden">
                <thead>
                    <tr>
                        <th className="p-3">ORIGINAL WORD</th>
                        <th className="p-3">COMPRESSED/DECOMPRESSED WORD</th>
                    </tr>
                </thead>
                <tbody>
                {input.map((item) => (
                    <tr key={item.id}>
                        <td className="p-3 text-center">"{item.text}"</td>
                        <td className="p-3 text-center">
                            "{processedData[item.id] !== undefined ? processedData[item.id] : "Loading..."}"
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default InputsList;
