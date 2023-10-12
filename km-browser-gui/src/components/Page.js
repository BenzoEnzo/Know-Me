import './Page.css';
import {useState} from "react";
import axios from "axios";
import {validateId} from "../functions/validateId";
import {useNavigate} from "react-router-dom";


function Page(){
    const [crypto, setCrypto] = useState('');
    const navigate = useNavigate();
    const [isError, setIsError] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    const [isButtonDisabled, setButtonDisabled] = useState(false);
    const handleGenerateId = async () => {
        try {
            const response = await axios.post('/api/user');
            setCrypto(response.data.crypto);
            setButtonDisabled(true);
            setTimeout(function () {
                setButtonDisabled(false);
            }, 20000);
        } catch (err) {
            console.error("Wystąpił błąd podczas generowania ID.");
        }
    }

    const handleValidate = () => {
        validateId(crypto)
            .then(() => {
                console.log("Logged in successfully!");
                navigate("/user")
        }).catch(error => {
            console.error("Wystąpił błąd podczas validacji id", error.message);
            setIsError(true);
            setErrorMessage("Wprowadzono błędne ID!");
            setTimeout(function () {
               setIsError(false);
            }, 2000);
        })
    };

    return (
        <main>
        <div className="panel">
            {!crypto && (
            <input placeholder="ID..." /> )}
            {crypto && (
                <input placeholder={crypto}/>
            )}
            <div>
                <button type="button" onClick={handleValidate}>Prześlij ID</button>
                <button id="idGenerateButton" onClick={handleGenerateId}
                        disabled={isButtonDisabled}
                        className={isButtonDisabled ? "disabled-button" : ""}>Wygeneruj ID</button>
            </div>
        </div>
            {isError && <div className="error-message">{errorMessage}</div>}
        </main>
    );
}
export default Page;