<template>
<!-- from-blue-100 to-blue-200  -->
<!-- from-red-50 to-red-100 -->
  <div class="bg-gradient-to-bl from-blue-100 to-blue-300 w-full">
    <div class="pt-20">
      <SearchBar />
    </div>
    <div class="pt-44 pb-20">
      <HomeContent />
    </div>
  </div>
</template>

<script>
import SearchBar from "../components/home/SearchBar"
import HomeContent from "../components/home/HomeContent"
export default {
  components: { SearchBar, HomeContent }
};
// console.log("Fetching");
// import axios from 'axios';
// axios.get("/api/hello")
//   .then(response => console.log(response.data))
</script>

<style>
/* Sample `apply` at-rules with Tailwind CSS
.container {
@apply min-h-screen flex justify-center items-center text-center mx-auto;
}
*/
.container {
  margin: 0 auto;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.title {
  font-family: "Quicksand", "Source Sans Pro", -apple-system, BlinkMacSystemFont,
    "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  display: block;
  font-weight: 300;
  font-size: 100px;
  color: #35495e;
  letter-spacing: 1px;
}

.subtitle {
  font-weight: 300;
  font-size: 42px;
  color: #526488;
  word-spacing: 5px;
  padding-bottom: 15px;
}

.links {
  padding-top: 15px;
}
</style>
