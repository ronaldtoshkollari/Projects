/* This Method is used to show nav Bar on Mobiles */
const navBurgers = document.querySelector(".nav-burger");
var counter = 1;

//Add event on click
navBurgers.addEventListener("click", function (e) {
  const navMenu = document.querySelector(".nav-menu");
  const width = window.innerWidth;
  if (width >= 1024) navMenu.style.left = "0%";

  if (counter % 2 == 1 && width < 1024) {
    navMenu.style.left = "0%";
  } else if (counter % 2 == 0 && width < 1024) {
    navMenu.style.left = "-50%";
  }
  counter++;
});

window.onresize = function (e) {
  const navMenu = document.querySelector(".nav-menu");
  const width = window.innerWidth;
  counter = 0;

  if (width >= 1024) navMenu.style.left = "0%";
};
