package gproduct.dao;

import gproduct.model.User;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAO {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("YourPersistenceUnitName");

    // Méthode pour ajouter un nouvel utilisateur
    public void createUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    // Méthode pour récupérer un utilisateur par son ID
    public User getUserById(long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(User.class, userId);
        } finally {
            entityManager.close();
        }
    }

    // Méthode pour récupérer un utilisateur par son nom d'utilisateur
    public User getUserByUsername(String username) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    // Méthode pour mettre à jour les informations d'un utilisateur
    public void updateUser(UserDAO user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    // Méthode pour supprimer un utilisateur
    public void deleteUser(long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, userId);
            if (user != null) {
                entityManager.remove(user);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
