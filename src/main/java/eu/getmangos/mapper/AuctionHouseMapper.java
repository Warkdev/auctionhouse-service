package eu.getmangos.mapper;

import org.mapstruct.Mapper;

import eu.getmangos.dto.AuctionHouseDTO;
import eu.getmangos.entities.AuctionHouse;

@Mapper(componentModel = "cdi")
public interface AuctionHouseMapper {

    AuctionHouseDTO map(AuctionHouse house);

    AuctionHouse map(AuctionHouseDTO dto);
}
