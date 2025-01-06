/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieticketapp;

/**
 * MovieTickets class implements the IMovieTickets interface.
 * ----------------------------------------------------------
 * 
 * This class is responsible for calculating ticket prices and validating ticket data.
 * It includes methods for calculating the total ticket price including VAT
 * and validating the input data for movie ticket sales.
 * 
 * @author Dylan
 */
public class MovieTickets implements IMovieTickets {
    private static final double VAT_RATE = 0.14; // Constant representing the VAT rate of 14%

    /**
     * Calculates the total ticket price including VAT.
     *
     * @param numberOfTickets the number of tickets purchased
     * @param ticketPrice the price of a single ticket
     * @return the total price including VAT
     */
    
    
    @Override
    public double CalculateTotalTicketPrice(int numberOfTickets, double ticketPrice) {
        // Calculate the total price before VAT
        double totalPrice = ticketPrice * numberOfTickets;
        // Return the total price including VAT
        return totalPrice + (totalPrice * VAT_RATE);
    }

    /**
     * Validates the input data for movie ticket sales.
     *
     * @param movieTicketData the data object containing ticket information
     * @return true if the data is valid, false otherwise
     */
    @Override
    
    
    public boolean ValidateData(MovieTicketData movieTicketData) {
        // Validate movie name: it should not be null or empty
        if (movieTicketData.getMovieName() == null || movieTicketData.getMovieName().trim().isEmpty()) {
            return false; // Invalid movie name
        }

        // Validate ticket price: it should be greater than zero
        if (movieTicketData.getTicketPrice() <= 0) {
            return false; // Invalid ticket price
        }
        // Validate ticket count: it should be greater than zero
        // If all validations pass, return true

        return movieTicketData.getNumberOfTickets() > 0;
    }
}