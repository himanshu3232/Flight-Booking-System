import { configureStore } from '@reduxjs/toolkit'
import bookingReducer from './slice/bookingsSlice'

export const store = configureStore({
  reducer: {
    bookings : bookingReducer
  }
})