/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 *
 * @author josera
 */
public class DAOException extends RuntimeException {
    private Throwable cause = null;

    public DAOException() {}

    public DAOException(String msg)
    {
      super(msg);
    }

    public DAOException(Throwable cause)
    {
      this.cause = cause;
    }

    public DAOException(String msg, Throwable cause)
    {
      super(msg);
      this.cause = cause;
    }

    public Throwable getCause()
    {
      return this.cause;
    }

    public String getMessage()
    {
      if (super.getMessage() != null) {
        return super.getMessage();
      }
      if (this.cause != null) {
        return this.cause.toString();
      }

      return null;
    }

    public void printStackTrace()
    {
      printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream out)
    {
      synchronized (out) {
        PrintWriter pw = new PrintWriter(out, false);
        printStackTrace(pw);

        pw.flush();
      }
    }

    public void printStackTrace(PrintWriter s)
    {
      super.printStackTrace(s);
      if (this.cause != null)
      {
        s.println("Caused by:");
        this.cause.printStackTrace(s);
      }
    }
}
