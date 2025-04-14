import { createSlice, PayloadAction } from '@reduxjs/toolkit'
import { IBookingRequest} from '../../components/BookingModal';

export interface IBooking extends IBookingRequest {
  id ?: number,
  pnr : string,
  checkedIn ?: boolean
}

const initialState: IBooking[] = []

const bookingsSlice = createSlice({
  name: 'bookings',
  initialState,
  reducers: {
    addBooking(state, action: PayloadAction<IBooking>) {
      state.push({
        id : state.length + 1,
        pnr : action.payload.pnr,
        flightId: action.payload.flightId,
        passengerName: action.payload.passengerName,
        passengerEmail: action.payload.passengerEmail,
        passengerAge: action.payload.passengerAge,
        paidAmount: action.payload.paidAmount,
        checkedIn: false,
      })
    },
    checkInBooking(state, action: PayloadAction<number>) {
      const booking = state.find((booking) => booking.id === action.payload);
      if (booking) {
        booking.checkedIn = true
      }
    },
  },
})

export const { addBooking, checkInBooking } = bookingsSlice.actions
export default bookingsSlice.reducer
