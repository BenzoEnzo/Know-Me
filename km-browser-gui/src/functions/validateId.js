export function validateId(crypto) {
    return fetch("/api/public/person/validate", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({crypto})
    })
        .then(response => {
            const authToken = response.headers.get('AUTHORIZATION');
            if (authToken) {
                sessionStorage.setItem("authToken", authToken);
            }

            if (response.ok && response.headers.get("content-type").includes("application/json")) {
                return response.json();
            } else {
                throw new Error('Server response is not JSON or response returned an error');
            }
        })
        .then(data => {
            if (data) {
                sessionStorage.setItem("id", data.id);
            }
            return data;
        });
}