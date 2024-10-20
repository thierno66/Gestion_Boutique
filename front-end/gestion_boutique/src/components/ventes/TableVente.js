import {  useCommande,  } from "./CommandeContext";
import {  useProduit } from "../ProduitContext";


function TableVente(){
    const [state,]=useCommande();
    const [produitState,]=useProduit();
    let total=0;
    
    return(
        <table className="table table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Commande</th>
                    <th>Produit</th>
                    <th>Quantite</th>
                    <th>Prix Unitaire</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                {state.venteProduit.map((liveVente)=>{
                    
                    const actualCommande=state.commandes.find((commande)=>{
                        console.log("commande.id: "+commande.id+" liveVente.commande: "+liveVente["commande"]);
                        return commande.id===liveVente.commandeId});
                    
                    
                    const actualProduit=produitState.produits.find((p)=>p.id===liveVente.produitId);
                    const cles = Object.keys(actualProduit);
                    console.log("cle "+cles);
                    const value=Object.values(actualProduit);
                    console.log("value "+value);
                    total=total+(actualProduit.prixVente*liveVente.quantite)
                    return <tr key={liveVente.id}>
                        <td>{liveVente.id}</td>
                        <td>{actualCommande.numeroCommande}</td>
                        <td>{actualProduit.nom}</td>
                        <td>{liveVente.quantite}</td>
                        <td>{actualProduit.prixVente}</td>
                        <td>{actualProduit.prixVente*liveVente.quantite}</td>
                    </tr>
                })}
            </tbody>
            <tfoot>
                <tr>
                    <td>Total</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>{total}</td>
                </tr>
            </tfoot>
        </table>
    );
}

export default TableVente;