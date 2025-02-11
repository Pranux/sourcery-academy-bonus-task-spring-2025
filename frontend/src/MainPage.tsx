import axios from 'axios';
import { useState, useEffect} from 'react';

const MainPage = () => {
    
    let [data, setData] = useState([]);

    const fetchInput = () => {
        axios.get('/api/employee')
            .then((res) => {
                setData(res.data);
            })
            .catch(console.log);
    }
    
    return (
        <div>Hello World</div>
    )
}

export default MainPage;