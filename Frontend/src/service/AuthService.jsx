import axios from "axios";

/**
 * Signs up a new user.
 *
 * @param {string} name - The first name of the user.
 * @param {string} surname - The last name of the user.
 * @param {string} email - The email of the user.
 * @param {string} password - The password of the user.
 *
 * @returns {Promise} A promise that resolves to the response of the axios post request.
 */
export const signUp = async (name, surname, email, password) => {
    return await axios.post(`http://localhost:8080/api/auth/register`, {
        email: email,
        password: password,
        username: `${name}${surname}`,
        name: `${name} ${surname}`
    });
}

/**
 * Signs in a user.
 *
 * @param {string} email - The email of the user.
 * @param {string} password - The password of the user.
 *
 * @returns {Promise} A promise that resolves to the response of the axios post request.
 */
export const signIn = async (email, password) => {
    return await axios.post(`http://localhost:8080/api/auth/token`, {
        email: email,
        password: password
    });
}
