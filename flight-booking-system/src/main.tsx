import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.tsx";
import "bootstrap/dist/css/bootstrap.min.css";
import "./index.css";
import { keycloak } from "./service/key-cloak.ts";
import axios from "axios";


keycloak
  .init({
    onLoad: "check-sso",
    silentCheckSsoRedirectUri: `${window.location.origin}/silent-check-sso.html`,
    checkLoginIframe: true,
    pkceMethod: "S256",
  })
  .then((authenticated) => {
    if (authenticated) {
      console.log("User is authenticated:", authenticated);
      axios.defaults.headers.common["Authorization"] = `Bearer ${keycloak.token}`;
      keycloak.onTokenExpired = () => {
        keycloak.updateToken(30)
      }
    } else {
      console.log("User is not authenticated:", authenticated);
    }
  })
  .catch((error) => {
    console.error("Keycloak initialization error:", error);
  });

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <App />
  </StrictMode>
);
