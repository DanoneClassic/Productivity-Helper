import axios from "axios";

/**
 * Retrieves all workspaces.
 *
 * @param {string} token - The token to authenticate the request.
 *
 * @returns {Promise} A promise that resolves to the response of the axios get request.
 */
export const getAllWorkspaces = async (token) => {
    return await axios.get(`http://localhost:8080/api/workspaces`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });
}

/**
 * Retrieves a workspace by ID.
 *
 * @param {string} inputID - The ID of the workspace.
 * @param {string} token - The token to authenticate the request.
 *
 * @returns {Promise} A promise that resolves to the response of the axios get request.
 */
export const getWorkspaceById = async (inputID, token) => {
    return await axios.get(`http://localhost:8080/api/workspaces/${inputID}`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });
}

/**
 * Retrieves workspaces by user ID.
 *
 * @param {string} inputID - The ID of the user.
 * @param {string} token - The token to authenticate the request.
 *
 * @returns {Promise} A promise that resolves to the response of the axios get request.
 */
export const getWorkSpacesByUserId = async (inputID, token) => {
    return await axios.get(`http://localhost:8080/api/workspaces/user/${inputID}`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });
}

/**
 * Creates a new workspace.
 *
 * @param {Object} workspace - The workspace to be created.
 * @param {string} token - The token to authenticate the request.
 *
 * @returns {Promise} A promise that resolves to the response of the axios post request.
 */
export const createWorkspace = async (workspace, token) => {
    return await axios.post(`http://localhost:8080/api/workspaces/save`, workspace, {
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': `application/json`
        },
        body: JSON.stringify({name: workspace.name, ownerId: workspace.ownerId})
    });
}

/**
 * Deletes a workspace by ID.
 *
 * @param {string} input_ID - The ID of the workspace.
 * @param {string} token - The token to authenticate the request.
 *
 * @returns {Promise} A promise that resolves to the response of the axios delete request.
 */
export const deleteWorkspace = async (input_ID, token) => {
    return await axios.delete(`http://localhost:8080/api/workspaces/deleteWorkSpaceById/${input_ID}`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
}

/**
 * Updates a workspace.
 *
 * @param {Object} workspace - The workspace to be updated.
 * @param {string} token - The token to authenticate the request.
 *
 * @returns {Promise} A promise that resolves to the response of the axios put request.
 */
export const updateWorkspace = async (workspace, token) => {
    return await axios.put(`http://localhost:8080/api/workspaces/update`, workspace, {
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': `application/json`
        },
        body: JSON.stringify({
            id: workspace.id,
            name: workspace.name,
            ownerId: workspace.ownerId,
            tasks: workspace.tasks})
    });
}