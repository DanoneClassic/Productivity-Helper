import React, {useEffect, useState} from 'react';
import WorkspaceItem from "./item/WorkspaceItem";
import CreateWorkspaceField from "./create-workspace-field/CreateWorkspaceField";
import {deleteWorkspace, getAllWorkspaces, updateWorkspace} from "../../../service/WorkspaceService";
import {useStateContext} from "../../../contexts/ContextProvider";

export default function Workspace(){
    const { token } = useStateContext();
    const [workspaces, setWorkspaces] = useState([])
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getAllWorkspaces(token)
            .then((result) => {
                setWorkspaces(result.data);
                setLoading(false);
            })
            .catch(e => {
                console.log(e);
                setLoading(false);
            });
    }, [token]);

    return (
        <div className='text-white w-4/5 mx-auto'>
            <h1 className='text-2xl font-bold mb-10'>Workspaces</h1>
            {workspaces.length > 0 && !loading ? (
                workspaces.map(workspace =>(
                    <WorkspaceItem
                        key={workspace.id}
                        workspace={workspace}
                        deleteWorkspace={deleteWorkspace}
                        updateWorkspace={updateWorkspace}
                    />
                    )
                )) : 'You don\'t have any workspaces!'
            }
            <CreateWorkspaceField setWorkspaces={setWorkspaces} />
        </div>
    );
}
