import { useNavigate } from "react-router-dom";
import { creerAdresse } from "../../services/ClientService";
import {  useClient } from "./ClientContext";


export const NewAdresse=()=>{
    const [state,dispatch]=useClient();
    const navigate=useNavigate();
    const ajouterAdresse=(event)=>{
        event.preventDefault();
        const newAdresse={
            nom:state.nomAdresse,
            description:state.descriptionAdresse
        }
        creerAdresse(newAdresse).then((response=>{
            if(response.status===200){
                dispatch({type:"addAdresse",payload:{adresse:response.data}});
                resetForm();
            }
        })).catch(err=>{
            if(err.response.status===401){
                navigate("/login")
            }
        });
    }
    const resetForm=()=>{
        dispatch({type:"resetFormAdresse"});
    }
    return (
        <div className="card">
            <div className="card-header">
                <h2>Creer Adresse</h2>
            </div>
            <form onSubmit={ajouterAdresse}>
            <div className="card-body">
                <div className="row mb-2" >
                    <label className="form-label" >Nom</label>
                    <input className="form-control" onChange={(e)=>dispatch({type:"changeNomAdresse",payload:{value:e.target.value}})} value={state.nomAdresse} />
                </div>
                <div className="row mb-2">
                    <label className="form-label">Description</label>
                    <input className="form-control" onChange={(e)=>dispatch({type:"changeDescriptionAdresse",payload:{value:e.target.value}})} value={state.descriptionAdresse}  />
                </div>
                <button className="btn btn-outline-success m-1" type="submit" >Ajouter</button>
                <button className="btn btn-outline-danger" type="reset" onClick={resetForm}>Annuler</button>
            </div>
            </form>
        </div>

    );
}