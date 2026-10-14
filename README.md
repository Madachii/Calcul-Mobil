# Calcul-Mobil

## Titlu si tema proiectului
NewsScroll: Știri verticale interactive prin derulare continuă  
NewsScroll este o miniaplicație Android destinată consumului rapid și eficient al știrilor preluate dintr-un API extern, adaptată stilului modern de navigare mobilă. Aplicația implementează un flux vertical continuu în care fiecare element oferă un rezumat vizual și textual imediat.  

Funcționalități principale:
- Parcurgere și detaliere: Navigare rapidă prin lista de știri și accesarea ecranului cu articolul complet.
- Stocare locală: Salvarea știrilor favorite pe dispozitiv pentru citire offline.
- Funcții inteligente și accesibilitate: Redare vocală (Text-to-Speech) și rezumare automată a articolelor cu ajutorul inteligenței artificiale (LLM).

Astfel, aplicația transformă informarea zilnică într-o experiență intuitivă, accesibilă și adaptată ritmului alert al utilizatorilor moderni.

## Membrii echipei

| Nume | GitHub | Rol |
|------|--------|-----|
| Koma Kristian-Nicolae | [@Madachii](https://github.com/Madachii) | Coordonator / Dezvoltator android |
| Hojda Paul-Andrei | [@ZetPlay10](https://github.com/ZetPlay10) | Dezvoltator android |
| Băbuț Ștefan-Andrei | [@andimm09](https://github.com/andimm09) | Dezvoltator android |

Madachii coordonează echipa. Ceilalți doi nu avem un rol fix, lucrăm la ce e nevoie în momentul respectiv. Împărțim taskurile săptămânal prin issue-urile de pe GitHub.

---

## Obiective propuse

| Must Have | Should Have | Could Have | Won't Have |
| :--- | :--- | :--- | :--- |
| Mai multe ecrane cu navigare între ele | Categorii de știri | Conturi de utilizator cu Firebase Authentication | Backend propriu |
| Feed vertical cu știri, sub formă de listă | Mesaje de încărcare și de eroare, de exemplu când nu e internet | Feed personalizat cu preferințele salvate în cloud, în Firebase | Versiune pentru iOS |
| Deschiderea unei știri din feed duce la ecranul de detaliu | Reîmprospătarea feedului | Rezumarea știrilor cu un LLM | Versiune web / desktop |
| Ecran de detaliu cu articolul complet | Paginare | Căutare vocală cu speech-to-text | Publicare în Google Play |
| Salvarea locală pe telefon a articolelor favorite | Partajare articol prin share sheet | Știri locale în funcție de locație, cu GPS |  |
| Preluare știri dintr-un API extern | Skeleton loaders + stare "gol" | Notificări pentru știri noi |  |
| Ecran "Favorite":  lista articolelor salvate | Mod întunecat | Etichete / arhivă pentru favorite, organizare pe dosare |  |
|  | Citirea știrilor cu text-to-speech | Istoric de citire + statistici simple de lectură |  |
|  |  | Deep links: linkul partajat deschide articolul direct în aplicație |  |
|  |  | Localizare RO/EN prin resurse strings.xml |  |
|  |  | CI cu GitHub Actions: build automat la fiecare push |  |

---
# Analiză și Benchmark API-uri Gratuite de Știri

Acest document centralizează toate API-urile de știri testate și 100% funcționale utilizate pentru agregarea datelor în cadrul proiectului. API-urile care au returnat erori de conexiune, timeout sau restricții de acces au fost excluse în urma testelor.

---



## Tabel API-uri Funcționale

| # | Nume API | Status & Latență | Necesită API Key? | Limite Plan Gratuit | Știri în Română (`RO`)? | Chei Răspuns JSON | Ce returnează concret (Conținut) |
| :- | :--- | :--- | :---: | :--- | :---: | :--- | :--- |
| **1** | **RSS2JSON (Digi24)** |  `200 OK` *(409ms)* | **Nu** | **10.000 req / zi** |  **Da** (Nativ) | `status`, `feed`, `items` | Titlu, rezumat, link direct, autor, data publicării și imagine (`thumbnail` / `enclosure`) din orice flux RSS românesc. |
| **2** | **NewsData.io** |  `200 OK` *(659ms)* | Da | **200 credite / zi** *(~2.000 știri/zi)* |  **Da** (`country=ro`) | `status`, `totalResults`, `results`, `nextPage` | Titlu, descriere curată, imagine articol (`image_url`), iconiță sursă (`source_icon`), categorie și link. |
| **3** | **World News API** |  `200 OK` *(856ms)* | Da | **50–150 pct / zi** |  **Da** (`source-countries=ro`) | `offset`, `number`, `available`, `news` | **Textul complet al articolului (`text`)**, titlu, imagine, autor și **analiză de sentiment** (scor între `-1` și `+1`). |
| **4** | **NewsAPI.org** |  `200 OK` *(446ms)* | Da | **100 req / zi** *(Doar pe `localhost`)* |  **Da** (`country=ro`) | `status`, `totalResults`, `articles` | Titlu, descriere, imagine (`urlToImage`), sursă și conținut trunchiat la 200 de caractere. |
| **5** | **GNews.io** |  `200 OK` *(739ms)* | Da | **100 req / zi** *(max 1 req/sec)* |  **Da** (`lang=ro`, `country=ro`) | `information`, `totalArticles`, `articles` | Știri indexate din Google News România: titlu, descriere scurtă, imagine, link și detalii despre publicație. |
| **6** | **TheNewsAPI.com** |  `200 OK` *(847ms)* | Da | **100 req / zi** *(3 știri/req pe free)* |  **Da** (`locale=ro`) | `meta`, `data` | Titlu, snippet, descriere, URL imagine, categorie și `uuid` unic pentru fiecare știre. |
| **7** | **Mediastack** |  `200 OK` *(524ms)* | Da | **100 req / lună** |  **Da** (`countries=ro`) | `pagination`, `data` | Titlu, descriere scurtă, categorie, limbă, țară și URL către sursa originală. |
| **8** | **Marketaux Finance** |  `200 OK` *(3160ms)* | Da | **100 req / zi** |  **Da** (`countries=ro`) | `meta`, `data` | **Știri financiare, bursă și crypto:** titlu, snippet, entități/companii detectate în text și scor de sentiment financiar. |
| **9** | **Currents API** |  `200 OK` *(387ms)* | Da | **600 req / zi** |  Parțial *(Predominant EN)* | `status`, `news`, `page` | **Cel mai rapid API din test (387ms).** Returnează titlu, descriere, imagine, autor și array de categorii. |
| **10** | **The Guardian API** |  `200 OK` *(533ms)* | Demo Key (`test`) | **500 req / zi** *(12 req/sec)* |  Nu *(Doar EN)* | `response` -> `results` | **Articole integrale (Full Text/HTML)**, titlu, secțiune, thumbnail și metadate complete. |
| **11** | **Dev.to (Forem)** |  `200 OK` *(515ms)* | **Nu** | **Nelimitat** |  Nu *(Tech / EN)* | `Array [...]` direct | Articole IT & Programare: titlu, descriere, cover image, tag-uri, timp de citire (`reading_time_minutes`) și număr de reacții. |
| **12** | **Wikipedia Featured** |  `200 OK` *(493ms)* | **Nu** | **Nelimitat** |  Nu *(Global / EN)* | `tfa`, `mostread`, `image`, `news` | Știrile zilei verificate enciclopedic (`news`), cele mai citite subiecte ale zilei (`mostread`) și imaginea zilei. |
| **13** | **HackerNews API** |  `200 OK` *(558ms)* | **Nu** | **Nelimitat** |  Nu *(Tech / EN)* | `Array [500 IDs]` | Returnează lista celor mai populare 500 ID-uri de știri Tech; detaliile (titlu, URL, scor) se preiau prin `/v0/item/{id}.json`. |
| **14** | **Spaceflight News** |  `200 OK` *(1383ms)* | **Nu** | **Nelimitat** |  Nu *(Space / EN)* | `count`, `next`, `previous`, `results` | Știri despre spațiu și tehnologie (NASA, ESA, SpaceX): titlu, rezumat, poză HD (`image_url`) și paginare automată. |
