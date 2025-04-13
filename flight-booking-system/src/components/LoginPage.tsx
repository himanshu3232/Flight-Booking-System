export default function LoginPage() {
  return (
    <>
      <div className="card text-center center">
        <h1 className="card-header gray">Flight Booking System</h1>
        <div className="card-body light-gray">
          <h5 className="card-title">Login via Keycloak</h5>
          <p className="card-text">
            You will be redirected to Keycloak auth page
          </p>
          <a href="#" className="btn btn-primary">
            Login
          </a>
        </div>
      </div>
    </>
  );
}
