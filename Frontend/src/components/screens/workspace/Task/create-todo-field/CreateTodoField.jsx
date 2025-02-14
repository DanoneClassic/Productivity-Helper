import React, { useState } from 'react'
import {createTask} from "../../../../../service/TaskService";
import {useStateContext} from "../../../../../contexts/ContextProvider";

const CreateTodoField = ({ setTodos, workspace }) => {
	const [title, setTitle] = useState('')
	const { token } = useStateContext();

	const addTodo = (title) => {
		const newTodo = {
			name: title,
			data: new Date().toISOString(),
			state: 'NEW',
		};
		createTask(newTodo, workspace, token).then(() => {
			document.location.reload();})
	}

	return (
		<div className='flex items-center justify-between mb-4 rounded-2xl border-zinc-800 text-zinc-800 bg-zinc-50 border-2 px-5 py-3 w-full mt-20 space-x-4'>
			<input
				type='text'
				onChange={e => setTitle(e.target.value)}
				value={title}
				onKeyPress={e => e.key === 'Enter' && addTodo(title)}
				className='bg-transparent w-full border-none outline-none'
				placeholder='Add a task'
			/>
		</div>
	)
}

export default CreateTodoField
