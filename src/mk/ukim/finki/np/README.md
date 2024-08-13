### Definitions - JAVA

**```Ctrl+D```** ends input stream in a Java 
program that reads from the console using ``System.in``.


**STATIC BLOCK:** e block kade shto mozhe da se pravi inicijalizacija na static promenlivi koi shto kje gi incijalizirame tamu, a toj kod vo blockot kje se izvishi koga klasata kje se vchita vo memorija.

**INSTANCE OF:** dali e nekoj instanca od nekoj (klasa/interfejs) vkluchuvajkji gi i site onie koi shto imeplementiraat (klasa/interfejs)
if(account instaceof A) => da e objekt od A ili od site koi shto go nasleduvaat A (SE ODNESUVA ZA KLASATA/INTERFEJS I SITE DECA na taa klasa))

**GETCLASS()** account.getClass().equals(otherClass)), so account.getClass() kazhi mi koja e klasata, so equals so nekoja druga klasa se proveruva dali klasata e klasata shto ja baram (ZA EDNA KLASATA SE ODNESUVA)

**STRATEGY:** razlichni presmetki koi shto mozhe da se stavat pod edna 'kapa'

**ANONIMNA KLASA:** klasa bez ime, za edna upotreba, se instancira vo daden moment se koristi vo toj moment
-sekoja anonimna klasa mora da implementira interfejs

**IMPLEMENTACIJA NA INTERFACE:** konkretna implementacija na interface znachi deka:
1. klasa koja go implementira
2. anonimna klasa
3. lambda expression (ova e izvodlivo samo preku funkciski interface (samo eden abstract method shto ima))

**FUNKCISKI INTERFEJSI**:
- **LAMBDA EXPRESSION**: se koristi koga imame funkciski interfejs i sakame da go implementirame

- **PREDICAT**: e interfejs koj shto prima eden argument i vrakja true/false(sekogash) (pr. i -> i < 100); se okristi kaj list.removeIf(logika), kaj filter

- **SUPPLIER**: dava neshto, no ne prima argumenti (pr. () -> Random().nextInt(100))

- **CONSUMER**: primer neshto, no ne vrakja rezultat (pr. s -> sout(s)) <- ova mozhe i so metod reference(::) bidejkji se povika nekoj metod
koj prima eden argument i koj znae kako da go 'hendla' toj argument

- **FUNCTION**: zema nekoj argument, vrakja rezultat (kako vo mat funkcija) (pr. x -> String.format("%d\n",x);); se koristi kaj map(zemam eden tip, pretvoram vo drug)

- **BIFUNCTION**: prima dva argumenti (mozhe da se i od razlichen tip) i vrakja rezultat (pr. (x, y) -> String.format("%d + %d = %d\n",x,y,x+y);); se koristi kaj reduce

**BUFFEREDREADER** klasa za chitanje na podatoci, koja shto ima nekolku prednosti od Scanner, ima pogolem buffer, pobrz vo chitanje;
nudi mozhnost da gi zememe site linii(.lines()); (**tret save za razlika od scanner)

- **reduce()**: od povekje neshta sakam edno na kraj
- **sorted()**: vo pozadina kje go razgleda compareTo() metodot za da znae na koj nachin da sortira; ako nemame compareTo kje se pobuni sorted(), nema da znae kako da sortira
- **sum()/count()**: sum() mora da raboti na integers ili doubles (znae samo da sobira ili integers ili double); count()
        znae da prebroi elementi od bilo koj tip (vrakja kolku elementi ima vo stream-ot i raboti na 
        bilo koj stream)
- **OPTIONAL**: pomaga da se spravime so nullPRTExceptions (pr. kutija i vnatra topka, ako ima topka okej mozhe da se zeme, ako ne eve ti prazna kutija)
vrakja prazen optional objekt ako nema;

**ObjectOutputStream**: nekoj koj shto kje ni ovozmozhi zapishuvanje vo binarna dadoteka

**SETS**:

SITE SETOVI SE KORISTAT ZA DA IZBEGNEME DUPLIKATI, TOA E ZAEDNICHKO ZA SITE

Sets complexity: [here](https://simplenotions.wordpress.com/2009/05/13/java-standard-data-structures-big-o-notation/) 
- **TreeSet**: mora elementite da se comparable, i ako stavime duplikate, gi ingorira, unikatno; se koristi za da gi sortirame elementite sprored nekoj comparator
   - koga da go koristime? Koga ne ni trebaat duplikati, da bidat sortirani elementite (idealna situacija)
- **HashSet**: najednostavna vremensta kompleksnost, no redosledot na elementite ne se zachuvuva
   - koga da go koristime? Koga kompleksnosta da ne nadminuva O(1)
- **LinkedHashSet**: ednostavna kompleksnost, da ne se sortirani elementite, no da go zachuvame redosled
   - koga da go koristime? O(1) kompleksnost + zachuvuvanje na redosledot
**MAPS**

(mapite kje se potrebni za broenje na pojavuvanja na nekoi elementi; za grupiranje)

Generichka Kolekcija parovi K,V
Nema duplikat na kluchevi vo bilo koja mapa

- **TreeMap**: mora K da bide comparable, ako ne e, togash mozheme da dodaeme komparator na K vo konstruktorot
izbegnuva duplikat kluchevi, ako dodademe so nov kluch kje go prebrishe
mapata e sortirana spored K;

- **HashMap**: se dodavaat vo kofiski spored hash(K), go izmestuva redosledot,
elementite shto se vo tip K, mora da imaat overriden hashCode();

- **LinkedHashMap**: redosledot na kluchevite kje si ostane kako shto sme gi dodale



