

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
            if (data && data.sessionId) {
                localStorage.setItem("token", data.sessionId);
            } else
            return data;
        });
}