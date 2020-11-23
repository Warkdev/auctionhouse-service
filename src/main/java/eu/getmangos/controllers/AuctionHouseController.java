package eu.getmangos.controllers;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import eu.getmangos.dto.Locale;
import eu.getmangos.entities.AuctionHouse;

@ApplicationScoped
public class AuctionHouseController {
    @Inject private Logger logger;

    @PersistenceContext(unitName = "DBC_PU")
    private EntityManager em;

    /**
     * Create a new Auction House in the database.
     * @param auctionHouse The Auction House to be created.
     * @throws DAOException An exception is raised if the Auction House can't be created.
     */
    @Transactional
    public void create(AuctionHouse auctionHouse) throws DAOException {
        logger.debug("create() entry.");

        try {
            em.persist(auctionHouse);
        } catch (Exception e) {
            logger.debug("Exception raised while creating the auction house: "+e.getMessage());
            throw new DAOException("Error while creating the auction house.");
        }

        logger.debug("create() exit.");
    }

    /**
     * Update an auctionHouse in the database.
     * @param auctionHouse The auctionHouse to be updated.
     * @throws DAOException An exception is raised if the auctionHouse can't be updated.
     */
    @Transactional
    public void update(AuctionHouse auctionHouse) throws DAOException {
        logger.debug("update() entry.");

        if(find(auctionHouse.getAuctionHouseId()) == null) {
            throw new DAOException("Entity does not exist");
        }

        try {
            em.merge(auctionHouse);
        } catch (Exception e) {
            logger.debug("Exception raised while updating the auction house: "+e.getMessage());
            throw new DAOException("Error while updating the auction house.");
        }

        logger.debug("update() exit.");
    }

    /**
     * Retrieves an auctionHouse based on its ID.
     * @param id The ID of the auctionHouse to retrieve.
     * @return The matching auctionHouse or null if not found.
     * @throws DAOException
     */
    public AuctionHouse find(Integer id) throws DAOException {
        logger.debug("find() entry.");

        if (id == null) {
            throw new DAOException("ID is null.");
        }

        try {
            AuctionHouse auctionHouse = em.createNamedQuery("AuctionHouse.findById", AuctionHouse.class).setParameter("id", id).getSingleResult();
            return auctionHouse;
        } catch (NoResultException nre) {
            return null;
        } finally {
            logger.debug("find() exit.");
        }
    }

    /**
     * Delete an auctionHouse based from the database.
     * @param id The ID of the auctionHouse to be deleted.
     * @throws DAOException An exception is raised if the auctionHouse can't be deleted.
     */
    @Transactional
    public void delete(Integer id) throws DAOException {
        logger.debug("delete() entry.");

        if (id == null) {
            throw new DAOException("ID is null.");
        }

        em.createNamedQuery("AuctionHouse.deleteAuction").setParameter("id", id).executeUpdate();

        logger.debug("delete() exit.");
    }

    /**
     * Retrieves the list of all auctions using paging.
     * @param page The page to be queried.
     * @param pageSize The size of the page.
     * @return A list of auctionHouse corresponding to the search.
     */
    @SuppressWarnings("unchecked")
    public List<AuctionHouse> findAll(int page, int pageSize) {
        logger.debug("findAll() entry.");

        List<AuctionHouse> list = em.createNamedQuery("AuctionHouse.findAll")
                                .setFirstResult((page - 1) * pageSize)
                                .setMaxResults(pageSize)
                                .getResultList();

        logger.debug("findAll() exit.");

        return list;
    }

    /**
     * Retrieves the list of all auction houses for a given faction house using pagination.
     * @param factionId The factionID see Faction.dbc
     * @param page The page to start from.
     * @param pageSize The amount of results.
     * @return A list of auction houses starting at the given page.
     * @throws DAOException An exception is raised if the factionId is null.
     */
    @SuppressWarnings("unchecked")
    public List<AuctionHouse> findForFaction(Integer factionId, int page, int pageSize) throws DAOException {
        logger.debug("findForFaction() entry.");

        if(factionId == null) {
            throw new DAOException("factionId is null.");
        }

        List<AuctionHouse> list = em.createNamedQuery("AuctionHouse.findByFactionId")
                                    .setParameter("factionId", factionId)
                                    .setFirstResult((page - 1) * pageSize)
                                    .setMaxResults(pageSize)
                                    .getResultList();

        logger.debug("findForFaction() exit.");

        return list;
    }

    /**
     * Updates the translation for a given auction house.
     * @param houseId The Auction House Id.
     * @param locale The locale for which this translation applies.
     * @param translation The translation to register in the database.
     */
    @Transactional
    public void updateTranslation(Integer houseId, Locale locale, String translation) throws DAOException {
        logger.debug("updateTranslation() entry.");

        if(houseId == null) {
            logger.debug("updateTranslation() exit.");
            throw new DAOException("houseId is null.");
        }

        if(find(houseId) == null) {
            logger.debug("updateTranslation() exit.");
            throw new DAOException("Auction House does not exist");
        }

        em.createQuery("UPDATE AuctionHouse a SET a.name"+locale.loc+" = :translation WHERE a.auctionHouseId = :houseId")
            .setParameter("translation", translation)
            .setParameter("houseId", houseId).executeUpdate();

        logger.debug("updateTranslation() exit.");
    }
}
