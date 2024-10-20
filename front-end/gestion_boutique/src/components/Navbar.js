import React, { useState } from "react";
import {  Link, Outlet } from "react-router-dom";
import { AiFillHome,  } from 'react-icons/ai';
import {  FaCartPlus, } from 'react-icons/fa';
import { IoIosPaper,} from 'react-icons/io';
import { FaUsers ,FaUser,FaMoneyBillWave} from "react-icons/fa";
import '../App.css';
import { isVendeur } from "../services/authService";

export default function Navbar() {
    const [sidebarActive, setSidebarActive] = useState(false);

    const toggleSidebar = () => {
        setSidebarActive(!sidebarActive);
    };
    
    const SidebarData = [
        {
            title: 'Home',
            path: '/accueil/home',
            icon: <AiFillHome />,
            cName: 'nav-text'
        },
        {
            title: 'Produits',
            path: '/accueil/produits',
            icon: <FaCartPlus />,
            cName: 'nav-text'
        },
        {
            title: 'Commandes',
            path: '/accueil/commandes',
            icon: <IoIosPaper />,
            cName: 'nav-text'
        },
        {
            title: 'Ventes',
            path: '/accueil/ventes',
            icon: <FaMoneyBillWave />,
            cName: 'nav-text'
        },
        {
            title: 'Clients',
            path: '/accueil/clients',
            icon: <FaUsers />,
            cName: 'nav-text'
        },
        {
            title: 'Utilisateurs',
            path: '/accueil/users',
            icon: <FaUser />,
            cName: 'nav-text'
        }
    ];

    return (

        <div>
            {/* Sidebar */}
            <div id="sidebar" className={sidebarActive ? "active" : ""}>
                <ul className="nav-menu-items">
                {isVendeur()?
                SidebarData.map((item, index) => {
                            if(index!==5)
                             return <li key={index} className={item.cName}>
                                <Link  to={item.path}>
                                    {item.icon}
                                    <span>{item.title}</span>
                                </Link>
                            </li>
                }):
                SidebarData.map((item, index) => {
                            
                    return <li key={index} className={item.cName}>
                        <Link  to={item.path}>
                            {item.icon}
                            <span>{item.title}</span>
                        </Link>
                    </li>
        })
                }
                </ul>

            </div>

            {/* Navbar */}
            <nav className={`navbar navbar-expand-lg navbar-dark bg-dark fixed-top ${sidebarActive ? "navbar-active" : ""}`}>
                <div className="container-fluid">
                    <button className="btn btn-dark" onClick={toggleSidebar}>
                        ☰
                    </button>
                    <a className="navbar-brand" href="#">Mon Site</a>
                </div>
            </nav>
            <div id="content" className={sidebarActive ? "content-active" : ""}>
                
                <Outlet></Outlet>
                
            </div>
        </div>
        
    );
}
