import React from 'react';
import './NotificationModal.css';

const NotificationModal = ({ isOpen, onJoinChat }) => {
    if (!isOpen) {
        return null;
    }

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>You're logged in!</h2>
                <p>You have an active session.</p>
                <button className="play-button" onClick={onJoinChat}>Rozmawiaj</button>
            </div>
        </div>
    );
};

export default NotificationModal;