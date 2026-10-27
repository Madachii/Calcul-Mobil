package ro.calculmobil.newsscroll.data

// Date statice pentru Saptamana 6. Cand vine API-ul (#24) se inlocuieste cu o implementare
// care preia stirile din retea, restul aplicatiei folosind tot NewsRepository.
object FakeNewsRepository : NewsRepository {

    private const val HOUR = 60 * 60 * 1000L

    // 20 oct 2026, 00:00 UTC
    private const val BASE_TIME = 1_792_454_400_000L

    private val articles = listOf(
        Article(
            id = "1",
            title = "Primăria Capitalei anunță noi linii de autobuz electric",
            description = "Trei trasee noi vor lega cartierele periferice de centru începând de luna viitoare.",
            content = "Primăria Capitalei a anunțat introducerea a trei noi linii de autobuz electric, " +
                "care vor lega cartierele din periferie de zona centrală. Autobuzele vor circula la " +
                "fiecare 10 minute în orele de vârf și la 20 de minute în restul zilei.\n\n" +
                "Reprezentanții societății de transport spun că noile trasee au fost alese în urma " +
                "unui sondaj făcut în rândul călătorilor. Abonamentele existente vor fi valabile și " +
                "pe noile linii, fără costuri suplimentare.",
            imageUrl = "https://picsum.photos/seed/newsscroll1/800/450",
            source = "Știri Locale",
            publishedAt = BASE_TIME + 9 * HOUR,
            url = "https://example.com/stiri/autobuze-electrice"
        ),
        Article(
            id = "2",
            title = "Echipa națională de handbal s-a calificat în sferturi",
            description = "Victorie strânsă în ultimul meci din grupă, decisă în ultimele secunde.",
            content = "Echipa națională de handbal a obținut calificarea în sferturile de finală după " +
                "o victorie la limită în ultimul meci din grupă. Golul decisiv a fost marcat cu " +
                "doar câteva secunde înainte de final.\n\n" +
                "Antrenorul a declarat după meci că echipa a arătat caracter și că obiectivul rămâne " +
                "o medalie. Următorul adversar va fi stabilit după meciurile de mâine.",
            imageUrl = "https://picsum.photos/seed/newsscroll2/800/450",
            source = "Sport Azi",
            publishedAt = BASE_TIME + 2 * 24 * HOUR + 21 * HOUR,
            url = "https://example.com/stiri/handbal-sferturi"
        ),
        Article(
            id = "3",
            title = "Studenții pot aplica pentru burse de mobilitate până la final de noiembrie",
            description = "Universitățile au publicat calendarul pentru semestrul de primăvară.",
            content = "Studenții interesați de o mobilitate în străinătate în semestrul de primăvară " +
                "pot depune dosarele până la sfârșitul lunii noiembrie. Selecția se face pe baza " +
                "mediei, a unei scrisori de motivație și a unui test de limbă.\n\n" +
                "Universitățile recomandă studenților să verifice din timp recunoașterea creditelor " +
                "la întoarcere, împreună cu coordonatorul de program.",
            imageUrl = null,
            source = "Educație Plus",
            publishedAt = BASE_TIME + 1 * 24 * HOUR + 11 * HOUR,
            url = "https://example.com/stiri/burse-mobilitate"
        ),
        Article(
            id = "4",
            title = "Meteorologii anunță primul val de frig al toamnei",
            description = "Temperaturile scad cu până la 10 grade în următoarele zile.",
            content = "Meteorologii avertizează că, de la jumătatea săptămânii, temperaturile vor " +
                "scădea cu până la 10 grade față de media perioadei. În zonele de munte sunt " +
                "așteptate primele ninsori.\n\n" +
                "Șoferii sunt sfătuiți să își pregătească mașinile pentru sezonul rece, iar " +
                "autoritățile locale au anunțat că utilajele de deszăpezire sunt pregătite.",
            imageUrl = "https://picsum.photos/seed/newsscroll4/800/450",
            source = "Meteo Info",
            publishedAt = BASE_TIME + 3 * 24 * HOUR + 7 * HOUR,
            url = "https://example.com/stiri/val-de-frig"
        ),
        Article(
            id = "5",
            title = "Un nou parc tehnologic va fi construit lângă Timișoara",
            description = "Investiția ar urma să creeze aproximativ 2.000 de locuri de muncă.",
            content = "Autoritățile județene au aprobat proiectul unui parc tehnologic în apropierea " +
                "Timișoarei. Acesta va găzdui firme de software, laboratoare de cercetare și un " +
                "centru de formare pentru tineri.\n\n" +
                "Lucrările ar urma să înceapă în primăvară, iar prima clădire ar putea fi gata în " +
                "doi ani. Universitățile din oraș au anunțat deja parteneriate pentru stagii de practică.",
            imageUrl = "https://picsum.photos/seed/newsscroll5/800/450",
            source = "Economia Azi",
            publishedAt = BASE_TIME + 14 * HOUR,
            url = "https://example.com/stiri/parc-tehnologic-timisoara"
        ),
        Article(
            id = "6",
            title = "Festivalul de film studențesc își deschide porțile weekendul acesta",
            description = "Peste 60 de scurtmetraje vor fi proiectate gratuit timp de trei zile.",
            content = "Festivalul de film studențesc începe vineri seara, cu peste 60 de scurtmetraje " +
                "realizate de studenți din toată țara. Proiecțiile sunt gratuite, în limita locurilor " +
                "disponibile.\n\n" +
                "Pe lângă proiecții, programul include ateliere de regie și montaj, precum și " +
                "discuții cu realizatorii după fiecare calup de filme.",
            imageUrl = "https://picsum.photos/seed/newsscroll6/800/450",
            source = "Cultura Azi",
            publishedAt = BASE_TIME + 4 * 24 * HOUR + 16 * HOUR,
            url = "https://example.com/stiri/festival-film-studentesc"
        ),
        Article(
            id = "7",
            title = "Spitalul județean primește un aparat RMN nou",
            description = "Pacienții vor aștepta mai puțin pentru investigații.",
            content = "Spitalul județean a pus în funcțiune un aparat RMN nou, cumpărat din fonduri " +
                "europene. Conducerea spitalului estimează că timpul de așteptare pentru investigații " +
                "va scădea la jumătate.\n\n" +
                "Programările se pot face telefonic sau online, iar investigațiile sunt decontate " +
                "pentru pacienții care au trimitere de la medicul specialist.",
            imageUrl = null,
            source = "Sănătate Info",
            publishedAt = BASE_TIME + 1 * 24 * HOUR + 18 * HOUR,
            url = "https://example.com/stiri/aparat-rmn"
        ),
        Article(
            id = "8",
            title = "O aplicație românească de învățat programare a ajuns la un milion de utilizatori",
            description = "Startup-ul fondat de trei absolvenți plănuiește extinderea în alte țări.",
            content = "O aplicație de învățat programare dezvoltată de un startup românesc a depășit " +
                "pragul de un milion de utilizatori. Aplicația propune lecții scurte, de câteva " +
                "minute, și exerciții interactive direct pe telefon.\n\n" +
                "Fondatorii spun că următorul pas este traducerea aplicației în mai multe limbi și " +
                "lansarea unui mod pentru profesori, care să poată urmări progresul elevilor.",
            imageUrl = "https://picsum.photos/seed/newsscroll8/800/450",
            source = "Tech Ro",
            publishedAt = BASE_TIME + 5 * 24 * HOUR + 10 * HOUR,
            url = "https://example.com/stiri/aplicatie-programare"
        ),
        Article(
            id = "9",
            title = "Lucrări de modernizare pe principalul bulevard din Cluj-Napoca",
            description = "Circulația va fi restricționată pe o bandă timp de două luni.",
            content = "Primăria Cluj-Napoca a anunțat începerea lucrărilor de modernizare pe unul " +
                "dintre principalele bulevarde ale orașului. Proiectul include piste noi de biciclete, " +
                "trotuare mai late și refacerea iluminatului public.\n\n" +
                "Pe durata lucrărilor, circulația va fi restricționată pe o bandă, iar șoferilor li " +
                "se recomandă rute alternative în orele de vârf.",
            imageUrl = "https://picsum.photos/seed/newsscroll9/800/450",
            source = "Știri Locale",
            publishedAt = BASE_TIME + 3 * 24 * HOUR + 13 * HOUR,
            url = "https://example.com/stiri/modernizare-bulevard-cluj"
        ),
        Article(
            id = "10",
            title = "Cercetătorii au descoperit o specie nouă de libelulă în Delta Dunării",
            description = "Descoperirea a fost făcută în timpul unei expediții de monitorizare.",
            content = "O echipă de biologi a identificat o specie de libelulă necunoscută până acum, " +
                "în timpul unei expediții de monitorizare a biodiversității din Delta Dunării.\n\n" +
                "Cercetătorii spun că descoperirea arată cât de importantă este protejarea zonelor " +
                "umede și că vor continua studiile pentru a afla cât de răspândită este specia.",
            imageUrl = null,
            source = "Natura Azi",
            publishedAt = BASE_TIME + 6 * 24 * HOUR + 8 * HOUR,
            url = "https://example.com/stiri/libelula-delta"
        )
    )

    override fun getArticles(): List<Article> = articles

    override fun getArticleById(id: String): Article? = articles.find { it.id == id }
}
