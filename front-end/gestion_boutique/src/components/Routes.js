import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Login } from "../pages/Login";
import Produit from "../pages/Produit";
import Commande from "../pages/Commande";
import CommandeInfo from "./ventes/CommandeInfo";
import User from "../pages/User";
import Client from "../pages/Client";
import Vente from "../pages/Vente";  // Assurez-vous d'importer Vente
import Navbar from "../components/Navbar"; // Assurez-vous que Navbar est correctement importé
import { Home } from "@mui/icons-material"; // Si Home est une icône, utilisez-la correctement
import ProtectedRoute from "./ProtectedRoute";

const AppRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route 
            path="/accueil" 
            element={
                // <ProtectedRoute>
                    <Navbar />
                // </ProtectedRoute>
                
                }>
          {/* Utilisez l'élément Home en tant qu'icône correctement */}
          <Route index element={<ProtectedRoute><Home /></ProtectedRoute>} /> 
          {/* Utilisation de ProtectedRoute pour les routes protégées */}
          <Route 
            path="home" 
            element={
              <ProtectedRoute>
                <Home />
              </ProtectedRoute>
            } 
          />
          <Route 
            path="produits" 
            element={
              <ProtectedRoute>
                <Produit />
              </ProtectedRoute>
            } 
          />
          <Route 
            path="commandes" 
            element={
              <ProtectedRoute>
                <Commande />
              </ProtectedRoute>
            } 
          />
          <Route 
            path="commandes/:id" 
            element={
              <ProtectedRoute>
                <CommandeInfo />
              </ProtectedRoute>
            } 
          />
          <Route 
            path="ventes" 
            element={
              <ProtectedRoute>
                <Vente />
              </ProtectedRoute>
            } 
          />
          <Route 
            path="clients" 
            element={
              <ProtectedRoute>
                <Client />
              </ProtectedRoute>
            } 
          />
          <Route 
            path="users" 
            element={
              <ProtectedRoute>
                <User />
              </ProtectedRoute>
            } 
          />
        </Route>
        <Route path='*' element={<Login />} />
      </Routes>
    </BrowserRouter>
  );
};

export default AppRoutes;
