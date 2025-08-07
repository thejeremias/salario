package io.github.thejeremias.salario.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Utilizado para proteger as rotas da aplicação de acesso não autenticado. 
 */

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getRequestURL().toString();
        boolean recursoEstatico = url.contains("javax.faces.resource");
        boolean paginaLogin = url.contains("/login");
        Object usuario = httpRequest.getSession().getAttribute("usuario");
        if (usuario == null && !paginaLogin && !recursoEstatico) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsf");
            return;
        }
        chain.doFilter(request, response);
    }

	@Override
	public void destroy() {}

}
