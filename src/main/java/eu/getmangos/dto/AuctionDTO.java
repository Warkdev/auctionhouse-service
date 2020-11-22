package eu.getmangos.dto;

import java.io.Serializable;
import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuctionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier for every auction")
    private Integer id;

    @Schema(description = "The Auction House ID, see AuctionHouse.dbc")
    private Integer houseid;

    @Schema(description = "The GUID of the item that is up for auction, see item_instance.guid")
    private Integer itemguid;

    @Schema(description = "The entry of the item up for auction, see item_template.entry")
    private Integer itemTemplate;

    @Schema(description = "The stack count of the item")
    private Integer itemCount;

    @Schema(description = "A link to the Item's RandomProperty Id, see item_template.Randompropertyid")
    private Integer itemRandompropertyid;

    @Schema(description = "The GUID of the owner of the item up for auction, see character.guid")
    private Integer itemowner;

    @Schema(description = "The buyout price of the item in copper")
    private Integer buyoutprice;

    @Schema(description = "The time when this auction will end.")
    private Date time;

    @Schema(description = "The GUID of the highest bidder, see character.guid")
    private Integer buyguid;

    @Schema(description = "The amount of copper of the last bid made on the item")
    private Integer lastbid;

    @Schema(description = "The amount of copper of the starting bid made on the item")
    private Integer startbid;

    @Schema(description = "The amount of copper spent on the deposit")
    private Integer deposit;
}