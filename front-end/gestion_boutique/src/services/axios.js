import axios from "axios";

export const axiosInstance=axios.create({
    baseURL:"http://localhost:8080/api",
    headers: {
        "Content-Type": "application/json",
      },
})

axiosInstance.interceptors.request.use(
    (config) => {
      const token = localStorage.getItem("token"); // Récupérer le token depuis localStorage
      if (token) {
        config.headers.Authorization = `Bearer ${token}`; // Ajouter le token à l'en-tête d'autorisation
      }
      return config;
    },
    (error) => Promise.reject(error)
  );
