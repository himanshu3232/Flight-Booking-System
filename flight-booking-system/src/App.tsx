import LoginPage from "./components/LoginPage";
import AppLayout from "./components/AppLayout";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import LandingPage from "./components/LandingPage";
import Booking from "./components/Booking";
import UnAuthorized from "./components/UnAuthorized";
import ProtectedRoute from "./components/ProtectedRoute";
import Bookings from "./components/Bookings";
import { Provider } from "react-redux";
import { store } from "./app/store";

export default function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <LoginPage />,
    },
    {
      path: "/app",
      element: (
        <ProtectedRoute>
          <AppLayout />
        </ProtectedRoute>
      ),
      children: [
        {
          path: "/app",
          element: <LandingPage />,
        },
        {
          path: "/app/booking",
          element: <Booking />,
        },
        {
          path: "/app/bookings",
          element: <Bookings />,
        },
      ],
    },
    {
      path: "/unauthorized",
      element: <UnAuthorized />,
    },
    {
      path: "*",
      element: <h1>404 Not Found</h1>,
    },
  ]);
  return (
    <>
      <Provider store={store}>
        <RouterProvider router={router} />
      </Provider>
    </>
  );
}
