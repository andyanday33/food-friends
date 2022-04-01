export const state = () => ({
    userData: null
})

export const mutations = {
    updateUserData(data) {
        state.userData = data;
        console.log(state.userData);
    },
    removeUserData() {
        state.userData = null;
    }
}