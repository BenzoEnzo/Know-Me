import './Roulette.css';
import React, {useEffect, useState} from "react";
import {useLocation} from "react-router-dom";
import axios from "axios";

const Roullette = () => {
    const location = useLocation();
    const data = location.state?.data;
    console.log(data);
    const userId = localStorage.getItem("id");
    const [timer, setTimer] = useState(0);

    const [isInQueue, setIsInQueue] = useState(false);

    const goQue = {
        userId: userId,
        isInQueue: true
    }

    const joinQueue = async () => {
        try {
            const response = await axios.post('/api/area/queue', goQue);

            if (response.data) {
                setIsInQueue(true);
            } else {

            }
        } catch (error) {
            console.error("Error joining the queue:", error);
        }
    }
    useEffect(() => {
        let interval;
        if (isInQueue) {
            interval = setInterval(() => {
                setTimer(prevTime => prevTime + 1);
            }, 1000);
        }
        return () => clearInterval(interval);
    }, [isInQueue]);

    return (
            <div className="roulette extended-panel">

                <div className="roulette-title">
                    Losuj parę !
                </div>
                {!isInQueue && (
                <div className="queue-text">
                    Dołącz do kolejki rozmówców
                </div>
                )}
                {isInQueue && (
                    <div className="queue-text">
                        Czas w kolejce: {timer} sekund
                    </div>
                )}
                {!isInQueue && (
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
                    )}
                {isInQueue && (<center>
                    <button className="play-button" onClick={joinQueue}>Exit</button>
                    </center>
                )
                }
                <div className="persona-lista">
                    <div className="table-row table-header">
                        <span>Lista Użytkowników znajdujących się w tym pokoju:</span>
                    </div>
                    {data.map(areaObj => (
                        <div className="table-row" key={areaObj.id}>
                            <span>{areaObj.name}</span>
                        </div>
                    ))}
                </div>
            </div>
        );
}
export default Roullette;