import { useCommande,  } from "./CommandeContext";
import { useProduit } from "../ProduitContext";
import { vendreProduit } from "../../services/CommandeService";

export default function NewVente(){
    const [state,dispach]=useCommande();
    const [produitState,]=useProduit();

    const addVente=(event)=>{
        event.preventDefault();
        const newVente={commandeId:state.commandeId,produitId:state.produitId,quantite:state.quantite};
            if(state.venteProduit.length>0 && state.venteProduit[0].commandeId!==state.commandeId  ){
                alert("Ce n'est pas la commande en cours ")
            }else{
                dispach({type:"liveVente",payload:{newVente:newVente}});
                dispach({type:"selectCommande",payload:{value:state.commandeId}});
                dispach({type:"selectProduit",payload:{value:""}});
                dispach({type:"changeQuantite",payload:{value:0}});
            }    
    }
    const resetForm=()=>{
        dispach({type:"resetFormVente"})
    }
    const valider=()=>{
        const ventes= state.venteProduit.map((vente=>{
            const data={produitId:vente.produitId,quantite:vente.quantite};
            return data;
        }))
        console.log(ventes);
        
        vendreProduit(state.venteProduit[0].commandeId,ventes).then((response)=>dispach({type:"addVente",}))
            .catch((error)=>console.log(error.response.data)
        )
    }
    return(
        <div className="card">
            <div className="card-header">
                <h2>Creer Vente</h2>
            </div>
            <div className="card-body">
                <form onSubmit={addVente} onReset={resetForm}> 
                <div className="row mt-3 mb-3 ms-1 me-1"> 
                    <label className="form-label">Commande</label>
                    <select className="form-select form-select-lg" aria-label="Large select example" 
                        onChange={(e)=>{
                            //setCommandeId(e.target.value);  
                            dispach({type:"selectCommande",payload:{value:parseInt(e.target.value)}});
                            }} value={state.commandeId}>
                            <option key={0} value={""}>choisir commandes</option>
                        {state.commandes.map((commande)=>{
                            return <option key={commande.id} value={commande.id}>{commande.numeroCommande}</option>
                        })}
                    </select>
                </div>
                <div className="row mt-3 mb-3 ms-1 me-1"> 
                    <label className="form-label">Produit</label>
                    <select className="form-select form-select-lg" aria-label="Large select example"
                            onChange={(e)=>{
                                dispach({type:"selectProduit",payload:{value:parseInt(e.target.value)}});
                                }} value={state.produitId}>
                            <option key={0} value={""}>choisir produit</option>
                        {produitState.produits.map((produit)=>{
                            return <option key={produit.id} value={produit.id}>{produit.nom}</option>
                        })}
                        
                    </select>
                </div>
                <div className="row mt-3 mb-3 ms-1 me-1">
                    <label className="form-label">Quantite</label>
                    <input type="number" className="form-control" onChange={(e)=>dispach({type:"changeQuantite",payload:{value:parseInt(e.target.value)}})} value={state.quantite} />
                </div>
                <button type="submit" className="btn btn-outline-success me-1 ms-1">Ajouter</button>
                <button type="reset" className="btn btn-outline-danger ms-1">Annuler</button>
                </form>
            </div>
            <button className="btn btn-outline-success m-1" onClick={valider}>Valider</button>
        </div>
    );
}
