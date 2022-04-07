export const state = () => ({
    cuisineData: null,
    isAdmin: false,
    user: {}, //There is a bug in nuxt local auth.user which I do not have the knowledge to resolve, 
    //we will store user object here instead
});

export const mutations = {
    SET_CUISINE_DATA: (state, data) => {
        state.cuisineData = data;
    },
    SET_IS_ADMIN: (state, data) => {
        state.isAdmin = data;
    },
    SET_USER: (state, data) => {
        state.user = data;
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
    },
    setUser({ commit, state }, newValue) {
        commit("SET_USER", newValue);
        return state.user;
    }
};

export const getters = {
    cuisines: (state) => {
        return state.cuisineData;
    },
    isAdmin: (state) => {
        return state.isAdmin;
    },
    user: (state) => {
        return state.user;
    }
};