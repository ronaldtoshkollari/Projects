window.onload = function () {
  //navigation bar
  const nav_back = document.querySelector(".nav-back");

  //our api link

  var url = "http://www.omdbapi.com/?apikey=c282adf7&r=json&plot=short&t=";

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

  const userTitles = getMovieTitles();

  userTitles
    .then((array) => {
      for (let i = 0; i < array.length; i++) {
        this.console.log(array[i]);

        //Here we must get the info that we need from Movie API
        var movieObj = getMovieFromApi(url + array[i]);
        movieObj.then((movieJson) => {
          const movieGrid = this.document.querySelector(".bookmarks-grid");

          //Movie Item
          var movieDiv = this.document.createElement("div");
          movieDiv.setAttribute("class", "grid-item");
          movieGrid.appendChild(movieDiv);

          //Image
          var movieImg = this.document.createElement("img");
          movieImg.setAttribute("src", movieJson.Poster);
          movieImg.setAttribute("alt", movieJson.Title + "_image");
          movieImg.setAttribute("class", "bookmark-image");
          movieImg.setAttribute("width", "200");
          movieImg.setAttribute("height", "250");

          //Paragraph Title
          var movieParagraphTitle = this.document.createElement("p");
          movieParagraphTitle.setAttribute("class", "bookmark-title");
          movieParagraphTitle.textContent = movieJson.Title;

          //Paragraph Plot
          var movieParagraphPlot = this.document.createElement("p");
          movieParagraphPlot.setAttribute("class", "bookmark-plot");
          movieParagraphPlot.textContent = movieJson.Plot;

          //Div with buttons
          var buttonsDiv = this.document.createElement("div");
          buttonsDiv.setAttribute("class", "bookmark-buttons");

          //More button
          var moreBtn = this.document.createElement("Button");
          moreBtn.setAttribute("class", "bookmark-more");
          moreBtn.setAttribute("onclick", "showModal(this)");
          moreBtn.textContent = "Περισσότερα";

          //Delete button
          var deleteBtn = this.document.createElement("Button");
          deleteBtn.setAttribute("class", "bookmark-remove");
          deleteBtn.textContent = "Αφαίρεση";
          deleteBtn.setAttribute("onclick", "removeMovie(this)");

          //appends
          movieDiv.appendChild(movieImg);
          movieDiv.appendChild(movieParagraphTitle);
          movieDiv.appendChild(movieParagraphPlot);
          movieDiv.appendChild(buttonsDiv);
          buttonsDiv.appendChild(moreBtn);
          buttonsDiv.appendChild(deleteBtn);
        });
      }
    })
    .catch((message) => {
      this.console.log(message);
    });
};

async function getMovieTitles() {
  const url = "http://localhost:8080/bookmarkedMovies";
  const request = new XMLHttpRequest();
  request.open("GET", url, false);
  request.send(null);
  var titles = request.responseText;

  titles = titles.replace(/"/g, "");
  titles = titles.replace(/]/g, "");
  titles = titles.replace("[", "");
  return titles.split(",");
}

async function getMovieFromApi(search) {
  this.console.log(search);
  var proxyUrl = "https://cors-anywhere.herokuapp.com/";
  const response = fetch(proxyUrl + search);
  return (await response).json();
}

function removeMovie(node) {
  grandfather = node.parentNode.parentNode.remove();
}

function showModal(node) {
  //Modal Elements
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

  var modalTtile = node.parentNode.parentNode.querySelector(".bookmark-title")
    .innerText;

  var url = "http://www.omdbapi.com/?apikey=c282adf7&r=json&plot=short&t=";
  var movieObj = getMovieFromApi(url + modalTtile);

  movieObj.then((movieJSON) => {
    //title
    moreTitle.innerHTML = movieJSON.Title;

    //poster
    morePoster.setAttribute("src", movieJSON.Poster);
    morePoster.setAttribute("alt", movieJSON.Title + "_image");

    //year
    moreYear.innerHTML = "Year: " + movieJSON.Year;
    //rated
    moreRated.innerHTML = "Rated: " + movieJSON.Rated;
    //released
    moreReleased.innerHTML = "Released: " + movieJSON.Released;
    //runtime
    moreRuntime.innerHTML = "Runtime: " + movieJSON.Runtime;
    //Genre
    moreGenre.innerHTML = "Genre: " + movieJSON.Genre;
    //Director
    moreDirector.innerHTML = "Director: " + movieJSON.Director;
    //Writer
    moreWriter.innerHTML = "Writer: " + movieJSON.Writer;
    //Actors
    moreActors.innerHTML = "Actors: " + movieJSON.Actors;
    //Plot
    morePlot.innerHTML = "Plot: " + movieJSON.Plot;
    //Language
    moreLanguage.innerHTML = "Language: " + movieJSON.Language;
    //Awards
    moreAwards.innerHTML = "Awards: " + movieJSON.Awards;
  });

  //User wants more info
  const modal = this.document.querySelector(".modal");
  var span = document.querySelector(".close");
  var modalContent = document.querySelector(".modal-content");
  modal.style.display = "block";
  modalContent.style.height = "100%";
  modalContent.style.overflowY = "scroll";

  span.addEventListener("click", function () {
    modal.style.display = "none";
  });
}
