package com.tri.exception;

public class WOnotMatch extends Exception
{
      // Parameterless Constructor
      public WOnotMatch() {}

      // Constructor that accepts a message
      public WOnotMatch(String message)
      {
         super(message);
      }
 }
