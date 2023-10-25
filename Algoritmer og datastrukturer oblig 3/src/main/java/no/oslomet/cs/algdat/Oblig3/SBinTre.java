package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    //avgjør om en verdi ligger i treet eller ikke (prekodet metode)
    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    //prekodet metode
    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    //prekodet metode
    public boolean tom() {
        return antall == 0;
    }

    //legger til noder i treet (kompendiet 5.2.3 a))
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ikke lov med nullverdier!");

        //p starter i roten
        Node<T> p = rot, q = null;
        //hjelpevariabel
        int cmp = 0;

        //fortsetter til p er ute av treet
        while (p != null){
            //q er forelder til p
            q = p;
            //bruker komparator metoden
            cmp = comp.compare(verdi, p.verdi);
            //flytter p
            p = cmp < 0 ? p .venstre : p.høyre;
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte
        //opretter ny node
        p = new Node<>(verdi, q);

        //p blir rotnode
        if (q == null) {rot = p;}
        //venstre barn til q
        else if (cmp < 0) {q.venstre = p;}
        //høyre barn til q
        else {q.høyre = p;}

        //antall øker
        antall++;
        //lagt inn en verdi i treet
        return true;
    }


    public boolean fjern(T verdi) {
        // treet har ingen nullverdier
        if (verdi == null) return false;
        // q skal være forelder til p
        Node<T> p = rot;
        Node<T> q = null;
        // leter etter verdi
        while (p != null) {
            // sammenligner
            int cmp = comp.compare(verdi,p.verdi);
            // går til venstre
            if (cmp < 0) { q = p; p = p.venstre; }
            // går til høyre
            else if (cmp > 0) { q = p; p = p.høyre; }
            // den søkte verdien ligger i p
            else break;
        }
        // finner ikke verdi
        if (p == null) return false;

        // Tilfelle 1) og 2)
        if (p.venstre == null || p.høyre == null) {
            // b for barn
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;

            if (b != null) {
                b.forelder = q;
            }

            if (p == rot) rot = b;
            else if (p == q.venstre) q.venstre = b;
            else q.høyre = b;
        }
        // Tilfelle 3)
        else {
            // finner neste i inorden
            Node<T> s = p, r = p.høyre;
            while (r.venstre != null) {
                // s er forelder til r
                s = r;
                r = r.venstre;
            }
            // kopierer verdien i r til p
            p.verdi = r.verdi;

            if (s != p) s.venstre = r.høyre;
            else s.høyre = r.høyre;
        }

        // det er nå én node mindre i treet
        antall--;
        return true;
    }

    //fjerner alle forekomstene av en verdi i treet
    public int fjernAlle(T verdi) {
        //teller hvor mange antall det finnes av en verdi
        int antallVerdi = 0;
        //antall verdi økes hver gang det er den samme
        while (fjern(verdi)) {
            antallVerdi++;
        }
        return antallVerdi;
    }

    //returnerer antall forekomster av verdi i treet
    public int antall(T verdi) {

        //lager ny node som settes lik rota til treet
        Node <T> p = rot;
        //lager telle verdi som holder styr på antall forekomster av en verdi i treet
        int antallVerdi = 0;

        //løkkes helt til p ikke går utenfor treet
        while (p != null) {
            //sammenlikner verdiene i parantesen ved bruk av compare-metoden
            int cmp = comp.compare(verdi, p.verdi);
            //dersom verdien er mindre enn rotnoden sin, legges den til som venstrebarn
            if (cmp < 0) {
                p = p.venstre;
            }
            else {
                //dersom verdien er lik rotnodens økes forekomst hjelpevariabelen
                if (cmp == 0) {
                    antallVerdi++;
                }
                //høyre oppdateres
                p = p.høyre;
            }
        }
        //returnverdi fordi dette ikke er en void metode
        return antallVerdi;
    }

    //traversere(rekursivt eller iterativt) treet i rekkefølge og sette pekere og nodeverdier til null
    //det er dermed ikke tilstrekkelig å sette rot og antall til null
    public void nullstill() {
        if (!tom()) {
            nullstillTreet(rot);
            rot = null;
            antall = 0;
        }
    }

    //hjelpemetode - guri
    private static <T> void nullstillTreet (Node<T> p) {
        //sjekker om treet er tomt
        if (p == null) {
            return;
        }

        //sletter barna
        nullstillTreet(p.venstre);
        nullstillTreet(p.høyre);

        //setter alle verdier til null
        p.verdi = null;
        p.forelder = null;
        p.venstre = null;
        p.høyre = null;

    }


    //returnerer første node post orden med p som rot
    private static <T> Node<T> førstePostorden(Node<T> p) {
        while (true) {

            //sjekker om p.venstre ikke er null og eventuelt setter p lik p.venstre
            if (p.venstre != null) {
                p = p.venstre;
            }
            //sjekker om p.høyre ikke er null og eventuelt setter p lik p.høyre
            else if (p.høyre != null) {
                p = p.høyre;
            }

            else {
                return p;
            }
        }
    }

    //returnerer den noden som kommer etter p i postorden, hvis p = siste i postorden, skal metoden returnere null
    private static <T> Node<T> nestePostorden(Node<T> p) {
        //lager nodene for å senere bruke til å sammenlikne verdier i if/else
        Node<T> forelder = p.forelder;
        Node<T> orden= null;

        //hvis forelder er null, skal det ikke fortsettes
        if (forelder == null) {
            return orden;
        }
        //hvis p er høyrebarn
        else if (forelder.høyre == p){
            orden = forelder;
        }

        //hvis p er venstrebarn
        else if (forelder.venstre == p) {
            //da er venstre enebarn
            if (forelder.høyre == null) {
                orden = forelder;
            }
            //forelder noden har høyre og venstrebarn
            else {
                orden = førstePostorden(forelder.høyre);
            }
        }
        return orden;
    }

    //traverserer et binærtre i postorden
    public void postorden(Oppgave<? super T> oppgave) {
        //setter en node lik førstepostorden sin rot
        Node<T> p = førstePostorden(rot);

        //så lenge noden ikke er null skal oppgaven utføres
        while (p != null) {
            //utfører oppgaven men verdien til noden
            oppgave.utførOppgave(p.verdi);

            //oppdaterer før while løkka kjøres igjen
            p = nestePostorden(p);
        }
    }


    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    //brukes til å utføre en oppgave
    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p.venstre != null){
            postordenRecursive(p.venstre, oppgave);
        }
        if (p.høyre != null) {
            postordenRecursive(p.høyre, oppgave);
        }
        oppgave.utførOppgave(p.verdi);
    }

    //iterativ metode bruker kø-traversering i treet nivå orden.
    // Arrayet som returneres av serialize skal inneholde verdiene i alle nodene i nivå orden
    public ArrayList<T> serialize() {
        //ny arrayDeque
        ArrayDeque<Node> deque = new ArrayDeque<>();

        //hentet rotnoden og lagt til i en array deque
        deque.addFirst(rot);

        //lager liste
        ArrayList<T> list = new ArrayList<>();

        //traverserer så lenge lista ikke er tom
        while (!deque.isEmpty()) {
            //tar ut første fra køen
            Node<T> first = deque.removeFirst();

            //legger til noden first sitt venstre barn
            if (first.venstre != null) {
                deque.addLast(first.venstre);
            }

            //legger til noden first sitt høyre barn
            if (first.høyre != null) {
                deque.addLast(first.høyre);
            }

            //legger til verdiene i lista
            list.add(first.verdi);
        }
        //returnerer lista
        return list;

    }

    //tar arrayet fra serialize, legger inn alle verdiene (igjen i nivå orden), og gjenskaper treet
    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        //lager et nytt søketre
        SBinTre<K> tre = new SBinTre<>(c);

        for (K k: data) {
            tre.leggInn(k);
        }
        return tre;
    }


} // ObligSBinTre
