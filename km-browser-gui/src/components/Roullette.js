import './Roulette.css';
import React, {useEffect, useState} from "react";
import {useLocation} from "react-router-dom";
import axios from "axios";
import {useNavigate} from "react-router-dom";


const Roullette = () => {
    const location = useLocation();
    const data = location.state?.data;
    const navigate = useNavigate();
    console.log(data);
    const userId = sessionStorage.getItem("id");
    const [timer, setTimer] = useState(0);
    const [sessionChattD, setSessionChattD] = useState("0");



    const [isInQueue, setIsInQueue] = useState(false);

    const goQue = {
        userId: userId,
        isInQueue: true
    }

    const chatSession = {
        sessionId: "",
        talkerId1: Number(userId),
        talkerId2: Number(userId)
    }

    const joinQueue = async () => {
        try {
            const response = await axios.post('/api/public/person/chat-queue', goQue);

            if (response.data) {
                setIsInQueue(true);
            } else {

            }
        } catch (error) {
            console.error("Error joining the queue:", error);
        }
    }
    const getSession = async () => {
        try {
            const response = await axios.post('/api/public/person/go-talk', chatSession)
            if(response.data){
                console.log(response.data);
                setSessionChattD(response.data.sessionId);
            }}catch (error) {
                console.error("Erorrito", error);
            }
        }

    const joinChat =   async () => {
        if(sessionChattD !== 0){
        sessionStorage.setItem("sessionChatId", sessionChattD);
        navigate("/talking-room");
        }
    }

    useEffect(() => {
        let interval;
        if (isInQueue) {
            interval = setInterval(() => {
                getSession();
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
                {isInQueue && (sessionChattD !== "undefined" && sessionChattD !== "0")  && (<center>
                    <button className="play-button" onClick={joinChat}>Rozmawiaj</button>
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