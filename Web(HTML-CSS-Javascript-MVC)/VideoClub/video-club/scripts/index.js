window.onload = function () {
  //about carousel
  let carousel_items = this.document.getElementsByClassName("carousel-item");
  for (let i = 0; i < 3; i += 1) {
    this.setInterval(function () {
      carousel_items[i].classList.remove("hidden");
      for (let j = 0; j < 3; j += 1) {
        if (j != i) {
          carousel_items[j].classList.add("hidden");
        }
      }
    }, 3500);
  }

  //about carousel
  var slideIndex = 0;
  showSlides();

  function showSlides() {
    var i;
    var slides = document.getElementsByClassName("carousel-item");
    for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slides.length) {
      slideIndex = 1;
    }
    slides[slideIndex - 1].style.display = "block";
    setTimeout(showSlides, 3500); // Change image every 3.5 seconds
  }

  //about login - sign up session
  var menus = document.getElementsByClassName("login-menu");
  var menuIsModified = false;

  var new_account_h4 = document.getElementsByTagName("h4")[0];
  new_account_h4.onclick = function () {
    if (!menuIsModified) {
      menus[0].style.display = "none";
      menus[1].style.display = "block";
      menuIsModified = true;
    } else {
      menus[0].style.display = "block";
      menus[1].style.display = "none";
      menuIsModified = false;
    }
  };

  var btn_back = document.getElementsByClassName("btn-back")[0];
  btn_back.onclick = function () {
    menus[0].style.display = "block";
    menus[1].style.display = "none";
    menuIsModified = false;
  };

  // var login_btn = document.getElementById("login-btn");
  // login_btn.onclick = function () {
  //   if (document.getElementById("username-field").value == "") {
  //     document.getElementsByClassName("field-label")[0].style.display = "block";
  //     return false;
  //   } else {
  //     document.getElementsByClassName("field-label")[0].style.display = "none";
  //   }

  //   if (document.getElementById("password-field").value == "") {
  //     document.getElementsByClassName("field-label")[1].style.display = "block";
  //     return false;
  //   } else {
  //     document.getElementsByClassName("field-label")[1].style.display = "none";
  //   }
  // };

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

  burgerMenu.addEventListener("click", function (e) {
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
