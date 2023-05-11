import { createStore } from 'vuex'

export default createStore({
    state: {
        //存储token
        Authorization: '',
        priviledage: ''
    },
    mutations: {
        // 修改token，并将token存入localStorage
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
