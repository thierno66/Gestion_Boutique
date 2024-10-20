import React, { useContext,useEffect,useMemo,  } from "react";
import { ContextUtils } from "../App";
import { useProduit } from "./ProduitContext";
import {ajouteProduit, modifierProduit, supprimerProduit} from '../services/produitService';
import { useNavigate } from "react-router-dom";
import { isAuthenticated } from "../services/authService";
export default function AjouterProduit() {
    const utils=useContext(ContextUtils);
    const [state,dispatch]=useProduit();
    const [id,setId] = utils.id;
    const [isUpdate,setIsUpdate]=utils.isUpdate;
    // const [user,setUser]=useState({})
    const navigate=useNavigate();
    useEffect(()=>{
        console.log("bonjour");
        
        if(isUpdate && id !==null){
            const foundedProduit=state.produits.find((p)=>p.id===id);
            dispatch({type:"changeNomProduit",payload:{value:foundedProduit.nom}})
            dispatch({type:"changeDescriptionProduit",payload:{value:foundedProduit.description}})
            dispatch({type:"changePrixAchat",payload:{value:foundedProduit.prixAchat}})
            dispatch({type:"changePrixVente",payload:{value:foundedProduit.prixVente}})
            dispatch({type:"selectCategorie",payload:{value:foundedProduit.categorieIds}})
            dispatch({type:"changeQuantiteDisponible",payload:{value:foundedProduit.quantiteDisponible}})
        }
        else if(!isUpdate && id!==null ){
            alert("voulez vous vraiment supprimer ce produit");
            supprimerProduit(id).then(response=>{
                if(response.status===200){
                    dispatch({type:"deletteProduit",payload:{id:id}})
                    setId(null);
                }
            }).catch(err=>{
                if(err.response.status===401){
                    navigate("/login")
                }
        })
        }
    },[id, isUpdate, dispatch, setId, state.produits, navigate])
    const buttonState=useMemo(()=>{
        return isUpdate?"Modifier":"Ajouter";
    },[isUpdate])
    const addProduit = (event) => {
        console.log("Dans addProduit "+isAuthenticated());
        
        if(isAuthenticated()){
            navigate("/login")
        }
        event.preventDefault();
        const newProduit = {
            nom:state.nomProduit,
            quantiteDisponible: state.quantiteDisponible,
            prixAchat: state.prixAchat,
            prixVente: state.prixVente,
            description:state.descriptionProduit,
            categorieIds: state.categorieIds
        };
        console.log(newProduit);
        
        ajouteProduit(newProduit).then((response)=>{
            if(response.status===200){
                dispatch({type:"addProduit",payload:{produit:response.data}});
                resetForm();
            }
        }).catch(err=>{
            if(err.response.status===401)
                alert("Vous n'est pas autorise a realiser cette operation")
        })   
    };
    const updateProduit = (event) => {
      event.preventDefault();
      const updatedProduit = {
        nom:state.nomProduit,
        quantiteDisponible: state.quantiteDisponible,
        prixAchat: state.prixAchat,
        prixVente: state.prixVente,
        description:state.descriptionProduit,
        categorieIds: state.categorieIds
    };
      modifierProduit(id,updatedProduit).then((response)=>{
        if(response.status===200){
            const produit=response.data;
            const updatedProduits = state.produits.map((p) => {
                if (p.id === produit.id) {
                    return {
                        ...p,
                        nom:produit.nom,
                        quantiteDisponible: produit.quantiteDisponible,
                        description:produit.description,
                        prixAchat: produit.prixAchat,
                        prixVente: produit.prixVente,
                        categories:produit.categories
                    };
                }
                return p;
            });
            dispatch({type:"updateProduit",payload:{newProduits:updatedProduits}})
        }
      }).catch((err)=>{
        if(err.response.status===401){
            navigate("/login");
        }
      })
      resetForm();
      setIsUpdate(false);
      setId(null);  
    }
    const resetForm=()=>{
        setId(null)
        dispatch({type:"resetProduitForm"})
        if(isUpdate){
            setIsUpdate(false)
        }
    }
    const handleOnSubmit=(event)=>{
      isUpdate?updateProduit(event):addProduit(event);
    }
    const handleOnsSelect=(event)=>{
        const ids=Array.from(event.target.selectedOptions,(option)=>parseInt(option.value) )
        dispatch({type:"selectCategorie",payload:{value:ids}})
    }
    return (
        <div className="card-content">
            <form onSubmit={handleOnSubmit}>
                <div className="row">
                    <div className="mb-3 col-6">
                        <label className="form-label">Nom :</label>
                        <input
                            type="text"
                            className="form-control"
                            onChange={(e) => dispatch({type:"changeNomProduit",payload:{value:e.target.value}})}
                            value={state.nomProduit}
                        />
                    </div>
                    <div className="mb-3 col-6">
                        <label className="form-label">Quantité Disponible :</label>
                        <input
                            type="number"
                            className="form-control"
                            onChange={(e) => dispatch({type:"changeQuantiteDisponible",payload:{value:parseInt(e.target.value)}})}
                            value={state.quantiteDisponible}
                        />
                    </div>
                </div>
                <div className="row">
                    <div className="mb-3 col-6">
                        <label className="form-label">Prix Achat :</label>
                        <input
                            type="number"
                            className="form-control"
                            onChange={(e) => dispatch({type:"changePrixAchat",payload:{value:parseFloat(e.target.value)}})}
                            value={state.prixAchat}
                        />
                    </div>
                    <div className="mb-3 col-6">
                        <label className="form-label">Prix Vente :</label>
                        <input
                            type="number"
                            className="form-control"
                            onChange={(e) => dispatch({type:"changePrixVente",payload:{value:parseFloat(e.target.value)}})}
                            value={state.prixVente}
                        />
                    </div>
                </div>
                <div className="row">
                    <div className="form-floating mb-3 col-6">
                        <textarea
                            className="form-control"
                            placeholder="Leave a comment here"
                            id="floatingTextarea2"
                            onChange={(e) => dispatch({type:"changeDescriptionProduit",payload:{value:e.target.value}})}
                            value={state.descriptionProduit}
                        ></textarea>
                        <label htmlFor="floatingTextarea2">Description</label>
                    </div>
                    <div className="mb-3 col-6">
                        <select
                            className="form-select form-select-lg"
                            aria-label="Large select example"
                            multiple size={1}
                            onChange={(e) => handleOnsSelect(e)}
                            value={state.categorieIds}
                        >
                            <option value="">Sélectionner une catégorie</option>
                            {state.categories.map((data) => (
                                <option key={data.id} value={data.id}>
                                    {data.nom}
                                </option>
                            ))}
                        </select>
                    </div>
                </div>
                <button className="btn btn-outline-success m-1" type="submit" >{buttonState}</button>
                <button
                    className="btn btn-outline-danger"
                    type="reset"
                    onClick={() => resetForm()}
                >
                    Annuler
                </button>
            </form>
        </div>
    );
}
