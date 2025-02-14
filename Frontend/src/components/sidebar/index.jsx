import React from 'react';
import {Link} from "react-router-dom";
import {useStateContext} from "../../contexts/ContextProvider";

const Sidebar = () => {
    const { setUser, setToken } = useStateContext();

    const onLogout = (event) => {
        event.preventDefault();
        setUser({})
        setToken(null)
    }

    return (

            <aside>
                <Link to="/workspace">Workspaces</Link>
                <Link to="/statistics">Statistics</Link>
                <Link to="/send">Paragraphs</Link>
                <Link to="/queries">Q&A</Link>
                <Link to="/settings">Settings</Link>
                <button onClick={onLogout}>Logout</button>
            </aside>

    );
};

export default Sidebar;