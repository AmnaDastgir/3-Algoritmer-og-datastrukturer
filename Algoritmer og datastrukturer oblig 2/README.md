# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer.
Oppgaven er levert av følgende studenter:
* Amna Dastgir, s364520, s364520@oslomet.no

# Arbeidsfordeling


* Jeg har løst denne obligatoriske oppgaven individuelt. Dermed er oppgave 1-8 løst av meg. I tillegg måtte jeg løse oppgave 7
* ettersom det dukket opp en feilmelding som ba meg kode oppdater() for at 8 skulle bestå. 


# Oppgavebeskrivelse

                                                    *OPPGAVE 1*
* Kaster ett unntakk (exception) for å sjekke om det finnes nullverdier. Lager en for løkke for å sjekke om lista er lengre
* enn 0 og inne har jeg en if-setning som finner den første verdien (som ikke er null) og setter den lik hode. I den andre 
* if-setningen skal det løkkes gjennom verdiene etter hode (som ble satt). De sjekkes om de er null, hvis ikke legges de til i
* lista. 

                                                    *OPPGAVE 2a)*
* Metoden toString() returnerer en tegnstreng med listens verdier. ToStringen ble bygd ved hjelp av en StringBuilder og tegn
* (paranteser og komma) ble lagt til ved den innebygde metoden append (=legg til) i StringBuildler. Så lengde verdier ikke er null
* legges de til i utskriften (med while løkke).

                                                    *OPPGAVE 2a)*
* Returnerer tegnstreng på samme måte som toString() men verdiene skal komme i omvendt rekkefølge. 

                                                    *OPPGAVE 2b)*
* Metoden skal legge en ny node med oppgitt verdi bakerst i listen og returnere true. Det sjekkes først etter nullverdier ved 
* exception (Objects.requireNonNull) og med en if-setning skal det sjekkes om lista er tom. Hvis den er ikke er tom legges det
* til i else flere noder bakerst (da-værende hale sin neste, som blir den nye halen). 

                                                    *OPPGAVE 3a)*
* Den private hjelpemetoden skal returnere noden med den gitte indeksen/posisjonen. I første if letes det fra hode til hale
* med bruk av neste pekere hvis indeks <= antall. Ellers letes det fra hale til hode med hjelp av bakover pekere. hent() setter 
* indeksen gjennom indeks-kontroll (for å sjekke gyldighet) og så returnerer verdien til den indeksen som sendes inn. 
* Oppdater() erstatter verdien på plass indeks med nyverdi, og returnere det som lå der fra før. Det utføres indekskontroll 
* og legges inn boolsk returnverdi. Også oppdateres den gamle verdien og setter den som nyverdi. 

                                                    *OPPGAVE 3b)*
* Subliste() returnerer en liste (en instans av klassen DobbeltLenketListe). FraTilKontroll() tester om det halvåpne intervallet 
* er lovlig og oppretter en dobbeltlenketliste type T generic. Det løkkes dermed gjennom for hvis lengden til lista er 
* større enn 0. Det er en if som tester hvis antall input er større enn null (=ikke lovlig). En if som tester om tallene som
* sendes inn overlapper (går ut av lista). Og en if som tester om tallet 'til' er høyere enn 'fra'.

                                                    *OPPGAVE 4*
* IndeksTil() returnerer indeks/posisjonen til verdi hvis den finnes. Først sjekkes det om det er nullverdier også løkkes det
* gjennom en for som sjekker om verdi og indeks henger sammen og returnerer riktig svar. Boolean inneholder() skal returnere true
* false avhengig om påstanden er sann/usann.

                                                    *OPPGAVE 5*
* leggInn() legger inn verdi i listen slik at den får indeks/posisjon 'indeks'. Først sjekkes det om det sendes inn nukverdi og
* evt kaster exception. En if sjekker om det sendes in negative indekser. Enda en if sjekker om indeksen er større en antall.
* Enda en if tester om det er en tom liste. Enda en if setter den nye verdien forrest, ellers plasseres en node mellom
* to andre etter å ha funnet indeksen til noden.

                                                    *OPPGAVE 6*
* Den første metoden skal fjerne (og returnere) verdien på posisjon indeks (som først må sjekkes). En if tester om det er en 
* node i lista. En else fjerner den første, enda en else fjerner den siste. Ellers brukes det hjekpemetode som finner og fjerner.
* Boolean metoden fjerner verdien fra listen og returnerer true. Det sjekkes om verdien er listen, om antallet er lik en 
* som indikerer på at det er ett element i lista ved hjelp av en if. Etter else skal verdiene omstilles. 

                                                    *OPPGAVE 7*
* Metoden skal tømme listen og 'nulle' alt. Starter i hode og går mot hale ved hjelpe pekeren neste. For hver node «nulles» 
* nodeverdien og alle nodens pekere. Til slutt settes både hode og hale til null, antall til 0 og endringer økes. 

                                                    *OPPGAVE 8*
* Next() skal først sjekke om iteratorendringer er lik endringer. Hvis ikke, kastes enConcurrentModificationException. Så 
* enNoSuchElementException hvis det ikke er flere igjen i listen (dvs. hvishasNext()ikke er sann/true). Deretter settes fjernOK 
* til sann/true, verdien til denne returneres og denne flyttes til den neste node. (fra oppgaveteksten)

      *KODENE ER INSPIRERT/MYE HENTET AV KOMPENDIET FRA METODER SOM BEFINNER SEG I KILDEKODE NEDERST I INNHOLDSFORTEGNELSEN*
