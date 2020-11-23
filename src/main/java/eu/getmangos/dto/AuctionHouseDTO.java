package eu.getmangos.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class AuctionHouseDTO {

  @Schema(description = "Unique identifier for every auction house")
  private Integer auctionHouseId;

  @Schema(description = "References the faction to which the Auction House belongs. (See faction.dbc)")
  private Integer factionId;

  @Schema(description = "The Auction House's cut of the final earnings.")
  private Integer auctionFee;

  @Schema(description = "The Deposit fee based on the sale price.")
  private Integer depositTax;

  @Schema(description = "The name of the auction house.")
  private String name;

  @Schema(description = "The localised Korean version of the auction house.")
  private String nameOne;

  @Schema(description = "The localised French version of the auction house.")
  private String nameTwo;

  @Schema(description = "The localised German version of the auction house.")
  private String nameThree;

  @Schema(description = "The localised Chinese version of the auction house.")
  private String nameFour;

  @Schema(description = "The localised Taiwanese version of the auction house.")
  private String nameFive;

  @Schema(description = "The localised Spanish (Traditional) version of the auction house.")
  private String nameSix;

  @Schema(description = "The localised Spanish (Latin America) version of the auction house.")
  private String nameSeven;

  @Schema(description = "A checksum value for the preceeding text fields.")
  private Float checksum;
}