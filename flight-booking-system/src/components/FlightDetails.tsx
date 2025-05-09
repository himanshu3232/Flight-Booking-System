import React from "react";
import BookingModal from "./BookingModal";
import Button from "@mui/material/Button";
import BookingSuccessfulModal from "./BookingSuccessfulModal";

export interface IFlightDetails {
  flightId: number;
  airline: string;
  departureAirport: string;
  arrivalAirport: string;
  departureDate: string;
  arrivalDate: string;
  duration: string;
  price: number;
  seatsAvailable: number;
}

export default function FlightDetails(flightDetails: IFlightDetails) {
  const [book, setBook] = React.useState(false);
  const [pnr, setPnr] = React.useState<string>("");
  const [open, setOpen] = React.useState(false);

  return (
    <>
      <div className="d-flex flex-row justify-content-center align-items-center gap-2 border border-1 rounded-3 p-3 m-2">
        <p>
          ✈️{flightDetails.airline} 🕜Duration: {flightDetails.duration}
          {" 💵"}
          Price: {flightDetails.price}{" "}
        </p>
        <Button
          variant="text"
          color="primary"
          onClick={() => {
            setBook(true);
          }}
        >
          Book Now
        </Button>
      </div>
      {book && (
        <BookingModal
          setPnr={setPnr}
          book={book}
          setBook={setBook}
          setOpen={setOpen}
          price={flightDetails.price}
          flightId={flightDetails.flightId}
        />
      )}
      {open && (
        <BookingSuccessfulModal open={open} setOpen={setOpen} pnr={pnr} />
      )}
    </>
  );
}
