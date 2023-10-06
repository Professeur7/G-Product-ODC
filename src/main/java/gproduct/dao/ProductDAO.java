package gproduct.dao;

import gproduct.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

public class ProductDAO {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("YourPersistenceUnitName");

    // Méthode pour ajouter un nouveau produit
    public void createProduct(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    // Méthode pour récupérer un produit par son ID
    public Product getProductById(long productId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Product.class, productId);
        } finally {
            entityManager.close();
        }
    }

    // Méthode pour mettre à jour un produit
    public void updateProduct(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    // Méthode pour supprimer un produit
    public void deleteProduct(long productId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Product product = entityManager.find(Product.class, productId);
            if (product != null) {
                entityManager.remove(product);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    // Méthode pour récupérer la liste de tous les produits
    public List<Product> getAllProducts() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        } finally {
            entityManager.close();
        }
    }
}
