import { keycloak } from "../service/key-cloak";

export default function LoginPage() {
  const handleLogin = async () => {
    try {
      await keycloak.login({
        redirectUri: window.location.origin + "/app",
      });
      console.log("token", keycloak.token);
    } catch (error) {
      alert("Login failed. Please try again.");
      console.error("Login error:", error);
    }
  };

  return (
    <>
      <div className="card text-center center">
        <h1 className="card-header gray">Flight Booking System</h1>
        <div className="card-body light-gray">
          <h5 className="card-title">Login via Keycloak</h5>
          <p className="card-text">
            You will be redirected to Keycloak auth page
          </p>
          <button onClick={handleLogin} className="btn btn-primary">
            Login
          </button>
        </div>
      </div>
    </>
  );
}
