package br.com.framework.implementacao.crud;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.framework.interfac.crud.InterfaceCrud;

@Component
@Transactional
public class InplementacaoCrud<T> implements InterfaceCrud<T> {

	private static final long serialVersionUID = 1L;
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@Autowired
	private JdbcTemplateImple jdbcTemplate;
	
	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplate;
	
	@Autowired
	private SimpleJdbcInsertImpl simpleJdbcInsert;
	
	@Autowired
	private SimpleJdbcClassImpl   simpleJdbcClass;
	
	public SimpleJdbcClassImpl getSimpleJdbcClass() {
		return simpleJdbcClass;
	}
	
	@Override
	public void save(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();
	}

	@Override
	public void persiste(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().persist(obj);
		executeFlushSession();
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		executeFlushSession();
	}

	@Override
	public void update(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();

	}

	@Override
	public void delete(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T merge(T obj) throws Exception {
		validaSessionFactory();
		obj = (T) sessionFactory.getCurrentSession().merge(obj);
		executeFlushSession();
		return obj;
	}

	@Override
	public List<T> findList(Class<T> entidade) throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append(" select distinct(entity) from ").append(entidade.getSimpleName()).append(" entity ");

		@SuppressWarnings("unchecked")
		List<T> lista = sessionFactory.getCurrentSession().createQuery(query.toString()).list();

		return lista;
	}

	@Override
	public Object findById(Class<T> entidade, Long id) throws Exception {
		validaSessionFactory();
		Object obj = sessionFactory.getCurrentSession().load(entidade, id);		
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findObjById(Class<T> entidade, Long id) throws Exception {
		validaSessionFactory();
		T obj = (T) sessionFactory.getCurrentSession().load(entidade, id);		
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByQueryDynamic(String query) throws Exception {
		validaSessionFactory();
		List<T> lista = new ArrayList<>();
		lista = sessionFactory.getCurrentSession().createQuery(query).list();
		return lista;
	}

	@Override
	public void executeUpdateQueryDynamic(String query) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().createQuery(query).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void executeUpdateSQLDynamic(String sql) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void clearSession() throws Exception {
		sessionFactory.getCurrentSession().clear();
	}

	@Override
	public void evict(Object obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().evict(obj);
	}

	@Override
	public Session getSession() throws Exception {
		validaSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<?> getListDinamicSQL(String sql) throws Exception {
		validaSessionFactory();
		List<?> lista = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return lista;
	}
	
	@Override
	public JdbcTemplate getJdbcTemplate() throws Exception {
		return jdbcTemplate;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() throws Exception {
		return simpleJdbcTemplate;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() throws Exception {
		return simpleJdbcInsert;
	}

	@Override
	public Long totalRegistros(String table) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) from ").append(table);
		return jdbcTemplate.queryForLong(sql.toString());
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		validaSessionFactory();
		Query queryReturn = sessionFactory.getCurrentSession().createQuery(query.toString());
		return queryReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception {
		validaSessionFactory();
		List<T> lista = new ArrayList<>();
		lista = sessionFactory.getCurrentSession().createQuery(query).setFirstResult(iniciaNoRegistro)
				.setMaxResults(maximoResultado).list();
		return lista;
	}
	
	/**
	 * Metodo para validar a fabrica de sessões, caso não houver será criado uma nova fabrica de sessões
	 * @return void
	 */
	private void validaSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		
		validaTransaction();
	}
	
	/**
	 * Verifica se existe uma transação, Se não houver então inicia uma nova transação
	 * @return void
	 */
	private void validaTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}
	
	/**
	 * Metodo para garantir que as operacões realizadas com ajax sejam commitadas.
	 *  @return void
	 */
	@SuppressWarnings("unused")
	private void commitProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}
	
	/**
	 * Metodo para garantir rollback caso as operacões ajax não sejam realizadas com sucesso.
	 * @return void
	 */
	@SuppressWarnings("unused")
	private void rollBackProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}
	
	/**
	 * Roda instantaneameto o SQL no banco de dados
	 */
	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}
	
	/**
	 * Consulta que retorna uma lista de Array de objetos
	 * @param sql
	 * @return List<Object[]>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getListSQLDinamicaArray(String sql) throws Exception{
		validaSessionFactory();
		List<Object[]> lista = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return lista;
	}

}
