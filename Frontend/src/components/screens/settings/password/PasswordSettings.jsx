import React from 'react';
import PropTypes from 'prop-types';


export default function PasswordSettings(){
    const handleSubmit = (e) => {
        console.log(e);
    };
    return (
        <div className="form-container w-full">
            <form onSubmit={handleSubmit}>
                <div className="relative w-full mb-3">
                    <input
                        type="password"
                        name="password"
                        placeholder="Password"
                        className="px-3 py-3 placeholder-grey text-grey bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full"
                        required
                    />
                </div>
                <div className="relative w-full mb-3">
                    <input
                        type="password"
                        name="password2"
                        placeholder="Confirm password"
                        className="px-3 py-3 placeholder-grey text-grey bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full"
                        required
                    />
                </div>
                <div className="text-center mt-6">
                    <input
                        type="submit"
                        value="Change password"
                        className="bg-pink text-white active:bg-blue text-sm font-bold uppercase px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 w-full"
                    />
                </div>
            </form>
        </div>
    );
}
