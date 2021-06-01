# Pein DiscordBot

## Conținut
* [Cerințele proiectului](#cerințe-proiect)
* [Membrii Echipei](#membrii-echipei)
* [Contribuții](#contribuții)
* [Descriere](#descriere)
* [Comenzi](#comenzi)
* [Tehnologii](#tehnologii)
* [Mod de utilizare](#mod-de-utilizare)
* [Sample](#exemple-de-feed-uri)

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
- Burcă Paul: Operații pe baza de date, comenzile #addNews, #categories, #setLanguage Lucru pe feed-uri, propunerea de utilizare a api-ului StackExchange;
- Filimon Dănuț-Dumitru: Comenzile #info, #clear, #news, #prefix, refactorizarea codului, propunerea de utilizare a api-ului Wolfram Alpha, scrierea README.md;
- Contribuții comune: Comanda #ask, adăugarea suportului pentru limba română și engleză, eventualele modificări aduse pe tabelele din baza de date (integrarea lor în proiect atunci când e modificată structura tabelelor), verificarea cazurilor posibile de execuție pentru fiecare comandă;
## Descriere
Pein este un bot pentru aplicația Discord ce utilizează DiscordJDA API.
Pein consumă servicii REST si utilizează ROME pentru a obține diferite informații pe tema unei categorii dorite dintr-un feed RSS.
Acest bot este capabil să raspundă la întrebări simple și să ofere informații pe diferite subiecte prin utilizarea API-urilor Wolfram Alpha și StackExchange.
<br><br>
## Comenzi
Prefixul pentru Pein este original '#':
* #info - Afișează informații referitoare la funcționalitățile puse la dispoziție de către Pein (modul de utilizare al comenzilor, creatorii lui Pein)
* #clear [4-100] - Șterge de la 4 la 100 mesaje
* #prefix [!#&/>] - Permite schimbarea prefix-ului utilizat de Pein: { !, #, &, /, >}
* #news {1-10} | [categorie] {1-10} 
  - Comanda simpla #news va afișa 3 mesaje conținând informații despre știri de pe un feed predefinit
  - Comanda #news {1-10} va afișa {1-10} mesaje conținând informații despre știri de pe un feed predefinit
  - Comanda #news [categorie] va afișa 3 mesaje conținând informații despre știri de pe un feed care are setată acea categorie
  - Comanda #news [categorie] {1-10} va afișa {1-10} mesaje conținând informații despre știri de pe un feed care are setată acea categorie
* #categories - Afișează categoriile disponibile
* #addNews [nume] [link] [categorii] - Adaugă un feed nou cu un nume specific și mai multe categorii
* #ask [stack | alpha] [întrebare] - Răspunde sau oferă informații utilizatorului în funcție de opțiunea selectată (stack sau alpha) pentru o întrebare simplă
* #setLanguage [ro | en] - Permite schimbarea limbii folosite de către Pein (Nu includ mesajele primite de la feed-uri și de la API-uri)
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
Script de creare pentru baza de date a bot-ului este: [Pein.sql](https://github.com/paulburca/DiscordBot/blob/main/Pein.sql)<br>
Fisierul persistence.xml trebuie modificat corespunzator:
```
        <properties>
            <property name="hibernate.connection.driver_class" value="org.postgresql.Driver"/>
            <property name="hibernate.connection.url" value="jdbc:postgresql:[DATABASE]"/>
            <property name="hibernate.connection.username" value="[USERNAME]"/>
            <property name="hibernate.connection.password" value="[PASSWORD]"/>
        </properties>
```
## Exemple de feed-uri
Exemple de feed-uri ce pot fi folosite se pot gasi in fisierul [feeds.txt](https://github.com/paulburca/DiscordBot/blob/main/feeds.txt)

