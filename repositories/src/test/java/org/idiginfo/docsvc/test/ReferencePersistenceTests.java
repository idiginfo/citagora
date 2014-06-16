package org.idiginfo.docsvc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.idiginfo.docsvc.jpa.citagora.PersonImpl;
import org.idiginfo.docsvc.jpa.citagora.ReferenceImpl;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ReferencePersistenceTests {

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveOrderWithItems() throws Exception {
		Reference ref = new ReferenceImpl();
		
		entityManager.persist(ref);
		entityManager.flush();
		assertNotNull(ref.getMyId());
	}

	@Test
	@Transactional
	public void testSaveAndGet() throws Exception {
		Reference ref = new ReferenceImpl();
		ref.addAuthor(new PersonImpl("author"));
		entityManager.persist(ref);
		entityManager.flush();
		// Otherwise the query returns the existing order (and we didn't set the
		// parent in the item)...
		entityManager.clear();
		Reference other = (Reference) entityManager.find(ReferenceImpl.class, ref.getMyId());
		assertEquals(1, other.getAuthors().size());
		assertEquals(other, other.getAuthors().iterator().next().getAuthorReferences().get(0));
	}

//	@Test
//	@Transactional
//	public void testSaveAndFind() throws Exception {
//		Order order = new Order();
//		Item item = new Item();
//		item.setProduct("foo");
//		order.getItems().add(item);
//		entityManager.persist(order);
//		entityManager.flush();
//		// Otherwise the query returns the existing order (and we didn't set the
//		// parent in the item)...
//		entityManager.clear();
//		Order other = (Order) entityManager
//				.createQuery(
//						"select o from Order o join o.items i where i.product=:product")
//				.setParameter("product", "foo").getSingleResult();
//		assertEquals(1, other.getItems().size());
//		assertEquals(other, other.getItems().iterator().next().getOrder());
//	}

}
