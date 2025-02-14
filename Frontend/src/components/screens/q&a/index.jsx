import React from 'react';
import {signIn} from "../../../service/AuthService";

export default function Queries(props) {
    const handleSubmit = (e) => {
        console.log(e);
    };
    return (
        <div className='text-white w-4/5 mx-auto'>
            <h1 className='text-2xl font-bold mb-10'>Q&A</h1>
            <h1 className='text-2xl font-bold m-7 mb-8'>Often asked questions:</h1>
            <h2 className='text-2xl  ml-20 '>Q: Will I get my money back?</h2>
            <h2 className='text-2xl  ml-20 '>A: No.</h2>
            <form onSubmit={handleSubmit}>
                <div className=' mb-4 rounded-2xl border-zinc-800 text-zinc-800 bg-zinc-50 border-2 px-5 py-3 w-full h-72 mt-20 space-x-4'>
                    <input
                        type='text'
                        // ref={}
                        className='bg-transparent w-full border-none outline-none'
                        placeholder='Contact us'
                    />
                </div>
                <div className="flexitems-end text-center mt-6">
                    <input
                        type="submit"
                        value="Submit"
                        className="bg-pink text-white active:bg-blue text-sm font-bold uppercase px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1"
                    />
                </div>
            </form>
        </div>

    );
}