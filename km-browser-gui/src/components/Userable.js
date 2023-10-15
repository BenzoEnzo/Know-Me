import React, {useEffect, useState} from 'react';
import axios from 'axios';
import './Userable.css';


const Userable = () => {
    const [selectedFile, setSelectedFile] = useState(null);
    const [uploadedImageURL, setUploadedImageURL] = useState('');
    const [name, setName] = useState('');
    const [newKey, setNewKey] = useState('');
    const [keys, setKeys] = useState([]);
    const token = localStorage.getItem("token");

    const onFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const onUpload = async () => {
        const formData = new FormData();
        formData.append('file', selectedFile);
        try {
            const response = await axios.post('/api/user/details', formData);
            setUploadedImageURL(response.data);
        } catch (error) {
            console.error('Error uploading file:', error);
        }
    };

    const fetchAllKeys = async () => {
        try {
            const response = await axios.get('/api/key');
            setKeys(response.data);
        } catch (error) {
            console.error('Error fetching keys:', error);
        }
    };

    const parseJSON = (data) => {
        try {
            return JSON.parse(data);
        } catch (error) {
            console.error('Error parsing JSON:', error);
            return null;
        }
    };

    const addKey = async () => {
        try {
            const response = await axios.post('/api/key', { name: newKey });
            if (response.data) {
                setKeys(prevKeys => [...prevKeys, response.data]);
                setNewKey('');
            } else {
                alert('Klucz o tej nazwie już istnieje.');
            }
        } catch (error) {
            console.error('Error adding key:', error);
        }
    };

    const updateUser = async () => {
        try {
            const response = await axios.post('/api/user/update', { name: name },
                {
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": "Bearer\t" + token
                    }
                });
            if (response.data) {
                setName([name])
            } else {
                alert('Klucz o tej nazwie już istnieje.');
            }
        } catch (error) {
            console.error('Error adding key:', error);
        }
    };
    useEffect(() => {
        fetchAllKeys();
    }, [newKey]);

    return (
        <div className="extended-panel">
            <div className="left-container">
                <div className="sub-container image-container">
                    {uploadedImageURL ? (
                        <img src={uploadedImageURL} alt="Uploaded" />
                    ) : (
                        <>
                            <img src="http://localhost:8061/ds.jpeg" alt="Załadowane zdjęcie" />
                            {uploadedImageURL && <img src={uploadedImageURL} alt="Załadowane zdjęcie" />}
                            <input type="file" onChange={onFileChange} />
                            <button onClick={onUpload}>Prześlij</button>
                        </>
                    )}

                </div>
                <div className="name-container">
                    <div className="sub-container">
                        <input
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            placeholder="Dodaj imie"
                        />
                        <button onClick={updateUser}>chng name</button>
                    </div>
                </div>
                <div className="sub-container description-container">
                    <h1>MIEJSCE NA OPIS</h1>
                </div>
            </div>
            <div className="right-container">
                <div className="sub-container keys-title">
                    Klucze
                </div>
                <div className="sub-container keys-table">
                    <div className="table-row table-header">
                        <span>Nazwa</span>
                        <span>Ilość osób</span>
                        <span>Wejdź</span>
                    </div>
                    {keys.map(keyObj => {
                        let keyParsed = parseJSON(keyObj.name);
                        if(!keyParsed) return null;
                        return (
                            <div className="table-row" key={keyObj.id}>
                                <span>{keyParsed.name}</span>
                            </div>
                        );
                    })}
                </div>
                <div className="sub-container">
                    <input
                        value={newKey}
                        onChange={(e) => setNewKey(e.target.value)}
                        placeholder="Dodaj nowy klucz"
                    />
                    <button onClick={addKey}>Dodaj Klucz</button>
                </div>
            </div>
        </div>
    );
};

export default Userable;
