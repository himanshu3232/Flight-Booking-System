import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { IBookingRequest } from "../../components/BookingModal";

export interface IBooking extends IBookingRequest {
  pnr: string;
  checkedIn?: boolean;
}

const initialState: IBooking[] = [];

const bookingsSlice = createSlice({
  name: "bookings",
  initialState,
  reducers: {
    addBooking(state, action: PayloadAction<IBooking>) {
      state.push({
        pnr: action.payload.pnr,
        flightId: action.payload.flightId,
        passengerName: action.payload.passengerName,
        passengerEmail: action.payload.passengerEmail,
        passengerAge: action.payload.passengerAge,
        paidAmount: action.payload.paidAmount,
        checkedIn: false,
      });
    },
    checkInBooking(state, action: PayloadAction<string>) {
      return state.filter((booking) => booking.pnr !== action.payload);
    },
  },
});

export const { addBooking, checkInBooking } = bookingsSlice.actions;
export default bookingsSlice.reducer;
