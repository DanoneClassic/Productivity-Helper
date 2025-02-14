import axios from "axios";

/**
 * Updates the account with the given id using the provided token.
 *
 * @param {string} token - The token to authenticate the request.
 * @param {number} id - The id of the account to update.
 *
 * @returns {Promise} A promise that resolves to the response of the axios post request.
 */
export const updateAccount = async (token, id) => {
    return await axios.post(`http://localhost:8080/api/users/$/update`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });
}