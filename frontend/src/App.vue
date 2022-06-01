<template>
  <v-app>
    <nav-bar></nav-bar>
    <v-main>
      <router-view />
    </v-main>
    <footer-bar></footer-bar>
  </v-app>
</template>

<script>
document.addEventListener("touchstart", () => {}, { passive: true });
document.addEventListener("mousewheel", () => {}, { passive: true });
import NavBar from "@/components/layout/NavBar.vue";
import FooterBar from "./components/layout/FooterBar.vue";
export default {
  name: "App",
  components: {
    NavBar,
    FooterBar,
  },
};
</script>

<style>
body {
  margin: 0;
}
</style>
