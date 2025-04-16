import { configureStore } from '@reduxjs/toolkit'
import bookingReducer from './slice/bookingsSlice'
import userReducer from './slice/userSlice'

export const store = configureStore({
  reducer: {
    bookings : bookingReducer,
    user : userReducer
  }
})