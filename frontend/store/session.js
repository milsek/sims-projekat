export const state = () => ({
    username: ''
  })

export const mutations = {
    update(state, value) {
        state.username = value
    }
}