package rs.ac.bg.fon.nprog.server.repository;

import java.util.List;

/**
 * Interfejs koji sadrzi genericke metode koje ce biti implementirane u brokeru
 * baze podataka.
 * 
 * @author Maja
 * @version 0.1
 *
 */
public interface Repository<T> {

	/**
	 * Vraca sve objekte iz baze koji zadovoljavaju uslov pretrage.
	 * 
	 * @param param Parametar tipa T koji je genericki tip.
	 * @return lista objekata kao List{@literal <T>}.
	 * @throws Exception ukoliko dodje do greske prilikom izvrsavanja upita.
	 */
	List<T> getAll(T param) throws Exception;

	/**
	 * Dodaje prosledjeni parametar u bazu podataka.
	 * 
	 * @param param Parametar tipa T koji je genericki tip.
	 * @throws Exception ukoliko dodje do greske prilikom izvrsavanja upita.
	 */
	void add(T param) throws Exception;

	/**
	 * Vrsi izmene nad prosledjenim parametrom u bazi podataka.
	 * 
	 * @param param Parametar tipa T koji je genericki tip.
	 * @throws Exception ukoliko dodje do greske prilikom izvrsavanja upita.
	 */
	void edit(T param) throws Exception;

	/**
	 * Brise prosledjeni paramater iz baze podataka.
	 * 
	 * @param param Parametar tipa T koji je genericki tip.
	 * @throws Exception ukoliko dodje do greske prilikom izvrsavanja upita.
	 */
	void delete(T param) throws Exception;

	/**
	 * Vraca objekat iz baze koji odgovara prosledjenom parametru.
	 * 
	 * @param param Parametar tipa T koji je genericki tip.
	 * @return T koji je genericki tip objekta.
	 * @throws Exception ukoliko dodje do greske prilikom izvrsavanja upita.
	 */
	T get(T param) throws Exception;

	/**
	 * Vraca objekat iz baze koji odgovara prosledjenom parametru, ali koristeci
	 * join uslov u upitu.
	 * 
	 * @param param Parametar tipa T koji je genericki tip.
	 * @return T koji je genericki tip objekta.
	 * @throws Exception ukoliko dodje do greske prilikom izvrsavanja upita.
	 */
	T getWithJoin(T param) throws Exception;
}
