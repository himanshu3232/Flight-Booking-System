import { Box, Toolbar } from "@mui/material";
import { useSelector } from "react-redux";
import { IBooking } from "../app/slice/bookingsSlice";
import CheckIn from "./CheckIn";

export default function Bookings() {
  const bookings : IBooking[] = useSelector((state: any) => state.bookings);
  return (
    <>
      <Box component="main" sx={{ p: 3 }}>
        <Toolbar />
        <div className="card text-center center">
        <h1 className="card-header gray">Bookings done</h1>
        <div className="card-body light-gray">
          <p className="card-text">
            {bookings.length > 0 ? bookings.map((booking, index) => <CheckIn key={index} {...booking}/>)
            : <h4 style={{color : "red"}}>No bookings done yet!</h4>}
          </p>
        </div>
      </div>
      </Box>
    </>
  );
}
