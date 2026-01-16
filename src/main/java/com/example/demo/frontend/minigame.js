let filmes = [];
let perguntaAtual = 0;
let respostas = [];

const perguntas = [
  "1 - Qual seu filme favorito?",
  "2 - Qual o filme mais famoso?",
  "3 - Qual o filme mais recente?"
];

// Busca todos os filmes do seu backend
async function carregarFilmes() {
  try {
    const res = await fetch("http://localhost:8080/FilmesPortifolio");
    filmes = await res.json();

    if (filmes.length < 2) {
      alert("Cadastre pelo menos 2 filmes para jogar o Mini Game.");
      return;
    }

    mostrarPergunta();
  } catch (e) {
    console.error(e);
    alert("Erro ao conectar com o servidor.");
  }
}

function mostrarPergunta() {
  if (perguntaAtual >= perguntas.length) {
    finalizarQuiz();
    return;
  }

  document.getElementById("pergunta").innerText = perguntas[perguntaAtual];

  const opcoesDiv = document.getElementById("opcoes");
  opcoesDiv.innerHTML = "";

  // sorteia 2 filmes aleatórios
  const filmesSorteados = sortearFilmes(2);

  filmesSorteados.forEach(filme => {
    const btn = document.createElement("button");
    btn.innerText = filme.nome;
    btn.onclick = () => responder(filme);
    opcoesDiv.appendChild(btn);
  });
}

function responder(filme) {
  respostas.push({
    pergunta: perguntas[perguntaAtual],
    resposta: filme.nome
  });

  perguntaAtual++;
  mostrarPergunta();
}

function sortearFilmes(qtd) {
  const copia = [...filmes];
  copia.sort(() => Math.random() - 0.5);
  return copia.slice(0, qtd);
}

function finalizarQuiz() {
  const card = document.getElementById("quiz-card");
  card.innerHTML = `
    <h2>🎉 Quiz Finalizado!</h2>
    ${respostas.map(r => `
      <p><strong>${r.pergunta}</strong><br>Resposta: ${r.resposta}</p>
    `).join("")}
    <br><br>
    <button onclick="voltar()">⬅ Voltar ao Catálogo</button>
  `;
}

function voltar() {
  window.location.href = "index.html";
}

// inicia o mini game
carregarFilmes();
