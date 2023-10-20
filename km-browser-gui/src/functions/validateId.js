


export function validateId(crypto) {
    return fetch("/api/user/validate", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({crypto})
    })
        .then(response => response.json())
        .then(data => {
            if (data) {
                localStorage.setItem("id",data.id);
                localStorage.setItem("token", data.sessionId);
                if(data.photoId !== null){
                localStorage.setItem("photoId", data.photoId);
                }
            } else
            return data;
        });
}