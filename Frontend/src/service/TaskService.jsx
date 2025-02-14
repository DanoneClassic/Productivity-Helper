import axios from "axios";

/**
 * Retrieves tasks by workspace ID.
 *
 * @param {string} workspaceId - The ID of the workspace.
 * @param {string} token - The token to authenticate the request.
 *
 * @returns {Promise} A promise that resolves to the response of the axios get request.
 */
export const getTasksByWorkspaceId = async (workspaceId, token) => {
    return await axios.get(`http://localhost:8080/api/workspaces/tasks/workspace/${workspaceId}`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });
}

/**
 * Creates a new task.
 *
 * @param {Object} task - The task to be created.
 * @param {string} workSpace - The workspace where the task will be created.
 * @param {string} token - The token to authenticate the request.
 *
 * @returns {Promise} A promise that resolves to the response of the axios post request.
 */
export const createTask = async (task, workSpace, token) => {
    return await axios.post(`http://localhost:8080/api/workspaces/${workSpace}/tasks`, task, {
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': `application/json`
        },
        body: JSON.stringify({
            name: task.name,
            data: task.data,
            state: task.state})
    });
}

/**
 * Removes a task from a workspace.
 *
 * @param {string} input_ID - The ID of the workspace.
 * @param {string} taskID - The ID of the task to be removed.
 * @param {string} token - The token to authenticate the request.
 *
 * @returns {Promise} A promise that resolves to the response of the axios delete request.
 */
export const removeTaskFromWorkspace = async (input_ID, taskID, token) => {
    return await axios.delete(`http://localhost:8080/api/workspaces/${input_ID}/tasks/${taskID}`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
}

/**
 * Updates the name of a task.
 *
 * @param {string} taskID - The ID of the task to be updated.
 * @param {string} newName - The new name of the task.
 * @param {string} token - The token to authenticate the request.
 *
 * @returns {Promise} A promise that resolves to the response of the axios put request.
 */
export const updateTaskName = async (taskID, newName, token) => {
    return await axios.put(`http://localhost:8080/api/workspaces/tasks/${taskID}/updateName`, newName, {
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': `application/json`
        },
        body: JSON.stringify({
            newName: newName})
    });
}

/**
 * Updates the status of a task.
 *
 * @param {string} taskID - The ID of the task to be updated.
 * @param {string} newStatus - The new status of the task.
 * @param {string} token - The token to authenticate the request.
 *
 * @returns {Promise} A promise that resolves to the response of the axios post request.
 */
export const updateTaskStatus = async (taskID, newStatus, token) => {
        return await axios.post(`http://localhost:8080/api/workspaces/tasks/${taskID}/status`, newStatus, {
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': `application/json`
            },
            body: JSON.stringify({
                newStatus: newStatus})
        });
}