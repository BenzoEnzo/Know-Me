export function validateId(crypto) {
    return fetch("/api/user/validate", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({crypto})
    })
        .then(response => {
            let token = response.headers.get("Authorization");
            localStorage.setItem('tokenJWT', token);
            if (!response.ok) {
                throw new Error("Registration failed");
            }
            return response.json();
        });
}