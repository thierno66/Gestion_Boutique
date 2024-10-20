import { createContext, useContext, useReducer } from "react";


const initialState={
    produits:[],
    categories:[],
    nomCategorie:"",
    descriptionCategorie:"",
    nomProduit:"",
    descriptionProduit:"",
    quantiteDisponible:0,
    prixAchat:0,
    prixVente:0,
    categorieIds:[]
}
const reducer=(state,action)=>{
    switch(action.type){
        case "addCategorie":
            return {
                ...state,
                categories:[...state.categories,action.payload.categorie]
            }
        case "changeNomCategorie":
            return {
                ...state,
                nomCategorie:action.payload.value
            }
        case "changeDescriptionCategorie":
            return {
                ...state,
                descriptionCategorie:action.payload.value
            }
        case "resetFormCategorie":
            return{
                ...state,
                descriptionCategorie:"",
                nomCategorie:""
            }
        case "changeNomProduit":
            return {
                ...state,
                nomProduit:action.payload.value,
            }
        case "changeDescriptionProduit":
            return {
                 ...state,
                descriptionProduit:action.payload.value,
            }
        case "changePrixAchat":
            return{
                ...state,
                prixAchat:action.payload.value
            }
        case "changePrixVente":
            return{
                ...state,
                prixVente:action.payload.value
            }
        case "changeQuantiteDisponible":
            return{
                ...state,
                quantiteDisponible:action.payload.value
            }
        case "selectCategorie":
            return{
                ...state,
                categorieIds:action.payload.value
            }
        case "addProduit":
            return {
                ...state,
                produits:[...state.produits,action.payload.produit]
            }
         case "deletteProduit":
            return {
                ...state,
                produits:state.produits.filter((produit)=>produit.id !=action.payload.id)
            }
        case "updateProduit":
            return {
                ...state,
                produits:action.payload.newProduits
            }
        case "resetProduitForm":
            return{
                ...state,
                quantiteDisponible:0,
                prixAchat:0,
                prixVente:0,
                nomProduit:"",
                descriptionProduit:"",
                categorieIds:""
            }
        case "initCategorie":
            return{
                ...state,
                categories:action.payload.categories
            }
        case "initProduits":
            return {
                ...state,
                produits:action.payload.produits
            }
        
    }
}
const ProduitContext=createContext();
export  const ProduitProvider=({children})=>{
    const [state,dispatch]=useReducer(reducer,initialState);
  return <ProduitContext.Provider 
          value={[state,dispatch]}>{children}
          </ProduitContext.Provider>

}
export const useProduit=()=>useContext(ProduitContext);
