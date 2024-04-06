package com.ticket.booking.showsync.exceptions;

public class ExistingUserException extends RuntimeException{
    public ExistingUserException(String msg){super(msg);}
}
