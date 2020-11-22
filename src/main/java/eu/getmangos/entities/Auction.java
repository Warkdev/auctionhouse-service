package eu.getmangos.entities;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "auction")
@NamedQueries({
    @NamedQuery(name = "Auction.findAll", query = "SELECT a FROM Auction a"),
    @NamedQuery(name = "Auction.findById", query = "SELECT a FROM Auction a WHERE a.id = :id"),
    @NamedQuery(name = "Auction.deleteAuction", query = "DELETE FROM Auction a WHERE a.id = :id"),
    @NamedQuery(name = "Auction.findByHouseid", query = "SELECT a FROM Auction a WHERE a.houseid = :houseid"),
    @NamedQuery(name = "Auction.findByItemowner", query = "SELECT a FROM Auction a WHERE a.itemowner = :itemowner")
})
public class Auction {

  /**
   * Unique identifier for every auction.
   */
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;
  /**
   * The AuctionHouseId (See AuctionHouse.dbc)
   */
  @Column(name = "houseid", nullable = false)
  private Integer houseid;
  /**
   * The GUID of the item that is up for auction. (See item_instance.guid)
   */
  @Column(name = "itemguid", nullable = false)
  private Integer itemguid;
  /**
   * The entry of the item up for auction. See (item_template.entry)
   */
  @Column(name = "item_template", nullable = false)
  private Integer itemTemplate;
  /**
   * The stack count of the item
   */
  @Column(name = "item_count", nullable = false)
  private Integer itemCount;
  /**
   * A link to the Item's RandomProperty Id (See item_template.Randompropertyid).
   */
  @Column(name = "item_randompropertyid", nullable = false)
  private Integer itemRandompropertyid;
  /**
   * The GUID of the owner of the item up for auction. (See character.guid)
   */
  @Column(name = "itemowner", nullable = false)
  private Integer itemowner;
  /**
   * The buyout price of the item in copper.
   */
  @Column(name = "buyoutprice", nullable = false)
  private Integer buyoutprice;
  /**
   * The time when this auction will end, measured in Unix time.
   */
  @Column(name = "time", nullable = false)
  private Long time;
  /**
   * The GUID of the highest bidder. See (character.guid)
   */
  @Column(name = "buyguid", nullable = false)
  private Integer buyguid;
  /**
   * The amount of copper of the last bid made on the item.
   */
  @Column(name = "lastbid", nullable = false)
  private Integer lastbid;
  /**
   * The amount of copper of the starting bid made on the item.
   */
  @Column(name = "startbid", nullable = false)
  private Integer startbid;
  /**
   * The amount of copper spent on the deposit.
   */
  @Column(name = "deposit", nullable = false)
  private Integer deposit;
}