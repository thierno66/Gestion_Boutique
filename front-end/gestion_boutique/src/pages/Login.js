import { useState } from "react";
import { login } from "../services/authService";
import { useNavigate } from "react-router-dom";

export function Login(){
    const [username,setUsename]=useState("");
    const [password,setPassword]=useState("");
    const [message,setMessage]=useState("");
    //const [isAuthenticated,setIsAuthenicated]=useAuth();
    const navigate=useNavigate();
    const handleOnSubmit=(event)=>{
        event.preventDefault();
        login(username,password).then((response)=>{
            if(response.status===200){
                localStorage.setItem("token",response.data);
                navigate("/accueil");
            }
            setUsename("");
            setPassword("");
        }).catch(error=>{
            setMessage(error.response.data);
        })
    }
    return(
        <div className="container">
            <div className="card">
                <div className="card-header">
                    <h2>Veillez vous Connecter</h2>
                </div>
                <div className="card-body">
                    <form onSubmit={handleOnSubmit}>
                        <div className="row">
                            <label className="form-label">Nom d'Utilisateur</label>
                            <input className="form-control" type="text" 
                                onChange={(e)=>{
                                    setUsename(e.target.value);
                                    setMessage("");
                                }}
                                value={username} />
                        </div>
                        <div className="row">
                            <label className="form-label">Password</label>
                            <input className="form-control" type="password" 
                                onChange={(e)=>{
                                    setPassword(e.target.value);
                                    setMessage("");
                                }}
                                value={password} />
                        </div>
                        <button className="btn btn-outline-success mt-3" type="submit">Connexion</button>
                    </form>
                    <h6 className="mt-2 text-danger">{message}</h6>
                </div>
            </div>
        </div>
    );
}