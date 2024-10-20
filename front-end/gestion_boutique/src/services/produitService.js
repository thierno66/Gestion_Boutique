import { axiosInstance } from "./axios"

export const ajouterCategorie=async (data)=>{
    const response= await axiosInstance.post("/categories",data);
    return response;
}

export const getAllCategories= async()=>{
    const response= await axiosInstance.get("/categories");
    return response;
}
export const ajouteProduit= async (produit)=>{
    const response= await axiosInstance.post("/produits",produit);
    return response;

}
export const getAllProduits=async()=>{
    const response= axiosInstance.get("/produits");
    return response;
}
export const modifierProduit=async(id,data)=>{
    const response=axiosInstance.put(`/produits/${id}`,data);
    return response;
}

export const supprimerProduit= async(id)=>{
    const response=await axiosInstance.delete(`produits/${id}`);
    return response;
}

