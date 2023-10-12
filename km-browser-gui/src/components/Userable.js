import React, { useState } from 'react';
import axios from 'axios';
import './Userable.css';

const Userable = () => {
    const [selectedFile, setSelectedFile] = useState(null);
    const [uploadedImageURL, setUploadedImageURL] = useState('');

    const onFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const onUpload = async () => {
        const formData = new FormData();
        formData.append('file', selectedFile);

        try {
            const response = await axios.post('/api/profile', formData);
            setUploadedImageURL(response.data);
        } catch (error) {
            console.error('Error uploading file:', error);
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

            </div>
        </div>
    );
};

export default Userable;
