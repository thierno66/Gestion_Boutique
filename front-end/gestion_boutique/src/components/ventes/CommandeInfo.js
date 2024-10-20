import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getAllLigneByCommande } from "../../services/CommandeService";

function CommandeInfo() {
  const { id } = useParams();  
  const [values, setValue] = useState([]); // Initialiser avec un tableau vide
  let total = 0;
  const [data, setData] = useState({}); // Initialiser avec un objet vide

  useEffect(() => {
    getAllLigneByCommande(id).then((response) => {
      setValue(response.data);
      setData(response.data[0]); // Mettre à jour avec les données reçues
    });
  }, [id]);  // Ajouter `id` comme dépendance

  return (
    <div className="card">
      <div className="card-header">
        {data && data.commande ? ( // Vérifier si `data` et `data.commande` sont définis
          <>
            <div className="row">
              <p><strong>Numero commande:</strong> {data.commande.numeroCommande}</p>
            </div>
            <div className="row">
              <p><strong>Jour:</strong> {data.commande.jour}</p>
            </div>
            <div className="row">
              <p><strong>Nom:</strong> {data.commande.client.nom} {data.commande.client.prenom}</p>
            </div>
            <div className="row">
              <p><strong>Adresse:</strong> {data.commande.client.adresse.nom} {data.commande.client.adresse.description}</p>
            </div>
            <div className="row">
              <p><strong>Telephone:</strong> {data.commande.client.telephone}</p>
            </div>
            <div className="row">
              <p><strong>Email:</strong> {data.commande.client.email}</p>
            </div>
          </>
        ) : (
          <p>Chargement des données...</p> // Afficher un message de chargement ou vide
        )}
      </div>
      <div className="card-body">
        <table className="table table-hover">
          <thead>
            <tr>
              <th>ID</th>
              <th>Produit</th>
              <th>Quantité</th>
              <th>Prix Unitaire</th>
              <th>Total</th>
            </tr>
          </thead>
          <tbody>
            {values.map((value, index) => {
              total += value.produit.prixVente * value.quantite;
              return (
                <tr key={index}>
                  <td>{value.produit.id}</td>
                  <td>{value.produit.nom}</td>
                  <td>{value.quantite}</td>
                  <td>{value.produit.prixVente}</td>
                  <td>{value.produit.prixVente * value.quantite}</td>
                </tr>
              );
            })}
          </tbody>
          <tfoot>
            <tr>
              <td>Total</td>
              <td></td>
              <td></td>
              <td></td>
              <td>{total}</td>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
  );
}

export default CommandeInfo;
