window.onload = function () {
  //navigation bar
  const nav_back = document.querySelector(".nav-back");

  nav_back.addEventListener("click", function () {
    const navBar = document.querySelector(".navbar");
    const navLinks = document.querySelector(".nav-links");
    const burgerMenu = document.querySelector(".burger-menu");

    navBar.style.backgroundColor = "rgba(84, 197, 144, 1)";
    navBar.style.position = "fixed";
    navBar.style.height = "70px";
    navBar.style.width = "100%";
    nav_back.style.display = "none";
    navLinks.style.display = "none";
    burgerMenu.style.alignSelf = "start";
    burgerMenu.style.display = "block";
  });

  const burgerMenu = document.querySelector(".burger-menu");

  burgerMenu.addEventListener("click", function () {
    const navBar = document.querySelector(".navbar");
    const nav_back = document.querySelector(".nav-back");
    const navLinks = document.querySelector(".nav-links");
    const burgerMenu = document.querySelector(".burger-menu");

    navBar.style.backgroundColor = "rgba(84, 197, 144, 0.9)";
    navBar.style.position = "fixed";
    navBar.style.height = "100%";
    navBar.style.width = "45%";
    burgerMenu.style.display = "none";
    nav_back.style.display = "block";
    navLinks.style.display = "flex";
  });
};
