import { createStore } from 'vuex'

export default createStore({
    state: {
        //�洢token
        Authorization: '',
        priviledage: ''
    },
    mutations: {
        // �޸�token������token����localStorage
        changeLogin (state,user) {
            state.Authorization = user.Authorization;
            localStorage.setItem('Authorization', user.Authorization);
        }
    },
    actions: {
    },
    modules: {
    }
})
