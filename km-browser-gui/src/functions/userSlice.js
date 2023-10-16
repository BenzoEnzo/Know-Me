import { createSlice } from '@reduxjs/toolkit';

export const userSlice = createSlice({
    name: 'user',
    initialState: {
        id: null,
    },
    reducers: {
        setId: (state, action) => {
            state.id = action.payload;
        },
    },
});

export const { setId } = userSlice.actions;
export const selectUserId = state => state.user.id;
export default userSlice.reducer;