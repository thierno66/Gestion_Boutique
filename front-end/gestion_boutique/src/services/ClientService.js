import { axiosInstance } from "./axios"

export const getAllAdresses=async()=>{
    const response=await axiosInstance.get("/adresses");
    return response;
}
export const getAllClients=async()=>{
    const response=await axiosInstance.get("/clients");
    return response;
}

export const creerAdresse= async(data)=>{
    const response=await axiosInstance.post("/adresses",data);
    return response;
}

export const creerClient= async(client)=>{
    const response=await axiosInstance.post("/clients",client);
    return response;
}

export const modifierClient= async (id,client)=>{
    return await axiosInstance.put(`/clients/${id}`,client);
}

export const supprimerClient=async (id)=>{
    return await axiosInstance.delete(`/clients/${id}`);
}