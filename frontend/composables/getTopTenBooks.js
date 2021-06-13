import axios from 'axios';

export default async function getTopTenBooks() {
  axios.get("/api/topten")
    .then(response => {
      return response.data;
    });
}


