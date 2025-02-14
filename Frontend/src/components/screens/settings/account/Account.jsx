import React, {useEffect, useState} from 'react';
import {useStateContext} from "../../../../contexts/ContextProvider";
import {getAccount} from "../../../../service/AccountService";


const userData = {
    name: 'John',
    surname: 'Doe',
    email: 'john.doe@example.com'
};

export default function Account(){

    const [formData, setFormData] = useState(userData);
    const { token, user } = useStateContext();
    const {account, setAccount} = useState();
    const [loading, setLoading] = useState(true);

    const newUser = JSON.parse(user);

    //
    // useEffect(() => {
    //     getAccount(token)
    //         .then((result) => {
    //             setAccount(result.data);
    //             setLoading(false);
    //         })
    //         .catch(e => {
    //             console.log(e);
    //             setLoading(false);
    //         });
    // }, [token]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setAccount(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        console.log(e);
    };
    return (
        <div className="form-container w-full">
            <form onSubmit={handleSubmit}>
                <div className="relative w-full mb-3">
                    <input
                        type="text"
                        name="name"
                        placeholder="Username"
                        value={newUser.username}
                        onChange={handleChange}
                        className="px-3 py-3 placeholder-grey text-grey bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full"
                        required
                    />
                </div>
                <div className="relative w-full mb-3">
                    <input
                        type="text"
                        name="email"
                        placeholder="Email"
                        value={newUser.email}
                        onChange={handleChange}
                        className="px-3 py-3 placeholder-grey text-grey bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full"
                        required
                    />
                </div>
                <div className="text-center mt-6">
                    <input
                        type="submit"
                        value="Save"
                        className="bg-pink text-white active:bg-blue text-sm font-bold uppercase px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 w-full"
                    />
                </div>
            </form>
        </div>
    );
}
