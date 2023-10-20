import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button, Form, ListGroup } from 'react-bootstrap';

const TalkingRoom = () => {
    const [ws, setWs] = useState(null);
    const [messages, setMessages] = useState([]);
    const [inputValue, setInputValue] = useState('');
    const sessionId = localStorage.getItem("sessionChatId");
    useEffect(() => {
        console.log(sessionId);
        const websocket = new WebSocket("ws://localhost:8061/api/live-chat/" + sessionId);
        websocket.onopen = () => {
            console.log("Connected to the websocket");
        };
        websocket.onmessage = (event) => {
            setMessages((prevMessages) => [...prevMessages, { type: 'bot', content: event.data }]);
        };
        websocket.onclose = () => {
            console.log("Disconnected from the websocket");
        };
        setWs(websocket);
        return () => {
            websocket.close();
        };
    }, []);

    const handleSendMessage = () => {
        if (inputValue.trim() !== '') {
            setMessages([...messages, { type: 'user', content: inputValue }]);
            sendMessage(inputValue);
            setInputValue('');
        }
    };

    const sendMessage = (message) => {
        if (ws) {
            ws.send(message);
        }
    };

    return (
        <Container className="chat-container">
            <Row className="chat-box">
                <Col xs={12}>
                    <ListGroup>
                        {messages.map((message, index) => (
                            <ListGroup.Item key={index} className={`chat-message ${message.type}`}>
                                {message.content}
                            </ListGroup.Item>
                        ))}
                    </ListGroup>
                </Col>
            </Row>
            <Row className="chat-input">
                <Col xs={10}>
                    <Form.Control
                        value={inputValue}
                        onChange={(e) => setInputValue(e.target.value)}
                        placeholder="Wpisz swoją wiadomość..."
                    />
                </Col>
                <Col xs={2}>
                    <Button onClick={handleSendMessage}>Wyślij</Button>
                </Col>
            </Row>
        </Container>
    );
};

export default TalkingRoom;
