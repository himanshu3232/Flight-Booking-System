import { ReactNode } from "react";
import { Navigate, Outlet } from "react-router-dom";

interface IProtectedRouteProps {
  isAllowed: boolean;
  children?: ReactNode
}

export default function ProtectedRoute({ isAllowed }: IProtectedRouteProps) {
  return isAllowed ? <Outlet /> : <Navigate to="/unauthorized" replace />;
}
