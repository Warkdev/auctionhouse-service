package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;

import eu.getmangos.controllers.AuctionHouseController;
import eu.getmangos.controllers.DAOException;
import eu.getmangos.dto.AuctionHouseDTO;
import eu.getmangos.dto.Locale;
import eu.getmangos.entities.AuctionHouse;
import eu.getmangos.mapper.AuctionHouseMapper;
import eu.getmangos.rest.AuctionHouseResource;

@RequestScoped
@Path("/v1")
@Tag(name = "auction")
public class AuctionHouseResourceService implements AuctionHouseResource {

    @Inject
    private Logger logger;

    @Inject
    private AuctionHouseController auctionHouseController;

    @Inject
    private AuctionHouseMapper auctionHouseMapper;

    @Override
    public Response findAllAuctionHouses(Integer page, Integer pageSize) {
        logger.debug("findAllAuctionHouses() entry.");

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 20;
        }

        if (pageSize > 100) {
            pageSize = 100;
        }

        List<AuctionHouseDTO> list = new ArrayList<>();

        for (AuctionHouse auction : auctionHouseController.findAll(page, pageSize)) {
            list.add(auctionHouseMapper.map(auction));
        }

        logger.debug("findAllAuctionHouses() exit.");

        return Response.status(200).entity(list).build();
    }

    @Override
    public Response findAuctionHouse(int houseId) {
        logger.debug("findAuctionHouse() entry.");

        try {
            AuctionHouseDTO auction = auctionHouseMapper.map(auctionHouseController.find(houseId));
            logger.debug("findAuctionHouse() exit.");
            return Response.status(200).entity(auction).build();
        } catch (DAOException dao) {
            logger.debug("findAuctionHouse() exit.");
            return Response.status(404).build();
        }
    }

    @Override
    public Response findForFaction(int factionId, Integer page, Integer pageSize) {
        logger.debug("findForFaction() entry.");

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 20;
        }

        if (pageSize > 100) {
            pageSize = 100;
        }

        List<AuctionHouseDTO> list = new ArrayList<>();

        try {
            for (AuctionHouse auction : auctionHouseController.findForFaction(factionId, page, pageSize)) {
                list.add(auctionHouseMapper.map(auction));
            }
        } catch (DAOException dao) {
            logger.debug("findForFaction() exit.");
            return Response.status(500).build();
        }

        logger.debug("findForFaction() exit.");

        return Response.status(200).entity(list).build();
    }

    @Override
    public Response translateAuctionHouse(Integer houseId, Locale locale, String translation) {
        logger.debug("translateAuctionHouse() entry.");

        try {
            auctionHouseController.updateTranslation(houseId, locale, translation);
        } catch (DAOException ex) {
            logger.debug("translateAuctionHouse() exit.");
            return Response.status(400).entity(ex.getMessage()).build();
        }

        return Response.status(200).entity("Translation has been updated").build();
    }

}
