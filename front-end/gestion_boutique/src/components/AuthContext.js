import { createContext, useContext, useState } from "react";

export const AutContext=createContext();
export const AuthProvider=({children})=>{
    const [isAuthenticated,setIsAuthenicated]=useState(false);
    return <AutContext.Provider value={[isAuthenticated,setIsAuthenicated]}>{children}</AutContext.Provider>
}


export const useAuth=()=>useContext(AutContext);