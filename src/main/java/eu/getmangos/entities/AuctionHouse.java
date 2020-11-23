package eu.getmangos.entities;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "AuctionHouse")
@NamedQueries({
    @NamedQuery(name = "AuctionHouse.findAll", query = "SELECT a FROM AuctionHouse a"),
    @NamedQuery(name = "AuctionHouse.findById", query = "SELECT a FROM AuctionHouse a WHERE a.AuctionHouseId = :id"),
    @NamedQuery(name = "AuctionHouse.findByFactionId", query = "SELECT a FROM AuctionHouse a WHERE a.FactionId = :factionId")
})
public class AuctionHouse {

  /**
   * Unique identifier for every auction house.
   */
  @Id
  @Column(name = "AuctionHouseId", nullable = false)
  private Integer auctionHouseId;
  /**
   * References the faction to which the Auction House belongs. (See faction.dbc)
   */
  @Column(name = "FactionId", nullable = false)
  private Integer factionId;
  /**
   * The Auction House's cut of the final earnings.
   */
  @Column(name = "AuctionFee", nullable = false)
  private Integer auctionFee;
  /**
   * The Deposit fee based on the sale price.
   */
  @Column(name = "DepositTax", nullable = false)
  private Integer depositTax;
  /**
   * The name of the auction house.
   */
  @Column(name = "Name")
  private String name;
  /**
   * The localised Korean version of the auction house.
   */
  @Column(name = "Name1")
  private String nameOne;
  /**
   * The localised French version of the auction house.
   */
  @Column(name = "Name2")
  private String nameTwo;
  /**
   * The localised German version of the auction house.
   */
  @Column(name = "Name3")
  private String nameThree;
  /**
   * The localised Chinese version of the auction house.
   */
  @Column(name = "Name4")
  private String nameFour;
  /**
   * The localised Taiwanese version of the auction house.
   */
  @Column(name = "Name5")
  private String nameFive;
  /**
   * The localised Spanish (Traditional) version of the auction house.
   */
  @Column(name = "Name6")
  private String nameSix;
  /**
   * The localised (Latin America) version of the auction house.
   */
  @Column(name = "Name7")
  private String nameSeven;
  /**
   * A checksum value for the preceeding text fields.
   */
  @Column(name = "TEXTCHECKSUM1")
  private Float checksum;
}