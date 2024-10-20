import { useCommande } from "./CommandeContext";
import { useClient } from "../client/ClientContext";
import { useNavigate } from "react-router-dom";
import { creerCommande } from "../../services/CommandeService";



export default function(){
    const [stateClient,]=useClient();
    const [state,dispach]=useCommande();
    const navigate=useNavigate();

    const addCommande=(event)=>{
        event.preventDefault();
        const newCommande={numeroCommande:state.numeroCommande + Date.now(),clientId:state.clientId};
        creerCommande(newCommande).then((response)=>{
            dispach({type:"submit",payload:{newCommande:response.data}});
            resetForm();
        }).catch(err=>{
            if(err.response.status===401){
                navigate("/login")
            }
        });  
    }
    
    const resetForm=()=>{
        dispach({type:"resetFormCommande"});
    }
    return (
        <div className="card">
            <div className="card-header">
                <h2>Creer Commande</h2>
            </div>
            <div className="card-body">
                <form onSubmit={addCommande} onReset={resetForm}>
                <div className="row mt-3 mb-3 ms-1 me-1">
                    <label className="form-label">Numero Commande</label>
                    <input type="text" className="form-control"
                     onChange={(e)=>{
                        dispach({type:"changeNumeroCommande",payload:{value:e.target.value}})
                     }} 
                     value={state.numeroCommande} />
                </div>
                <div className="row mt-3 mb-3 ms-1 me-1"> 
                    <label className="form-label">Client</label>
                    <select className="form-select form-select-lg" aria-label="Large select example" onChange={(e)=>{
                        dispach({type:"selectClient",payload:{clientId:parseInt(e.target.value)}})
                    }} 
                        value={state.clientId}>
                        {stateClient.clients.map((client)=>{
                            return <option value={client.id} key={client.id}>{client.nom} {client.prenom}</option>
                        })}
                        
                    </select>
                </div>
                <button type="submit" className="btn btn-outline-success me-1 ms-1">Ajouter</button>
                <button type="reset" className="btn btn-outline-danger ms-1">Annuler</button>
                </form>
            </div>
        </div>
    );
}