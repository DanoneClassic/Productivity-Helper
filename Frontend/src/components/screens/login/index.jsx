import {useState} from "react";
import {signIn} from "../../../service/AuthService";
import {Link, useNavigate} from "react-router-dom";
import {useStateContext} from "../../../contexts/ContextProvider.jsx";


export default function SignIn () {
    const [auth, setAuth] = useState({email:'', password:''});
    const {setUser, setToken} = useStateContext();
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        signIn(auth.email, auth.password)
            .then(({data}) => {
                setToken(data.token);
                setUser(JSON.stringify(data.user));
                navigate('/');
                navigate(0);})
            .catch(e => {console.log(e)})
    };

    return (
        <div className="container mx-auto px-4 h-full">
            <div className="flex content-center items-center justify-center h-full">
                <div className="w-full lg:w-4/12 px-4">
                    <div className="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded-lg bg-grey border-0">
                        <div className="rounded-t mb-0 px-6 py-6">
                            <div className="text-center mb-3">
                                <h6 className="text-white text-sm font-bold">
                                    Sign In
                                </h6>
                            </div>
                            <div className="btn-wrapper text-center">
                                <form onSubmit={handleSubmit}>
                                    <div className="relative w-full mb-3">
                                        <input
                                            type="text"
                                            name="username"
                                            placeholder="Username"
                                            value={auth.email}
                                            onChange={(e) => setAuth((prevState) => ({
                                                ...prevState,
                                                email: e.target.value
                                            }))}
                                            className="px-3 py-3 placeholder-grey text-grey bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full"
                                            required
                                        />
                                    </div>
                                    <div className="relative w-full mb-3">
                                        <input
                                            type="password"
                                            name="password"
                                            placeholder="Password"
                                            value={auth.password}
                                            onChange={(e) => setAuth((prevState) => ({
                                                ...prevState,
                                                password: e.target.value
                                            }))}
                                            className="px-3 py-3 placeholder-grey text-grey bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full"
                                            required
                                        />
                                    </div>
                                    <div className="text-center mt-6">
                                        <input
                                            type="submit"
                                            value="Sign In"
                                            className="bg-pink text-white active:bg-blue text-sm font-bold uppercase px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 w-full"
                                        />
                                    </div>
                                    <p className="text-center text-white text-sm font-bold mt-6">
                                        Not Registered? <Link to="/signup" className="text-blue">Create an account</Link>
                                    </p>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};
