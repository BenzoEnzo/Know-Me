import './Page.css';
import {useState} from "react";
import axios from "axios";
import {validateId} from "../functions/validateId";

function Page(){
    const [crypto, setCrypto] = useState('');
    const [validate, setValidate] = useState('');
    const handleGenerateId = async () => {
        try {
            const response = await axios.post('/api/user');
            setCrypto(response.data.crypto);
            let button = document.getElementById("idGenerateButton");
            button.disabled = true;
            setTimeout(function () {
                button.disabled = false;
            }, 10000);
        } catch (err) {
            console.error("Wystąpił błąd podczas generowania ID.");
        }
    }

    const handleValidate = () => {
        validateId(crypto)
            .then(() => {
                console.log("Logged in successfully!");
        }).catch(error => {
            console.error("Wystąpił błąd podczas validacji id", error.message);
        })
    };

    return (
        <main>
        <div className="panel">
            {!crypto && (
            <input placeholder="ID..." /> )}
            {crypto && (
                <input placeholder={crypto} aria-placeholder/>
            )}
            <div>
                <button type="button" onClick={handleValidate()}>Prześlij ID</button>
                <button id="idGenerateButton" onClick={handleGenerateId}>Wygeneruj ID</button>
            </div>
        </div>
        </main>
    );
}
export default Page;