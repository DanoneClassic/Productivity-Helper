import {createContext, useContext, useState} from "react";

/**
 * Creates a context for the application state.
 */
const StateContext = createContext({
    user: null,
    token: null,
    setUser: () => {},
    setToken: () => {}
});

/**
 * Provides the application state context.
 *
 * @param {Object} props - The children components.
 * @returns {JSX.Element} The context provider component.
 */
export const ContextProvider = ({children}) => {
    const [user, _setUser] = useState(sessionStorage.getItem('USER'));
    const [token, _setToken] = useState(sessionStorage.getItem('ACCESS_TOKEN'));

    /**
     * Sets the token in the session storage.
     *
     * @param {string} input_token - The token.
     */
    const setToken = (input_token) => {
        if (input_token) {
            sessionStorage.setItem('ACCESS_TOKEN', input_token);
        } else {
            sessionStorage.removeItem('ACCESS_TOKEN');
        }
    }

    /**
     * Sets the user in the session storage.
     *
     * @param {string} input_user - The user.
     */
    function setUser(input_user) {
        if (input_user) {
            sessionStorage.setItem('USER', input_user);
        } else {
            sessionStorage.removeItem('USER');
        }
    }

    return (
        <StateContext.Provider value={{
            user,
            token,
            setUser,
            setToken,
        }}>
            {children}
        </StateContext.Provider>
    )
}

/**
 * Uses the application state context.
 *
 * @returns {Object} The application state context.
 */
export const useStateContext = () => useContext(StateContext);