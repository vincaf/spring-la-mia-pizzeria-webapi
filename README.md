### Todo
#### Parte 1: 
Generare in progetto nuovo una semplice entita' a piacere (*SEMPLICE*), e generiamo l'ecosistema corrispondente: *pojo* + *repo* + *service*
Andiamo poi a generare un `Controller` di tipo *REST* che ci permetta di ottenere la lista di tutti gli elementi peresenti in tabella, che verrano inseriti dal `run()` del `CommandLineRunner`.

Per generare un `Controller` di tipo *REST* sono necessarie le seguenti annotazioni:
```java
@RestController
@RequestMapping("/api/1/book")
@CrossOrigin("*")
public class BookApiController {

	@Autowired
	private BookServ bookServ;
	
	@GetMapping("/all")
	public List<Book> getAll() {
		
		List<Book> books = bookServ.findAll();
		return books;
	}
}
```
*Nota Bene:*
- `@RestController` al posto di `@Controller`
- `@CrossOrigin("*")` per evitare i problemi di *CORS policy*
- tipo di ritorno dei metodi: da `String` a oggetti concreti che verrano poi *serializzati* in **JSON**

Una volta creato il controller sara' possibile testarlo prima in *PostMan* per verificare che i dati arrivino correttamente. Cercare poi di ottenere lo stesso risultato in **JS** attraverso uno qualsiasi dei metodi conosciuti oppure quello semplificato visto a lezione:
```js
fetch('http://localhost:8080/api/1/book/all')
  .then((response) => response.json())
  .then((data) => console.log(data));

console.log('hello, world');
```

---

### Todo
#### Parte 2: 
Generare un nuovo progetto vue (*super-semplificato*), creare una pagina in cui e' possibile vedere tutte le pizze presenti in DB. Aggiungere poi tutte le funzioni necessario per l'implementazione della *CRUD* completa dell'entita', al netto delle relazioni (vedi il codice visto a lezione durante la live).

### *Bonus*
Dare la possibilita' all'utente di effettuare una ricerca testuale delle pizze (solo *nome* della pizza), sempre sviluppando il tutto **senza la necessita' di ricaricare la pagina**.
