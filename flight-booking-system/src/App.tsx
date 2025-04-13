import LoginPage from "./components/LoginPage"
import AppLayout from "./components/AppLayout"
import { createBrowserRouter, RouterProvider } from "react-router-dom"
import LandingPage from "./components/LandingPage"
import Booking from "./components/Booking"
import Search from "./components/Search"
import UnAuthorized from "./components/UnAuthorized"
import ProtectedRoute from "./components/ProtectedRoute"
import { useState } from "react"

export default function App() {
  const [isLogin, setIsLogin] = useState(false);
  const router = createBrowserRouter(
    [
      {
        path: '/',
        element: <LoginPage/>
      },
      {
        path: '/app',
        element: (
          <ProtectedRoute isAllowed={isLogin}>
            <AppLayout/>
          </ProtectedRoute>
        ),
        children: [
          {
            path: '/app',
            element: <LandingPage/>
          },
          {
            path: '/app/booking',
            element: <Booking/>
          },
          {
            path: '/app/search',
            element: <Search/>
          }
        ]
      },
      {
        path: '/unauthorized',
        element: <UnAuthorized/>
      },
      {
        path: '*',
        element: <h1>404 Not Found</h1>
      }
      
    ]
  )
  return (
    <>
    <RouterProvider router={router}/>
    </>
  )
}
