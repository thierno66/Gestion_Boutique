import {axiosInstance} from './axios';
export const getAllCommande=async()=>{
    return await axiosInstance.get("/commandes");
}
export const creerCommande=async(commande)=>{
    return await axiosInstance.post("/commandes",commande)
}

export const vendreProduit=async(commandeId,commandes)=>{
    return await axiosInstance.post(`/commandes/${commandeId}/vendre`,commandes);
}

export const getAllLigneByCommande=async (commandeId)=>{
    return axiosInstance.get(`/commandes/${commandeId}/ligne-commande`);
}
export const getAllCommandeByUser=async()=>{
    return await axiosInstance.get("/users/commandes");
}