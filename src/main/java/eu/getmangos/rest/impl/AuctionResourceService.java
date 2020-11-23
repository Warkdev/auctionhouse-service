package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;

import eu.getmangos.controllers.AuctionController;
import eu.getmangos.controllers.DAOException;
import eu.getmangos.dto.AuctionDTO;
import eu.getmangos.dto.MessageDTO;
import eu.getmangos.entities.Auction;
import eu.getmangos.mapper.AuctionMapper;
import eu.getmangos.rest.AuctionResource;

@RequestScoped
@Path("auction/v1/")
@Tag(name = "auction")
public class AuctionResourceService implements AuctionResource {

    @Inject private Logger logger;

    @Inject private AuctionController auctionController;

    @Inject private AuctionMapper auctionMapper;

    @Override
    public Response findAllAuctions(Integer page, Integer pageSize) {
        logger.debug("findAllAuctions() entry.");

        if(page == null) {
            page = 1;
        }

        if(pageSize == null) {
            pageSize = 20;
        }

        if(pageSize > 100) {
            pageSize = 100;
        }

        List<AuctionDTO> list = new ArrayList<>();

        for(Auction auction : auctionController.findAll(page, pageSize)) {
            list.add(auctionMapper.auctionToDTO(auction));
        }

        logger.debug("findAllAuctions() exit.");

        return Response.status(200).entity(list).build();
    }

    @Override
    public Response findAuction(int id) {
        logger.debug("findAuction() entry.");

        try {
            AuctionDTO auction = auctionMapper.auctionToDTO(auctionController.find(id));
            if(auction == null) {
                return Response.status(404).entity(new MessageDTO("Auction doesn't exist")).build();
            }
            return Response.status(200).entity(auction).build();
        } catch (DAOException dao) {
            logger.debug("finfindAuctiondAll() exit.");
            return Response.status(500).entity(new MessageDTO(dao.getMessage())).build();
        }

    }

    @Override
    public Response findForAuctionHouse(int houseId, Integer page, Integer pageSize) {
        logger.debug("findForAuctionHouse() entry.");

        List<AuctionDTO> list = new ArrayList<>();

        if(page == null) {
            page = 1;
        }

        if(pageSize == null) {
            pageSize = 20;
        }

        if(pageSize > 100) {
            pageSize = 100;
        }

        try {
            for(Auction auction : auctionController.findForAuctionHouse(houseId, page, pageSize)) {
                list.add(auctionMapper.auctionToDTO(auction));
            }
        } catch(DAOException dao) {
            logger.debug("findForAuctionHouse() exit.");
            return Response.status(400).entity(new MessageDTO(dao.getMessage())).build();
        }

        logger.debug("findForAuctionHouse() exit.");

        return Response.status(200).entity(list).build();
    }

    @Override
    public Response findForOwner(int ownerId, Integer page, Integer pageSize) {
        logger.debug("findForOwner() entry.");

        if(page == null) {
            page = 1;
        }

        if(pageSize == null) {
            pageSize = 20;
        }

        if(pageSize > 100) {
            pageSize = 100;
        }

        List<AuctionDTO> list = new ArrayList<>();

        try {
            for(Auction auction : auctionController.findForOwner(ownerId, page, pageSize)) {
                list.add(auctionMapper.auctionToDTO(auction));
            }
        } catch(DAOException dao) {
            logger.debug("findForOwner() exit.");
            return Response.status(400).entity(new MessageDTO(dao.getMessage())).build();
        }

        logger.debug("findForOwner() exit.");

        return Response.status(200).entity(list).build();
    }

    @Override
    public Response createAuction(AuctionDTO entity) {
        try {
            this.auctionController.create(auctionMapper.dtoToEntity(entity));
        } catch (DAOException daoEx) {
                return Response.status(400).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
                return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(201).entity(new MessageDTO("Auction has been created.")).build();
    }

    @Override
    public Response updateAuction(Integer auctionId, AuctionDTO entity) {
        try {
            Auction auction = auctionMapper.dtoToEntity(entity);
            auction.setId(auctionId);
            this.auctionController.update(auction);
        } catch (DAOException daoEx) {
                return Response.status(400).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
                return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(200).entity(new MessageDTO("Auction has been updated.")).build();
    }

    @Override
    public Response deleteAuction(Integer id) {
        try {
            this.auctionController.delete(id);
        } catch (DAOException daoEx) {
                return Response.status(400).entity(new MessageDTO(daoEx.getMessage())).build();
        } catch (Exception ex) {
                return Response.status(500).entity(new MessageDTO(ex.getMessage())).build();
        }
        return Response.status(204).entity(new MessageDTO("Auction has been deleted.")).build();
    }

}
