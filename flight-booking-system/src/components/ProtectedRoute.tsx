import { ReactNode } from "react";
import { Navigate } from "react-router-dom";

interface IProtectedRouteProps {
  isAllowed: boolean;
  children?: ReactNode
}

export default function ProtectedRoute({ isAllowed, children }: IProtectedRouteProps) {
  if (!isAllowed) return <Navigate to="/unauthorized" replace />;
  return <>{children}</>; 
}

