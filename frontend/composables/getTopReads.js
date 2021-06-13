import axios from 'axios';

export default async function getTopReads() {
  axios.get("/api/topreads")
    .then(response => {
      console.log(response.data)
      return response.data;
    });
}
