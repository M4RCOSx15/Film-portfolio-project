let idFilmeAberto = null;
const grid = document.getElementById("grid-filmes");

async function carregarFilmes() {
    if (!grid) return; 
    try {
        const res = await fetch("http://localhost:8080/FilmesPortifolio");
        const filmes = await res.json();
        grid.innerHTML = "";
        if (filmes.length === 0) {
            grid.innerHTML = "<p>Nenhum filme cadastrado.</p>";
            return;
        }
        filmes.forEach(filme => {
            const poster = filme.urlFilme || filme.url_imagem || filme.posterUrl || "https://via.placeholder.com/300x450?text=Sem+Poster";
            grid.innerHTML += `
                <div class="movie-card" onclick="abrirFilme(${filme.id})">
                    <img src="${poster}" class="movie-poster-img">
                    <div class="movie-info">
                        <h3>${filme.nome}</h3>
                        <span>${filme.genero || "Geral"} • ${filme.ano}</span>
                    </div>
                </div>`;
        });
    } catch (e) { console.error(e); }
}

async function adicionarFilme() {
    const inputBusca = document.getElementById("filmeBusca");
    if (!inputBusca) return;
    const nome = inputBusca.value.trim();
    if (!nome) { alert("Digite o nome!"); return; }
    try {
        await fetch(`http://localhost:8080/FilmesPortifolio/buscar?nome=${encodeURIComponent(nome)}`, { method: "POST" });
        inputBusca.value = ""; 
        carregarFilmes();
    } catch (error) { alert("Erro ao adicionar"); }
}

async function abrirFilme(id) {
    try {
        const res = await fetch(`http://localhost:8080/FilmesPortifolio/${id}`);
        const filme = await res.json();
        idFilmeAberto = id; 
        document.getElementById("modal-title").innerText = filme.nome;
        document.getElementById("modal-description").innerText = filme.descricao || "Sem descrição.";
        document.getElementById("modal-poster").src = filme.urlFilme || filme.url_imagem || filme.posterUrl;
        document.getElementById("movie-modal").style.display = "flex";
    } catch (e) { console.error(e); }
}

async function deletarFilme() {
    if (!idFilmeAberto) return;
    if (confirm("Deletar filme?")) {
        await fetch(`http://localhost:8080/FilmesPortifolio/${idFilmeAberto}`, { method: "DELETE" });
        fecharFilme(); carregarFilmes();
    }
}

function fecharFilme() {
    const modal = document.getElementById("movie-modal");
    if (modal) modal.style.display = "none";
}

function irParaMiniGame() { window.location.href = "minigame.html"; }

// --- LÓGICA DO ARDUINO ---
function iniciarMonitoramentoArduino() {
    setInterval(async () => {
        try {
            const resposta = await fetch("http://localhost:8080/arduino/status");
            const comando = (await resposta.text()).trim();

            if (comando === "OPCAO_A") {
                console.log("Comando Arduino: A");
                clicarBotaoQuiz(0);
            } else if (comando === "OPCAO_B") {
                console.log("Comando Arduino: B");
                clicarBotaoQuiz(1);
            }
        } catch (erro) { /* Java desligado */ }
    }, 500);
}

function clicarBotaoQuiz(index) {
    // Busca os botões dentro da div "opcoes" do mini game
    const containerOpcoes = document.getElementById("opcoes");
    if (!containerOpcoes) return;

    const botoes = containerOpcoes.querySelectorAll('button');
    
    if (botoes[index]) {
        console.log("Clicando no botão: " + botoes[index].innerText);
        botoes[index].click(); 
        
        // Efeito visual de confirmação
        const originalBg = botoes[index].style.background;
        botoes[index].style.background = "white";
        botoes[index].style.color = "red";
        setTimeout(() => {
            botoes[index].style.background = originalBg;
            botoes[index].style.color = "white";
        }, 200);
    }
}

window.onload = () => {
    if (grid) carregarFilmes();
    iniciarMonitoramentoArduino();
};