export const state = () => ({
    searchValue: ''
  })

export const mutations = {
    update(state, value) {
        state.searchValue = value
    }
}