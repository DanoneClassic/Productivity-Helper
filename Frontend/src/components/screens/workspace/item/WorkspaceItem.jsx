import React, { useState } from 'react';
import { BsTrash } from 'react-icons/bs';
import { BsPencilSquare } from 'react-icons/bs';
import cn from 'classnames';
import {Link} from "react-router-dom";
import {deleteWorkspace, updateWorkspace} from "../../../../service/WorkspaceService";
import {useStateContext} from "../../../../contexts/ContextProvider";

const WorkspaceItem = ({ workspace, removeWorkspace, changeWorkspaceTitle }) => {
    const [isEditing, setIsEditing] = useState(false);
    const [newTitle, setNewTitle] = useState(workspace.title || '');
    const { token, user } = useStateContext();

    const handleTitleChange = (e) => {
        setNewTitle(e.target.value);
    };

    const toggleEdit = () => {
        setIsEditing(!isEditing);
    };

    const handleKeyPress = (e) => {
        if (e.key === 'Enter') {
            const newWorkspace = {
                id: workspace.id,
                name: newTitle,
                tasks: workspace.tasks,
                ownerId: workspace.ownerId,
            };
            updateWorkspace(newWorkspace, token).then(() => {
                document.location.reload();});
            setIsEditing(false);
        }
    };

    return (
        <div className='flex items-center justify-between mb-4 rounded-2xl bg-zinc-800 p-5 w-full'>
            <Link to={`/task/${workspace.id}`} className='flex items-center flex-grow'>
                {isEditing ? (
                    <input
                        type="text"
                        value={newTitle}
                        onChange={handleTitleChange}
                        onKeyPress={handleKeyPress}
                        className="ml-2 bg-zinc-800 text-white outline-none border-none flex-grow"
                        autoFocus
                    />
                ) : (
                    <span
                        className={cn('ml-2', 'text-white')}
                    >
                        {workspace.name}
                    </span>
                )}
            </Link>
            <button onClick={toggleEdit}>
                <BsPencilSquare
                    size={22}
                    className='text-gray-600 hover:text-red-700 transition-colors ease-in-out duration-300'
                />
            </button>
            <button onClick={() => {deleteWorkspace(workspace.id, token).then(() => {document.location.reload();})}}>
                <BsTrash
                    size={22}
                    className='text-gray-600 hover:text-red-700 transition-colors ease-in-out duration-300'
                />
            </button>
        </div>
    );
};

export default WorkspaceItem;