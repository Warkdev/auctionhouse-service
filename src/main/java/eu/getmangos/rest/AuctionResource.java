package eu.getmangos.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import eu.getmangos.dto.AuctionDTO;

public interface AuctionResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves a list of auctions from the database.",
        description = "This API is returning a list of auctions from the database. A missing page and page size will return the first 20 results."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of auctions", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = AuctionDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response findAllAuctions(@QueryParam("page") Integer page, @QueryParam("page_size") Integer pageSize);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves an auction from the database.",
        description = "This API is returning the matching auction from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The matching auction", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = AuctionDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Auction not found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response findAuction(@PathParam("id") int id);

    @GET
    @Path("/auction_house/{auction_house_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves a paginable view of the auctions at a given Auction House.",
        description = "This API is returning the matching auctions from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The matching auctions", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = AuctionDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response findForAuctionHouse(@PathParam("auction_house_id") int houseId, @QueryParam("page") Integer page, @QueryParam("page_size") Integer pageSize);

    @GET
    @Path("/owner/{owner_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves a paginable view of the auctions for a given owner.",
        description = "This API is returning the matching auctions from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The matching auctions", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = AuctionDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response findForOwner(@PathParam("owner_id") int ownerId, @QueryParam("page") Integer page, @QueryParam("page_size") Integer pageSize);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates an auction within the database.",
        description = "This API is creating a new auction within the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "201", description = "The auction has been created"),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response createAuction(AuctionDTO entity);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an auction within the database.",
        description = "This API is updating a new auction within the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The auction has been created"),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    public Response updateAuction(@PathParam("id") Integer auctionId, AuctionDTO entity);

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete an auction",
        description = "This API is deleting an existing auction based on the provided id."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "204", description = "The auction has been deleted", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    public Response deleteAuction(@PathParam("id") Integer id);
}
