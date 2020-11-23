package eu.getmangos.rest;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import eu.getmangos.dto.AuctionHouseDTO;
import eu.getmangos.dto.Locale;

public interface AuctionHouseResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves a list of auction houses from the database.",
        description = "This API is returning a list of auction houses from the database. A missing page and page size will return the first 20 results."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of auction houses", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = AuctionHouseDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response findAllAuctionHouses(@QueryParam("page") Integer page, @QueryParam("page_size") Integer pageSize);

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves an auction house from the database.",
        description = "This API is returning the matching auction house from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The matching auction house", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = AuctionHouseDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Auction House not found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response findAuctionHouse(@PathParam("id") int houseId);

    @GET
    @Path("faction/{faction_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves a paginable view of the auction houses for a given faction.",
        description = "This API is returning the matching auction houses from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The matching auction houses", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = AuctionHouseDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response findForFaction(@PathParam("faction_id") int factionId, @QueryParam("page") Integer page, @QueryParam("page_size") Integer pageSize);

    @PUT
    @Path("translate/{id}")
    @Operation(summary = "Updates the translation for a given Auction House")
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The matching auction house translation has been updated", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response translateAuctionHouse(@PathParam("id") Integer houseId, Locale locale, String translation);
}
