package eu.getmangos.mapper;

import java.util.Date;

import org.mapstruct.Mapper;

import eu.getmangos.dto.AuctionDTO;
import eu.getmangos.entities.Auction;

@Mapper(componentModel = "cdi")
public interface AuctionMapper {

    AuctionDTO auctionToDTO(Auction auction);

    Auction dtoToEntity(AuctionDTO dto);

    default Long mapTime(Date date) {
        return date.getTime();
    }

    default Date mapTime(Long time) {
        return new Date(time);
    }
}
