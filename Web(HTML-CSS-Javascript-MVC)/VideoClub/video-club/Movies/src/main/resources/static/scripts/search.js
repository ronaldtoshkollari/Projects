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

  //getting the movie from API when user request it
  const search = document.querySelector(".search-area");

  //changing placeholder on mobile version
  if (window.innerWidth <= 1024) {
    search.setAttribute("placeholder", "Αναζήτηση");
  }

  //API url
  var url = "http://www.omdbapi.com/?apikey=c282adf7&r=json&plot=full&t=";
  var movie = "";
  var movieSearch = null;
  var movieObj = null;

  //User wants more info
  const modal = this.document.querySelector(".modal");
  const btn = this.document.querySelector("#result-more");
  const bookmarks = this.document.querySelector("#result-bookmarks");
  var span = document.querySelector(".close");

  btn.addEventListener("click", function () {
    modal.style.display = "block";
  });

  span.addEventListener("click", function () {
    modal.style.display = "none";
  });

  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };

  //eventListener search stats when user has not type anything for 1.5 sec
  search.addEventListener("keyup", function () {
    clearTimeout(movieSearch);
    movieSearch = setTimeout(getMovie, 1500);
  });

  //this function is used to get JSON Object
  async function getMovie() {
    //this method is used to update our page
    movieObj = await getData();

    if (movieObj === null) {
      return;
    }

    if (movieObj.Response == "True") {
      //adding poster
      var poster = document.querySelector("#result_poster");
      var title = document.querySelector("#result-title");
      var plot = document.querySelector("#result-plot");
      var language = document.querySelector("#result-language");
      var imgClass = document.querySelector(".result-item-image");
      const message = document.querySelector("#add_message");
      message.innerHTML = "";
      imgClass.style.display = "block";
      document.querySelector("#result-more").style.display = "inline-block";
      document.querySelector("#result-bookmarks").style.display = "block";
      plot.style.display = "block";
      language.style.display = "block";

      poster.setAttribute("src", movieObj.Poster);
      poster.setAttribute("alt", movieObj.Title + "_image");
      title.innerHTML = movieObj.Title;
      title.style.color = "white";
      plot.innerHTML = movieObj.Plot;
      language.innerHTML = movieObj.Language;

      //More Description
      var moreTitle = document.querySelector("#movie-title");
      var morePoster = document.querySelector("#movie-poster");
      var moreYear = document.querySelector("#movie-year");
      var moreRated = document.querySelector("#movie-rated");
      var moreReleased = document.querySelector("#movie-released");
      var moreRuntime = document.querySelector("#movie-runtime");
      var moreGenre = document.querySelector("#movie-genre");
      var moreDirector = document.querySelector("#movie-director");
      var moreWriter = document.querySelector("#movie-writer");
      var moreActors = document.querySelector("#movie-actors");
      var morePlot = document.querySelector("#movie-plot");
      var moreLanguage = document.querySelector("#movie-language");
      var moreAwards = document.querySelector("#movie-awards");

      //title
      moreTitle.innerHTML = movieObj.Title;

      //poster
      morePoster.setAttribute("src", movieObj.Poster);
      morePoster.setAttribute("alt", movieObj.Title + "_image");

      //year
      moreYear.innerHTML = "Year: " + movieObj.Year;
      //rated
      moreRated.innerHTML = "Rated: " + movieObj.Rated;
      //released
      moreReleased.innerHTML = "Released: " + movieObj.Released;
      //runtime
      moreRuntime.innerHTML = "Runtime: " + movieObj.Runtime;
      //Genre
      moreGenre.innerHTML = "Genre: " + movieObj.Genre;
      //Director
      moreDirector.innerHTML = "Director: " + movieObj.Director;
      //Writer
      moreWriter.innerHTML = "Writer: " + movieObj.Writer;
      //Actors
      moreActors.innerHTML = "Actors: " + movieObj.Actors;
      //Plot
      morePlot.innerHTML = "Plot: " + movieObj.Plot;
      //Language
      moreLanguage.innerHTML = "Language: " + movieObj.Language;
      //Awards
      moreAwards.innerHTML = "Awards: " + movieObj.Awards;

      //Responsive
      if (window.innerWidth <= 1024) {
        var section_a = document.querySelector(".section-a-search");
        var modal = document.querySelector(".modal");
        var modalContent = document.querySelector(".modal-content");

        section_a.style.height = "100%";
        modal.style.position = "initial";
        modalContent.style.position = "initial";
      }
    } else {
      var title = document.querySelector("#result-title");
      var plot = document.querySelector("#result-plot");
      var language = document.querySelector("#result-language");
      var imgClass = document.querySelector(".result-item-image");
      imgClass.style.display = "none";
      document.querySelector("#result-more").style.display = "none";
      document.querySelector("#result-bookmarks").style.display = "none";

      title.innerHTML = "Could Not Find the movie.";
      title.style.color = "yellow";
      plot.style.display = "none";
      language.style.display = "none";

      var section = document.querySelector(".section-a-search");
      section.style.height = "150vh";
    }
  }

  async function getData() {
    movie = document.forms["seaction-a-search-form"]["search-area"].value;

    if (movie.trim() == "") {
      return null;
    }

    var proxyUrl = "https://cors-anywhere.herokuapp.com/";
    var search = proxyUrl + url + movie;
    const response = fetch(search);
    return (await response).json();
  }
};

function doNothing() {
  console.log("User pressed enter");
}

function addMovie() {
  const url = "http://localhost:8080/add";
  var title = document.querySelector("#result-title").innerHTML;
  const message = document.querySelector("#add_message");
  message.innerHTML = "Η ταινία αποθηκευτηκε στα αγαπημένα";

  const addRequest = new XMLHttpRequest();
  addRequest.open("POST", url);
  addRequest.setRequestHeader("Content-type", "plain/text");
  addRequest.send(title);
}
