package br.com.framework.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable{
	
	/**
	 * Salva uma entidade no banco de dados
	 * @param obj
	 * @throws Exception
	 */
	void save(T obj) throws Exception;
	
	/**
	 * Salva uma entidade no banco de dados
	 * @param obj
	 * @throws Exception
	 */
	void persiste(T obj) throws Exception;
	
	/**
	 * Salva ou atualiza uma entidade no banco de dados
	 * @param obj
	 * @throws Exception
	 */
	void saveOrUpdate(T obj) throws Exception;
	
	/**
	 * atualiza uma entidade no banco de dados
	 * @param obj
	 * @throws Exception
	 */
	void update(T obj) throws Exception;
	
	/**
	 * deleta uma entidade no banco de dados
	 * @param obj
	 * @throws Exception
	 */
	void delete(T obj) throws Exception;
	
	/**
	 * Salva ou atualiza e retorna o objeto persistente(com a primaryKey)
	 * @param obj
	 * @return T
	 * @throws Exception
	 */
	T merge(T obj) throws Exception;
	
	/**
	 * Retorna uma lista de dados de uma determinada classe
	 * @param objs
	 * @return List<T>
	 * @throws Exception
	 */
	List<T> findList(Class<T> objs) throws Exception;
	
	/**
	 * Busca por id
	 * @param entidade
	 * @param id
	 * @return Object
	 * @throws Exception
	 */
	Object findById(Class<T> entidade, Long id) throws Exception;
	
	
	/**
	 * Busca por id
	 * @param entidade
	 * @param id
	 * @return T
	 * @throws Exception
	 */
	T findObjById(Class<T> entidade, Long id) throws Exception;
	
	/**
	 * Consulta por query dinâmica
	 * @param query
	 * @return List<T>
	 * @throws Exception
	 */
	List<T> findListByQueryDynamic(String query) throws Exception;
	
	/**
	 * Executa update com HQL
	 * @param query
	 * @throws Exception
	 */
	void executeUpdateQueryDynamic(String query) throws Exception;
	
	/**
	 * Executa update com SQL
	 * @param sql
	 * @throws Exception
	 */
	void executeUpdateSQLDynamic(String sql) throws Exception;
	
	/**
	 * Limpa sessão do Hibernate
	 * @throws Exception
	 */
	void clearSession() throws Exception;
	
	/**
	 * Retira um objeto da sessão do Hibernate
	 * @param obj
	 * @throws Exception
	 */
	void evict(Object obj) throws Exception;
	
	/**
	 * Retorna a sessão do Hibernate
	 * @return Session
	 * @throws Exception
	 */
	Session getSession() throws Exception;
	
	/**
	 * Retorna uma lista de dados atraves de um sql
	 * @return List<?>
	 * @throws Exception
	 */
	List<?> getListDinamicSQL() throws Exception;
	
	/**
	 * Prove classes para trabalhar com jdbc abstraido pelo spring (Maior performace)
	 * @return JdbcTemplate
	 * @throws Exception
	 */
	JdbcTemplate getJdbcTemplate() throws Exception;
	
	/**
	 *  Prove classes para trabalhar com jdbc abstraido pelo spring (Maior performace)
	 * @return SimpleJdbcTemplate
	 * @throws Exception
	 */
	SimpleJdbcTemplate getSimpleJdbcTemplate() throws Exception;
	
	/**
	 *  Prove classes para trabalhar com jdbc abstraido pelo spring (Maior performace).
	 * @return SimpleJdbcInsert
	 * @throws Exception
	 */
	SimpleJdbcInsert getSimpleJdbcInsert() throws Exception;
	
	/**
	 * Retorna o total de registros de uma determinda tabela
	 * @param table
	 * @return Long
	 * @throws Exception
	 */
	Long totalRegistros(String table) throws Exception;
	
	/**
	 * Monta query dinamica
	 * @param query
	 * @return Query
	 * @throws Exception
	 */
	Query obterQuery(String query) throws Exception;
	
	/**
	 * Carregamento dinamico
	 * @param query
	 * @param iniciaNoRegistro
	 * @param maximoResultado
	 * @return List<T>
	 * @throws Exception
	 */
	List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception;
	
}
