package no.oslomet.cs.algdat.Oblig1;

////// Løsningsforslag Oblig 1 ////////////////////////

import jdk.jshell.spi.ExecutionControl;

import java.lang.UnsupportedOperationException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class Oblig1 {
    private Oblig1() {}

    ///// Oppgave 1 //////////////////////////////////////

    public static int maks(int[] a) {
        if (a.length < 1) {throw new java.util.NoSuchElementException("Tabellen har ingen elementer");} //a.length < 1
        else if (a.length == 1) {int størsteVerdi = a[0];} //a.length = 1

        int minsteTall = a[0];    // instansierer verdier

        for (int i = 1; i < a.length; i++) { //løkker gjennom arrayet, sammenlikner verdier, minste settes til venstre
            if (a[i] < a[i - 1]) {
                int tmp = a[i];
                a[i] = a[i - 1];
                a[i - 1] = tmp;
            }
            minsteTall = a[i];
        }

        return minsteTall; //returnerer siste verdien i arrayet(array.lengde - 1 pga idneksering)
    }

    public static int ombyttinger(int[] a) {
        if (a.length < 1) {throw new java.util.NoSuchElementException("Tabellen har ingen elementer");} //a.length<1
        else if (a.length == 1) {int størsteVerdi = a[0];} //arrayet har lengde lik 1

        int ombyttinger = 0;                   //instansierer verdier og hjelpevariabelen 'ombyttinger'

        for (int i = 1; i < a.length; i++) {  //løkker, gjør det samme som i oppgave 1, men her øker hjelpevariabel
            if (a[i] < a[i - 1]) {            //for hver gang. mye kildekode inspirert av kompendie sin maks-metode
                int minsteTall = a[i];
                a[i] = a[i - 1];
                a[i - 1] = minsteTall;

                ombyttinger++;                //øker antallombytting en gang
            }
        }
        return ombyttinger; //returnerer antall ganger tallene har bytta plasser

/*Hvor mange (som funksjon av n) blir det for en tabell med n verdier?
*I denne type sortering (bubble sort) er det slik at mengde operasjoner ganges med opphøyd i annen. O(n^2) vil si
* at prosesstiden øker med opphøyd i annen av mengde inputs, og run-time firedobles (n^4)*/

/*Siden vi vil sortere arraye med tallene i stigende rekkefølge, vil det bli flest ombyttinger dersoma arrayet fra før
hadde vært i synkende rekkefølge*/
/*Det blir færrest ombyttinger npr listen er sortert riktig (altså stigende rekkefølge) --> 0 ombyttinger*/
/*I snitt vil det derfor bli en ombytting hver gang det befinner seg et stort tall foran et mindre. Det blir n-h
* ombyttinger der h er det n-te harmoniske tallet. Antar at det er dårligere enn maks (fra før) fordi
* de ikke har ombyttinger*/
    }


    ///// Oppgave 2 //////////////////////////////////////
    public static int antallUlikeSortert(int[] a) {
        if (a.length == 0) {return 0;}            //a.length er lik 0

        for (int i = 1; i < a.length; i++) {      //tester om arrayet er sortert stigende
            if (a[i] < a[i - 1]) {
                throw new IllegalStateException("Arrayet er ikke sortert stigende");
            }
        }
        int teller = 1;                           //instanierer tellerverdi

        for (int i = 0; i < a.length - 1; i++) {  //teller øker dersom tallene i sammenlikningen er ulike
            if (a[i] != a[i + 1]) {
                teller++;
            }
        }
        return teller;                            // returnerer antall ulike sortert
    }


    ///// Oppgave 3 //////////////////////////////////////
    public static int antallUlikeUsortert(int[] a) {
        if (a.length <= 0) {return 0;}           //hvis lengden er mindre enn eller lik null
        else if (a.length == 1) {return 1;}      //lengden til array lik 1

        int teller = 1;                          //instansierer verdier jeg trenger
        int sammenlikn = 0;

        for (int i = 1; i < a.length; i++) {     //løkker gjennom lista og sammenlikner, øker 'sammenlikner' ved true
            for (int x = 0; x < i; x++) {
                if (a[x] == a[i]) {
                    sammenlikn++;
                }
            }
            if (sammenlikn == 0) {
                teller++;
            }
            sammenlikn = 0;
        }
        return teller;                            //returnerer antall ulike sortert
    }


    ///// Oppgave 4 //////////////////////////////////////
    public static int[] delsortering(int[] a) {
        int oddetall = 0;                                    //instansierer verdier
        int venstre = 0;
        int høyre = a.length - 1;                            //bevarer siste verdi

        boolean sjekk = true;                                //oppretter en boolsk sjekkverdi
        while (sjekk) {                                      //sjekker om elementet er et oddetall
            while (venstre <= høyre && a[venstre] % 2 != 0){
                venstre++;
                oddetall++;
            }
            while (venstre <= høyre && a[høyre] % 2 == 0){  //sjekker om elementet er et oddetall
                høyre--;
            }
            if (venstre < høyre){
                byttInt(a,venstre++,høyre--);
                oddetall++;
            }
            else {
                sjekk = false;
            }
        }
        quicksort(a, oddetall,a.length);                     //en quicksort til oddtall og en til partall
        quicksort(a,0,oddetall);
        return a;                                            //må returnere noe slik at metoden ikke blir void
    }


    ///// Oppgave 5 //////////////////////////////////////
    public static char[] rotasjon(char[] a) {
        if (a.length < 2) {}                          //lengden til a < 2 som betyr ingenting skjer

        else{
            int siste = a[a.length - 1];              //bevarer siste verdi

            for (int i = a.length - 1; i > 0; i--){   //roterer n ganger mot høyre
                a[i] = a[i - 1];
            }
            a[0] = (char) siste;
        }
        return a;
    }


    ///// Oppgave 6 //////////////////////////////////////
    public static char[] rotasjon(char[] a, int k) {
        if (a.length < 2) {}                                          //tom- eller ett element tabell, ingen rotasjon

        else {                                                        //1.3.13 g kompediet
            if((k %= a.length) < 0){
                k += a.length;
            }

            for (int begin = 0, end = a.length - 1; begin < end;){    //definerer parametere til mulige scenarioer
                bytt(a,begin++,end--);
            }
            for (int begin = 0, end = k - 1; begin < end;){
                bytt(a,begin++,end--);
            }
            for (int begin = k, end = a.length - 1; begin < end;){
                bytt(a, begin++, end--);
            }
        }
        return a;                                                      //returnerer for å ikke få en void metode
    }

    public static void bytt(char [] a, int i, int j){  //1.1.8 sin bytt metode fra kompendiet
        char midlertidig = a[i];
        a[i] = a[j];
        a[j] = midlertidig;
    }


    ///// Oppgave 7 //////////////////////////////////////
    /// 7a)
    public static String flett(String s, String t) {

        StringBuilder slutt = new StringBuilder();              ///for å lagre den siste strengen

        for (int i = 0; i < s.length() || i < t.length(); i++){ //for hver indeks i strengene
            if (i < s.length())                                 //velger verdien til første streng hvis den eksisterer
                slutt.append(s.charAt(i));                      //metode inspirasjon fra geeksforgeeks
            if (i < t.length())                                 //velger andre verdien hvis den eksisterer
                slutt.append(t.charAt(i));
        }
        return slutt.toString();
    }

    public static void main (String [] args){                   //kjøringskode
        String a = flett("ABC", "DEFGH");
        String b = flett( "IJKLMN", "OPQ");
        String c = flett("", "AB");
        System.out.println(a + " " + b + " " + c);
    }

    /// 7b)
    public static String flett(String... s) {

        StringBuilder slutt = new StringBuilder();
        int lengde = 0;

        for (int i = 0;i < s.length; i++){   //teller elementer til arrayet
            lengde += s[i].length();
        }

        for (int i = 0; i < lengde; i++){
            for (int x = 0; x < s.length; x++){
                if (i < s[x].length()){
                    slutt.append(s[x].charAt(i));
                }
            }
        }
        return slutt.toString();
    }


    ///// Oppgave 8 //////////////////////////////////////
    public static int[] indekssortering(int[] a) {
        int [] indeks = new int[a.length];                                   //initialiserer ny int array, lengde lik a

        for (int i = 0; i < a.length; i++){                                  //setter indekser i det tomme arrayet indeks
            indeks[i] = i;
        }

        for (int i = a.length - 1; i > 0; i--){ for (int x = 0; x < i; x++){ //løkker gjennom for å sette verdier sammen
            if (a[indeks[i]] < a[indeks[x]]){
                int midlertidig = indeks[i];
                indeks[i] = indeks[x];
                indeks[x] = midlertidig;
            }
        }
        }
        return indeks;
    }


    ///// Oppgave 9 //////////////////////////////////////
    public static int[] tredjeMin(int[] a) {
        int lengde = a.length;     // gjør om a.length til lengde for finere kode
        int [] aKopi= Arrays.copyOf(a,3);
        aKopi=indekssortering(aKopi);//kaller arrayet fra oppgave 8

        if (a.length < 3)     // ønsker å ha minst tre verdier
        {
            throw new NoSuchElementException("a.length(" + a.length + ") < 3!");
        }

        int minsteposisjon = aKopi[0];     // m er posisjonen til den minste verdien
        int nestminsteposisjon = aKopi[1];    // nm er posisjonen til nest minst v
        int tredjeminsteposisjon = aKopi[2];    // tm er posisjonen til tredje minst

        //gjør slik som i oppgave 4 i 1.2.5 a)

        if (a[nestminsteposisjon] < a[minsteposisjon]) //hvis nestminsteplassen er mindre enn minsteplassen
        {
            minsteposisjon= 1;
            nestminsteposisjon = 0;
        }

        if (a[tredjeminsteposisjon] < a[minsteposisjon]) //hvis tredjeminsteplass er mindre enn minsteplass
        {
            int temp = tredjeminsteposisjon;  //dannes det en temp med verdien til tredje minst
            tredjeminsteposisjon = minsteposisjon; //der tredjeminstposisjon blir gjort om til minsteplass
            minsteposisjon = temp;
        }

        if (a[tredjeminsteposisjon] < a[nestminsteposisjon]) //dersom tredjeminstposisjon er mindre enn nestminstposisjon
        {
            int temp = tredjeminsteposisjon;
            tredjeminsteposisjon = nestminsteposisjon; //tredjeminsteposisjon blir lik som nestminst
            nestminsteposisjon = temp;  //der nestminstposisjon blir gjort om til tredjeminsteplass
        }

        int minstverdi = a[minsteposisjon];                // minst verdi som skal være lik minsteposisjonene
        int nestmverdi = a[nestminsteposisjon];           // nest minst verdi som skal være lik nestminsteposisjonen
        int tredjemverdi = a[tredjeminsteposisjon];         // tredje minst verdi som skal være lik tredjeminsteposisjonen

        for (int i = 3; i < lengde; i++) //løkke som verdisjekker posisjoenene opp til 3 og setter de inn på riktig plass
        {
            int verdi = a[i];

            if (verdi < tredjemverdi)
            {
                if (verdi < nestmverdi)
                {
                    if (verdi < minstverdi)
                    {
                        tredjeminsteposisjon = nestminsteposisjon;
                        tredjemverdi = nestmverdi;

                        nestminsteposisjon = minsteposisjon;
                        nestmverdi = minstverdi;

                        minsteposisjon = i;
                        minstverdi = verdi;
                    }
                    else
                    {
                        tredjeminsteposisjon = nestminsteposisjon;
                        tredjemverdi = nestmverdi;

                        nestminsteposisjon = i;
                        nestmverdi = verdi;
                    }
                }
                else
                {
                    tredjeminsteposisjon = i;
                    tredjemverdi = verdi;
                }
            }

        }

        return new int[] { minsteposisjon, nestminsteposisjon, tredjeminsteposisjon }; //returnerer plassen til de tre elementene våre

    }


    ///// Oppgave 10 //////////////////////////////////////
    public static int bokstavNr(char bokstav) {
        return bokstav;                      //returnerer bokstav fra 'inneholdt' metode
    }

    public static boolean inneholdt(String a, String b) {
        int [] tabellA = new int[256];       //definerer lengder
        int [] tabellB = new int[256];

        boolean sjekk = false;

        if (a.equals("")){return true;}            //så lenge string a er tom så er b alltid inneholdt

        else if (a.length() > b.length()){         //dersom string a sin lengde er større enn enn b sin lengde
            return false;
        }

        else{                                     //dersom alle betingelser er bestått fortsetter koden nedover
            for (int i = 0; i < a.length(); i++){ //i begge for øker verdien av indeksene med en
                tabellA[bokstavNr(a.charAt(i))]++;
            }
            for (int i = 0; i < b.length(); i++){
                tabellB[bokstavNr(b.charAt(i))]++;
            }

            int x = 0;
            while (x < a.length()){               //bokstavene i a sjekkes
                if (tabellA[bokstavNr(a.charAt(x))] <= tabellB[bokstavNr(a.charAt(x))]){
                    sjekk = true;                 //bokstavene fra a finnes i b men testen er ikke fullført
                }
                else{                             //hopper ut av løkken dersom det ikke stemmer
                    sjekk = false;
                    break;
                }
                x++;
            }
        }
        return sjekk;                              //returnerer svaret fra den boolske sjekkverdien avhengig av resultat
    }



    //ekstra metoder vi trenger for programmene, quicksort er fullstendig hentet fra kompendiet

    public static void byttInt(int [] a, int i, int j){  //1.1.8 sin bytt metode fra kompendiet
        int midlertidig = a[i];
        a[i] = a[j];
        a[j] = midlertidig;
    }


    private static int parter0(int[] a, int venstre, int høyre, int skilleverdi){  //programkode 1.3.9 a)
       while (true){
           while (venstre <= høyre && a[venstre] < skilleverdi) venstre++;         //stoppverdi for venstre
           while (venstre <= høyre && a[høyre] >= skilleverdi) høyre--;            //stoppverdi for høyre

           if (venstre < høyre) byttInt(a, venstre++,høyre--);
           else return venstre;
       }

    }

    private static int sParter0(int[] a, int venstre, int høyre, int indeks){  //programkode 1.3.9 f)
        byttInt(a, indeks, høyre);                                             //skilleverdi flyttes bakerst a[indeks]
        int pos = parter0(a, venstre, høyre - 1, a[høyre]);              //partisjonerer a[v:h- 1] = kompendiet
        byttInt(a, pos, høyre);                                                //får skilleverdi på riktig plass ved bytt
        return pos;                                                            //returner posisjonen til skilleverdi
    }


    private static void quicksort0(int [] a, int venstre, int høyre){       //programkode1.3.9 h)
        if (venstre >= høyre) return;
        int k = sParter0(a, venstre, høyre, (venstre + høyre)/2);
        quicksort0(a, venstre, k - 1);
        quicksort0(a, k + 1, høyre);
    }

    public static void quicksort(int [] a, int fra, int til){ //a [fra:til] = venstre:høyre. sorterer hele a[].
        quicksort0(a, fra, til - 1);

    }


}  // Oblig1