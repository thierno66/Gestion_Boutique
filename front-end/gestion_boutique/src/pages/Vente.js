import React, { useEffect } from "react";
import NewCommande from "../components/ventes/NewCommande";
import NewVente from "../components/ventes/NewVente";
import TableVente from "../components/ventes/TableVente";
import { useCommande } from "../components/ventes/CommandeContext";
import { useNavigate } from "react-router-dom";
import {  getAllProduits } from "../services/produitService";
import { useProduit } from "../components/ProduitContext";
import { useClient } from "../components/client/ClientContext";
import { getAllClients } from "../services/ClientService";
import { getAllCommandeByUser } from "../services/CommandeService";

export default function Vente(){
    const [,dispach]=useCommande();
    const [,dispatch]=useProduit();
    const [,dispatchClient]=useClient();
    const navigate=useNavigate();
    useEffect(()=>{
        getAllCommandeByUser().then(response=>{
            console.log(response.data);
            
            dispach({type:"intitCommandes",payload:{commandes:response.data}});
        }).catch(err=>{
            if(err.response.status===401){
                navigate("/login")
            }
        })

        getAllProduits().then((response)=>{
            if(response.status===200)
              dispatch({type:"initProduits",payload:{produits:response.data}})
          }).catch((err)=>{
            if(err.response.status===401){
              navigate("/login")
            }
          });
          getAllClients().then((response)=>{
            dispatchClient({type:"initClients",payload:{clients:response.data}})
          }).catch(err=>{
            if(err.response.status===401){
                navigate("/login")
            }
          })
    },[dispach,navigate,dispatchClient,dispatch])
    return (
        <div className="container-fluid">
            <div className="row" >
                <div className="col-6 ">
                    <NewVente />
                </div>
                <div className="col-6 ">
                    <NewCommande />
                </div>
                <div className="mt-3">
                    <TableVente />
                </div>
            </div>
        </div>
    );
}