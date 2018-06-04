package br.com.infoflavio.controller;

import java.util.List;

import br.com.infoflavio.dao.AnimalDAO;
import br.com.infoflavio.vo.AnimalVO;


public class AnimalController {
	
    private AnimalDAO dao = AnimalDAO.getInstance();
	
	public List<AnimalVO> listarAnimais() throws Exception{
		return dao.listarAnimais();		
	}
	
	public Boolean atualizarAnimal(AnimalVO vo) throws Exception {
		return dao.atualizarAnimal(vo);	
	}
	
	public Boolean deletarAnimal(Integer id) throws Exception {
		return dao.deletarAnimal(id);	
	}
	
	public List<AnimalVO> listarAnimaisSemFicha() throws Exception {
		return dao.listarAnimaisSemFicha();	
	}
}
