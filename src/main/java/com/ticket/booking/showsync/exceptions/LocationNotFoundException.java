package com.ticket.booking.showsync.exceptions;

public class LocationNotFoundException extends RuntimeException{
    public LocationNotFoundException(String msg){
        super(msg);
    }
}
