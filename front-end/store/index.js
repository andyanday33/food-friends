export const state = () => ({
    cuisineData: null,
    isAdmin: false
});

export const mutations = {
    SET_CUISINE_DATA: (state, data) => {
        state.cuisineData = data;
    },
    SET_IS_ADMIN: (state, data) => {
        state.isAdmin = data;
    }
};

export const actions = {
    setCuisines({ commit, state }, newValue) {
        commit("SET_CUISINE_DATA", newValue);
        return state.cuisineData;
    },
    setIsAdmin({ commit, state }, newValue) {
        commit("SET_IS_ADMIN", newValue);
        return state.isAdmin;
    }
};

export const getters = {
    cuisines: (state) => {
        return state.cuisineData;
    },
    isAdmin: (state) => {
        return state.isAdmin;
    }
};