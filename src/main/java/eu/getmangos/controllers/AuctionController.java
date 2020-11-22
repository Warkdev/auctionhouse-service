package eu.getmangos.controllers;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import eu.getmangos.entities.Auction;

@RequestScoped
public class AuctionController {
    @Inject private Logger logger;

    @PersistenceContext(name = "CHAR_PU")
    private EntityManager em;

    /**
     * Create a new auction in the database.
     * @param auction The auction to be created.
     * @throws DAOException An exception is raised if the auction can't be created.
     */
    @Transactional
    public void create(Auction auction) throws DAOException {
        logger.debug("create() entry.");

        try {
            em.persist(auction);
        } catch (Exception e) {
            logger.debug("Exception raised while creating the auction: "+e.getMessage());
            throw new DAOException("Error while creating the auction.");
        }

        logger.debug("create() exit.");
    }

    /**
     * Update an auction in the database.
     * @param auction The auction to be updated.
     * @throws DAOException An exception is raised if the auction can't be updated.
     */
    @Transactional
    public void update(Auction auction) throws DAOException {
        logger.debug("update() entry.");

        if(find(auction.getId()) == null) {
            throw new DAOException("Entity does not exist");
        }

        try {
            em.merge(auction);
        } catch (Exception e) {
            logger.debug("Exception raised while updating the auction: "+e.getMessage());
            throw new DAOException("Error while updating the auction.");
        }

        logger.debug("update() exit.");
    }

    /**
     * Retrieves an auction based on its ID.
     * @param id The ID of the auction to retrieve.
     * @return The matching auction or null if not found.
     * @throws DAOException
     */
    public Auction find(Integer id) throws DAOException {
        logger.debug("find() entry.");

        if (id == null) {
            throw new DAOException("ID is null.");
        }

        try {
            Auction auction = em.createNamedQuery("Auction.findById", Auction.class).setParameter("id", id).getSingleResult();
            return auction;
        } catch (NoResultException nre) {
            return null;
        } finally {
            logger.debug("find() exit.");
        }
    }

    /**
     * Delete an auction based from the database.
     * @param id The ID of the auction to be deleted.
     * @throws DAOException An exception is raised if the auction can't be deleted.
     */
    @Transactional
    public void delete(Integer id) throws DAOException {
        logger.debug("delete() entry.");

        if (id == null) {
            throw new DAOException("ID is null.");
        }

        em.createNamedQuery("Auction.deleteAuction").setParameter("id", id).executeUpdate();

        logger.debug("delete() exit.");
    }

    /**
     * Retrieves the list of all auctions using paging.
     * @param page The page to be queried.
     * @param pageSize The size of the page.
     * @return A list of auction corresponding to the search.
     */
    @SuppressWarnings("unchecked")
    public List<Auction> findAll(int page, int pageSize) {
        logger.debug("findAll() entry.");

        List<Auction> list = em.createNamedQuery("Auction.findAll")
                                .setFirstResult((page - 1) * pageSize)
                                .setMaxResults(pageSize)
                                .getResultList();

        logger.debug("findAll() exit.");

        return list;
    }

    /**
     * Retrieves the list of all auctions for a given auction house using pagination.
     * @param houseId The auction house ID, see AuctionHouse.dbc
     * @param page The page to start from.
     * @param pageSize The amount of results.
     * @return A list of auctions starting at the given page.
     * @throws DAOException An exception is raised if the houseId is null.
     */
    @SuppressWarnings("unchecked")
    public List<Auction> findForAuctionHouse(Integer houseId, int page, int pageSize) throws DAOException {
        logger.debug("findForAuctionHouse() entry.");

        if(houseId == null) {
            throw new DAOException("houseId is null.");
        }

        List<Auction> list = em.createNamedQuery("Auction.findByHouseid")
                                    .setParameter("houseid", houseId)
                                    .setFirstResult((page - 1) * pageSize)
                                    .setMaxResults(pageSize)
                                    .getResultList();

        logger.debug("findForAuctionHouse() exit.");

        return list;
    }

    /**
     * Retrieves the list of all auctions for a given owner using pagination.
     * @param ownerId The owner GUID.
     * @param page The page to start from.
     * @param pageSize The amount of results.
     * @return A list of auctions starting at the given page.
     * @throws DAOException An exception is raised if the ownerId is null.
     */
    @SuppressWarnings("unchecked")
    public List<Auction> findForOwner(Integer ownerId, int page, int pageSize) throws DAOException {
        logger.debug("findForOwner() entry.");

        if(ownerId == null) {
            throw new DAOException("ownerId is null.");
        }

        List<Auction> list = em.createNamedQuery("Auction.findByItemowner")
                                    .setParameter("itemowner", ownerId)
                                    .setFirstResult((page - 1) * pageSize)
                                    .setMaxResults(pageSize)
                                    .getResultList();


        logger.debug("findForOwner() exit.");

        return list;
    }
}
