import React, { useState } from 'react';
import {useStateContext} from "../../../../contexts/ContextProvider";
import {createWorkspace} from "../../../../service/WorkspaceService";

const CreateWorkspaceField = ({ setWorkspaces }) => {
    const [title, setTitle] = useState('');
    const { token, user } = useStateContext();

    const newUser = JSON.parse(user);

    const addItem = (title) => {
        const newWorkspace = {
            name: title,
            tasks: [],
            ownerId: newUser.id,
        };
        createWorkspace(newWorkspace, token).then(() => {
            document.location.reload();});
    };

    return (
        <div className='flex items-center justify-between mb-4 rounded-2xl border-zinc-800 text-zinc-800 bg-zinc-50 border-2 px-5 py-3 w-full mt-20 space-x-4'>
            <input
                type='text'
                onChange={e => setTitle(e.target.value)}
                value={title}
                onKeyPress={e => e.key === 'Enter' && addItem(title)}
                className='bg-transparent w-full border-none outline-none'
                placeholder='Add a workspace item'
            />
        </div>
    );
}

export default CreateWorkspaceField;