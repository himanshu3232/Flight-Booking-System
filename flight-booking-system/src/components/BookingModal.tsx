import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import { useFormik } from "formik";
import axios from "axios";
import BookingSuccessfulModal from "./BookingSuccessfulModal";
import { useDispatch } from "react-redux";
import { addBooking } from "../app/slice/bookingsSlice";

export interface IBookingRequest {
  flightId: number;
  passengerName: string;
  passengerEmail: string;
  passengerAge: number;
  paidAmount: number;
}

export interface IBookingResponse {
  bookingId: number;
  pnr: string;
  seatNumber: string;
}

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

export default function BookingModal({
  flightId,
  setBook,
}: {
  flightId: number;
  setBook: React.Dispatch<React.SetStateAction<boolean>>;
}) {
  const [open, setOpen] = React.useState(true);
  const handleClose = () => setOpen(false);
  const [response, setResponse] = React.useState<null | IBookingResponse>(null);
  const dispatch = useDispatch();

  const formik = useFormik({
    initialValues: {
      flightId,
      passengerName: "",
      passengerEmail: "",
      passengerAge: 0,
      paidAmount: 0,
    },
    onSubmit: async (values: IBookingRequest): Promise<void> => {
      const { data } = await axios.post("http://localhost:8072/booking-service/booking", values);
      console.log(data)
      if (data) {
        setOpen(false);
        setBook(false);
        setResponse(data);
        dispatch(addBooking({ ...values, pnr: data.pnr }));
      }
    },
  });

  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Book FLight
          </Typography>
          <form onSubmit={formik.handleSubmit}>
            <label htmlFor="text">Full Name:</label>
            <input
              id="name"
              name="passengerName"
              type="text"
              onChange={formik.handleChange}
              value={formik.values.passengerName}
            />

            <label htmlFor="email">Email Address:</label>
            <input
              id="email"
              name="passengerEmail"
              type="email"
              onChange={formik.handleChange}
              value={formik.values.passengerEmail}
            />

            <label htmlFor="number">Age:</label>
            <input
              id="age"
              name="passengerAge"
              type="number"
              onChange={formik.handleChange}
              value={formik.values.passengerAge}
            />

            <button type="submit">Confirm Booking</button>
          </form>
        </Box>
      </Modal>

      {response && <BookingSuccessfulModal {...response} />}
    </div>
  );
}
