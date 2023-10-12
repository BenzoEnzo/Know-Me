import './Userable.css';
const Userable = () => {
    return (
        <div className="extended-panel">
            <div className="left-container">
                <div className="sub-container image-container">
                        <h1>MIEJSCE NA ZDJECIE</h1>
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