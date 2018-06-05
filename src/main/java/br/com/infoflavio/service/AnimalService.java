package br.com.infoflavio.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.infoflavio.controller.AnimalController;
import br.com.infoflavio.exception.RestFulBeanException;
import br.com.infoflavio.vo.AnimalVO;

@Path("/animal")
public class AnimalService {
	
    private AnimalController controller = new AnimalController();
	
		
	@GET
	@Path("/listarAnimais")
	@Produces("application/json")
	public List<AnimalVO> listarAnimais() {
		
		List<AnimalVO> lista;
		try {
			lista = controller.listarAnimais();
		} catch (Exception e) {
			throw new RestFulBeanException(e.getMessage(), e.getMessage(), this.getClass(), e.getCause());
		}
		return lista;
	}
	
	@GET
	@Path("/listarAnimaisSemFicha")
	@Produces("application/json")
	public List<AnimalVO> listarAnimaisSemFicha() {
		
		List<AnimalVO> lista;
		try {
			lista = controller.listarAnimaisSemFicha();
		} catch (Exception e) {
			throw new RestFulBeanException(e.getMessage(), e.getMessage(), this.getClass(), e.getCause());
		}
		return lista;
	}

	@PUT
	@Path("/atualizarAnimal")
	@Consumes("application/json; charset=UTF-8")
	@Produces("text/plain")
	public Response atualizarAnimal(AnimalVO vo) throws Exception {
		try {
			controller.atualizarAnimal(vo);
			return Response.status(200).entity("cadastro atualizado.").build();
		} catch (Exception e) {
			throw new RestFulBeanException(e.getMessage(), e.getMessage(), this.getClass(), e.getCause());
		}
	}
	
	@DELETE
	@Path("/deletarAnimal/{id}")
	@Produces("text/plain")
	public Response deletarAnimal(@PathParam("id") Integer id) throws Exception {
		try {
			controller.deletarAnimal(id);
			return Response.status(200).entity("animal deletado.").build();
		} catch (Exception e) {
			throw new RestFulBeanException(e.getMessage(), e.getMessage(), this.getClass(), e.getCause());
		}
	}
}
