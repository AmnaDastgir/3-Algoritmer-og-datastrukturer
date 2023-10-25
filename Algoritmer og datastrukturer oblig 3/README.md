# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Amna Dastgir, s364520, s364520@oslomet.no


# Oppgavebeskrivelse

                                                 * Oppgave 1 *
I oppgave 1 startes det med å sjekke om det finnes nullverdier. Det opprettes varibler som videre brukes i while-løkka
som legger inn verdier som enten venstre eller høyre barn, avhengig av hva som skjer i den den forenklede if-setningen.

                                                 * Oppgave 2 *
I oppgave 2 skal metoden returnere antall forekomster av verdi i treet. Dette gjøres ved å lage en node (starter i rot), 
og en tellevariabel. Det skal løkkes i en while (så lenge den verdien ikke er null), inne i løkka er det brukt en 
compare som sammenlikner verdiene og øker antall dersom det finnes enda en verdi som er lik. 

                                                 * Oppgave 3 *
Fra oblig oppgaveteksten: "Førstepostorden skal returnere første node post orden med p som rot, og nestePostorden skal returnere den noden som 
kommer etter p i postorden. Hvis p er den siste i postorden, skal metoden returnere null". Dermed er det en while-løkke
som skal skje så lenge boolean-outputet er true. Inni den er det en if som sjekker om nodens venstre og høyre ikke er
nullverdier, og setter dem til venstre og høyre.
    Gått frem ved å lage variabler. Deretter finnes det en if setning som sjekker om forelder er null og returnerer 
henholdsvis. sjekker venstre, høyre, og dersom noden har høyre og venstrebarn.

                                                 * Oppgave 4 *
Her startes det ved å sette en ny node lik rota (fordi det er der vi vil starte). Så lenge oppgaven ikke er ugyldig skal
oppgaven utføres (gjøres ved en while-løkke i kildekoden). Deretter må vi oppdatere feltet før det så løkkes igjen. 

                                       * Oppgave 5 og 6 fra oppgaveteksten*
lage serialize som gjør om binærtreet til et array, og tilsvarende deserialize som tar et array og gjør om til et 
binært søketre. serialize skal være iterativ og må bruke en kø til å traversere treet i nivå orden. Arrayet som returneres av serialize skal inneholde verdiene i alle nodene i nivå orden. Deserialize skal da ta dette arrayet, og legge inn alle verdiene (igjen
i nivå orden), og dermed gjenskape treet.


* KODENE ER INSPIRERT/MYE HENTET AV KOMPENDIET FRA METODER SOM BEFINNER SEG I KILDEKODE NEDERST I INNHOLDSFORTEGNELSEN *

//test