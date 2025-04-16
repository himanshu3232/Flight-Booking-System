import { Box, Toolbar } from "@mui/material";

export default function LandingPage() {
  return (
    <>
      <div className="bg-img"></div>
      <Box component="main" sx={{ p: 3 }}>
        <Toolbar />
        <div
          className="card center"
          style={{ width: 800, backgroundColor: "beige" }}
        >
          <div className="card-body">
            <h5 className="card-title display-2">Book Flights Instantly!</h5>
            <h6 className="card-subtitle mb-2 text-body-secondary">Features</h6>
            <p className="card-text">
              🏠 Easy Start Simple home screen with big, clear buttons “From”
              and “To” fields with auto-suggestions.
            </p>
            <p className="card-text">
              🛫 Hassle-Free Search Hit “Search” and see flights instantly No
              complex filters—just sort by cheapest
            </p>
          </div>
        </div>
      </Box>
    </>
  );
}
