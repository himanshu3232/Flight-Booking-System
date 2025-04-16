import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import axios from "axios";
import { useDispatch } from "react-redux";
import { checkInBooking } from "../app/slice/bookingsSlice";

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

export default function BasicModal({
  open,
  setOpen,
  pnr,
}: {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  pnr: string;
}) {
  const handleClose = () => {
    setOpen(false);
  };

  const dispatch = useDispatch();

  const handleCancel = async () => {
    const response = await axios.delete(
      "http://localhost:8072/booking-service/booking",
      {
        params: { pnr },
      }
    );

    console.log(response);

    if (response.status === 200) {
      setOpen(false);
      alert("Booking Cancelled Successfully");
      dispatch(checkInBooking(pnr));
    }
  };

  return (
    <>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Are You Sure You Want to Cancel ?
          </Typography>
          <div className="d-flex">
            <Button onClick={handleCancel}>Confirm</Button>
            <Button onClick={handleClose}>Revert</Button>
          </div>
        </Box>
      </Modal>
    </>
  );
}
