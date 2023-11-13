export const parseJSON = (data) => {
    try {
        return JSON.parse(data);
    } catch (error) {
        console.error('Error parsing JSON:', error);
        return null;
    }
};