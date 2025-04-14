import { Box, Toolbar } from "@mui/material";
import SearchForm from "./SearchForm";

export default function Booking() {
  return (
    <>
      <Box component="main" sx={{ p: 3 }}>
        <Toolbar />
        <div className="card text-center center">
          <h1 className="card-header gray">Book Flights</h1>
          <div className="card-body light-gray">
            <h5 className="card-title">Search Flights</h5>
            <SearchForm />
          </div>
        </div>
      </Box>
    </>
  );
}
