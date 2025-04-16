import Keycloak from "keycloak-js";

const config = {
  url: "http://localhost:7080",
  realm: "master",
  clientId: "flight-booking-system",
};

export const keycloak = new Keycloak(config);

