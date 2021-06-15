// not used lmao

import axios from 'axios';

const getTopReads = () => {
  axios.get("/api/topreads")
    .then(response => {
      return response.data;
    });
}

export default getTopReads