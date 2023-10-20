import React, {useContext, useEffect, useState} from 'react';
import axios from 'axios';
import './Userable.css';
import {useNavigate} from "react-router-dom";
import Roullette from "./Roullette";



const Userable = () => {
    const [selectedFile, setSelectedFile] = useState(null);
    const [uploadedImageURL, setUploadedImageURL] = useState('');
    const [name, setName] = useState('');
    const [gender,setGender] = useState('')
    const [newKey, setNewKey] = useState('');
    const [keys, setKeys] = useState([]);
    const [keyId, setKeyId] = useState([]);
    const [areaSize, setAreas] = useState('');
    const areaId = localStorage.getItem("areaId");
    const userId = localStorage.getItem("id");
    const navigate = useNavigate();
    const [isEditing, setIsEditing] = useState(false);
    const [description, setDescription] = useState('Twój opis...');
    const [areasResp, setAreasResp] = useState(null);
    const [imageSrc, setImageSrc] = useState(null);
    const fileName = localStorage.getItem("photoId");
    const [mode, setMode] = useState('edit');

    const switchToEditMode = () => {
        setMode('edit');
    }

    const switchToPreviewMode = () => {
        setMode('preview');
    }

    useEffect(() => {
        // Pobieranie zdjęcia podczas ładowania komponentu
        const loadImage = async () => {
            try {
                const response = await axios.get(`/api/user/profile-image/load/${fileName}`, { responseType: 'arraybuffer' });
                console.log(response.data);
                const base64Image = `data:image/jpeg;base64,${btoa(new Uint8Array(response.data).reduce((data, byte) => data + String.fromCharCode(byte), ''))}`;
                setImageSrc(base64Image);
            } catch (error) {
                console.error("Błąd podczas ładowania zdjęcia:", error);
            }
        };

        loadImage();
    }, [fileName]);

    const onFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const payload = {
        id: userId,
        name: name,
        describe: description,
        gender: gender
    }

    const join = {
        joinerId: userId,
        keyId: keyId
    }


    const onUpload = async () => {
        const formData = new FormData();
        formData.append('file', selectedFile);
        formData.append('userId', userId);
        try {
            const response = await axios.post('/api/user/profile-image', formData);
            setUploadedImageURL(response.data);
            localStorage.setItem("photoId", "azx" + userId + ".jpeg");
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

    const fetchUserName = async () => {
        try {
            const response = await axios.post('/api/user/read', {id: userId});
            setName(response.data.name);
            setDescription(response.data.describe);
            setGender(response.data.gender);
            console.log(response);
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
            const response = await axios.post('/api/user/update', payload)
            if (response.data) {
                setName([name])

            } } catch (error) {
            console.error('Error adding key:', error);
        }
    };

  const updateGender = async (event) => {
      try {
          payload.gender = event.target.value;
          const response = await axios.post('/api/user/update', payload)
          if(response.data){
              setGender([gender])
          } }catch (error)
          {
              console.error('Error adding key:', error);
          }
      };


    useEffect(() => {
        fetchAllKeys();
    }, [newKey]);

    useEffect(() => {
        fetchUserName();
    }, [gender]);

    const handleEnterClick = async (event) => {
        const user = {
            id: userId
        };

        const key = {
            id: event.target.value
        };

        const requestPayload = {
            user: user,
            key: key
        };

        try {
            const response = await axios.post('/api/area', requestPayload,
                {
                    headers: {
                        "Content-Type": "application/json"
                    }
                });

            if (response.status === 201) {
                console.log("Successfully joined area");
                navigate('/roulette', { state: {data: response.data}});
            }
        } catch (error) {
            console.error("Error creating the area:", error);
        }
    }
    return (
        <div className="extended-panel">
            <div className="left-container">
                <div className="mode">
                    <button className={`mode-button edit-mode-button ${mode === 'edit' ? 'active' : ''}`} onClick={switchToEditMode}>Edycja</button>
                    <button className={`mode-button preview-mode-button ${mode === 'preview' ? 'active' : ''}`} onClick={switchToPreviewMode}>Podgląd</button>
                </div>

                {mode === 'edit' && (
                    <>
                        <div className="name-container">
                            <input
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                                placeholder="Dodaj imie"
                            />
                            <button onClick={updateUser}>chng name</button>
                        </div>
                        <div className="image-container">
                            {imageSrc && <img src={imageSrc} alt="Uploaded" />}
                            <input type="file" onChange={onFileChange} />
                            <button onClick={onUpload}>Prześlij</button>
                        </div>
                        <div className="gender-container">
                            <div className="gender-selection">
                                <div className="gender-option">
                                    Kobieta
                                    <input type="radio" onChange={updateGender} name={gender} value="K" checked={gender === 'K'} />
                                </div>
                                <div className="gender-option">
                                    Mężczyzna
                                    <input type="radio" onChange={updateGender} name={gender} value="M" checked={gender === 'M'} />
                                </div>
                            </div>
                        </div>

                        <div className={`sub-container description-container ${isEditing ? 'edit-mode' : ''}`}>
                            <div className="description-container-in">
                                {isEditing ? (
                                    <>
                                    <textarea
                                        className="edit-description"
                                        value={description}
                                        onChange={(e) => setDescription(e.target.value)}
                                    ></textarea>
                                        <button
                                            className="edit-button mini-button"
                                            onClick={() => {
                                                setIsEditing(false);
                                                updateUser();
                                            }}
                                        >
                                            Zapisz
                                        </button>
                                    </>
                                ) : (
                                    <div
                                        className="description-text"
                                        onClick={() => setIsEditing(true)}
                                    >
                                        {description}
                                    </div>
                                )}
                            </div>
                        </div>
                    </>
                )}

                {mode === 'preview' && (
                    <div className="preview-container">
                        <div className="image-preview">
                            {imageSrc && <img src={imageSrc} alt="User's uploaded image" />}
                        </div>
                        <div className="gradient-divider"></div>
                        <div className="name-preview">
                            {name}
                        </div>

                        <div className="gender-preview">
                            {gender === 'K' ? 'Kobieta' : 'Mężczyzna'}
                        </div>

                        <div className="description-preview">
                            {description}
                        </div>
                    </div>
                )}
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
                        let numOfPeople = areaSize[keyObj.id]
                        return (
                            <div className="table-row" key={keyObj.id}>
                                <span>{keyParsed.name}</span>
                                <span>{numOfPeople}</span>
                                <button className="mini-button" onClick={handleEnterClick} value={keyObj.id}>X</button>
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
