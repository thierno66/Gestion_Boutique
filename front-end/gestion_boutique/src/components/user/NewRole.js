import { useContext, } from "react";
import { UserContext } from "./UserContext";

export default function NewRole(){
    const [state,dispatch]=useContext(UserContext);
    const addRoles=(event)=>{
        event.preventDefault();
        const roleWithGreatherId=state.roles.reduce((previousValue,curentValue)=>{
            return curentValue.id>previousValue.id?curentValue:previousValue;
        },state.roles[0]);
        const NewRole={id:roleWithGreatherId.id+1,nom:state.nomRole};
        dispatch({type:"addRole",payload:{role:NewRole}})
        resetForm();
    }
    const resetForm=()=>dispatch({type:"resetFormRole"});
    return (
        <div className="card">
            <div className="card-header">
                <h2>Creer Role</h2>
            </div>
            <div className="card-body">
                <form onSubmit={addRoles} onReset={resetForm}>
                    <div className="row m-3">
                        <label className="form-label">Nom</label>
                        <input type="text" className="form-control" 
                            onChange={(event)=>dispatch({type:"changeNomRole",payload:{value:event.target.value}})} 
                            value={state.nomRole} />
                    </div>
                    <button className="btn btn-outline-success m-1" type="submit">Ajouter</button>
                    <button className="btn btn-outline-danger" type="reset" >Annuler</button>
                </form>
            </div>
        </div>
    );
}