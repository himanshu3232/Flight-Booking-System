import Button from "@mui/material/Button";
import { IBooking } from "../app/slice/bookingsSlice";
import axios from "axios";
import React from "react";
import CheckedInModal from "./CheckedInModal";
import { keycloak } from "../service/key-cloak";
import CancelBookingModal from "./CancelBookingModal";

export default function CheckIn(booking: IBooking) {
  const [checkedIn, setCheckedIn] = React.useState(false);
  const [id, setId] = React.useState<number>(-1);
  const [open, setOpen] = React.useState(false);
  const [seatNumber, setSeatNumber] = React.useState<string>("");
  const handleCheckIn = async () => {
    await keycloak.updateToken(5);

    const response = await axios.post(
      "http://localhost:8072/checkin-service/checkin",
      {}, //request body
      {
        params: { pnr: booking.pnr },
        headers: { Authorization: `Bearer ${keycloak.token}` },
      }
    );

    console.log(response);

    if (response.status === 201) {
      setCheckedIn(true);
      setId(response.data.checkInId);
      setSeatNumber(response.data.seatNumber);
    }
  };
  return (
    <>
      <div className="d-flex">
        {!booking.checkedIn && (
          <>
            <p>🛂Name: {booking.passengerName}</p>
            <p>✈️PNR : {booking.pnr}</p>
            <div style={{ marginLeft: "auto" }}>
              <Button onClick={handleCheckIn}>Check In</Button>
              <Button onClick={() => setOpen(true)}>Cancel</Button>
            </div>
          </>
        )}
      </div>
      {checkedIn && (
        <CheckedInModal
          setCheckedIn={setCheckedIn}
          id={id}
          seatNumber={seatNumber}
          checkedIn={checkedIn}
          pnr={booking.pnr}
        />
      )}
      {open && (
        <CancelBookingModal pnr={booking.pnr} open={open} setOpen={setOpen} />
      )}
    </>
  );
}
