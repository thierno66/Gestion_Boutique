import React, { useEffect, useState } from "react";
import { getAllUser } from "../services/authService";


function Home(){
  const [users,setUsers]=useState();
  useEffect(()=>{
    getAllUser().then((data)=>setUsers(data)).catch((error)=>console.log("erreur "+error)
    )
  },[])
    return(
        <div className='home'>
      <h1>Home</h1>
    </div>
    )
}

export default Home;