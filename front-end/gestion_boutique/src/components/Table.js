import React, { useContext } from "react";
import { FaEdit } from "react-icons/fa";
import { MdDelete } from "react-icons/md";
import { ContextUtils } from "../App";
import { isVendeur } from "../services/authService";

export default function Table({ champs, columns, donnees, donnees1, enplacement }) {
    const { isUpdate, id } = useContext(ContextUtils); // Déstructuration de value
    const [, setisUpdate] = isUpdate;     
    const [, setId] = id;
    
 if(!isVendeur())
    return (
        <table className="table table-hover">
            <thead>
                <tr>
                    {columns.map((column, index) => (
                        <th key={index}>{column}</th>
                    ))}
                </tr>
            </thead>
            <tbody>
                {donnees.map((data) => (
                    <tr key={data.id}>
                        {champs.map((champ, index) => {
                            let p = data[champ];
                            if (Array.isArray(p)) {
                                return (
                                    <td key={index}>
                                        <ul>
                                            {p.map((d) => {
                                                const value = donnees1.find((dat) => dat.id === d.id);
                                                return <li key={d.id}>{value?.nom || "Unknown"}</li>;
                                            })}
                                        </ul>
                                    </td>
                                );
                            } else if (index === enplacement) {  
                                p=p.nom;
                            }
                            
                            return <td key={index}>{p}</td>;
                        })}
                        <td>
                            <button
                                className="btn btn-outline-success m-1"
                                onClick={() => {
                                    setisUpdate(true);
                                    setId(data.id);
                                }}
                            >
                                <FaEdit />
                            </button>
                            <button
                                className="btn btn-outline-danger"
                                onClick={() => setId(data.id)}
                            >
                                <MdDelete />
                            </button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
    return (
        <table className="table table-hover">
            <thead>
                <tr>
                    {columns.map((column, index) => (
                        <th key={index}>{column}</th>
                    ))}
                </tr>
            </thead>
            <tbody>
                {donnees.map((data) => (
                    <tr key={data.id}>
                        {champs.map((champ, index) => {
                            let p = data[champ];
                            if (Array.isArray(p)) {
                                return (
                                    <td key={index}>
                                        <ul>
                                            {p.map((d) => {
                                                const value = donnees1.find((dat) => dat.id === d.id);
                                                return <li key={d.id}>{value?.nom || "Unknown"}</li>;
                                            })}
                                        </ul>
                                    </td>
                                );
                            } else if (index === enplacement) {  
                                p=p.nom;
                            }
                            
                            return <td key={index}>{p}</td>;
                        })}
                    </tr>
                ))}
            </tbody>
        </table>
    );
}
