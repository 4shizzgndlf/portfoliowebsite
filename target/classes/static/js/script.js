function updateClock() {
    const clock = document.getElementById("clock");

    if (!clock) return;

    const now = new Date();

    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');

    clock.textContent = `${hours}:${minutes}:${seconds}`;
}

setInterval(updateClock, 1000);
updateClock();

document.querySelectorAll(".selectorImage").forEach(img => {
    img.addEventListener("click", function () {

        document.querySelectorAll(".selectorImage")
            .forEach(i => i.classList.remove("active"));

        this.classList.add("active");

        document.getElementById("portraitImage").src = this.src;
    });
});

// SCROLL ANIMATION
const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.classList.add("show");
        }
    });
}, {
    threshold: 0.2
});

// observe fade-in elements
document.querySelectorAll(".fade-in, .stagger").forEach(el => {
    observer.observe(el);
});

if (window.location.pathname === "/") {

    window.addEventListener("scroll", () => {
        const home = document.querySelector(".homeContainer");
        const about = document.querySelector(".aboutSection");
        const skills = document.querySelector(".skillsSection");
        const contact = document.querySelector(".contactSection");

        const navHome = document.querySelector("#navHome");
        const navAbout = document.querySelector("#navAbout");
        const navSkills = document.querySelector("#navSkills");
        const navContact = document.querySelector("#navContact");

        [navHome, navAbout, navSkills, navContact].forEach(item =>
            item?.classList.remove("active")
        );

        const scrollPos = window.scrollY + window.innerHeight / 2;

        if (scrollPos >= contact.offsetTop) {
            navContact?.classList.add("active");
        } else if (scrollPos >= skills.offsetTop) {
            navSkills?.classList.add("active");
        } else if (scrollPos >= about.offsetTop) {
            navAbout?.classList.add("active");
        } else {
            navHome?.classList.add("active");
        }
    });

}
const currentPath = window.location.pathname;

if (currentPath === "/register") {
    document.querySelector("#navRegister")?.classList.add("active");
    document.querySelector("#navHome")?.classList.remove("active");
}

if (currentPath === "/login") {
    document.querySelector("#itemLogInBtn")?.classList.add("active");
    document.querySelector("#navHome").classList.remove("active");
}

console.log(window.location.pathname);




