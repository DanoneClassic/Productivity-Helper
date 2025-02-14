import React from 'react';
import Account from "./account/Account";
import PasswordSettings from "./password/PasswordSettings";

export default function Settings() {
    return (
        <div className='text-white w-4/5 mx-auto'>
            <h1 className='text-2xl font-bold mb-10'>Settings</h1>
            <div className="flex flex-col items-center justify-center mx-auto px-4 h-full">
                <Account />
                <div className="decorative-bar w-full h-1 bg-zinc-50 my-8"></div>
                <PasswordSettings />
            </div>
        </div>
    );
}
