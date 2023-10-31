import React, { useState, useEffect } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

function Chat() {
    const [stompClient, setStompClient] = useState(null);
    const [messages, setMessages] = useState([]);

    useEffect(() => {
        const socket = new SockJS('http://localhost:8064/chat');
        const client = new Client({
            webSocketFactory: () => socket
        });

        client.onConnect = (frame) => {
            console.log('Connected: ' + frame);

            client.subscribe('/topic/messages', (messageOutput) => {
                const message = JSON.parse(messageOutput.body);
                setMessages(prevMessages => [...prevMessages, message]);
            });
        };

        client.onStompError = (frame) => {
            console.error('STOMP Error: ' + frame.headers['message']);
        };

        client.activate();

        setStompClient(client);

        return () => {
            if(client && client.connected) {
                client.deactivate();
            }
        };
    }, []);

    const sendMessage = (content) => {
        if(stompClient && stompClient.connected) {
            const chatMessage = { sender: 'User', content: content };
            stompClient.publish({ destination: "/app/send", body: JSON.stringify(chatMessage) });
        }
    };

    return (
        <div>
            <div className="messages">
                {messages.map((msg, idx) => (
                    <p key={idx}>{msg.sender}: {msg.content}</p>
                ))}
            </div>
            <button onClick={() => sendMessage('Hello, World!')}>Send Message</button>
        </div>
    );
}

export default Chat;