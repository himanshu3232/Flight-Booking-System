import { ReactNode } from "react";
import { Navigate } from "react-router-dom";
import { keycloak } from "../service/key-cloak";

interface IProtectedRouteProps {
  children?: ReactNode;
}

export default function ProtectedRoute({ children }: IProtectedRouteProps) {

  if (keycloak.authenticated) return <Navigate to="/unauthorized" replace />;
  return <>{children}</>;
}
