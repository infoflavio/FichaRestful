package br.com.infoflavio.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.core.InjectParam;

import br.com.infoflavio.controller.FichaController;
import br.com.infoflavio.exception.RestFulBeanException;
import br.com.infoflavio.vo.FichaVO;

@Path("/ficha")
public class FichaService {
	
	@InjectParam
    private FichaController controller;
	
	@GET
	@Produces("application/json")
	@Consumes("application/json; charset=UTF-8")
	@Path("/buscarFicha")
	public Response buscarFicha(@QueryParam("id") Integer id) {
		FichaVO vo;
		try {
			vo = controller.buscarFicha(id);
		} catch (Exception e) {
			throw new RestFulBeanException(e.getMessage(), e.getMessage(), this.getClass(), e.getCause());
		}
		return Response.status(200).entity((vo)).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
	@Path("/listarFichas")
	@Produces("application/json")
	public Response listarFichas(@QueryParam("id") Integer id, @QueryParam("dataInicio") Date dataInicio, @QueryParam("dataFim") Date dataFim) {
		
		List<FichaVO> lista;
		try {
			lista = controller.listarFichas(id, dataInicio, dataFim);
		} catch (Exception e) {
			throw new RestFulBeanException(e.getMessage(), e.getMessage(), this.getClass(), e.getCause());
		}
		return Response.status(200).entity((lista)).header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Path("/cadastrarFicha")
	@Consumes("application/json; charset=UTF-8")
	@Produces("text/plain")
	public String cadastrarFicha(FichaVO vo) throws Exception {
		try {
			controller.cadastrarFicha(vo);
			return "cadastro realizado.";
		} catch (Exception e) {
			throw new RestFulBeanException(e.getMessage(), e.getMessage(), this.getClass(), e.getCause());
		}
	}
	
	@PUT
	@Path("/atualizarFicha")
	@Consumes("application/json; charset=UTF-8")
	@Produces("text/plain")
	public String atualizarFicha(FichaVO vo) throws Exception {
		try {
			controller.atualizarFicha(vo);
			return "ficha atualizada.";
		} catch (Exception e) {
			throw new RestFulBeanException(e.getMessage(), e.getMessage(), this.getClass(), e.getCause());
		}
	}
	
	@DELETE
	@Path("/deletarFicha/{id}")
	@Produces("text/plain")
	public Response deletarFicha(@PathParam("id") Integer id) throws Exception {
		try {
			controller.deletarFicha(id);
			return Response.status(200).entity("ficha deletada.").build();
		} catch (Exception e) {
			throw new RestFulBeanException(e.getMessage(), e.getMessage(), this.getClass(), e.getCause());
		}
	}
}
