import React, { useState } from 'react';
import axios from 'axios';
import './Userable.css';
import {updateName} from "../functions/update";

const Userable = () => {
    const [selectedFile, setSelectedFile] = useState(null);
    const [uploadedImageURL, setUploadedImageURL] = useState('');
    const [name, setName] = useState('');
    const [newKey, setNewKey] = useState('');
    const [keys, setKeys] = useState([]);

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

    const addKey = async () => {
        try {
            const response = await axios.post('/api/key', { name: newKey });
            if (response.data) {
                setKeys([...keys, newKey]);
                setNewKey('');
            } else {
                alert('Klucz o tej nazwie już istnieje.');
            }
        } catch (error) {
            console.error('Error adding key:', error);
        }
    };

    return (
        <div className="extended-panel">
            <div className="left-container">
                <div className="sub-container image-container">
                    {uploadedImageURL ? (
                        <img src={uploadedImageURL} alt="Uploaded" />
                    ) : (
                        <>
                            <h1>MIEJSCE NA ZDJECIE</h1>
                            <input type="file" onChange={onFileChange} />
                            <button onClick={onUpload}>Prześlij</button>
                        </>
                    )}
                </div>
                <div className="name-container">
                    <input placeholder="Zapisz Imię" />
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
                    {keys.map(key => (
                        <div className="table-row" key={key}>
                            <span>{key}</span>
                            {/* Jeśli chcesz wyświetlić ilość osób i przycisk "Wejdź", dodaj je tutaj. */}
                        </div>
                    ))}
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
