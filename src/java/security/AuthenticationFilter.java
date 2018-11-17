/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import View.Login;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hello
 */
@WebFilter(filterName = "AuthenticationFilter2", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {
    
   private FilterConfig config;
   Login userView= new Login();

    

   @Override
  public void doFilter(ServletRequest req, ServletResponse resp,
      FilterChain chain) throws IOException, ServletException {
    if (((HttpServletRequest) req).getSession().getAttribute(userView.AUTH_KEY) == null) {
      ((HttpServletResponse) resp).sendRedirect("../");
    } else {
      chain.doFilter(req, resp);
    }
  }

   @Override
  public void init(FilterConfig config) throws ServletException {
    this.config = config;
  }

   @Override
  public void destroy() {
    config = null;
  }
    
}
