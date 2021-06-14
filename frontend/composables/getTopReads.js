// not used lmao

import axios from 'axios';

const getTopReads = () => {
  axios.get("/api/topreads")
    .then(response => {
      console.log(response.data)
      return response.data;
    });
}

export default getTopReads