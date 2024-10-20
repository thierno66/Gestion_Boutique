import React, { useContext, useEffect, useState } from "react";
import { MdDelete } from "react-icons/md";
import { Link, useNavigate } from "react-router-dom";
import {  useCommande, } from "../components/ventes/CommandeContext";
import { getAllCommande, getAllCommandeByUser } from "../services/CommandeService";
import { getAllUser, isVendeur } from "../services/authService";
import { FaSearch } from "react-icons/fa";
import { UserContext, useUser } from "../components/user/UserContext";

export default function Commande(){
    const [state,dispach]=useCommande();
    const [user,dispatchUser]=useContext(UserContext);
    const [select,setSelect]=useState();
    const [filteredCommande,setfilteredCommande]=useState([]);
    const [date, setDate] = useState(null);
    //const date=new Date();
    const navigate=useNavigate();
    useEffect(()=>{
        console.log(date);
        
        if(isVendeur()){
            getAllCommandeByUser().then((response)=>{
                dispach({type:"intitCommandes",payload:{commandes:response.data}});
            }).catch(err=>{
                if(err.response.status===401){
                    navigate("/login")
                }
            })
        }else{
            getAllCommande().then((response)=>{
                dispach({type:"intitCommandes",payload:{commandes:response.data}});
                setfilteredCommande(response.data)
                console.log(new Date(response.data[0].jour));
            }).catch(err=>{
                if(err.response.status===401){
                    navigate("/login")
                }
            });
        }
    },[dispach,navigate]);
    useEffect(()=>{
        getAllUser().then((data=>{
            console.log(data);
            dispatchUser({type:"initialiserUser",payload:{users:data}})
            
        }));
    },[])
    const handleOnSearch=()=>{
        if(!select){
            setfilteredCommande(state.commandes);
        }else{
            const data=state.commandes.filter((commande)=>commande.user.email===select);
            setfilteredCommande(data);
        }
    }
    if(!isVendeur() && user)
        return (
        <div className="container-fluid">
            <div className="card">
                <div className="card-body">
                    <div className="row ">
                        <div className="col-9">
                            {/* <label className="form-label">Commande par utilisateur</label> */}
                            <select className="form-select" onChange={(e)=>setSelect(e.target.value)} >
                                <option value={""}>selectionner l'utilisateur</option>
                                {user.users.map((u)=><option key={u.id} value={u.email}>{u.email}</option>)}
                            </select>
                        </div>
                        <div className="col-3">
                            <button className="btn btn-outline-info" onClick={(e)=>handleOnSearch()}><FaSearch /></button>
                        </div>
                    </div>
                </div>
            </div>
            
            <div className="card mt-2">
            <div className="card-header ">
                <h4>List Commande</h4>
            </div>
            <div className="card-body">
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <td>ID</td>
                            <td>Numero Commande</td>
                            <td>Jour</td>
                            <td>Client</td>
                            <td>Adresse</td>
                            <td>Action</td>
                        </tr>
                    </thead>
                    <tbody>
                        {filteredCommande.map((commande)=>{
                             const date = new Date(commande.jour);
                             const formattedDate = date.toLocaleDateString(); // Format de date (ex: "9/7/2024")
                             const formattedTime = date.toLocaleTimeString();
                           return <tr key={commande.id}>
                                <td>{commande.id}</td>
                                <td>{commande.numeroCommande}</td>
                                <td>{formattedDate} {formattedTime}</td>
                                <td>{commande.client.prenom} {commande.client.nom}</td>
                                <td>{commande.client.adresse.nom} {commande.client.adresse.description}</td>
                                <td>
                                    <Link to={`/accueil/commandes/${commande.id}`}  className="btn btn-outline-info me-2">Info</Link>
                                    <button className="btn btn-outline-danger"><MdDelete /></button>
                                </td>
                            </tr>
                        })}
                    </tbody>
                </table>
            </div>
            </div>
        </div>
    );
    return (
        <div className="container-fluid">
            <div className="card ">
            <div className="card-header ">
                <h4>List Commande</h4>
            </div>
            <div className="card-body">
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <td>ID</td>
                            <td>Numero Commande</td>
                            <td>Jour</td>
                            <td>Client</td>
                            <td>Adresse</td>
                            <td>Action</td>
                        </tr>
                    </thead>
                    <tbody>
                        {state.commandes.map((commande)=>{

                           return <tr key={commande.id}>
                                <td>{commande.id}</td>
                                <td>{commande.numeroCommande}</td>
                                <td>{commande.jour}</td>
                                <td>{commande.client.prenom} {commande.client.nom}</td>
                                <td>{commande.client.adresse.nom} {commande.client.adresse.description}</td>
                                <td>
                                    <Link to={`/accueil/commandes/${commande.id}`}  className="btn btn-outline-info me-2">Info</Link>
                                    <button className="btn btn-outline-danger"><MdDelete /></button>
                                </td>
                            </tr>
                        })}
                    </tbody>
                </table>
            </div>
            </div>
        </div>
    );
}