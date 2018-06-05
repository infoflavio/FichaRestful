package br.com.infoflavio.controller;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;

import br.com.infoflavio.dao.FichaDAO;
import br.com.infoflavio.vo.FichaVO;

@Stateful
public class FichaController {
	
    private FichaDAO dao = FichaDAO.getInstance();
	
	public List<FichaVO> listarFichas(Integer id, Date dataInicio, Date dataFim) throws Exception{
		return dao.listarFichas(id, dataInicio, dataFim);		
	}
	
	public FichaVO buscarFicha(Integer id) throws Exception{
		return dao.buscarFicha(id);		
	}
	
	public Boolean cadastrarFicha(FichaVO vo) throws Exception{
		return dao.cadastrarFicha(vo);	
	}
	
	public Boolean atualizarFicha(FichaVO vo) throws Exception {
		return dao.atualizarFicha(vo);	
	}
	
	public Boolean deletarFicha(Integer id) throws Exception {
		return dao.deletarFicha(id);	
	}
	

}
