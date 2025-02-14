import React from 'react';
import Sidebar from "../sidebar";
import {Navigate, Outlet} from "react-router-dom";
import {useStateContext} from "../../contexts/ContextProvider";

export default function UserLayout() {

    const {user, token, setUser, setToken} = useStateContext();

    if (!token) {
        return <Navigate to="/login"/>
    }

    return (
    <div id="userLayout">
        <Sidebar />
        <div className="content">
            <main>
                <Outlet/>
            </main>
        </div>
    </div>
    )
}