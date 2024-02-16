import Entities.Author;
import Entities.Book;
import config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Long authorId = 1L;
        String country = "USA";

        // 1.
        String hql1 = "FROM Book b WHERE b.publicationYear > '2010'";
        Query<Book> query1 = session.createQuery(hql1, Book.class);
        List<Book> books1 = query1.getResultList();

        // 2
        String hql2 = "UPDATE Book b SET b.price = b.price * 1.1 WHERE b.author.id = :authorId";
        Query<?> query2 = session.createQuery(hql2);
        query2.setParameter("authorId", authorId);
        int updatedCount = query2.executeUpdate();

        // 3.
        Author authorToDelete = session.get(Author.class, authorId);
        if (authorToDelete != null) {
            session.remove(authorToDelete);
        }

        // 4.
        String hql4 = "SELECT AVG(b.price) FROM Book b";
        Query<Double> query4 = session.createQuery(hql4, Double.class);
        Double averagePrice = query4.uniqueResult();

        // 5.
        String hql5 = "SELECT a.name, COUNT(b) FROM Author a JOIN a.books b GROUP BY a.name";
        Query<Object[]> query5 = session.createQuery(hql5, Object[].class);
        List<Object[]> results = query5.getResultList();
        for (Object[] result : results) {
            String authorName = (String) result[0];
            Long bookCount = (Long) result[1];
            System.out.println("Author: " + authorName + ", Book Count: " + bookCount);
        }

        // 6.
        String hql6 = "SELECT b FROM Book b JOIN b.author a WHERE a.country = :country";
        Query<Book> query6 = session.createQuery(hql6, Book.class);
        query6.setParameter("country", country);
        List<Book> books6 = query6.getResultList();


        // 7.already defined

        // 8.
        String subquery = "SELECT AVG(bookCount) FROM (SELECT COUNT(b.id) as bookCount FROM Author a JOIN a.books b GROUP BY a.id) AS counts";
        Query<Double> subqueryQuery = session.createQuery(subquery, Double.class);
        Double averageBookCount = subqueryQuery.uniqueResult();




        if (averageBookCount != null) {
            String hql8 = "SELECT a.name FROM Author a JOIN a.books b GROUP BY a.name HAVING COUNT(b) > :averageBookCount";
            Query<String> query8 = session.createQuery(hql8, String.class);
            query8.setParameter("averageBookCount", averageBookCount.intValue());
            List<String> authors8 = query8.getResultList();
        } else {
            System.out.println("No average book count found.");
        }

        transaction.commit();
        session.close();
    }
}
