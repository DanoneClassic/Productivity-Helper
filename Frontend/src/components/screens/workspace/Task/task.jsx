import React, {useEffect, useState} from 'react'
import CreateTodoField from './create-todo-field/CreateTodoField'
import TodoItem from './item/TodoItem'
import {useParams} from "react-router-dom";
import {useStateContext} from "../../../../contexts/ContextProvider";
import {
	getTasksByWorkspaceId,
	removeTaskFromWorkspace,
	updateTaskName,
	updateTaskStatus
} from "../../../../service/TaskService";
import {getWorkspaceById} from "../../../../service/WorkspaceService";

export default function Task () {
	const { workspaceID } = useParams();
	const { token } = useStateContext();
	const [loading, setLoading] = useState(true);
	const [newTitleFlag, setNewTitleFlag] = useState(false);
	const [todos, setTodos] = useState([]);
	const [newTitle, setNewTitle] = useState('');

	useEffect(() => {
		getTasksByWorkspaceId(workspaceID, token)
			.then((result) => {
				setTodos(result.data);
				setLoading(false);
			})
			.catch(e => {
				console.log(e);
				setLoading(false);
			});
	}, [workspaceID, token]);

	if (!newTitleFlag) {
		getWorkspaceById(workspaceID, token).then(result => {
			setNewTitle(result.data.name)
			setNewTitleFlag(true)
		})
	}

	return (
		<div className='text-white w-4/5 mx-auto'>
			<h1 className='text-2xl font-bold mb-10'>{newTitle}</h1>
			{todos.length > 0 && !loading ?
				(todos.map(todo => (
					<TodoItem
					key={todo.id}
					todo={todo}
					changeTodo={updateTaskStatus}
					removeTodo={removeTaskFromWorkspace}
					changeTodoTitle={updateTaskName}
					workspaceID={parseInt(workspaceID)}
					workspaceTitle={newTitle}
				/>)
			)) : 'You don\'t have any tasks!'}
			<CreateTodoField
				setTodos={setTodos}
				workspace={parseInt(workspaceID)}
			/>
		</div>
	)
}
