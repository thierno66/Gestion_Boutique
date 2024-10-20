import AjouterProduit from "../components/AjouterProduit";
import Table from "../components/Table";
import AjouterCategorie from "../components/AjouterCategorie";
import {  useProduit } from "../components/ProduitContext";
import { useEffect } from "react";
import { getAllCategories, getAllProduits } from "../services/produitService";
import { useNavigate } from "react-router-dom";
import { isVendeur } from "../services/authService";


function Produit(){     
    const [state,dispatch]=useProduit(); 
    const columns=["ID","Nom","Description","Quantité Disponible","Prix Vente","Prix Achat","Catégories","Action"];
    const columns1=["ID","Nom","Description","Quantité Disponible","Prix Vente","Prix Achat","Catégories"];
    const champs=["id","nom","description","quantiteDisponible","prixVente","prixAchat","categories"]
    const navigate=useNavigate();
    useEffect(()=>{
      getAllCategories().then((response=>{
        if(response.status===200){
          console.log(response.data);
          dispatch({type:"initCategorie",payload:{categories:response.data}})
        }
      })).catch((err)=>{
        if(err.response.status===401){
          navigate("/login")
        }
      })
      getAllProduits().then((response)=>{
        if(response.status===200)
          dispatch({type:"initProduits",payload:{produits:response.data}})
      }).catch((err)=>{
        if(err.response.status===401){
          navigate("/login")
        }
      })
    },[])

  if(isVendeur())
    return <div className="card">
              <div className="card-body">
                <Table champs={champs} columns={columns1} donnees={state.produits}  donnees1={state.categories} enplacement={6} />
              </div>
          </div>
    return (
        <div className='container-fluid'>
          <div className="row ">
            <div className="col-6">
              <div className="card">
                <div className="card-body">
                <h2>Produits</h2>
                <AjouterProduit />
                </div>
              </div>
            </div>
            <div className="col-6">
            <div className="card">
                <div className="card-body">
                <h2>Categories</h2>
                <AjouterCategorie  />
                </div>
              </div>
            </div>
          </div>
          <div className="card mt-1" >
          <div className="card-body">
            <Table champs={champs} columns={columns} donnees={state.produits}  donnees1={state.categories} enplacement={6} />
          </div>
          </div>
          
      </div>
    )
}

export default Produit;