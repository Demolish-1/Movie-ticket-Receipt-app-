package movieticketapp;

/**
 * MovieTicketData class represents the data associated with a movie ticket sale.
 * ------------------------------------------------------------------------------
 * 
 * It includes information about the movie name, the number of tickets purchased,
 * and the price per ticket.
 * 
 * @author Dylan
 */
public class MovieTicketData {
    private String movieName; // The name of the movie
    private int numberOfTickets; // The number of tickets purchased
    private double ticketPrice; // The price of a single ticket

    /**
     * Constructor to initialize MovieTicketData with provided values.
     *
     * @param movieName the name of the movie
     * @param numberOfTickets the number of tickets purchased
     * @param ticketPrice the price of a single ticket
     */
    public MovieTicketData(String movieName, int numberOfTickets, double ticketPrice) {
        this.movieName = movieName; // Set the movie name
        this.numberOfTickets = numberOfTickets; // Set the number of tickets
        this.ticketPrice = ticketPrice; // Set the ticket price
    }

    /**
     * Gets the name of the movie.
     *
     * @return the name of the movie
     */
    public String getMovieName() {
        return movieName; // Return the movie name
    }

    /**
     * Gets the number of tickets purchased.
     *
     * @return the number of tickets
     */
    public int getNumberOfTickets() {
        return numberOfTickets; // Return the number of tickets
    }

    /**
     * Gets the price of a single ticket.
     *
     * @return the ticket price
     */
    public double getTicketPrice() {
        return ticketPrice; // Return the ticket price
    }
}