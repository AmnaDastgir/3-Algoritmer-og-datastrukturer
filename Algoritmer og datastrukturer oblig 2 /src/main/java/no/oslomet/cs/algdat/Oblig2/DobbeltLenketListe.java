package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;

//test?
public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    // peker til den første i listen
    private Node<T> hode;
    // peker til den siste i listen
    private Node<T> hale;
    // antall noder i listen 
    private int antall;
    // antall endringer i listen
    private int endringer;

    public DobbeltLenketListe() {
        //(+/-) 1 for hver økning og reduksjon
        antall = 0;
        //økes med 1 for hver mutator
        endringer = 0;
        //første element i lista
        hale = null;
        //siste element i lista
        hode = null;
    }

    public DobbeltLenketListe(T[] a) {

        //setter hode for denne metoden (kontruktør)
        this.hode = null;
        //setter hale for denne metoden (konstruktør)
        this.hale = null;

        Objects.requireNonNull(a);

        //dersom listas lengde er større enn null
        if (a.length > 0){
            int i = 0;
            for (; i < a.length; i++) {
                //finner første element som ikke er null og setter hode
                if (a[i] != null) {
                    hode = new Node<>(a[i], null, null);
                    //øker antall telleren
                    antall++;
                    break;
                }
            }
            hale = hode;
            //hvis hode ikke er null skal følgende skje
            if (hode != null) {
                i++;
                //løkker gjennom for å sjekke om de neste verdiene ikke er null
                for (; i < a.length; i++) {
                    //da legges verdiene til som nye noder og setter hale sin neste
                    if (a[i] != null) {
                        hale.neste = new Node<>(a[i], hale, null);
                        hale = hale.neste;
                        //antall økes med en hvis det legges til ny node
                        antall++;
                    }
                }
            }
        }
    }

    //returnerer en liste (en instans av klassen DobbeltLenketListe)
    public Liste<T> subliste(int fra, int til) {
        //tester om det halvåpne tabellintervallet a[fra:til> er lovlig
        fratilKontroll(antall,fra,til);
        //opretter en dobbeltlenket liste type T generic
        DobbeltLenketListe<T> liste = new DobbeltLenketListe<>();
        //lager en variabel for 'lengde' med parameterne som subliste() tar inn
        int lengde = til - fra;
        //løkker gjennom 'for' hvis lengden til lista er større enn 0
        if (lengde > 0){
            for (int i = fra; i < til; i++){
                //fra å med "fra"-verdien skal noder legges til i listen
                Node<T> temp = finnNode(fra);
                liste.leggInn(temp.verdi);
                //'fra' øker slik at vi går fra venstre til høyre og ikke løkker gjennom det samme repeterende ganger
                fra++;
            }
        }
        //resetter endringer
        endringer = 0;
        return liste;
    }

    @Override
    //returnerer antall verdier i listen
    public int antall() {
        return antall;
    }

    //returnerer true/false avhengig av antall verdien
    @Override
    public boolean tom() {
        return antall == 0;
    }

    //skal legge en ny node med oppgitt verdi bakerst i listen og returnere true
    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi);
        // betyr at lista er tom
        if (antall == 0) {
            hode = hale = new Node<>(verdi,null,null);
        }
        // legger en ny verdi bakerst
        else {
            // Ikke tomt
            hale = hale.neste = new Node<>(verdi, hale,null);
        }

        //øker antall
        antall++;
        //øker endringer
        endringer++;
        //returnerer den boolske verdien for metoden avhengig av resultatet
        return true;
    }

    //legger inn verdi i listen slik at den får indeks/poisjon 'indeks'
    @Override
    public void leggInn(int indeks, T verdi) {
        //sjekker om det sendes inn nullverdi og kaster exception
        Objects.requireNonNull(verdi, "Kan ikke legge inn null verdier");

        //kan ikke sende en negativ indeks inn ettersom tellinga starter fra 0 (som er det første elementet)
        if (indeks < 0) {
            throw new IndexOutOfBoundsException("Indeks: " + indeks + " er negativ.");
        }

        //sjekker om indeks er større enn antall (== utenfor lista)
        else if (indeks > antall) {
            throw new IndexOutOfBoundsException("Indeks: " + indeks + " er større enn antall noder noder.");
        }

        //tom liste og da settes alle pekerne til null
        else if (antall == 0) {
            hode = hale = new Node<>(verdi, null, null);
        }

        //setter den nye verdien forrest
        else if (indeks == 0) {
            hode = hode.forrige = new Node<>(verdi,null, hode);
        }

        //setter den nye verdien bakerst
        else if (indeks == antall) {
            hale = hale.neste = new Node<>(verdi, hale,null);
        }

        else {
            //plasserer en node i mellom to andre etter å ha funnet indeksen til noden
            Node<T> p = finnNode(indeks);
            p.forrige = p.forrige.neste = new Node<>(verdi, p.forrige, p);
        }

        //enda en verdi i listen
        antall++;
        endringer++;
    }

    //returnere true/false avhengig om listen er tom eller ikke
    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    //
    @Override
    public T hent(int indeks) {
        //indeks går gjennom indeks-kontroll (sjekke gyldighet) og så returnerer verdien til den indeksen som sendes inn
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    //returnerer indeksen/posisjonen til verdi hvis den finnes i listen og returnerer -1 hvis den ikke finnes
    @Override
    public int indeksTil(T verdi) {
        //sjekker om det sendes inn nullverdier
        if (verdi == null) {return -1;}

            Node<T> p = hode;
            for (int i = 0; i < antall; i++, p = p.neste) {
                if (p.verdi.equals(verdi)) {
                    return i;
                }
            }
        return -1;
    }

    //erstatter verdien på plass indeks med nyverdi, og returnere det som lå der fra før
    @Override
    public T oppdater(int indeks, T nyverdi) {
        //sjekker for nullverdier
        if (nyverdi == null){
            throw new NullPointerException("Ikke tillatt med nullverdier!");
        }

        //utfører indekskontroll og legger inn boolsk returnverdi
        indeksKontroll(indeks, false);
        Node<T> p = finnNode(indeks);

        //oppdaterer den gamle verdien og setter den som nyverdi
        T gammelverdi = p.verdi;
        p.verdi = nyverdi;

        //øker endringer
        endringer++;
        return gammelverdi;
    }

    //fjerner verdi fra listen og så returnere true
    @Override
    public boolean fjern(T verdi) {
        //finner null verdi og returnerer boolean
        if (verdi == null) {return false;}

        Node<T> p = hode;
        //leter etter en ikke-null verdi
        while (p != null){
            if (p.verdi.equals(verdi)) break;
            p = p.neste;
        }

        //verdien er ikke i lista
        if (p == null) {
            return false;
        }

        //antall lik en betyr at det kun finnes en node i lista
        else if (antall == 1) {
            hode = hale = null;
        }

        //den første skal fjernes
        else if (p == hode){
            hode = hode.neste;
            hode.forrige = null;
        }

        //siste skal fjernes
        else if (p == hale) {
            hale = hale.forrige;
            hale.neste = null;
        }

        else {
            p.forrige.neste = p.neste;
            p.neste.forrige = p.forrige;
        }

        //omstiller verdier
        p.verdi = null;
        p.forrige = p.neste = null;

        //antall blir mindre og endringer øker
        antall--;
        endringer++;

        return true;
    }

    //fjerner (og returnerer) verdien på posisjon indeks (som først må sjekkes)
    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks,false);

        Node<T> p = hode;

        //betyr at det kun  er en node i lista
        if (antall == 1) {
            hode = hale = null;
        }

        //den første skal tas vekk
        else if (indeks == 0) {
            hode = hode.neste;
            hode.forrige = null;
        }

        //den siste skal tas vekk
        else if (indeks == antall - 1) {
            p = hale;
            hale = hale.forrige;
            hale.neste = null;
        }
        // <-- () --> () <-- () -->
        else {
            //bruker hjelpemetode
            p = finnNode(indeks);
            p.forrige.neste = p.neste;
            p.neste.forrige = p.forrige;
        }

        //skal returneres
        T verdi = p.verdi;
        //brukes om igjen
        p.verdi = null;
        p.forrige = p.neste = null;

        //en verdi mindre i listen
        antall--;
        //en endring skjer
        endringer++;
        return verdi;
    }

    @Override
    public void nullstill() {
        Node<T> p = hode;

        while (p != null) {
            Node<T> q = p.neste;

            p.neste = null;
            p.forrige = null;
            p.verdi = null;

            p = q;
        }

        antall = 0;
        endringer = 0;
        hode = hale = null;
    }

    @Override
    //returnerer tegnstreng med listens verdier
    public String toString() {
        //bygge opp tegnstrengen
        StringBuilder builder = new StringBuilder();
        //legger til (= appender) det som står inni parantesen
        builder.append("[");

       //kaller tom() som returnerer true/false avhengig av antall verdien
       if (!tom()) {
            //lager en node og setter den lik hodet (=første verdi)
            Node<T> current = hode;

            //så lenge noden ikke  er ugyldig skal verdien til noden legges inn, og komma
            while (current != null) {
                builder.append(current.verdi);
                if (current.neste != null) {
                    builder.append(", ");
                }
                //setter current til lik den neste helt til vi er ferdige
                current = current.neste;
            }
        }

       //legger til en sluttparantes da alle nodene er sett igjennom
       builder.append("]");
       
       //returnerer metoden
       return builder.toString();
    }

    //returnere tegnstreng på samme måte som toString() men verdiene skal komme i omvendt rekkefølge
    public String omvendtString() {
        StringBuilder builder = new StringBuilder();
        //legger til en startparantes
        builder.append("[");

        //finner første verdi som ikke er null og setter den lik hale
        if (hale != null) {
            //hale er en current node og så lenge current ikke er null (altså har en verdi) skal noder legges inn i while
            Node<T> current = hale;
            while (current != null) {
                builder.append(current.verdi);
                if (current.forrige != null) {
                    builder.append(", ");
                }
                current = current.forrige;
            }
        }
        builder.append("]");
        return builder.toString();
    }

    //skal lage ny instans av iteratorklassen
    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
        //DobbeltLenketListeIterator iterator = new DobbeltLenketListeIterator();
        //return iterator;
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks,false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            //p starter på den første i listen
            denne = hode;
            //blir sann når next() kalles
            fjernOK = false;
            //teller endringer
            iteratorendringer = endringer;
        }

        //sette pekeren denne til den noden som hører til den oppgitte indeksen
        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        /*sjekker om iteratorendringer er lik endringer, ellers kaster den en ConcurrentModificationException,
        NoSuchElementException hvis det ikke er flere igjen i listen. Da blir hasNext() ikke true/sann*/
        @Override
        public T next() {
            if (denne == null) {
                throw new NoSuchElementException("Det er ikke flere igjen i lista!");
            }

            if (iteratorendringer != endringer){
                throw new ConcurrentModificationException("Iteratorendringer er ikke lik endringer!");
            }

            fjernOK = true;
            //passer på verdien i 'denne'
            T verdi = denne.verdi;
            //øker 'denne' til neste
            denne = denne.neste;

            return verdi;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }


    //privat hjelpemetode til oppgave 3a)
    private Node<T> finnNode (int indeks){
        Node <T> p;

        //leter fra hode til hale med bruk av neste peker hvis indeks <= antall
        if (indeks <= antall / 2){
            p = hode;
            for (int i = 0; i < indeks; i++){
                p = p.neste;
            }
        }
        //ellers letes det fra hale til hode med bruk av bakover peker
        else {
            p = hale;
            for (int i = antall - 1; i > indeks; i--){
                p = p.forrige;
            }
        }
        //returnerer node med gitt indeks/posisjon
        return p;
    }

    //public hjelpetabell til oppgave 3b) (programkode 1.2.3 a))
    //tester om det halvåpne tabellintervallet a[fra:til> er lovlig
    private void fratilKontroll (int antall, int fra, int til) {
        //hvis antallet til array = lik 0 kalles metoden tom() som returnerer true/false avhengig om listen er tom (eller ikke)
        if (antall == 0){
            tom();
        }

        //går inn i if hvis antall input fra er større enn null == ikke lovlig
        if (fra < 0){
            throw new IndexOutOfBoundsException("fra(" + fra + ") er negativ!");
        }

        //mengde input til kan ikke være større enn antallet == da er vi utenfor rekkevidden til det lovlige
        if (til > antall) {
            throw new IndexOutOfBoundsException("til(" + til + ") > antall(" + antall + ")");
        }

        //ikke mulig å ha et høyere tall fra enn til. vi beveger oss fra fra --> til (venstre --> høyre)
        if (fra > til) {
            throw new IllegalArgumentException("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
        }
    }
} // class DobbeltLenketListe