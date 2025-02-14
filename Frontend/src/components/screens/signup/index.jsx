import React, { useState } from 'react';
import {signUp} from "../../../service/AuthService";
import {useNavigate} from "react-router-dom";



const SingUp = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        name: '',
        surname: '',
        email: '',
        password: '',
        password2: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        signUp(formData.name, formData.surname, formData.email, formData.password)
            .then()
            .catch(e => {console.log(e)})
        navigate('../');
    };

    return (
        <div className="container mx-auto px-4 h-full">
            <div className="flex content-center items-center justify-center h-full">
                <div className="w-full lg:w-4/12 px-4">
                    <div className="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded-lg bg-grey border-0">
                        <div className="rounded-t mb-0 px-6 py-6">
                            <div className="text-center mb-3">
                                <h6 className="text-white text-sm font-bold">
                                    Sign Up
                                </h6>
                            </div>
                            <form onSubmit={handleSubmit}>
                                <div className="relative w-full mb-3">
                                    <input
                                        type="text"
                                        name="name"
                                        placeholder="Name"
                                        value={formData.name}
                                        onChange={handleChange}
                                        className="px-3 py-3 placeholder-grey text-grey bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full"
                                        required
                                    />
                                </div>
                                <div className="relative w-full mb-3">
                                    <input
                                        type="text"
                                        name="surname"
                                        placeholder="Surname"
                                        value={formData.surname}
                                        onChange={handleChange}
                                        className="px-3 py-3 placeholder-grey text-grey bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full"
                                        required
                                    />
                                </div>
{/*                                <div className="relative w-full mb-3">
                                    <input
                                        type="text"
                                        name="username"
                                        placeholder="Username"
                                        value={formData.username}
                                        onChange={handleChange}
                                        className="px-3 py-3 placeholder-grey text-grey bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full"
                                        required
                                    />
                                </div>*/}
                                <div className="relative w-full mb-3">
                                    <input
                                        type="text"
                                        name="email"
                                        placeholder="Email"
                                        value={formData.email}
                                        onChange={handleChange}
                                        className="px-3 py-3 placeholder-grey text-grey bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full"
                                        required
                                    />
                                </div>
                                <div className="relative w-full mb-3">
                                    <input
                                        type="password"
                                        name="password"
                                        placeholder="Password"
                                        value={formData.password}
                                        onChange={handleChange}
                                        className="px-3 py-3 placeholder-grey text-grey bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full"
                                        required
                                    />
                                </div>
                                <div className="relative w-full mb-3">
                                    <input
                                        type="password"
                                        name="password2"
                                        placeholder="Confirm password"
                                        value={formData.password2}
                                        onChange={handleChange}
                                        className="px-3 py-3 placeholder-grey text-grey bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full"
                                        required
                                    />
                                </div>
                                <div className="text-center mt-6">
                                    <input
                                        type="submit"
                                        value="Sign Up"
                                        className="bg-pink text-white active:bg-blue text-sm font-bold uppercase px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 w-full"
                                    />
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SingUp;
