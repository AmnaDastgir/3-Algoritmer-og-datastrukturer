# Obligatorisk oppgave 1 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Amna Dastgir, s364520, s364520@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å danne en if metode som sjekket om det fantes elementer i tabellen jeg ønsker skal gå
igjennom koden. Etter dette instansierte jeg verdiene og løkket gjennom arrayet samt sammenliknet verdiene. Da dette var gjort ble minste verien satt
til venstre, og jeg returnerte tallet. Deretter instansierer verdiene og hjelpevariabelen 'ombyttinger' ved å sette int ombyttinger = 0;.
Etter dette var gjort dannet jeg en for løkke som løkker gjennom arrayet og gjør det samme som i if, men her øker hjelpevariabel for hver gang.
Her er en del av kildekoden inspiret av kompendiet sin maksmetode. Til slutt returnerer jeg ombyttinger som gir oss antall ganger tallene har bytta plass.

I oppgave 2 og 3 så brukte jeg den gode kunnskapsutbytten fra forelesningsvideoene hvor Andre går gjennom liknende koder for
antall ulike tall i både sorterte og usorterte array. Videoen var veldig beskrivende så jeg slet med å forstå
koden. Det skal først testes om kodene fyller kravene for å kjøres (om verken arrayene er sortert i riktig eller ikke
riktig rekkefølge).

For å løse oppgave 4 måtte jeg ta hensyn til hvilken metode jeg skulle bruke for å kunne løse oppgaven,
i og med at oppgaven har bedt meg om å ikke bruke hjelpetabeller.
Jeg prøvde ulike metoder for å løse oppgaven og kom rask frem til den koden jeg har nå.

Oppgave 5 fikk jeg til med hjelp av kompendiet 1.3.13. Her har jeg brukt metoden oppgaven ber meg om å bruke.
Videre bruker if-setning, hvor jeg har skrevet inn a sin lengde mindre enn to, dette betyr at ingenting skjer.
I else-setningen vil den siste verdien bevares. For-løkken gjør det mulig å rotere n antall ganger mot høyere side.
Dette fører til at metoden roterer innholdet i tabellen a med en enhet.


I oppgave 6 tenkte jeg først og fremst på alle mulige scenarioer som kan være utfallet, på en måte slik som testene tester
koden på mange ulike måter. Dersom lengden til int [] a er < 2 skal ingenting skje. Så er det et else utsagn som følger
koden videre. Der befinner det seg tre for løkker som er inspirert av kompediets kode 1.3.13 g).

I oppgave 7a ble jeg bedt om å bruke public static String flett (String s, String t), dermed har jeg startet koden med denne metoden.
Videre for å løse oppgaven har jeg brukt for løkker og if-setninger, for å komme frem til en løsning har vi sett på en anen kildekode.
Til slutt lagde jeg en main metode for å kjøre koden.

I 7b startet jeg oppgaven med public static String flett(String... s).
Her har jeg brukt for løkke for å kunne telle elementene i arrayet. For løkkene vil først finne
det første tegnet fra hver tegnstreng, deretter det andre tegnet.

I oppgave 8 satte jeg meg ned og undret hvordan jeg kan komme fram til en løsning på oppgaven. Det må først
opprettes en ny tom array med lengde lik 'int a[]'. Her skal jeg plassere alle indeksene men først må array bli fylt med
indekser fra 0 til array.length (som for løkka følgende har ansvar for). Den neste for løkka har en innebygd for løkke
fordi på den måten kan verdier settes sammen ved hjelp av sammenlikningen if(). Til slutt returneres indeks tabellen.

Oppgave 9 var litt vanskeligere å få til så jeg måtte være litt kreative.
Med dette endte jeg opp med å ta i bruk en eldre  oppgave i kompendiet (oppgave 3 under 1.2.5) som utgangspunkt.
Det jeg slet mest med var det å kalle metoden fra oppgave 8 på en rikktig måte. For å få dette til valgte jeg å
danne en "kopi" (aKopi=indekssortering(aKopi);). Dermed klarte koden å bestå testen.

I oppgave 10 har jeg først opprettet to array med lengde lik 256. Og i disse kan String a og String b (som befinner seg
i parameterene til oppgaven) fylle ut. Oppgaven ber om en boolsk verdi som resultat dersom kravet fylles eller ikke
så dermed er det lagd en variabel 'sjekk'. Dersom lengdekravene ikke går opp returneres det en false ellers kjører
programmet videre. Der befinner det seg to for-løkker som tester kravet oppgaven har. Senere sjekkes bokstavene i a,
hvis det gjør kjører programmet videre og løkken sender ut et resultat.
