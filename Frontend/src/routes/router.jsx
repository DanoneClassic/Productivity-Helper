import {createBrowserRouter, Navigate} from "react-router-dom";
import UserLayout from "../components/layout/UserLayout";
import Workspace from "../components/screens/workspace";
import Task from "../components/screens/workspace/Task/task";
import GuestLayout from "../components/layout/GuestLayout";
import Login from "../components/screens/login";
import Signup from "../components/screens/signup";
import Queries from "../components/screens/q&a";
import Settings from "../components/screens/settings/AccountSettings";
import {Statistics} from "../components/screens/statistics/Statistics";

/**
 * Defines the routes for the application.
 *
 * @returns {JSX.Element} The router component.
 */
const router = createBrowserRouter([
    {
        path: '/',
        element: <UserLayout />,
        children: [
            {
                path: '/workspace',
                element: <Workspace />
            },
            {
                path: '/task/:workspaceID',
                element: <Task />
            },
            {
                path: '/queries',
                element: <Queries />
            },
            {
                path: '/settings',
                element: <Settings />
            },
            {
                path: '/statistics',
                element: <Statistics />
            },
        ]
    },
    {
        path: '/',
        element: <GuestLayout />,
        children: [
            {
                path: '/login',
                element: <Login />
            },
            {
                path: '/signup',
                element: <Signup />
            },
        ]
    },
    // {
    //     path: '*',
    //     element: <NotFound />
    // }
])

export default router;