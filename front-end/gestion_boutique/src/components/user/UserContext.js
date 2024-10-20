import { createContext, useContext, useReducer } from "react";

// const userItems=[
//     {
//         id:1,
//         nom:"Diallo",
//         prenom:"Issagha",
//         telephone:"782345678",
//         email:"issagha@gmail.com",
//         password:"12345678",
//         roles:[1,2]
//     },
//     {
//         id:2,
//         nom:"Ba",
//         prenom:"Youssou",
//         telephone:"782345678",
//         email:"youssou@gmail.com",
//         password:"12345678",
//         roles:[1]
//     },
//     {
//         id:3,
//         nom:"Diallo",
//         prenom:"Amainatou",
//         telephone:"782345678",
//         email:"aminatou@gmail.com",
//         password:"12345678",
//         roles:[1,2]
//     }
// ]
// const roleItems=[
//     {
//         id:1,
//         nom:"ROLE_GERANT"
//     },
//     {
//         id:2,
//         nom:"ROLE_VENDEUR"
//     }
// ]
const initialState={
    users:[],
    roles:[],
    nom:"",
    prenom:"",
    email:"",
    telephone:"",
    password:"",
    nomRole:"",
    roleName:[]
}
const reducer=(state,action)=>{
    switch(action.type){
        case "changeNom":
            return{
                ...state,
                nom:action.payload.value
            }
        case "changePrenom":
            return {
                ...state,
                prenom:action.payload.value
            }
        case "changeEmail":
            return {
                ...state,
                email:action.payload.value
            }
        case "changeTelephone":
            return {
                ...state,
                telephone:action.payload.value
            }
        case "changePassword":
            return {
                ...state,
                password:action.payload.value
            }
        case "changeRoleName":
            return {
                ...state,
                roleName:action.payload.value
            }
        
        case "changeNomRole":
            return {
                ...state,
                nomRole:action.payload.value
            }
        case "addRole":
            return{
                ...state,
                roles:[...state.roles,action.payload.role]
            }
        case "addUser":
            return{
                ...state,
                users:[...state.users,action.payload.user]
            }
        case "updateUser":
            return{
                ...state,
                users:action.payload.updatedUser
            }
        case "deleteUser":
            return{
                ...state,
                users:state.users.filter((user)=>user.id !=action.payload.id)
            }
        case "resetFormRole":
            return{
                ...state,
                nomRole:""
            }
        case "resetFormUser":
            return{
                ...state,
                nom:"",
                prenom:"",
                email:"",
                telephone:"",
                password:"",
                roleName:[]

            }
        case "initialiserUser":
            return{
                ...state,
                users:action.payload.users
            }
        case "initializeRole":
            return{
                ...state,
                roles:action.payload.roles
            }
    }
}
export const UserContext=createContext();
export const UserProvider=({children})=>{
    const [state,dispatch]=useReducer(reducer,initialState);
    return <UserContext.Provider value={[state,dispatch]}>
                {children}
            </UserContext.Provider>
}
export const useUser=()=>useContext(UserContext);