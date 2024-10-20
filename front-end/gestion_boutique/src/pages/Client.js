import { NewAdresse } from "../components/client/NewAdresse";
import NewClient from "../components/client/NewClient";
import Table from "../components/Table";
import { useClient } from "../components/client/ClientContext";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getAllAdresses, getAllClients } from "../services/ClientService";

export default function Client() {
    const [state, dispatch] = useClient();
    const columns = ["ID", "Nom", "Prenom", "Telephone", "Email", "Adresse", "Action"];
    const champs = ["id", "nom", "prenom", "telephone", "email", "adresse"];
    const navigate = useNavigate();

    useEffect(() => {
        // Fetch all clients
        getAllClients().then(response => {
            if (response.status === 200) {
                dispatch({ type: "initClients", payload: { clients: response.data } });
            }
        }).catch(err => {
            if (err.response && err.response.status === 401) {
                navigate("/login");
            }
            console.error("Error fetching clients:", err);
        });
    }, [dispatch, navigate]); // Added dispatch and navigate to the dependencies array

    useEffect(() => {
        // Fetch all addresses
        getAllAdresses().then(response => {
            if (response.status === 200) {
                dispatch({ type: "initAdresse", payload: { adresses: response.data } });
            }
        }).catch(err => {
            if (err.response && err.response.status === 401) {
                navigate("/login");
            }
            console.error("Error fetching addresses:", err);
        });
    }, [dispatch, navigate]); // Added dispatch and navigate to the dependencies array

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-6">
                    <NewClient />
                </div>
                <div className="col-6">
                    <NewAdresse />
                </div>
                <div className="card m-2">
                    <div className="card-body">
                        <Table columns={columns} champs={champs} donnees={state.clients} donnees1={state.adresses} enplacement={5} />
                    </div>
                </div>
            </div>
        </div>
    );
}
