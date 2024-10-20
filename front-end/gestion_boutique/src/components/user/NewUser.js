import { useContext, useEffect, useMemo, useState } from "react";
import { ContextUtils } from "../../App";
import { UserContext } from "./UserContext";
import { createUser, deleteUser, updateUser } from "../../services/authService";

export default function NewUser(){
    const [state,dispatch]=useContext(UserContext);
    const utils=useContext(ContextUtils)
    const [id,setId]=utils.id;
    const [isUpdate,setIsUpdate]=utils.isUpdate;
    useEffect(()=>{
        if(id!=null && isUpdate){
            const userToUpdate=state.users.find((user)=>user.id===id)
            dispatch({type:"changeNom",payload:{value:userToUpdate.nom}});
            dispatch({type:"changePrenom",payload:{value:userToUpdate.prenom}});
            dispatch({type:"changeEmail",payload:{value:userToUpdate.email}});
            dispatch({type:"changePassword",payload:{value:userToUpdate.password}});
            dispatch({type:"changeRoleName",payload:{value:userToUpdate.roles}});
            dispatch({type:"changeTelephone",payload:{value:userToUpdate.telephone}});
        }else if(id!=null && !isUpdate ){
            alert("voulez vous supprimer cet utilisateur")
            deleteUser(id).then((response)=> dispatch({type:"deleteUser",payload:{id:id}}))
            setId(null);
        }
    },[id])
    const buttonValue=useMemo(()=>{
            return isUpdate?"Modifier":"Ajouter";
    },[id]);
    const getSelectedRoles=(event)=>{
        const selectedValues=Array.from(event.target.selectedOptions,(option)=>option.value);
        dispatch({type:"changeRoleName",payload:{value:selectedValues}})
    }

    const addUser=(event)=>{
        event.preventDefault();
        const newUser={
            nom:state.nom,prenom:state.prenom,
            email:state.email,telephone:state.telephone,
            roleName:state.roleName,password:state.password
        };
        createUser(newUser).then((user)=>{
            dispatch({type:"addUser",payload:{user:user}})
        }).catch(err=>console.log("Erreur lors de la creation de l'utilisateur "+err))
        resetForm();
    }
    const updatedUser=(event)=>{
        event.preventDefault();
        const user={
            nom:state.nom,
            prenom:state.prenom,
            telephone:state.telephone,
            email:state.email,
            password:state.password,
            roleNames:state.roleName
        }
        updateUser(id,user).then((user)=>{
            const updateUsers=state.users.map((u)=>{
                    if(u.id===id){
                        return{
                            ...u,
                            nom:user.nom,prenom:user.prenom,
                            telephone:user.telephone,email:user.email,
                            roles:user.roles
                        }
                    }
                    return u;
                })
            dispatch({type:"updateUser",payload:{updatedUser:updateUsers}})
            console.log("Utilisateur modifier avec success");
        })
        setId(null)
        setIsUpdate(false);
        resetForm();
    }
    const resetForm=()=>{
        dispatch({type:"resetFormUser"})
    }
    const handleOnSubmit=(event)=>{
        return isUpdate?updatedUser(event):addUser(event);
    }
    return(
        <div className="card">
            <div className="card-header">
                <h2>Creer Utilisateur</h2>
            </div>
            <div className="card-body">
                <form onSubmit={handleOnSubmit} onReset={resetForm} >
                    <div className="row m-3">
                        <div className="col-6">
                            <label className="form-label">Nom</label>
                            <input type="text"  className="form-control" 
                                onChange={(e)=>dispatch({type:"changeNom",payload:{value:e.target.value}})} 
                                value={state.nom} />
                        </div>
                        <div className="col-6 ">
                            <label className="form-label">Prenom</label>
                            <input type="text" className="form-control"
                                onChange={(e)=>dispatch({type:"changePrenom",payload:{value:e.target.value}})} 
                                value={state.prenom} />
                        </div>
                    </div>
                    <div className="row m-3">
                        <div className="col-6">
                            <label className="form-label">Email</label>
                            <input type="text" className="form-control" 
                                onChange={(e)=>dispatch({type:"changeEmail",payload:{value:e.target.value}})} 
                                value={state.email} />
                        </div>
                        <div className="col-6">
                            <label className="form-label">Password</label>
                            <input type="text" className="form-control"
                                onChange={(e)=>dispatch({type:"changePassword",payload:{value:e.target.value}})} 
                                value={state.password} />
                        </div>
                    </div>
                    <div className="row m-3">
                        <div className="col-6">
                            <label className="form-label">Telephone</label>
                            <input type="text" className="form-control" 
                                onChange={(e)=>dispatch({type:"changeTelephone",payload:{value:e.target.value}})} 
                                value={state.telephone} />
                        </div>
                        <div className="col-6">
                            <label className="form-label">Roles</label>
                            <select class="form-select" 
                                onChange={(e)=>getSelectedRoles(e)} multiple size={2}  value={state.roleName}  >
                                {state.roles.map((role)=>{
                                    return <option value={role.nom}>{role.nom}</option>
                                })}
                            </select>
                        </div>
                    </div>
                    
                    <button className="btn btn-outline-success m-1" type="submit">{buttonValue}</button>
                    <button className="btn btn-outline-danger" type="reset">Annuler</button>
                </form>
            </div>

        </div>
    );
}