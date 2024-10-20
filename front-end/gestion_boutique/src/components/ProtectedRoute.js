import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { isAuthenticated, isTokenExpired } from "../services/authService";

export default function ProtectedRoute({ children }) {
  const navigate = useNavigate(); // Déclare la fonction navigate avant de l'utiliser
  useEffect(() => {
    if (!isAuthenticated() || isTokenExpired() ) {
        console.log("dans useEffect");
      navigate("/login"); // Redirige l'utilisateur vers la page de connexion s'il n'est pas authentifié
    }
  }, [isAuthenticated, navigate]); // Ajoute isAuthenticated et navigate comme dépendances pour éviter les avertissements

  return isAuthenticated ? children : null; // Retourne les enfants si l'utilisateur est authentifié, sinon retourne null
}









// import {  useNavigate } from "react-router-dom";
// import { useAuth } from "./AuthContext";
// import { useEffect } from "react";

// export default function ProtectedRoute({children}){
//     const [isAuthenticated,]=useAuth();
//     const verification=({children})=>{
//         if(!isAuthenticated){
//             navigate("/login");
//         }
//         console.log(children)
//         return children;
//     }
//     const navigate=useNavigate();
//     useEffect(()=>{
//         verification(children)
//     },[])
// }