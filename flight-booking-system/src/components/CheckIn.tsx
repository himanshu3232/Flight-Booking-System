import Button from "@mui/material/Button";
import { checkInBooking, IBooking } from "../app/slice/bookingsSlice";
import { useDispatch } from "react-redux";
import axios from "axios";
import React from "react";
import CheckedInModal from "./CheckedInModal";

export default function CheckIn(booking: IBooking) {
  const dispatch = useDispatch();
  const [checkedIn, setCheckedIn] = React.useState(false);
  const handleCheckIn = async () => {
    const id = booking.id;
    if (id) {
      dispatch(checkInBooking(id));
    }
    const response = await axios.post(
      "http://localhost:8072/checkin-service/checkin",
      {
        params: {
          pnr: booking.pnr,
        },
      }
    );
    if (response) {
      setCheckedIn(true);
    }
  };
  return (
    <>
      <div className="d-flex">
        {!booking.checkedIn && (
          <p>
            ✈️PNR : {booking.pnr} 🛂Passenger Name: {booking.passengerName}{" "}
            💵Paid: {booking.paidAmount}{" "}
          </p>
        )}
        <Button onClick={handleCheckIn}>Check In</Button>
      </div>
      {checkedIn && <CheckedInModal />}
    </>
  );
}
