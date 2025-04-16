import * as React from "react";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import { useFormik } from "formik";
import axios, { HttpStatusCode } from "axios";
import { useDispatch } from "react-redux";
import { addBooking } from "../app/slice/bookingsSlice";
import { keycloak } from "../service/key-cloak";

export interface IBookingRequest {
  flightId: number;
  passengerName: string;
  passengerEmail: string;
  passengerAge: number;
  paidAmount: number;
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
  setOpen,
  book,
  price,
  setPnr,
}: {
  flightId: number;
  book: boolean;
  setBook: React.Dispatch<React.SetStateAction<boolean>>;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  price: number;
  setPnr: React.Dispatch<React.SetStateAction<string>>;
}) {
  const handleClose = () => setBook(false);
  const dispatch = useDispatch();

  const formik = useFormik({
    initialValues: {
      flightId,
      passengerName: "",
      passengerEmail: "",
      passengerAge: 0,
      paidAmount: price,
    },
    onSubmit: async (values: IBookingRequest): Promise<void> => {
      await keycloak.updateToken(5)
      const res = await axios.post(
        "http://localhost:8072/booking-service/booking",
        values,
        {
          headers: {
            Authorization: `Bearer ${keycloak.token}`,
            "Content-Type": "application/json",
          },
        }
      );
      const newPnr: string = res.data.pnr;
      if (res.status === HttpStatusCode.Created) {
        setBook(false);
        setOpen(true);
        setPnr(newPnr);
        dispatch(addBooking({ ...values, pnr: newPnr }));
      }
    },
  });

  return (
    <div>
      <Modal
        open={book}
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
    </div>
  );
}
