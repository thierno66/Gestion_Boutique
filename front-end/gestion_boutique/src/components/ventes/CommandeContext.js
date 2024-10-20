import {  createContext, useContext, useReducer } from "react";

const initialState={
    commandes:[],
    numeroCommande:"",
    clientId:"",
    produitId:"",
    commandeId:"",
    quantite:0,
    venteProduit:[],
}

const commandeReducer=(state,action)=>{
    switch(action.type){
        case "submit":
            return{
                ...state,
                commandes:[...state.commandes,action.payload.newCommande]
            }
        case "selectClient":
            return{
                ...state,
                clientId:action.payload.clientId
            }
        case "changeNumeroCommande":
            return{
                ...state,
                numeroCommande:action.payload.value
            }
        case "selectProduit":  
            return{
                ...state,
                produitId:action.payload.value
            }
        case "selectCommande":
            return{
                ...state,
                commandeId:action.payload.value,
            }
        case "changeQuantite":
            return{
                ...state,
                quantite:action.payload.value
            }
        case "liveVente":
            return{
                ...state,
                venteProduit:[...state.venteProduit,action.payload.newVente]
            }
        case "addVente":
            return{
                ...state,
                venteProduit:[]
            }
        case "resetFormCommande":
            return{
                ...state,
                clientId:"",
                numeroCommande:""
            }
        case "resetFormVente":
            return{
                ...state,
                commandeId:"",
                quantite:0,
                produitId:""
            }
        case "intitCommandes":
            return{
                ...state,
                commandes:action.payload.commandes
            }
    }
}

const CommandeContext=createContext();
export const CommandeProvider=({children})=>{
    const [state,dispatch]=useReducer(commandeReducer,initialState);

    return <CommandeContext.Provider 
            value={[state,dispatch]
            }>
                {children}
            </CommandeContext.Provider>
}
export const useCommande=()=>useContext(CommandeContext);