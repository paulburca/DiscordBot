# Pein DiscordBot

## Conținut
* [Cerințele proiectului](#cerințe-proiect)
* [Membrii Echipei](#membrii-echipei)
* [Contribuții](#contribuții)
* [Descriere](#descriere)
* [Comenzi](#comenzi)
* [Tehnologii](#tehnologii)
* [Mod de utilizare](#mod-de-utilizare)
* [Exemplu](#exemple-de-feed-uri)

## Cerințele proiectului
- Servicii REST, Discord API
- Crearea unui bot pentru Discord capabil să ofere mesaje preluate prin RSS
pe anumite teme (programare, Java, etc) și să răspundă la întrebări simple
- Pot fi folosite biblioteci RSS (Rich Site Summary or Really Simple
Syndication) cum ar fi ROME
<br><br>
## Membrii echipei
- [Burcă Paul](https://github.com/paulburca)
- [Filimon Dănuț-Dumitru](https://github.com/Danie83)
<br><br>
## Contribuții
- Burcă Paul: Operații pe baza de date, comenzile #addNews, #categories, #setLanguage, interacționarea cu feed-uri folosind ROME, propunerea de utilizare a API-ului StackExchange (împreună cu implementarea acestuia);
- Filimon Dănuț-Dumitru: Comenzile #info, #clear, #news, #prefix, refactorizarea codului, propunerea de utilizare a API-ului Wolfram Alpha (împreună cu implementarea acestuia), scrierea README.md;
- Contribuții comune: Comanda #ask; adăugarea suportului pentru limba română și engleză; eventualele modificări aduse pe tabelele din baza de date (integrarea lor în proiect atunci când e modificată structura tabelelor); verificarea cazurilor posibile de execuție pentru fiecare comandă, evitarea pe cât posibil a potențialelor probleme ce pot apărea (bug-uri), task ce face parte din datoria fiecărui programator; alte modificări aduse pe parcurs și reimplementări ale comenzilor (executarea pe Thread-uri a comenzilor, etc...);
## Descriere
Pein este un bot pentru aplicația Discord ce utilizează DiscordJDA.
Pein consumă servicii REST si utilizează ROME pentru a obține diferite informații pe tema unei categorii dorite dintr-un feed RSS.
Acest bot este capabil să raspundă la întrebări simple și să ofere informații pe diferite subiecte prin utilizarea API-urilor Wolfram Alpha și StackExchange.
<br><br>
## Comenzi
Prefixul setat pentru Pein este '#':
* #info - Afișează informații referitoare la funcționalitățile puse la dispoziție de către Pein (modul de utilizare al comenzilor, creatorii lui Pein);
* #clear [4-100] - Șterge de la 4 la 100 mesaje;
* #prefix [!#&/>] - Permite schimbarea prefix-ului utilizat de Pein: { !, #, &, /, >};
* #news {1-10} | [categorie] {1-10} 
  - Comanda simplă #news va afișa 3 mesaje conținând informații despre știri de pe un feed predefinit
  - Comanda #news {1-10} va afișa {1-10} mesaje conținând informații despre știri de pe un feed predefinit
  - Comanda #news [categorie] va afișa 3 mesaje conținând informații despre știri de pe un feed care are setată acea categorie
  - Comanda #news [categorie] {1-10} va afișa {1-10} mesaje conținând informații despre știri de pe un feed care are setată acea categorie
* #categories - Afișează categoriile disponibile;
* #addNews [nume] [link] [categorii] - Adaugă un feed nou cu un nume specific și mai multe categorii;
* #ask [stack | alpha] [întrebare] - Răspunde sau oferă informații utilizatorului în funcție de opțiunea selectată (stack sau alpha) pentru o întrebare simplă;
* #setLanguage [ro | en] - Permite schimbarea limbii folosite de către Pein (Nu include mesajele primite de la feed-uri și de la API-uri);
  - Limba folosită de Pein este setată pe engleză
<br><br>
## Tehnologii
* [Java Discord API](https://github.com/DV8FromTheWorld/JDA)
```
        <dependency>
            <groupId>net.dv8tion</groupId>
            <artifactId>JDA</artifactId>
            <version>4.2.1_259</version>
        </dependency>
```
* Sistemul de baze de date relaționale utilizat este PostgreSQL
```
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
```
* Java Persistence API este utilizat pentru interacționarea cu baza de date
* JDOM este folosit pentru manipularea fișierelor XML
```
        <dependency>
            <groupId>org.jdom</groupId>
            <artifactId>jdom</artifactId>
            <version>1.1.3</version>
        </dependency>
```
* GSON este folosit pentru manipularea fișierelor JSON
```
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.5</version>
        </dependency>
```
* ROME este un framework utilizat pentru parsarea de feed-uri RSS
```
        <dependency>
            <groupId>rome</groupId>
            <artifactId>rome</artifactId>
            <version>1.0</version>
        </dependency>
```
* Consumarea de servicii REST prin utilizarea framework-ului Spring (RestTemplate)
<br><br>
## Mod de utilizare
Token-ul folosit pentru testarea bot-ului Pein nu poate fi făcut public deoarece este cheia esențială pentru controlarea bot-ului.
Dacă se dorește utilizarea codului sursă pentru a fi rulat separat (deoarece Pein nu este hostat) este nevoie de un token ce poate fi obținut pe https://discord.com/developers/applications după crearea unei noi aplicații, selectarea acesteia, navigarea în secțiunea "Bot", selectarea opțiunii "Add Bot".
Token-ul de pe site trebuie dat ca argument la pornirea programului.

Scriptul de creare pentru baza de date a bot-ului este: [Pein.sql](https://github.com/paulburca/DiscordBot/blob/main/Pein.sql)<br>
Fișierul persistence.xml trebuie modificat corespunzător:
```
        <properties>
            <property name="hibernate.connection.driver_class" value="org.postgresql.Driver"/>
            <property name="hibernate.connection.url" value="jdbc:postgresql:[DATABASE]"/>
            <property name="hibernate.connection.username" value="[USERNAME]"/>
            <property name="hibernate.connection.password" value="[PASSWORD]"/>
        </properties>
```
<br><br>
## Exemple de feed-uri
Exemple de feed-uri ce pot fi folosite se pot găsi în fișierul [feeds.txt](https://github.com/paulburca/DiscordBot/blob/main/feeds.txt).

