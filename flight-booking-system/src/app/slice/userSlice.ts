import { createSlice } from "@reduxjs/toolkit";

export interface IUser {}

const initialState: IUser = {};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {},
});

export const {} = userSlice.actions;
export default userSlice.reducer;
