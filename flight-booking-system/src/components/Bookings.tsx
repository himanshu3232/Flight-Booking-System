import { Box, Toolbar } from "@mui/material";
import { useSelector } from "react-redux";
import { IBooking } from "../app/slice/bookingsSlice";
import CheckIn from "./CheckIn";

export default function Bookings() {
  const bookings: IBooking[] = useSelector((state: any) => state.bookings);
  return (
    <>
      <Box component="main" sx={{ p: 3 }}>
        <Toolbar />
        <div className="card text-center center">
          <h1 className="card-header gray">Bookings done</h1>
          <div className="card-body light-gray">
            <ul className="list-group list-group-flush">
              {bookings.length > 0 ? (
                bookings.map((booking, index) => (
                  <li key={index} className="list-group-item">
                    <CheckIn {...booking} />
                  </li>
                ))
              ) : (
                <h4 style={{ color: "red" }}>No bookings done yet!</h4>
              )}
            </ul>
          </div>
        </div>
      </Box>
    </>
  );
}
