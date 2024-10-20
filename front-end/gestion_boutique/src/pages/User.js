import { useContext, useEffect,  } from "react";
import NewRole from "../components/user/NewRole";
import NewUser from "../components/user/NewUser";
import Table from "../components/Table";
import { UserContext } from "../components/user/UserContext";
import { getAllRoles, getAllUser } from "../services/authService";


export default function User(){
    const [state,dispatch]=useContext(UserContext);
    const columns=["ID","Nom","Prenom","Email","Telephone","Roles","Action"];
    const champs=["id","nom","prenom","email","telephone","roles"]
    useEffect(()=>{
        getAllUser().then((data=>{
            dispatch({type:"initialiserUser",payload:{users:data}})
        }));
        getAllRoles().then((roles)=>{
            dispatch({type:"initializeRole",payload:{roles:roles}});
            
        })
    },[])
    return (
        <div className="container-fluid">
            <div className="row ">
                <div className="col-6 ">
                    <NewUser />
                </div>
                <div className="col-6">
                    <NewRole />
                </div> 
            </div>
            <div className="card mt-3" >
                <div className="card-body">
                    <Table champs={champs} columns={columns} donnees={state.users} donnees1={state.roles} />
                </div>
                
            </div>
        </div>
    );
}