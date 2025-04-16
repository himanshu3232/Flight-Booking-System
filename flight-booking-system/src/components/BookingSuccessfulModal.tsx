import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import React from "react";

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

export default function BookingSuccessfulModal({
  pnr,
  open,
  setOpen,
}: {
  pnr: string;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  open: boolean;
}) {
  const handleClose = () => setOpen(false);

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
            Flight Booked Successfully!
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            Your PNR details are: {pnr}
          </Typography>
        </Box>
      </Modal>
    </div>
  );
}
