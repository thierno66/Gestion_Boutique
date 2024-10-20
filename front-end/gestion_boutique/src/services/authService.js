import { jwtDecode } from "jwt-decode";
import { axiosInstance } from "./axios";

export const login= async (username,password)=>{
        const response=await axiosInstance.post("/users/connexion",{username,password});
        return response;
}

export const createUser= async(data)=>{
    try {
        const response=await axiosInstance.post("/users/inscription",data);
        return response.data;
    } catch (error) {
        console.log("erreur lors de la creation de l'utilisateur "+error)
    }
}
export const getAllUser = async () => {
    try {
      const response = await axiosInstance.get("/users");
      return response.data; // Retourner les données des utilisateurs
    } catch (error) {
      if (error.response) {
        console.log("Erreur de réponse du serveur lors de la récupération des utilisateurs :", error.response.data);
      } else if (error.request) {
        console.log("Erreur de requête lors de la récupération des utilisateurs : aucune réponse reçue", error.request);
      } else {
        console.log("Erreur lors de la récupération des utilisateurs :", error.message);
      }
    }
  };
  export const getAllRoles=async()=>{
    try {
        const response= await axiosInstance.get("/roles");
        return response.data;
    } catch (error) {
        console.log("Erreur lors de la recuprerations des roles")
    }
  }
  export const updateUser= async(id,data)=>{
    try {
        const response=await axiosInstance.put(`/users/${id}`,data);
        return response.data;
    } catch (error) {
        console.log("Erreur lors de la modification de l'utilisateur "+error)
    }
  }
  export const deleteUser=async(id)=>{
    const response= await axiosInstance.delete(`/users/${id}`);
    return response;
  }


export const isAuthenticated=()=>{
    return localStorage.getItem('token') !== null;
}
export const isTokenExpired=()=>{
  const decodeToken=getUser();
  
  const expiration=decodeToken.expiration;
  console.log(Date.now()>expiration)
  console.log(Date.now());
  
  return Date.now()>expiration;
}
export const getUser=()=>{
  const token=localStorage.getItem('token');
  if(token){
    const decodeToken=jwtDecode(token)
    return decodeToken;
  }

}

export const isVendeur=()=>{
  const user=getUser();
  if(user){
    const roleList= user.roles.filter((role)=>role.roleName==="ROLE_GERANT");
    return roleList.length>0?false:true;
  }
}