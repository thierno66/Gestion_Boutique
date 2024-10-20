import './App.css';

import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import { createContext, useState } from 'react';
import { CommandeProvider } from './components/ventes/CommandeContext';
import { ProduitProvider } from './components/ProduitContext';
import { ClientProvider } from './components/client/ClientContext';
import { UserProvider } from './components/user/UserContext';
import { AuthProvider } from './components/AuthContext';
import AppRoutes from './components/Routes';

export const ContextUtils = createContext();
const ProviderUtile = ({ children }) => {
  const [id, setId] = useState(null);
  const [isUpdate, setIsUpdate] = useState(false);
  return (
    <ContextUtils.Provider value={{ id: [id, setId], isUpdate: [isUpdate, setIsUpdate] }}>
      {children}
    </ContextUtils.Provider>
  );
}

function App() {
  return (
    <AuthProvider>
      <ProviderUtile>
        <ProduitProvider>
          <UserProvider>
            <ClientProvider>
              <CommandeProvider>
                <AppRoutes />
              </CommandeProvider>
            </ClientProvider>
          </UserProvider>
        </ProduitProvider>
      </ProviderUtile>
    </AuthProvider>
  );
}

export default App;
