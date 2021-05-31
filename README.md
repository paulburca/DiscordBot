# Pein DiscordBot

## Conținut
* [Cerințele proiectului](#cerințe-proiect)
* [Descriere](#descriere)
* [Comenzi](#comenzi)
* [Setup](#setup)
* [Sample](#sample)

## Cerințele proiectului
- Servicii REST, Discord API
- Crearea unui bot pentru Discord capabil să ofere mesaje preluate prin RSS
pe anumite teme (programare, Java, etc) și să răspundă la întrebări simple
- Pot fi folosite biblioteci RSS (Rich Site Summary or Really Simple
Syndication) cum ar fi ROME
<br><br>
## Descriere
Pein este un bot pentru aplicația Discord ce utilizează DiscordJDA API.
Pein consumă servicii REST si utilizează ROME pentru a obține diferite informații pe tema unei categorii dorite dintr-un feed RSS.
Acest bot este capabil să raspundă la întrebări simple și să ofere informații pe diferite subiecte.
<br><br>
## Comenzi
* #info - Afișează informații referitoare la funcționalitățile puse la dispoziție de către Pein (modul de utilizare al comenzilor, creatorii lui Pein)
* #clear [4-100] - Șterge de la 4 la 100 mesaje
* #news {[1-10]} | {[categorie][1-10]} 
  - Comanda simpla #news va afișa 3 mesaje conținând informații despre știri de pe un feed predefinit
  - Comanda #news [1-10] va afișa [1-10] mesaje conținând informații despre știri de pe un feed predefinit
  - Comanda #news [categorie] va afișa 3 mesaje conținând informații despre știri de pe un feed care are setată acea categorie
  - Comanda #news [categorie][1-10] va afișa [1-10] mesaje conținând informații despre știri de pe un feed care are setată acea categorie
* #categories - Afișează categoriile disponibile.
* #addNews [nume] [link] [categorie] - Adaugă un feed nou
* #ask [întrebare] - Răspunde la o întrebare simplă pusă de către utilizator
* #prefix [!#&/>] - Permite schimbarea prefix-ului utilizat de Pein: { !, #, &, /, >}
* #setLang [ro/en] - Permite schimbarea limbii folosite de către Pein (Nu includ mesajele primite de la feed-uri și de la Wolfram Alpha API)
<br><br>
## Cum pot folosi Pein?
Pein foloseste baza de date PostgreSQL avand urmatorul script de creare: [Pein.sql](https://github.com/paulburca/DiscordBot/blob/main/Pein.sql)<br>
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

