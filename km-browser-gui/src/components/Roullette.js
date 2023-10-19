import './Roulette.css';
import React, {useState} from "react";
import {useLocation} from "react-router-dom";
import axios from "axios";

const Roullette = () => {
    const location = useLocation();
    const data = location.state?.data;

        const [isInQueue, setIsInQueue] = useState(false);

        const joinQueue = async () => {
            try {
                const response = await axios.post('/api/area/queue', data);

                if (response.data && response.data.joined) {
                    setIsInQueue(true);
                } else {

                }
            } catch (error) {
                console.error("Error joining the queue:", error);
            }
        };

        return (
            <div className="roulette extended-panel">
                <div className="roulette-title">
                    Losuj parę !
                </div>
                <div className="queue-text">
                    Dołącz do kolejki rozmówców
                </div>
                <div className="play-container">
                    <div className="gender-container">
                        <label>
                            Kobieta
                            <input type="radio" name="gender" value="K"/>
                        </label>
                        <label>
                            Mężczyzna
                            <input type="radio" name="gender" value="M"/>
                        </label>
                    </div>
                    <button className="play-button" onClick={joinQueue}>Losuj</button>
                </div>
                <div className="persona-lista">
                    <div className="table-row table-header">
                        <span>Lista Użytkowników znajdujących się w tym pokoju:</span>
                    </div>
                    {data.map(areaObj => (
                        <div className="table-row" key={areaObj.id}>
                            <span>{areaObj.userName}</span>
                        </div>
                    ))}
                </div>
            </div>
        );
}
export default Roullette;