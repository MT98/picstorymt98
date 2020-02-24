package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Utilisateur;
import entities.Album;
import environnement.Constantes;
import service.AlbumService;
import service.ServiceException;
/**
 * Servlet Filter implementation class NotAdminFilter
 */
@WebFilter(urlPatterns = {"user/profil", "albums/add", "albums/delete"}, description = "Filtre de contrôle d'acces aux fonctionnalités nécessitant d'avoir les droits.")
public class RightFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RightFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
	
		String uri = httpRequest.getRequestURI();
		if(uri.endsWith("user/profil"))
		{
			if(session != null &&  session.getAttribute("utilisateur") != null && ((Utilisateur)session.getAttribute("utilisateur")).getIsAdmin() == false &&  ((Utilisateur)session.getAttribute("utilisateur")).getId() != Long.parseLong(request.getParameter("id")) )
			{
				((HttpServletResponse)response).sendRedirect(httpRequest.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage=Vous ne disposez pas assez de droit pour acceder a cette page!");
			}else
			{
				// pass the request along the filter chain
				chain.doFilter(request, response);
			}
		}else if(uri.endsWith("albums/add") || uri.endsWith("albums/delete"))
		{
			AlbumService as = new AlbumService();
			if(session != null &&  session.getAttribute("utilisateur") != null)				
			{
				Utilisateur user = (Utilisateur)(session.getAttribute("utilisateur"));
				Album album;
				try {
					album = ((Album)as.getAlbumById(Long.valueOf(request.getParameter("id"))));
					if(album != null && user != null && album.getProprietaire().getId() != user.getId())
					{
						((HttpServletResponse)response).sendRedirect(httpRequest.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage=Vous ne disposez pas assez de droit pour acceder a cette page!");
					}else
					{
						// pass the request along the filter chain
						chain.doFilter(request, response);
					}
				} catch (NumberFormatException | ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					((HttpServletResponse)response).sendRedirect(httpRequest.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage=Program Error!");
				}
					
			}else
			{
				// pass the request along the filter chain
				chain.doFilter(request, response);
			}
		}else
		{
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
