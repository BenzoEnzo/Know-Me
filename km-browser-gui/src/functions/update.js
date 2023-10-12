export function updateName(name) {
    return fetch("/api/user/details", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({name})
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("failed");
            }
            return response.json();
        });
}