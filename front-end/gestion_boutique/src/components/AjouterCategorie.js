import { useProduit } from "./ProduitContext";
import {ajouterCategorie} from "../services/produitService"
import { useNavigate } from "react-router-dom";

export default function AjouterCategorie() {
    const [state,dispatch] = useProduit();
    const navigate=useNavigate();
    const addCategorie = (e) => {
        e.preventDefault();
        const newCategorie = { nom:state.nomCategorie,description:state.descriptionCategorie };
        ajouterCategorie(newCategorie).then((response)=>{
            if(response.status==200){
                dispatch({type:"addCategorie",payload:{categorie:response.data}});
            }
        }).catch(err=>{
            if (err.response.status==401){
                navigate("/login")
            }
            console.log(err.response.data)
        })
        resetForm();
    }
    const resetForm=()=>{
        dispatch({type:"resetFormCategorie"});
    }
    return (
        <form onSubmit={addCategorie} onReset={resetForm}>
            <div className="mb-3">
                <label className="label-form">Nom</label>
                <input 
                    type="text" 
                    className="form-control" 
                    onChange={(e) => dispatch({type:"changeNomCategorie",payload:{value:e.target.value}})} 
                    value={state.nomCategorie} 
                />
            </div>
            <div className="mb-3">
                <label className="label-form">Description</label>
                <input 
                    type="text" 
                    className="form-control" 
                    onChange={(e) => dispatch({type:"changeDescriptionCategorie",payload:{value:e.target.value}})} 
                    value={state.descriptionCategorie} 
                />
            </div>
            <button type="submit" className="btn btn-outline-success m-1">Ajouter</button>
            <button type="reset" className="btn btn-outline-danger" onClick={resetForm}>Annuler</button>
        </form>
    );
}