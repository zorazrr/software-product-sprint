// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random fun fact about myself to the page.
 */
function addRandomFact() {
    const facts = [
        "I love anything matcha flavored.",
        "My name has the meaning of sunrise and dawn.",
        "I like Marvel Movies.",
        "I am trying to learn ice skating.",
        "My favorite anime is The Promised Neverland.",
    ];

    // Pick a random fun fact.
    const fact = facts[Math.floor(Math.random() * facts.length)];

    // Add it to the page.
    const factContainer = document.getElementById("fact-container");
    factContainer.innerText = fact;
}

/**
 * Gets messages as json from hello endpoint. Randomly select one.
 */
async function getMessage() {
    const res = await fetch("/hello");
    const message = await res.json();
    const oneMessage = message[Math.floor(Math.random() * message.length)];
    document.getElementById("message-container").innerText = oneMessage;
}

/**
 * Gets contact-me form messages then render
 */
async function loadMessages() {
    const board = document.getElementById('message-board');
    fetch("/list-messages").then(res => res.json()).then((messages) => {
        messages.forEach((message) => {
            board.appendChild(createCard(message));
        })
    });
}

/**
 * Renders messages as html elements
 * Would be so much easier with React T-T
 */
function createCard(message) {
    const block = document.createElement('col');
    block.classList.add('card');
    block.id = 'card';
    const date = new Date(message.timestamp);
    block.innerHTML =
        `<div class='card-header'>`
        + date.toLocaleDateString()
        + `</div>`
        + `<div class = 'card-body'>`
        + message.message
        + `<br/>`
        + `<small class="text-muted"> this person likes `
        + message.drink
        + `</small>`
        + `</div>`;
    return block;
}

/**
 * Dark Mode Toggle Control
 */
document.getElementById("dark-mode-toggle").onclick = function (e) {
    darkmode.toggleDarkMode();
};
