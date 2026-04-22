function updateClock() {
    const now = new Date();

    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');

    document.getElementById("clock").textContent =
        `${hours}:${minutes}:${seconds}`;
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