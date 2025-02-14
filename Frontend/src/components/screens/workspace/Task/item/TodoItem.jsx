import React, {useState} from 'react'
import cn from 'classnames'
import {BsPencilSquare, BsTrash} from 'react-icons/bs'

import Check from './Check'
import {useStateContext} from "../../../../../contexts/ContextProvider";

const TodoItem = ({ todo, changeTodo, changeTodoTitle, removeTodo, workspaceID }) => {
	const [isEditing, setIsEditing] = useState(false);
	const [newTitle, setNewTitle] = useState(todo.title || '');
	const { token } = useStateContext();

	const handleTitleChange = (e) => {
		setNewTitle(e.target.value);
	};

	const toggleEdit = () => {
		setIsEditing(!isEditing);
		console.log(todo)
	};

	const handleKeyPress = (e) => {
		if (e.key === 'Enter') {
			changeTodoTitle(todo.id, newTitle, token);
			setIsEditing(false);
			document.location.reload();
		}
	};
	return (
		<div className='flex items-center justify-between mb-4 rounded-2xl bg-zinc-800 p-5 w-full'>
			<button
				className='flex items-center flex-grow'
				onClick={() => changeTodo(todo.id, 'COMPLETED', token)}
			>
				<Check isCompleted={todo.state === 'COMPLETED'} />
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
						className={cn('ml-2', {
							'line-through': todo.state === 'COMPLETED',
						})}
					>
                        {todo.name}
                    </span>
				)}
			</button>
			<button onClick={toggleEdit}>
				<BsPencilSquare
					size={22}
					className='text-gray-600 hover:text-red-700 transition-colors ease-in-out duration-300'
				/>
			</button>
			<button onClick={() => {removeTodo(workspaceID, todo.id, token); document.location.reload();}}>
				<BsTrash
					size={22}
					className='text-gray-600 hover:text-red-700 transition-colors ease-in-out duration-300'
				/>
			</button>
		</div>
	)
}

export default TodoItem
