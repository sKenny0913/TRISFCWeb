package com.tri.exception;

public class WOnotFound extends Exception
{
      // Parameterless Constructor
      public WOnotFound() {}

      // Constructor that accepts a message
      public WOnotFound(String message)
      {
         super(message);
      }
 }
