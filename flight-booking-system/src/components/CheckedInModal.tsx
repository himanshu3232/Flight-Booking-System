import * as React from "react";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
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

export default function CheckedInModal({
  id,
  seatNumber,
  setCheckedIn,
  checkedIn,
  pnr,
}: {
  id: number;
  seatNumber: string;
  setCheckedIn: React.Dispatch<React.SetStateAction<boolean>>;
  checkedIn: boolean;
  pnr: string;
}) {
  const dispatch = useDispatch();

  const handleClose = () => {
    setCheckedIn(false);
    if (id) {
      dispatch(checkInBooking(pnr));
    }
  };

  return (
    <div>
      <Modal
        open={checkedIn}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Check-In Successful!
          </Typography>
          <br />
          <p>CheckIn Id: {id}</p>
          <p>Seat Number: {seatNumber}</p>
        </Box>
      </Modal>
    </div>
  );
}
