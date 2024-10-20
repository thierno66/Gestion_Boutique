import { createContext, useContext, useReducer } from "react";

const initialState={
    clients:[],
    adresses:[],
    nomAdresse:"",
    descriptionAdresse:"",
    nomClient:"",
    prenomClient:"",
    telephone:"",
    email:"",
    adresseId:""
}
const reducer=(state,action)=>{
    switch(action.type){
        case "changeNomAdresse":
            return{
                ...state,
                nomAdresse:action.payload.value
            }
        case "changeDescriptionAdresse":
            return{
                ...state,
                descriptionAdresse:action.payload.value
            }
        case "addAdresse":
            return{
                ...state,
                adresses:[...state.adresses,action.payload.adresse]
            }
        case "resetFormAdresse":
            return {
                ...state,
                nomAdresse:"",
                descriptionAdresse:""
            }
        case "changeNomClient":
            return{
                ...state,
                nomClient:action.payload.value
            }
        case "changePrenomClient":
            return{
                ...state,
                prenomClient:action.payload.value
            }
        case "changeTelephone":
            return{
                ...state,
                telephone:action.payload.value
            }
        case "changeEmail":
            return{
                ...state,
                email:action.payload.value
            }
        case "selectAdresse":
            return{
                ...state,
                adresseId:action.payload.value
            }
        case "addClient":
            return{
                ...state,
                clients:[...state.clients,action.payload.client]
            }
        case "updateClient":
            return{
                ...state,
                clients:action.payload.updatedClients,
            }
        case "deleteClient":
            return{
                ...state,
                clients:state.clients.filter((client)=>client.id !== action.payload.id)
            }
        case "resetFormClient":
            return{
                ...state,
                nomClient:"",
                prenomClient:"",
                adresseId:"",
                email:"",
                telephone:""
            }
        case "initAdresse":
            return{
                ...state,
                adresses:action.payload.adresses
            }
        case "initClients":
            return{
                ...state,
                clients:action.payload.clients
            }
        default:
            return{}
    }
}
const ClientContext=createContext();
export const ClientProvider=({children})=>{
    const [state,dispatch]=useReducer(reducer,initialState);
    return(
        <ClientContext.Provider value={[state,dispatch]}
        >{children}</ClientContext.Provider>
    );
}

export const useClient=()=>useContext(ClientContext);
