import { useContext, useEffect, useMemo,  } from "react";
import { ContextUtils } from "../../App";
import { useClient } from "./ClientContext";
import { creerClient, modifierClient, supprimerClient } from "../../services/ClientService";
import { useNavigate } from "react-router-dom";


export default function NewClient(){
    const [state,dispatch]=useClient();
    const utils=useContext(ContextUtils);
    const [isUpdate,setIsUpdate]=utils.isUpdate;
    const [id,setId]=utils.id;
    const navigate=useNavigate();
    const buttonValue=useMemo(()=>{
        return isUpdate?"Modifier":"Ajouter";
    },[isUpdate]);
    useEffect(()=>{
        const foundedClient=state.clients.find((client)=>client.id===id)
        if(foundedClient && isUpdate){
            dispatch({type:"changeNomClient",payload:{value:foundedClient.nom}})
            dispatch({type:"changePrenomClient",payload:{value:foundedClient.prenom}})
            dispatch({type:"changeEmail",payload:{value:foundedClient.email}})
            dispatch({type:"changeTelephone",payload:{value:foundedClient.telephone}})
            dispatch({type:"selectAdresse",payload:{value:foundedClient.adresse.id}})
        }
        else if(!isUpdate && id!==null){
            supprimerClient(id).then(()=>{
                alert("Voulez supprimer ce client")
                dispatch({type:"deleteClient",payload:{id:id}})
                setId(null)
            }).catch((err)=>{
                if(err.response.status===401){
                    navigate("/login")
                }
            })
        }
    },[id,dispatch,setId,isUpdate,state.clients,navigate]);
    const addClient=(event)=>{
        event.preventDefault();
        const client={
            nom:state.nomClient,
            prenom:state.prenomClient,
            adresseId:state.adresseId,
            email:state.email,
            telephone:state.telephone,
        }
        creerClient(client).then((response)=>{
            if(response.status===200){

                dispatch({type:"addClient",payload:{client:response.data}})
                handleOnResset()
            }
        }).catch(err=>{
            if(err.response.status===401){
                navigate("/login");
            }
        })
;
    }
    const handleOnSubmit=(event)=>{
        isUpdate?updateClient(event):addClient(event);
    }
    const updateClient=(event)=>{
        event.preventDefault();
        const client={
            nom:state.nomClient,
            prenom:state.prenomClient,
            adresseId:state.adresseId,
            email:state.email,
            telephone:state.telephone,
        }
        modifierClient(id,client).then(response=>{
            const clientModifier=response.data;
            const updatedClients=state.clients.map((client)=>{
                if(client.id===id){
                    return {
                        ...client,
                        nom:clientModifier.nom,
                        prenom:clientModifier.prenom,
                        telephone:clientModifier.telephone,
                        email:clientModifier.email,
                        adresse:clientModifier.adresse
                    }
                }
                return client;
            });
            dispatch({type:"updateClient",payload:{updatedClients:updatedClients}})
            setIsUpdate(false);
            setId(null);
            handleOnResset()
        })
    }

    const handleOnResset=()=>{
        dispatch({type:"resetFormClient"})
    }
    return(
        <div className="card">
            <div className="card-header">
                <h2>Creer Client</h2>
            </div>
            <div className="card-body">
                <form onSubmit={handleOnSubmit}> 
                <div className="row mb-2" >
                    <div className="col-6">
                        <label className="form-label">Nom</label>
                        <input type="text" className="form-control" 
                        onChange={(e)=>dispatch({type:"changeNomClient",payload:{value:e.target.value}})} 
                        value={state.nomClient}  />
                    </div>
                    <div className="col-6">
                        <label className="form-label">Prenom</label>
                        <input className="form-control" 
                        onChange={(e)=>dispatch({type:"changePrenomClient",payload:{value:e.target.value}})}  
                        value={state.prenomClient}  />
                    </div>
                </div>
                <div className="row mb-2" >
                    <div className="col-6">
                        <label className="form-label">Telephone</label>
                        <input type="text" className="form-control" 
                        onChange={(e)=>dispatch({type:"changeTelephone",payload:{value:e.target.value}})} 
                        value={state.telephone}  />
                    </div>
                    <div className="col-6">
                        <label className="form-label">Email</label>
                        <input className="form-control" 
                        onChange={(e)=>dispatch({type:"changeEmail",payload:{value:e.target.value}})} 
                        value={state.email}  />
                    </div>
                </div>
                <div className="row mb-2 mt-2">
                        <label className="form-label">Adresse</label>
                        <select className="form-select form-select-lg" aria-label="Large select example" 
                        onChange={(e)=>dispatch({type:"selectAdresse",payload:{value:parseInt(e.target.value)}})} 
                        value={state.adresseId} >
                            <option value="">------selectioner l'adresse------</option> 
                            {state.adresses.map((adresse)=>{
                                return <option key={adresse.id} value={adresse.id}>{adresse.nom} {adresse.description}</option>
                            })}
                            
                        </select>
                </div>
                <button className="btn btn-outline-success m-1">{buttonValue}</button>
                <button className="btn btn-outline-danger " onClick={handleOnResset}>Annuler</button>
                </form>
            </div>
        </div>
    );
}