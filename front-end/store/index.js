export const state = () => ({
    cuisineData: null
});

export const mutations = {
    SET_CUISINE_DATA: (state, data) => {
        state.cuisineData = data;
    }
};

export const actions = {
    setCuisines({ commit, state }, newValue) {
        commit("SET_CUISINE_DATA", newValue);
        return state.cuisineData;
    }
};

export const getters = {
    cuisines: (state) => {
        return state.cuisineData;
    }
};