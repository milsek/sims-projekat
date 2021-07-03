export const state = () => ({
    email: '',
    role: ''
  })

export const mutations = {
    update(state) {
      let cookies = getCookies()
      state.email = cookies['mail'] ? cookies['mail'] : ''
      state.role = cookies['role'] ? cookies['role'] : ''
    }
}

var getCookies = function(){
    var pairs = document.cookie.split(";");
    var cookies = {};
    for (var i=0; i<pairs.length; i++){
      var pair = pairs[i].split("=");
      cookies[(pair[0]+'').trim()] = unescape(pair.slice(1).join('='));
    }
    return cookies;
  }