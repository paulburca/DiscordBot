# Pein DiscordBot

## Conținut
* [Cerințele proiectului](#cerințe-proiect)
* [Descriere](#descriere)
* [Comenzi](#comenzi)

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
* !info - Afișează informații referitoare la funcționalitățile puse la dispoziție de către Pein (modul de utilizare al comenzilor, creatorii lui Pein)
* !clear [număr] - Șterge [număr] mesaje și afișează un mesaj corespunzător când își termină treaba.
* !news {[număr]} | {[categorie][număr]} 
  - Comanda simpla !news va afișa 3 mesaje conținând informații despre știri de pe un feed predefinit
  - Comanda !news [număr] va afișa [număr] mesaje conținând informații despre știri de pe un feed predefinit

