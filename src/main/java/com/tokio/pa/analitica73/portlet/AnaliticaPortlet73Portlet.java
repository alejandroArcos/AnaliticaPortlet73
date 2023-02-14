package com.tokio.pa.analitica73.portlet;

import com.tokio.cotizador.CotizadorService;
import com.tokio.cotizador.Bean.Persona;
import com.tokio.cotizador.Bean.SolicitudResponse;
import com.tokio.cotizador.Bean.SolicitudResponseProducto;
import com.tokio.pa.analitica73.constants.AnaliticaPortlet73PortletKeys;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author urielfloresvaldovinos
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AnaliticaPortlet73",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AnaliticaPortlet73PortletKeys.ANALITICAPORTLET73,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AnaliticaPortlet73Portlet extends MVCPortlet {
	
private static Log _log = LogFactoryUtil.getLog(AnaliticaPortlet73Portlet.class);
	
	@Reference
	CotizadorService _CotizadorService;
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String usuario = themeDisplay.getUser().getFullName();
		String pantalla = "LOGIN";
		//Se envia un cero significa que es filtrar por todos
		String agente = "0";
		Calendar cal= Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		
		Map<Integer, String> meses = new HashMap<>();
		meses.put(1, "Enero");
		meses.put(2, "Febrero");
		meses.put(3, "Marzo");
		meses.put(4, "Abril");
		meses.put(5, "Mayo");
		meses.put(6, "Junio");
		meses.put(7, "Julio");
		meses.put(8, "Agosto");
		meses.put(9, "Septiembre");
		meses.put(10, "Octubre");
		meses.put(11, "Noviembre");
		meses.put(12, "Diciembre");
		
		SolicitudResponse solResp = null;
		SolicitudResponseProducto solResp2 = null;
		
		try {
	/*		
			solResp = _CotizadorService.getCotSol(year, month+1, agente, usuario, pantalla);
			
			solResp2 = _CotizadorService.getCotSolProd(year, month+1, agente, usuario, pantalla);
			
			Gson gson = new Gson();
			String stringJsonDatos = gson.toJson(solResp.getListaDatosKPI());
			renderRequest.setAttribute("datosJson", stringJsonDatos);
			//_log.info("grafica:"+ stringJsonDatos);

			String stringJsonDatos2 = gson.toJson(solResp2.getListaDatosKPIProd() );
			HttpServletRequest originalRequest = PortalUtil
					.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));

			List<Persona> persona = (List<Persona>) originalRequest.getSession().getAttribute("listaAgentes");
			
			

			renderRequest.setAttribute("objeto2", stringJsonDatos2);
			renderRequest.setAttribute("listaAgente", persona);
			
			renderRequest.setAttribute("empresarialResp", solResp2.getListaDatosKPIProd().get(0));
			renderRequest.setAttribute("familiarResp", solResp2.getListaDatosKPIProd().get(1));
			_log.info("grafica:"+ solResp2.getListaDatosKPIProd().get(0));
			_log.info("grafica2:"+ solResp2.getListaDatosKPIProd().get(1));
*/			

			HttpServletRequest originalRequest = PortalUtil
					.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
			List<Persona> persona = (List<Persona>) originalRequest.getSession().getAttribute("listaAgentes");
			renderRequest.setAttribute("listaAgente", persona);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		renderRequest.setAttribute("ano", year);
		renderRequest.setAttribute("mes", month+1);
		renderRequest.setAttribute("meses", meses);
		
		super.doView(renderRequest, renderResponse);	
	}
}